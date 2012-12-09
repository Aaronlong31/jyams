(function($){
	var successFunction = null;
	var projectListGrid;
	$(function(){
		var contextPath = "/jyams";
		$("<div>").load(contextPath + "/html/selectProject.html", function(){
			$('#_selectProjectDiv').modal({
				backdrop:true,
				keyboard:true,
				show : false
			});
			var searchYear = 0;
			$.get(contextPath + "/project/searchYear", function(data){
				searchYear = data;
			})
			projectListGrid = $("#_projectList").jqGrid({
				url : contextPath + "/project/basic",
				datatype : "json",
				colNames : ["客户", "项目编号", "项目名称"],
				colModel : [
					{name : "clientName", index : "clientName", width : 100, search: true,searchoptions :{sopt:["eq", "cn"]}},
					{name : "projectId", key : true, index : "projectId", width : 90, search: true,searchoptions :{sopt:["eq"]}, searchrules : {integer: true}},
					{name : "projectName", index : "projectName", width : 322, search: true,searchoptions :{sopt:["eq", "cn"]}},
				],
				rowNum : 20,
				width: "auto",
				height: 300,
				rowList : [10, 20, 30],
				pager : "#_projectPager",
				viewRecords : true,
				sortorder: "desc",
				jsonReader: {
					repeatitems : false
				},
				ondblClickRow : function(rowid, iRow, iCol, e){
					var $tr = $($("#_projectList").find("tr").get(iRow));
					var projectId = $($tr.find("td").get(1)).text();
					var projectName = $($tr.find("td").get(2)).text();
					successFunction(projectId, projectName);
					$('#_selectProjectDiv').modal("hide");
				},
				onSelectRow : function(rowid, status){
					$("#_selectedRow").val(rowid);
				}
			}).jqGrid('navGrid','#_projectPager',{edit:false,add:false,del:false,search:false})
			.jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false})
			.navButtonAdd('#_projectPager', {
				caption : searchYear - 1,
				id : "_preYear",
				buttonicon : "ui-icon-triangle-1-w",
				onClickButton : function (){
					updateSearchYear(-1);
				}
			})
			.navButtonAdd('#_projectPager', {
				caption : searchYear,
				id : "_curYear"
			})
			.navButtonAdd('#_projectPager', {
				caption : searchYear + 1,
				id : "_nextYear",
				buttonicon : "ui-icon-triangle-1-e",
				onClickButton : function (){
					updateSearchYear(1);
				}
			});
			$("#_selectProjectBtn").click(function(){
				var $tr = $("#" + $("#_selectedRow").val());
				var projectId = $($tr.find("td").get(1)).text();
				var projectName = $($tr.find("td").get(2)).text();
				successFunction(projectId, projectName);
				$('#_selectProjectDiv').modal("hide");
			});
			$("#_cancel").click(function(){
				$('#_selectProjectDiv').modal("hide");
			});
		}).appendTo($("body"));
	});
	function updateSearchYear(incre){
		$.post(contextPath + "/project/updateSearchYear", {"increYear" : incre, "_method" : "PUT"}, function (data){
			$("#_preYear, #_curYear, #_nextYear").each(function(){
				$(this).find(".ui-pg-div").html(Number($(this).text()) + incre);
			});
			projectListGrid.trigger("reloadGrid");
		});
	}
	
	$.selectProject = function(success){
		successFunction = success;
		$('#_selectProjectDiv').modal("show");
	}
})(jQuery);