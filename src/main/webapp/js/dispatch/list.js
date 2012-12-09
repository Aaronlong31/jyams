require(["jquery", 
         "../bootstrap", 
         "../common", 
         "../jquery/jquery-ui-1.9.1.custom",
         "../i18n/datepicker.i18n",
         "../i18n/grid.i18n",
         "../jquery/jqgrid/jquery.jqGrid"], function($) {
	$(function(){
		var dataPickerF = function(element){
			$(element).datepicker({
		        onSelect : function (dateText, inst){
					$(this).focus();
		        }
			});
		};
		var ctx = getContextPath();
		var buildSelectF = function(xmlHttpRequest){
			var selectHtml = "<select>";
			selectHtml += "<option value=''>全部</option>";
			var data = $.parseJSON(xmlHttpRequest.responseText);
			$.each(data.persons, function(i, person){
				selectHtml += "<option value='"+person.personName+"'>" + person.personName + "</option>";
			});
			selectHtml += "</select>";
			return selectHtml;
		};
		$("#dispatchList").jqGrid({
			url : ctx + "/dispatch",
			datatype : "json",
			colNames : ["操作", "", "日期","员工名称", "项目编号", "项目名称","派工类型", "施工开始时间", "施工结束时间" , "有效工时"],
			caption : "派工列表",
			colModel : [
				{name : 'act',index:'act', width:75,sortable:false, align: "center", search : false},
				{name : 'dispatchId', key : true, index:'dispatchId', width:75,sortable:false, align: "center", search : false, hidden:true},
				{name : "dispatch.dispatchDay", index : "dispatchDay", width : 150, align : "center"},
				{name : "personName", index : "personName", width : 150, align : "center"},
				{name : "dispatch.projectId", index : "projectId", width : 150, align : "center"},
				{name : "dispatch.projectName", index : "projectName", width : 350, align : "center"},
				{name : "dispatch.dispatchTypeString", index : "dispatchType", width : 150, align : "center"},
				{name : "startTimeString", index : "startTimeString", width : 150, align : "center"},
				{name : "endTimeString", index : "endTimeString", width : 150, align : "center"},
				{name : "hours", index : "hours", width : 150, align : "center"}
			],
			rowNum : 20,
			autowidth: true,
			rownumbers: true,
			gridview: true,
			height: 400,
			rowList : [10, 20, 30],
			pager : "#dispatchPager",
			viewrecords : true,
			sortorder: "desc",
			jsonReader: {
				repeatitems : false
			},
			gridComplete : function(){
				var ids = $("#dispatchList").jqGrid('getDataIDs');
				$.each(ids, function(i, id){
					var approval = "<a href=\"" + ctx + "/dispatch/"+id+"/edit\">编辑</a>";
					$("#dispatchList").jqGrid('setRowData', id, {act:approval});
				});
			}
		}).jqGrid('navGrid','#dispatchPager',{edit:false,add:false,del:false,search:false})
          .jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
	});
});
