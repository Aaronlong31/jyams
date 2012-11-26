<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无锡金业科技自动化管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
<style type="text/css">
#selectProjectDiv > div{margin: 0 auto}
.ui-state-hover a, .ui-state-hover a:hover,.ui-widget-content a{color: #FC9604;}
</style>
</head>

<body class="bodyStyle">
<div class="content contentwidth">
	<div class="main_panel">
		<h3 class="crumbs">
		<img src="../images/sidebar_h3_bg1.gif" alt="" />当前位置：<a href="#">首页</a>审批采购</h3>
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
			<div id="selectProjectDiv">
				<table id="purchaseList" title="待审核的采购单列表"></table>
				<div id="purchasePager"></div>
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
	$("#purchaseList").jqGrid({
		url : "${ctx}/purchase/material/approveList_json.action",
		datatype : "json",
		colNames : ["操作","采购单ID", "采购单编号", "申请人","申请时间", "指定到货日期", "送货地址" , "收货人"],
		caption : "待审核的采购单列表",
		colModel : [
		   //{name : "purchaseId", width : 100, fixed: true, sortable : false, resize: false, formatter:'actions', formatoptions:{keys:true}},
		    {name : 'act',index:'act', width:75,sortable:false, align: "center", search : false},
			{name : "purchaseId", key : true, hidden: true, width : 0, align : "center"},
			{name : "purchaseCode", index : "purchaseCode", width : 150, align : "center"},
			{name : "applierName", index : "applierName", width : 150, align : "center", stype : "select",searchoptions:{ dataUrl : "${ctx}/person/listPersonsSimple_JSON.action", buildSelect:buildSelectF}},
			{name : "applyTimestamp", index : "applyTimestamp", width : 150, align : "center", searchoptions:{dataInit:dataPickerF}},
			{name : "arrivalDateString", index : "arrivalDate", width : 150, align : "center", searchoptions:{dataInit:dataPickerF}},
			{name : "deliveryAddress", index : "deliveryAddress", width : 150, align : "center"},
			{name : "consigneeName", index : "consigneeName", width : 150, align : "center", stype : "select", searchoptions:{ dataUrl : "${ctx}/person/listPersonsSimple_JSON.action", buildSelect:buildSelectF}}
		],
		rowNum : 20,
		autowidth: true,
		rownumbers: true,
		gridview: true,
		height: 400,
		rowList : [10, 20, 30],
		pager : "#purchasePager",
		viewrecords : true,
		sortorder: "desc",
		jsonReader: {
			repeatitems : false
		},
		gridComplete : function(){
			var ids = $("#purchaseList").jqGrid('getDataIDs');
			$.each(ids, function(i, id){
				var approval = "<a href=\"toApprove.action?approveNum=1&purchaseId="+id+"\">审核</a>";
				$("#purchaseList").jqGrid('setRowData', id, {act:approval});
			});
		}
	});
	$("#purchaseList").jqGrid('navGrid','#purchasePager',{edit:false,add:false,del:false,search:false});
	$("#purchaseList").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
	$("div.help").simpletip({
		content : $("div.helpContent").html(),
		fixed : true
	});
});
</SCRIPT>
</body>
</html>
