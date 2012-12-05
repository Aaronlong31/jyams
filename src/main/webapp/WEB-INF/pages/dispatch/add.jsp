<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${ctitle} - 新增派工</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/validator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/i18n/grid.i18n.js"></script>
<script type="text/javascript" src="${ctx}/js/i18n/datepicker.i18n.js"></script>
<script type="text/javascript" src="${ctx}/js/i18n/validator.i18n.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/js/selectProject.js"></script>
<style type="text/css">
#projectPager_right{width:0}
.uneditable-input{cursor: pointer;}
.box{
	height: 500px;
	text-align: center;
}
ul{
	list-style-type: none;
	border: 1px solid #ddd;
	-webkit-border-radius: 4px 0 4px 0;
	-moz-border-radius: 4px 0 4px 0;
	border-radius: 4px 0 4px 0;
	margin:0px;
	overflow-y : auto;
	height: 456px;
}
li{
	height : 30px;
	cursor:move;
	text-align :center;
	color : #4F4F4F;
	background-color: #F5F5F5;
	border: 1px solid #ddd;
	margin: 2px;
	line-height: 30px;
	padding-left: 5px;
}
ul.unDispatched, ul.dispatched{padding: 20px;background-color: #ddd}
div.departments{
	text-align: center;
}
ul.dispatched li span{width:120px;margin-right: 10px;}
</style>
</head>
<body>
<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<h4>新增派工</h4>
			</div>
		</div>
	</div>
	<div id="message" class="alert alert-error"></div>
	<form action="" class="form-horizontal ">
		<table class="table table-condensed">
	    	<tr>
				<td colspan="2">
					<div class="control-group">
						<label class="control-label" for="projectName">项目<span class="red_star">*</span></label>
						<div class="controls" style="width:100%">
							<span id="projectId" class="input-small uneditable-input">${dispatch.projectId}</span>
							<span id="projectName" class="span5 uneditable-input">${dispatch.projectName}</span>
							<a class="btn btn-primary" id="selectProjectBtn">选择项目</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="companyPrincipalId">派工类型<span class="red_star">*</span></label>
						<div class="controls">
							<div class="btn-group" data-toggle="buttons-radio">
								<input type="hidden" id="dispatchType" value="${dispatch.dispatchType}"/>
								<button type="button" name="dispatchType" value="1" class="btn dispatchType">正常派工</button>
								<button type="button" name="dispatchType" value="2" class="btn dispatchType">加班派工</button>
							</div>
						</div>
					</div>
				</td>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="constructPlace">派工日期</label>
						<div class="controls">
							<input name="dispatchDayString" id="dispatchDay" value="${dispatch.dispatchDayString}" type="text" class="input-big"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<hr/>
		<div class="control-group departments">
	    	<div class="btn-group" data-toggle="buttons-radio">
	    		<button type="button" class="btn department active" value="0">全部</button>
	    		<c:forEach items="${dp1}" var="d">
	    			<button type="button" class="btn department" value="${d.departmentId}">${d.departmentName}</button>
	    		</c:forEach>
	    	</div>
    	</div>
    	<div class="alert alert-success">
    		<span class="label label-info">提示：</span>
    		可以将左侧的员工拖动到右侧方框以添加派工，将右侧的员工拖动到左侧方框以取消派工。
    	</div>
		<div class="container-fluid">
		  <div class="row-fluid">
		    <div id="undispatched" class="span4 box ">
		    	<h4>选择员工</h4>
		    	<ul class="unDispatched" onselectstart="return false">
			    	<c:forEach items="${dp1}" var="d">
			    		<c:forEach items="${d.persons}" var="p">
			    			<li personId="${p.personId }" departmentId="${d.departmentId}"><span>${d.departmentName} - ${p.personName}</span></li>
			    		</c:forEach>
		    		</c:forEach>
			    </ul>
		    </div>
		    <div id="dispatched" class="span8 box ">
		    	<h4>已选择员工</h4>
		    	<ul class="dispatched" onselectstart="return false">
			    	<c:forEach items="${dispatchWorks}" var="d" >
		    			<li personId="${d.key.personId }" 
		    				startTime="${d.key.startTimeString}" endTime="${d.key.endTimeString}" 
		    				departmentId="${d.value.departmentId}">
		    				<span>${d.value.departmentName} - ${d.key.personName}</span>
		    			</li>
			    	</c:forEach>
			    </ul>
		    </div>
		  </div>
		</div>
	</form>
	<div class="form-actions center">
		<button type="submit" class="btn btn-primary" id="addDispatchBtn" data-loading-text="保存中......">保存</button>
		<button type="button" class="btn" id="cancel">取消</button>
    </div>
</div>
</body>
<script language="javascript">
	$(function() {
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
		if($("#message").text() == ""){
			$("#message").hide();
		}
		
		$(".startTime").bind("change", function(){
			$.post("${ctx}/dispatch/session", {
		  		"_method" : "PUT", 
		  		"_service" : "changeStartTime",
		  		"personId" : $(this).parent().attr("personId"), 
		  		"startTime" : this.value
		  	});
		});
		$(".endTime").bind("change", function(){
			$.post("${ctx}/dispatch/session", {
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
            	$.post("${ctx}/dispatch/session", {
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
			  	$.post("${ctx}/dispatch/session", {
			  		"_method" : "PUT", 
			  		"_service" : "addDispatchWork",
			  		"personId" : ui.item.attr("personId"), 
			  		"startTime" : startSelect.val(), 
			  		"endTime" : endSelect.val()
			  	});
			},
			remove : function(event, ui){
				var personId = ui.item.attr("personId");
            	$("#dispatched li[personId="+personId+"]").remove();
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
			$.post("${ctx}/dispatch/session", {"_method" : "PUT", "dispatchType" : $(this).val()});
		}).each(function(){
			if(this.value == $("#dispatchType").val()){
				$(this).addClass("btn-primary active");
			}
		});
		$("#addDispatchBtn").click(function(){
			$.post("${ctx}/dispatch").success(function(){
				alert("保存成功！");
				$(this).button('reset');
			}).error(function(e, jqxhr, settings, exception){
				var errorInfo = JSON.parse(e.responseText).errorInfo;
				alert("保存失败！" + errorInfo.message);
			});
		});
		$("#dispatchDay").change(function(){
			$.post("${ctx}/dispatch/session", {"_method" : "PUT", "dispatchDayString" : this.value});
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
			$.post("${ctx}/dispatch/session", {"projectId" : projectId, "projectName" : projectName, "_method" : "PUT"});
		}
	});
</script>
</html>