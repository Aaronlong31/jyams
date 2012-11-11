<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
<title>客户商详细信息</title>
</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">部门/人员管理</a>查看客户
		</h3>
		<div class="main_content two_rows">
			<div class="sub_panel" >
				<h4><div class="tittle">查看客户<div class="tittle_end"></div></div><div class="close_panel"></div></h4>
				<div class="main_content">
					<p class="table_bar">
                    	<button type="button" class="btn" id="modifyClientBtn"><img src="../images/btn_edit.gif" alt="" />修改</button>
                    </p>
                    <input type="hidden" name="client.clientId" value="${client.clientId }"/>
					<table class="detail_info">
						<tr>
							<td class="td_first">客户商名称:</td>
							<td><s:property value="client.clientName" /></td>
						</tr>
						<tr>
							<td class="td_first">地址:</td>
							<td><s:property value="client.address" /></td>
						</tr>
						<tr>
							<td class="td_first">联系电话:</td>
							<td><s:property value="client.phone" /></td>
						</tr>
						<tr>
							<td class="td_first">邮箱:</td>
							<td><s:property value="client.email" /></td>
						</tr>
						<tr>
							<td class="td_first">客户负责人:</td>
							<td>
								<s:iterator value="client.principals">
									<s:property value="name"/><br>
								</s:iterator>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#modifyClientBtn").click(function(){
			location="input.action?clientId=${client.clientId}";
		});
	});
</script>
</html>