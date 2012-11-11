<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在建项目列表</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
<STYLE type="text/css">
#searchDiv{position: fixed;display: none;top:67px;left:10px;}
</STYLE>
</head>
<body>
<div class="content">
	<div class="main_panel">
		<h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">在建项目</a>在建项目列表
		</h3>
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
</body>
<script type="text/javascript">
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
	$("#projectList").jqGrid({
		url : "${ctx}/buildingProject/list_json.action",
		datatype : "json",
		colNames : ["操作", "项目编号", "项目名称", "客户商", "客户负责人", "公司负责人", "施工地点", "订单编号", "要求完工日", "可延后时间", "预估成本（RMB）" , "实际成本（RMB）", "合同总价（RMB）", "利润率"],
		caption : "施工流程列表",
		colModel : [
		    {name : 'act',index:'act', width:75,sortable:false, align: "center", search : false},
			{name : "projectId", key : true, index : "projectId", width : 100, align : "center"},
			{name : "project.projectName", index : "projectName", width : 350, align : "center"},
			{name : "project.clientName", index : "clientName", width : 150, align : "center"},
			{name : "project.clientPrincipalName", index : "clientPrincipalName", width : 150, align : "center"},
			{name : "project.companyPrincipalName", index : "companyPrincipalName", width : 150, align : "center"},
			{name : "project.constructPlace", index : "constructPlace", width : 150, align : "center"},
			{name : "project.orderId", index : "orderId", width : 250, align : "center"},
			{name : "project.requiredCompletionDate", index : "requiredCompletionDate", width : 150, align : "center"},
			{name : "project.canDelayDay", index : "canDelayDay", width : 150, align : "center"},
			{name : "estimateCost", index : "estimateCost", width : 150, align : "center"},
			{name : "actualCost", index : "actualCost", width : 150, align : "center"},
			{name : "contractPrice", index : "contractPrice", width : 150, align : "center"},
			{name : "margin", index : "margin", width : 150, align : "center"}
		],
		rowNum : 20,
		autowidth: true,
		rownumbers: true,
		gridview: true,
		height: 400,
		rowList : [10, 20, 30],
		pager : "#projectPager",
		viewrecords : true,
		sortorder: "desc",
		jsonReader: {
			repeatitems : false
		},
		gridComplete : function(){
			var ids = $("#projectList").jqGrid('getDataIDs');
			$.each(ids, function(i, id){
				var approval = "<a href=\"view.action?projectId="+id+"\">查看</a>";
				$("#projectList").jqGrid('setRowData', id, {act:approval});
			});
		}
	}).jqGrid('navGrid','#projectPager',{edit:false,add:false,del:false,search:false})
		.jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false})
		.navButtonAdd('#projectPager', {
			caption : "<s:property value="#session.searchYear - 1"/>年",
			id : "preYear",
			buttonicon : "ui-icon-triangle-1-w",
			onClickButton : function (){
				updateSearchYear(-1);
			}
		})
		.navButtonAdd('#projectPager', {
			caption : "<s:property value="#session.searchYear"/>年",
			id : "curYear"
		})
		.navButtonAdd('#projectPager', {
			caption : "<s:property value="#session.searchYear + 1"/>年",
			id : "nextYear",
			buttonicon : "ui-icon-triangle-1-e",
			onClickButton : function (){
				updateSearchYear(1);
			}
	});
	function updateSearchYear(incre){
		$.ajax({
			data : {"increYear" : incre},
			url : "../project/updateSearchYear.action",
			success : function (data){
				window.location.reload();
			}
		});
	}
	$("#projectList").jqGrid('navGrid','#projectPager',{edit:false,add:false,del:false,search:false});
	$("#projectList").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
	$("div.help").simpletip({
		content : $("div.helpContent").html(),
		fixed : true
	});
});
</script>
</html>