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
<title>${ctitle} - 在建项目详情</title>
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
				<h4>在建项目详情</h4>
			</div>
		</div>
	</div>
	<hr/>
	<a class="btn btn-warning" href="${ctx}/buildingProject/toEdit/${buildingProject.projectId}">编辑在建项目</a>
	<div id="message" class="alert alert-success">${message}</div>
	<table class="table table-striped table-condensed">
		<tr>
			<td class="td_first">项目编号:</td>
			<td>${buildingProject.projectId}</td>
			<td class="td_first">项目名称:</td>
			<td>${buildingProject.project.projectName}</td>
		</tr>
		<tr>
			<td class="td_first">公司负责人:</td>
			<td>${buildingProject.project.companyPrincipalName}</td>
			<td class="td_first">项目状态:</td>
			<td>${buildingProject.statusString}</td>
		</tr>
		<tr>
			<td class="td_first">客户:</td>
			<td>${buildingProject.project.clientName}</td>
			<td class="td_first">客户负责人:</td>
			<td>${buildingProject.project.clientPrincipalName}</td>
		</tr>
		<tr>
			<td class="td_first">施工地点:</td>
			<td>${buildingProject.project.constructPlace}</td>
			<td class="td_first">出订单日:</td>
			<td>${buildingProject.project.orderDate}</td>
		</tr>
		<tr>
			<td class="td_first">订单编号:</td>
			<td>${buildingProject.project.orderId}</td>
			<td class="td_first">报价单编号:</td>
			<td>${buildingProject.project.quoteId}</td>
		</tr>
		<tr>
			<td class="td_first">要求完工日:</td>
			<td>${buildingProject.project.requiredCompletionDate}</td>
			<td class="td_first">可延后时间:</td>
			<td>${buildingProject.project.canDelayDay}天</td>
		</tr>
		<tr>
			<td class="td_first">实际成本（RMB）:</td>
			<td>${buildingProject.actualCost}元</td>
			<td class="td_first">预估成本（RMB）:</td>
			<td>${buildingProject.estimateCost}元</td>
		</tr>
		<tr>
			<td class="td_first">利润率:</td>
			<td>${buildingProject.margin}</td>
			<td class="td_first">合同总价（RMB）:</td>
			<td>${buildingProject.contractPrice}元</td>
		</tr>
		<tr>
			<td class="td_first">已付款（RMB）:</td>
			<td>${buildingProject.paidMoney}</td>
			<td class="td_first">未付款（RMB）:</td>
			<td>${buildingProject.unpaidMoney}</td>
		</tr>
		<tr>
			<td class="td_first">创建:</td>
			<td>${buildingProject.project.creatorName}&nbsp;
				${buildingProject.project.createdTimestamp}
			</td>
			<td class="td_first">最后修改:</td>
			<td>
				${buildingProject.lastModifierName}&nbsp;
				${buildingProject.lastModifiedTimestamp}
			</td>
		</tr>
		<tr>
			<td class="td_first">开票:</td>
			<td>
				${buildingProject.invoicerName}&nbsp;
				${buildingProject.invoiceTimestamp}
			</td>
			<td class="td_first">完工:</td>
			<td>
				${buildingProjectcompletionPersonName}&nbsp;
				${buildingProject.completionTimestamp}
			</td>
		</tr>
		<tr>
			<td class="td_first">订单内容:</td>
			<td colspan="3">${buildingProject.project.orderContent}</td>
		</tr>
	</table>
</div>
</body>
<script type="text/javascript">
$(function(){
	if($("#message").text() == ""){
		$("#message").hide();
	}
	$("#addDetail").toggle(function(){
		$("#addDetailDiv").show();
	}, function(){
		$("#addDetailDiv").hide();
	});
	$("#addDetailForm").validate({
		rules:{
			"buildingProjectDetail.cost":{
				required: true,
				number:true
			}
		},
		messages:{
			"buildingProjectDetail.cost":{
				required: "请输入花费金额！",
				number:"花费金额必须为数字！"
			}
		}
	});
});
</script>
</html>