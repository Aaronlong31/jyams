<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
</head>
<body>
<div class="content contentwidth">
	<div class="main_panel">
		<h3 class="crumbs">
		<img src="../images/sidebar_h3_bg1.gif" alt="" />当前位置：<a href="#">首页</a>采购单状态列表</h3>
		<div class="main_content">
			<div class="help">操作提示</div>
			<div class="helpContent">
				<ul>
					<li>单击采购单列表中的某一行，即可在采购单详情列表中查看采购单详情；</li>
					<li>列表有类似Excel的修改列宽的功能；</li>
					<li>单击列表的标题，可以对列表进行排序；</li>
					<li>在列表标题下方的输入框中输入条件后按<span>回车键</span>可对结果进行过滤，类似Excel；</li>
					<li>在列表标题下方有<span>向左</span>和<span>向右</span>的按钮，可进行翻页；</li>
				</ul>
			</div>
			<div id="selectProjectDiv">
				<table id="purchaseList"></table>
				<div id="purchasePager"></div>
				<table id="purchaseItemList"></table>
				<div id="purchaseItemPager"></div>
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
	var buildStatusSelectF = function(xmlHttpRequest){
		var selectHtml = "<select>";
		selectHtml += "<option value=''>全部</option>";
		var data = $.parseJSON(xmlHttpRequest.responseText);
		for(var value in data){
			selectHtml += "<option value='"+value+"'>" + data[value] + "</option>";
		}
		selectHtml += "</select>";
		return selectHtml;
	};
	var buildTypeSelectF = function(xmlHttpRequest){
		var selectHtml = "<select>";
		selectHtml += "<option value=''>全部</option>";
		var data = $.parseJSON(xmlHttpRequest.responseText);
		for(var value in data){
			selectHtml += "<option value='"+value+"'>" + data[value] + "</option>";
		}
		selectHtml += "</select>";
		return selectHtml;
	};
	$("#purchaseList").jqGrid({
		url : "${ctx}/purchase/listPurchaseStatus_json.action",
		datatype : "json",
		colNames : ["采购单ID", "采购单编号", "类型", "状态", "申请人", "申请时间", "指定到货日期", "送货地址" , "收货人"],
		caption : "采购单列表",
		colModel : [
		   //{name : "purchaseId", width : 100, fixed: true, sortable : false, resize: false, formatter:'actions', formatoptions:{keys:true}},
			{name : "purchaseId", key : true, hidden: true, width : 0, align : "center"},
			{name : "purchaseCode", index : "purchaseCode", width : 150, align : "center"},
			{name : "typeString", index : "type", width : 150, align : "center", stype : "select", searchoptions:{ dataUrl : "listTypeString_json.action", buildSelect : buildTypeSelectF }},
			{name : "statusString", index : "status", width : 150, align : "center", stype : "select", searchoptions:{ dataUrl : "listStatusString_json.action", buildSelect : buildStatusSelectF}},
			{name : "applierName", index : "applierName", width : 150, align : "center", stype : "select",searchoptions:{ dataUrl : "${ctx}/person/listPersonsSimple_JSON.action", buildSelect : buildSelectF}},
			{name : "applyTimestamp", index : "applyTimestamp", width : 150, align : "center", searchoptions:{dataInit:dataPickerF}},
			{name : "arrivalDateString", index : "arrivalDate", width : 150, align : "center", searchoptions:{dataInit:dataPickerF}},
			{name : "deliveryAddress", index : "deliveryAddress", width : 150, align : "center"},
			{name : "consigneeName", index : "consigneeName", width : 150, align : "center", stype : "select", searchoptions:{ dataUrl : "${ctx}/person/listPersonsSimple_JSON.action", buildSelect:buildSelectF}}
		],
		rowNum : 20,
		autowidth : true,
		rownumbers: true,
		gridview: true,
		height: 220,
		rowList : [10, 20, 30],
		pager : "#purchasePager",
		viewrecords : true,
		sortorder: "desc",
		jsonReader: {
			repeatitems : false
		},
		onSelectRow: function(ids) {
			if(ids != null) {
				$("#purchaseItemList").jqGrid('setGridParam',{url:"listPurchaseItem_json.action?purchaseId="+ids,page:1});
				$("#purchaseItemList").jqGrid('setCaption',"采购单明细: "+ $(ids).children("td[aria-describedby=purchaseList_purchaseCode]").text())
				.trigger('reloadGrid');
			}
		}
	});
	$("#purchaseList").jqGrid('navGrid','#purchasePager',{edit:false,add:false,del:false,search:false});
	$("#purchaseList").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
	$("#purchaseItemList").jqGrid({
		datatype : "json",
		colNames : ["采购单项ID", "项目编号", "项目名称", "采购单项","规格", "数量", "单位" , "单价","金额", "状态", "到货时间", "备注", "供应商", "电话"],
		caption : "采购单明细：",
		colModel : [
			{name : "purchaseItemId", key : true, hidden: true, width : 0, align : "center"},
			{name : "projectId", index : "projectId", width : 150, align : "center"},
			{name : "projectName", index : "projectName", width : 350, align : "center"},
			{name : "materialName", index : "materialName", width : 200, align : "center"},
			{name : "specifications", index : "specifications", width : 100, align : "center"},
			{name : "count", index : "count", width : 150, align : "center"},
			{name : "unit", index : "unit", width : 150, align : "center"},
			{name : "unitPrice", index : "unitPrice", width : 150, align : "center", formatter : "currency", formatoptions : {prefix : "￥",thousandsSeparator:",", defaultValue : ""}},
			{name : "cost", index : "cost", width : 150, align : "center", formatter : "currency", formatoptions : {prefix : "￥",thousandsSeparator:",", defaultValue : ""}},
			{name : "statusString", index : "status", width : 140, align : "center"},
			{name : "purchaseTimestamp", index : "purchaseTimestamp", width : 300, align : "center"},
			{name : "remark", index : "remark", width : 150, align : "center"},
			{name : "supplier", index : "supplier", width : 200, align : "center"},
			{name : "telephone", index : "telephone", width : 150, align : "center"}
		],
		autowidth : true,
		rownumbers: true,
		gridview: true,
		viewrecords : true,
		footerrow : true,
		userDataOnFooter : true,
		altRows : true,
		sortable : false,
		jsonReader: {
			repeatitems : false
		},
		loadComplete : function(data){
			var totalCost = 0.0;
			$.each(data.rows, function(i, item){
				totalCost += item.cost;
			});
			$("#purchaseItemList").footerData("set", {unit : "总金额：", cost : totalCost}, true);
		}
	});
	$("div.help").simpletip({
		content : $("div.helpContent").html(),
		fixed : true
	});
});
</SCRIPT>
</body>
</html>
