require(["jquery", 
    "../common",
    "../jquery/jquery-ui-1.9.1.custom",
    "../jquery/validator/jquery.validate",
    "../i18n/datepicker.i18n",
    "../i18n/validator.i18n",
    "../i18n/grid.i18n",
    "../jquery/jqgrid/jquery.jqGrid",
    "../selectProject",
    "../bootstrap"
], function($) {
	$(function() {
        var $message = $("#message");
		if($message.text() != ""){
            $message.show();
		}
		var ctx = getContextPath();
		var dispatchId = "${dispatch.dispatchId}";
//		$("#title").text(dispatchId == "" ? "新建派工" : "编辑派工");
		var times = ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", 
		     		 "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", 
		     		 "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
		     		 "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
		     		 "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
		     		 "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "24:00"];
		var timeSelect = $("<select>");
	  	$.each(times, function(i, time){
			$("<option value='" + time + "'>" + time + "</option>").appendTo(timeSelect);
		});
	  	$(".dispatched li").each(function(){
	  		var $this = $(this);
	  		timeSelect.clone().addClass("startTime span4").val($this.attr("startTime")).appendTo($this);
		  	timeSelect.clone().addClass("endTime span4").val($this.attr("endTime")).appendTo($this);
	  	});
		
		$(".startTime").bind("change", function(){
			$.post(ctx + "/dispatch/session", {
		  		"_method" : "PUT", 
		  		"_service" : "changeStartTime",
		  		"personId" : $(this).parent().attr("personId"), 
		  		"startTime" : this.value
		  	});
		});
		$(".endTime").bind("change", function(){
			$.post(ctx + "/dispatch/session", {
		  		"_method" : "PUT", 
		  		"_service" : "changeEndTime",
		  		"personId" : $(this).parent().attr("personId"), 
		  		"endTime" : this.value
		  	});
		});
		$("ul.unDispatched").sortable({
            connectWith: ".dispatched",
            cursor: "move",
            receive: function( event, ui ) {
            	ui.item.find("select").remove();
            	$.post(ctx + "/dispatch/session", {
			  		"_method" : "PUT", 
			  		"_service" : "deleteDispatchWork",
			  		"personId" : ui.item.attr("personId")
			  	});
            },
            remove : function(event, ui){
            	var personId = ui.item.attr("personId");
            	$("#undispatched li[personId="+personId+"]").remove();
            }
           
        });
		$("ul.dispatched").sortable({
            connectWith: ".unDispatched",
            cursor: "move",
            receive: function( event, ui ) {
			  	var startSelect = timeSelect.clone().addClass("startTime span4");
			  	var endSelect = timeSelect.clone().addClass("endTime span4");
			  	startSelect.val("08:00");
			  	endSelect.val("17:00");
			  	ui.item.append(startSelect);
			  	ui.item.append(endSelect);
			  	$.post(ctx + "/dispatch/session", {
			  		"_method" : "PUT", 
			  		"_service" : "addDispatchWork",
			  		"personId" : ui.item.attr("personId"), 
			  		"startTime" : startSelect.val(), 
			  		"endTime" : endSelect.val()
			  	});
			},
			remove : function(event, ui){
				var personId = ui.item.attr("personId");
            	$("#dispatched li[personId=" + personId + "]").remove();
			}
        });
		$("button.department").click(function(){
			if(this.value == 0){
				$(".row-fluid li").show();
			} else {
				$("li[departmentId!="+this.value+"]").hide();
				$("li[departmentId="+this.value+"]").show();
			}
		});
		$(".dispatchType").click(function(){
			$(".dispatchType").removeClass("btn-primary");
			$(this).addClass("btn-primary");
			$.post(ctx + "/dispatch/session", {"_method" : "PUT", "dispatchType" : $(this).val()});
		}).each(function(){
			if(this.value == $("#dispatchType").val()){
				$(this).addClass("btn-primary active");
			}
		});
		
		var saveUrl = (dispatchId == "") ? ctx + "/dispatch" : ctx + "/dispatch/" + dispatchId;
		var saveMethod = (dispatchId == "") ? "POST" : "PUT";
		$("#addDispatchBtn").click(function(){
			$.post(saveUrl, {"_method" : saveMethod}).success(function(){
				alert("保存成功！");
				$(this).button('reset');
			}).error(function(e, jqxhr, settings, exception){
				var errorInfo = JSON.parse(e.responseText).errorInfo;
				alert("保存失败！" + errorInfo.message);
			});
		});
		$("#dispatchDay").change(function(){
			$.post(ctx + "/dispatch/session", {"_method" : "PUT", "dispatchDayString" : this.value});
		}).datepicker();  
		$("#cancel").click(function(){
			$('#selectProjectDiv').modal("hide");
		});
		$("#projectId, #projectName, #selectProjectBtn").click(function(){
			$.selectProject(function(projectId, projectName){
				$("#projectId").text(projectId);
				$("#projectName").text(projectName);
				saveDispatchProject(projectId, projectName)
			});
		});
		function saveDispatchProject(projectId, projectName){
			$.post(ctx + "/dispatch/session", {"projectId" : projectId, "projectName" : projectName, "_method" : "PUT"});
		}
	});
});
