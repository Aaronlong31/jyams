<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户商列表</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
</head>
<body>
<div>
<h2>项目列表</h2>
<div>
<strong>共搜索到<span style="color:red">${totalCount}</span>个客户商</strong>
</div>
<table  border="1" align="center" cellpadding="0" cellspacing="0" style="margin: 0px 0px 0px 0px;">
<tr>
<td width="16%"  align="center">客户商名称</td>
	<td width="16%"  align="center">地址</td>
	<td width="16%"  align="center">联系电话</td>
	<td width="16%"  align="center">邮箱</td>
	<td width="16%"  align="center">热度</td>
	<td width="16%"  align="center">修改</td>
</tr>

<s:iterator value = "clients" status="status" id = "c">
	<tr>
	    <td style="text-align: left;">
			<a href="view.action?clientId=${c.clientId}&flag=1">${clientName}</a>
		</td>
		<td style="text-align: left;">
			${address}
		</td>
		<td style="text-align: left;">
			${phone}
		</td>
		<td style="text-align: left;">
			${email}
		</td>
		<td style="text-align: left;">
			${priority}
		</td>
		<td>
			<a href="select.action?clientId=${c.clientId}&flag=2">修改</a>
		</td>
	</tr>
</s:iterator>

</table>
</div>
</body>
</html>