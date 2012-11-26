<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无锡市金业科技自动化管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/convertCurrency.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>

</head>

<body onselectstart="return false" oncontextmenu= "return false">

<div id="menuFold" class="menu_open"></div>
<div class="header">
	<span style="margin-left:100px;font-size:24px;color: #fff;font-family:微软雅黑;">无锡市金业科技自动化管理系统</span>
    <ul class="header_link">
    	<li><a href="" onclick="window.open('main.action','content')">返回首页</a></li> 
        <li class="menu_line3"></li>
    	<li class="header_userinfo">欢迎:&nbsp;
    		<a target="content" href='person/view.action?personId=<security:authentication property="principal.userId" />' style="color: #ddd"><b><security:authentication property="principal.username" /></b></a></li>
        <li class="menu_line3"></li>
        <li class="header_exit"><a href="j_spring_security_logout" target="_top">退出登录</a></li>   
    </ul>
</div>

</body>

</html>