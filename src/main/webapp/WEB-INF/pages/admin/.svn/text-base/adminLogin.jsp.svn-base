<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<div id="menuFold" class="menu_open"></div>
<div class="header">
    <ul class="header_link">
    	<li class="header_userinfo">欢迎:&nbsp;<a href="#" onclick="window.open('organization/viewPersonByMyself.action','content')"  style="color: #ddd"><b><security:authentication property="principal.username" /></b></a></li>
        <!--<li>&nbsp;在线5人</li>
        -->
        <li class="menu_line3"></li>
        <li class="header_exit"><a href="/jyams-web/j_spring_security_logout" target="_top">退出登录</a></li>   
    </ul>
</div>
<div class="sidebar">
	  <div class="main_panel">
	  		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />项目管理<div class="open_panel"></div></h3>
	       	<ul class="sidebar_menu">
	            <li onclick="linkPages('project/input.action','content');"><span class="link">添加项目</span></li>
	        	<li onclick="linkPages('project/list.action','content');"><span class="link">查看项目</span></li>
	        	<li onclick="linkPages('project/listMine.action','content');"><span class="link">查看我负责的项目</span></li>
	        	<li onclick="linkPages('buildingProject/list.action','content');"><span class="link">查看在建项目</span></li>
	        	<li onclick="linkPages('dispatch/list.action','content');"><span class="link">派工管理</span></li>
	        </ul>
	  </div>
	  <div class="main_panel">
	  		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />部门/员工管理<div class="open_panel"></div></h3>
	       	<ul class="sidebar_menu">
	            <li onclick="linkPages('person/list.action','content');"><span class="link">公司员工信息</span></li>
	        	<li onclick="linkPages('person/input.action','content');"><span class="link">添加员工</span></li>
	        	<li onclick="linkPages('department/list.action','content');"><span class="link">公司部门信息</span></li>
	        	<li onclick="linkPages('department/input.action','content');"><span class="link">添加部门</span></li>
	        </ul>
	  </div>
	  <div class="main_panel">
	  		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />客户管理<div class="open_panel"></div></h3>
	       	<ul class="sidebar_menu">
	            <li onclick="linkPages('client/input.action','content');"><span class="link">添加客户</span></li>
	            <li onclick="linkPages('client/list.action','content');"><span class="link">客户列表</span></li>
	            <li onclick="linkPages('clientPrincipal/list.action','content');"><span class="link">客户负责人列表</span></li>
	            <li onclick="linkPages('clientPrincipal/list.action','content');"><span class="link">客户负责人列表</span></li>
	        </ul>
	  </div>
	  <div class="main_panel">
	  		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />采购管理<div class="open_panel"></div></h3>
	       	<ul class="sidebar_menu">
	            <li onclick="linkPages('purchase/listPurchaseApplis.action','content');"><span class="link">采购申请</span></li>
	        	<li onclick="linkPages('purchase/listPurchaseOrders.action','content');"><span class="link">采购订单</span></li>
	        </ul>
	  </div>
	  <div class="main_panel">
	  		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />报价管理<div class="open_panel"></div></h3>
	       	<ul class="sidebar_menu">
	            <li onclick="linkPages('quote/input.action','content');"><span class="link">报价</span></li>
	            <li onclick="linkPages('quote/list.action','content');"><span class="link">报价列表</span></li>
	        </ul>
	  </div>
	  <div class="main_panel">
	  		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />考勤管理<div class="open_panel"></div></h3>
	       	<ul class="sidebar_menu">
	            <li onclick="linkPages('','content');"><span class="link">考勤</span></li>
	        </ul>
	  </div>
</div>
<div class="content">
	<div class="main_panel">
    	<h3 class="crumbs">
        	<img src="../images/sidebar_h3_bg1.gif" alt="" />
        	当前位置：<a href="#">首页</a>添加采购申请
        </h3>
        <div class="error ${param.error == true ? '' : 'hide'}">
			  登陆失败<br>
			  ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
		</div>
		<form action="j_spring_security_check" method="POST">
			用户名：<input type="text" name="j_username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"/><br>
			密码：<input type="password" name="j_password"><br>
			<input type="submit" value="登录"/>
			<input type="reset" value="重置"/>
		</form>
    </div>
</div>
<div class="footer">
&nbsp;&nbsp;&nbsp;&nbsp;
</div>
</body>
</html>