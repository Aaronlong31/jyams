<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无锡金业科技自动化管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/validator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-ui-1.9.1.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
<style type="text/css">
.ui-state-hover a, .ui-state-hover a:hover,.ui-widget-content a{color: #FC9604;}
</style>
</head>

<body>
<div class="content">
	<div class="main_panel">
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
			<div id="projectDiv">
				<table id="projectList" title="施工流程列表"></table>
				<div id="projectPager"></div>
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
	$("#projectList").jqGrid({
		url : "${ctx}/project",
		datatype : "json",
		colNames : ["操作","项目编号", "客户名称", "施工地点","订单号", "出单日期", "要求完工日" , "可延后时间", "订单内容", "客户负责人", "施工负责人"],
		caption : "施工流程列表",
		colModel : [
		    {name : 'act',index:'act', width:75,sortable:false, align: "center", search : false},
			{name : "projectId", key : true, index : "projectId", width : 100, align : "center"},
			{name : "clientName", index : "clientName", width : 150, align : "center"},
			{name : "constructPlace", index : "constructPlace", width : 150, align : "center"},
			{name : "orderId", index : "orderId", width : 150, align : "center"},
			{name : "orderDate", index : "orderDate", width : 150, align : "center"},
			{name : "requiredCompletionDate", index : "requiredCompletionDate", width : 150, align : "center"},
			{name : "canDelayDay", index : "canDelayDay", width : 150, align : "center"},
			{name : "orderContent", index : "orderContent", width : 350, align : "center"},
			{name : "clientPrincipalName", index : "clientPrincipalName", width : 150, align : "center"},
			{name : "companyPrincipalName", index : "companyPrincipalName", width : 150, align : "center"}
		],
		rowNum : 20,
		autowidth: true,
		rownumbers: true,
		gridview: true,
		height: 400,
		rowList : [10, 20, 30],
		pager : "#projectPager",
		//emptyrecords: "没有记录！",
		viewrecords : true,
		sortorder: "desc",
		jsonReader: {
			repeatitems : false
		},
		gridComplete : function(){
			var ids = $("#projectList").jqGrid('getDataIDs');
			$.each(ids, function(i, id){
				var approval = "<a href=\"${ctx}/project/"+id+"\">查看</a>";
				$("#projectList").jqGrid('setRowData', id, {act:approval});
			});
		}
	}).jqGrid('navGrid','#projectPager',{edit:false,add:false,del:false,search:false})
		.jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false})
		.navButtonAdd('#projectPager', {
			caption : "${searchYear - 1}年",
			id : "preYear",
			buttonicon : "ui-icon-triangle-1-w",
			onClickButton : function (){
				updateSearchYear(-1);
			}
		})
		.navButtonAdd('#projectPager', {
			caption : "${searchYear}年",
			id : "curYear"
		})
		.navButtonAdd('#projectPager', {
			caption : "${searchYear + 1}年",
			id : "nextYear",
			buttonicon : "ui-icon-triangle-1-e",
			onClickButton : function (){
				updateSearchYear(1);
			}
		});
	function updateSearchYear(incre){
		$.ajax({
			data : {"increYear" : incre},
			url : "${ctx}/project/updateSearchYear",
			type : "POST",
			success : function (data){
				window.location.reload();
			}
		});
	}
	$("div.help").simpletip({
		content : $("div.helpContent").html(),
		fixed : true
	});
});
</SCRIPT>
</body>
</html>