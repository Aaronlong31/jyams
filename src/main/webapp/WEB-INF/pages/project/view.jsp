<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-ui-1.9.1.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
<script type="text/javascript" src="${ctx}/js/convertCurrency.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<title>项目详情</title>
</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a>查看项目
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<p class="table_bar">
                    	<button type="button" class="btn" id="modifyProjectBtn"><img src="../images/btn_edit.gif" alt="" />修改</button>
<%--                     	<c:if test="${project.status} and 1 eq 1">
                    		<button type="button" class="btn4" id="completionProjectBtn"><img src="../images/btn_edit.gif" alt="" />完工项目</button>
                    	</c:if>
                    	<c:if test="${project.status} and 16 eq 16">
                    		<button type="button" class="btn4" id="openProjectBtn"><img src="../images/btn_edit.gif" alt="" />开启项目</button>
                    	</c:if>
 --%>                    </p>
					<table class="table_normal">
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
							<td class="td_first">最后修改时戳:</td>
							<td class="timestamp">${project.lastModifiedTimestamp}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
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