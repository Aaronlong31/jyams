require(["jquery", 
         "../bootstrap", 
         "../common", 
         "../jquery/jquery-ui-1.9.1.custom",
         "../i18n/grid.i18n",
         "../jquery/jqgrid/jquery.jqGrid"], function($) {
	$(function(){
		
		$("#year, #month").change(function(){
			dispatchJqGrid.jqGrid("setGridParam", {url : getRequestUrl()}).trigger("reloadGrid");
		});
		
		var ctx = getContextPath();
		function getRequestUrl(){
			var ym = $("#year").val() + "-" + ($("#month").val() > 9 ? $("#month").val() : "0" + $("#month").val());
			return ctx + "/dispatch/monthly?personId=" + $("#personId").val() + "&month=" + ym;
		}
		var dispatchJqGrid = $("#dispatchList").jqGrid({
			url : getRequestUrl(),
			datatype : "json",
			colNames : ["日期", "项目编号", "施工开始时间", "施工结束时间" , "有效工时（小时）", "当日工资"],
			caption : "派工列表",
			colModel : [
				{name : "dispatch.dispatchDay", width : 150, sortable: false,align : "center"},
				{name : "dispatch.projectId", index : "projectId", width : 150, sortable: false,align : "center"},
				{name : "startTimeString", index : "startTimeString", width : 150, sortable: false,align : "center"},
				{name : "endTimeString", index : "endTimeString", width : 150, sortable: false,align : "center"},
				{name : "hours", index : "hours", width : 150, sortable: false,align : "center"},
				{name : "cost", index : "cost", width : 150, sortable: false,align : "right", formatter : "currency", formatoptions : {prefix : "￥",thousandsSeparator:",", defaultValue : ""}}
			],
			autowidth: true,
			rownumbers: true,
			gridview: true,
			height: 300,
			footerrow : true,
			userDataOnFooter : true,
			viewrecords : true,
			jsonReader: {
				repeatitems : false
			},
			loadComplete : function(data){
				var totalCost = 0.0;
				$.each(data.rows, function(i, item){
					totalCost += item.cost;
				});
				$("#dispatchList").footerData("set", {hours : "当月总工资：", cost : totalCost}, true);
			}
		});
	});
});
