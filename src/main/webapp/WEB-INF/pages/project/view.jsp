<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.js"></script>
<title>${ctitle}-项目详情</title>
<style type="text/css">
td.td_first{
	text-align: right;
	font-weight: bold;
}
</style>
</head>
<body>
<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<h4>项目详情</h4>
			</div>
		</div>
	</div>
	<hr/>
	<table class="table table-striped table-condensed">
		<tr>
			<td class="td_first">项目编号:</td>
			<td>${project.projectId }</td>
			<td class="td_first">项目名称:</td>
			<td>${project.projectName}</td>
		</tr>
		<tr>
			<td class="td_first">施工负责人:</td>
			<td>${project.companyPrincipalName}</td>
			<td class="td_first">订单内容:</td>
			<td>${project.orderContent}</td>
		</tr>
		<tr>
			<td class="td_first">客户商:</td>
			<td>${project.clientName}</td>
			<td class="td_first">客户负责人:</td>
			<td>${project.clientPrincipalName}</td>
		</tr>
		<tr>
			<td class="td_first">施工地点:</td>
			<td>${project.constructPlace}</td>
			<td class="td_first">出订单日:</td>
			<td>${project.orderDate }</td>
		</tr>
		<tr>
			<td class="td_first">订单编号:</td>
			<td>${project.orderId}</td>
			<td class="td_first">报价单编号:</td>
			<td>${project.quoteId}</td>
		</tr>
		<tr>
			<td class="td_first">要求完工日:</td>
			<td>${project.requiredCompletionDate}</td>
			<td class="td_first">可延后时间:</td>
			<td>${project.canDelayDay}天</td>
		</tr>
		<tr>
			<td class="td_first">创建者:</td>
			<td>${project.creatorName}</td>
			<td class="td_first">创建时戳:</td>
			<td class="timestamp">${project.createdTimestamp}</td>
		</tr>
		<tr>
			<td class="td_first">最后修改人姓名:</td>
			<td>${project.lastModifierName}</td>
			<td class="td_first">最后修改时间:</td>
			<td class="timestamp">${project.lastModifiedTimestamp}</td>
		</tr>
	</table>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#modifyProjectBtn").click(function(){
			location="input.action?projectId=${project.projectId}";
		});
		$("#completionProjectBtn").click(function(){
			location="completion.action?projectId=${project.projectId}";
		});
		$("#openProjectBtn").click(function(){
			location="open.action?projectId=${project.projectId}";
		});
	});
</script>
</html>