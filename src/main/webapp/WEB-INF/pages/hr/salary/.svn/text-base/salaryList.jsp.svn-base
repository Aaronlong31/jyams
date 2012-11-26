<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无锡金业科技自动化管理系统 - 员工工资列表</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
<style type="text/css">
#salaryDiv > div{margin: 0 auto}
.ui-state-hover a, .ui-state-hover a:hover,.ui-widget-content a{color: #FC9604;}
#salaryPager_center{display:none}
.help{display:none}
</style>
</head>

<body class="bodyStyle">
<div class="content contentwidth">
	<div class="main_panel">
		<h3 class="crumbs">
		<img src="../images/sidebar_h3_bg1.gif" alt="" />当前位置：<a href="#">首页</a>员工工资列表</h3>
		<div class="help">操作提示</div>
		<div class="helpContent">
			<ul>
				<li>单击采购单列表中的某一行，即可在采购单详情列表中查看采购单详情；</li>
				<li>列表有类似Excel的修改列宽的功能；</li>
				<li>单击列表的标题，可以对列表进行排序；</li>
				<li>在列表标题下方的输入框中输入条件后按<span>回车键</span>可对结果进行过滤，类似Excel；</li>
				<li>在列表标题下方有<span>向左</span>和<span>向右</span>的按钮，可进行翻页；</li>
				<li>在采购单明细列表中，点击<span>&ldquo;确认到货&rdquo;</span>，即可修改采购单项为已到货。</li>
			</ul>
		</div>
		<div class="main_content">
			<div id="salaryDiv" style="float: left;width: 350px;">
				<table id="salaryList"></table>
				<div id="salaryPager"></div>
			</div>  
			<div style="float: left;width: 500px;">
				<table id="dailySalaryList"></table>
			</div>   	
		</div>
	</div>
</div>
<SCRIPT type="text/javascript">
$(function(){
	$("#salaryList").jqGrid({
		url : "${ctx}/salary/listMonthlySalary_json.action",
		datatype : "json",
		colNames : ["员工ID", "月份", "员工姓名", "月工资"],
		caption : "员工工资列表",
		colModel : [
		    {name : "personId", key: true, hidden : true, width:0},
		    {name : "monthString", align: "center", width:100},
		    {name : "personName", index : "personName", width : 150, align : "center"},
		    {name : "cost", index : "cost", width :100, align : "right", formatter : "currency", formatoptions : {prefix : "￥",thousandsSeparator:","}}
		],
		autowidth : true,
		height : "400",
		rownumbers: true,
		gridview: true,
		pager : "#salaryPager",
		viewrecords : true,
		sortorder: "desc",
		jsonReader: {
			repeatitems : false
		},
		onSelectRow: function(ids) {
			if(ids != null) {
				var personId = ids;
				$("#dailySalaryList").jqGrid('setGridParam',{url:"listDailySalary_json.action?personId="+personId, page:1})
					.trigger('reloadGrid');
			}
		}
	});
	$("#salaryList").jqGrid('navGrid','#salaryPager',{edit:false,add:false,del:false,search:false})
		.navButtonAdd('#salaryPager', {
			id : "preMonth",
			caption : "上一月",
			buttonicon : "ui-icon-triangle-1-w",
			onClickButton : function (){
				updateSearchYear(-1);
			}
		})
		.navButtonAdd('#salaryPager', {
			id:"nextMonth",
			caption : "下一月",
			buttonicon : "ui-icon-triangle-1-e",
			onClickButton : function (){
				updateSearchYear(1);
			}
		});
	$("#dailySalaryList").jqGrid({
		datatype : "json",
		colNames : ["日期","开始时间","结束时间","工时","日工资"],
		caption : "工资详细清单",
		colModel : [
			{name : "dayString", index : "day", width : 130, align : "center"},
			{name : "startTimeString", index : "day", width : 100, align : "center"},
			{name : "endTimeString", index : "day", width : 100, align : "center"},
			{name : "hours", index : "hours", width : 130, align : "center"},
			{name : "cost", index : "salary", width : 100, align : "right",formatter : "currency", formatoptions : {prefix : "￥",thousandsSeparator:","}}
		],
		autowidth : true,
		rownumbers: true,
		gridview: true,
		viewrecords : true,
		footerrow : true,
		userDataOnFooter : true,
		altRows : true,
		height : "auto",
		sortable : false,
		jsonReader: {
			repeatitems : false
		},
		loadComplete : function(data){
			var totalCost = 0.0;
			var totalHours = 0.0;
			$.each(data.rows, function(i, item){
				totalCost += item.cost;
				totalHours += item.hours;
			});
			$("#dailySalaryList").footerData("set", {hours : "共" + totalHours + "小时", cost : totalCost}, true);
		}
	});
	function updateSearchYear(incre){
		$.ajax({
			data : {"increMonth" : incre},
			url : "updateSearchMonth_json.action",
			success : function (data){
				$("#salaryList").trigger("reloadGrid");
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
