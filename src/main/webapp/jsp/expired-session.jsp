<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/linkPage.js"></script>
<title>重复登录</title>
</head>
<body>
<h1>重复登录</h1>
<hr>
<div>
	<span>您的账号在其他地方，您被迫下线，请重新登录。</span>
	 <a href="#" onclick="if(parent.frames.length>0){parent.location='${ctx}/welcome.action';}else{window.location='${ctx}/welcome.action;'}" style="text-decoration:underline;color:green;font-weight:bold;">请重新登录！</a>
</div>
<hr>
</body>
</html>