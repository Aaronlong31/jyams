<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询操作日志</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
</head>

<body>
<div class="content">
	<div class="main_panel">
		<h3 class="crumbs">
		<img src="../images/sidebar_h3_bg1.gif" alt="" />当前位置：<a href="#">首页</a>操作日志列表</h3>
		<div class="main_content">
			<div class="help">操作提示</div>
			<div class="helpContent">
				<ul>
					<li>列表有类似Excel的修改列宽的功能；</li>
					<li>单击列表的标题，可以对列表进行排序；</li>
					<li>在列表标题下方的输入框中输入条件后按<span>回车键</span>可对结果进行过滤，类似Excel；</li>
					<li>在列表标题下方有<span>向左</span>和<span>向右</span>的按钮，可进行翻页；</li>
				</ul>
			</div>
			
			<div id="operateLogDiv">
				<table id="operateLogList" title="操作日志列表"></table>
				<div id="operateLogPager"></div>
			</div>     	
		</div>
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
	var buildSelectOperatorName = function(xmlHttpRequest){
		var selectHtml = "<select>";
		selectHtml += "<option value=''>全部</option>";
		var data = $.parseJSON(xmlHttpRequest.responseText);
		$.each(data, function(i, item){
			selectHtml += "<option value='" + item['username'] + "'>" + item['username'] + "</option>";
		});
		selectHtml += "</select>";
		return selectHtml;
	};
	var buildSelectF = function (xmlHttpRequest){
		var selectHtml = "<select>";
		selectHtml += "<option value=''>全部</option>";
		var data = $.parseJSON(xmlHttpRequest.responseText);
		for(var value in data){
			selectHtml += "<option value='"+value+"'>" + data[value] + "</option>";
		}
		selectHtml += "</select>";
		return selectHtml;
	};
	$("#operateLogList").jqGrid({
		url : "${ctx}/operateLog/list_json.action",
		datatype : "json",
		colNames : ["操作员", "时间", "类型","信息", "模块", "对象" , "IP"],
		caption : "操作日志列表",
		colModel : [
			{name : "operatorName", key : true, index : "operatorName", width : 60, align : "center", stype : "select",searchoptions:{ dataUrl : "${ctx}/operateLog/listOperator_JSON.action", buildSelect: buildSelectOperatorName}},
			{name : "operateTimestamp", index : "operateTimestamp", width : 150, align : "center"},
			{name : "operateTypeString", index : "operateType", width : 50, align : "center", stype : "select",searchoptions:{ dataUrl : "${ctx}/operateLog/listOperateType_JSON.action", buildSelect: buildSelectF}},
			{name : "operateInfo", index : "operateInfo", width : 350, align : "left"},
			{name : "operateModuleString", index : "operateModule", width : 80, align : "center", stype : "select",searchoptions:{ dataUrl : "${ctx}/operateLog/listOperateModule_JSON.action", buildSelect: buildSelectF}},
			{name : "operateModelString", index : "operateModel", width : 80, align : "center", stype : "select",searchoptions:{ dataUrl : "${ctx}/operateLog/listOperateModel_JSON.action", buildSelect: buildSelectF}},
			{name : "ip", index : "ip", width : 150, align : "center"}
		],
		rowNum : 20,
		autowidth: true,
		rownumbers: true,
		gridview: true,
		height: 400,
		rowList : [10, 20, 30],
		pager : "#operateLogPager",
		viewrecords : true,
		sortorder: "desc",
		jsonReader: {
			repeatitems : false
		}
	}).jqGrid('navGrid','#operateLogPager',{edit:false,add:false,del:false,search:false})
		.jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
	$("div.help").simpletip({
		content : $("div.helpContent").html(),
		fixed : true
	});
});
</SCRIPT>
</html>