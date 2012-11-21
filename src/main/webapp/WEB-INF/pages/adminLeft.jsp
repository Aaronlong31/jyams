<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无锡金业科技自动化管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/linkPage.js"></script>
</head>
<body style="background:#fff">
<security:authorize ifAllGranted="ROLE_添加项目">
</security:authorize>
<div class="sidebar">
	<div class="main_panel">
		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />项目管理<div class="open_panel"></div></h3>
		<ul class="sidebar_menu">
			<li class="open_menu">
				<span class="link">操作员管理</span>
				<ul class="sidebar_sub_menu">
					<li onclick="linkPages('user/list.action','content');"><span class="link">操作员列表</span></li>
				</ul>
			</li>
		</ul>
	</div>
</div>
</body>
</html>