<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${ctitle} - 派工列表</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-ui-1.9.1.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
</head>

<body>
<div class="container">
	<div id="dispatchDiv">
		<table id="dispatchList" title="派工列表"></table>
		<div id="dispatchPager"></div>
	</div>     	
</div>
<SCRIPT type="text/javascript">
$(function(){
	var dataPickerF = function(element){
		$(element).datepicker({
			yearRange:'-10:+10',
			changeYear:true,
			changeMonth:true,
			dayNamesMin: ['日','一', '二', '三', '四', '五', '六' ],
	        dateFormat:'yy-mm-dd',
	        monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
	        monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
	        onSelect : function (dateText, inst){
				$(this).focus();
	        }
		});
	};
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
		url : "${ctx}/dispatch",
		datatype : "json",
		colNames : ["日期","员工名称", "项目编号", "项目名称","派工类型", "施工开始时间", "施工结束时间" , "有效时间"],
		caption : "派工列表",
		colModel : [
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
		}
	});
	$("#dispatchList").jqGrid('navGrid','#dispatchPager',{edit:false,add:false,del:false,search:false});
	$("#dispatchList").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
});
</SCRIPT>
</body>
</html>
