<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Random"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script type="text/javascript">
if(window.location.href != window.top.location.href){
	window.top.location.href = window.location.href;
}
</script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>

</head>
<body>
<div class="header">
	<span style="margin-left:100px;font-size:24px;color: #fff;font-family:微软雅黑;">无锡市金业科技自动化管理系统</span>
</div>
<div class="content" style="padding: 0px;height: 800px;">
	<div style="width: 226px;float: left;">
		  <div class="main_panel" style="margin-bottom: 0px;height: 800px;">
		  		<h3 style="cursor: default;"><img src="images/sidebar_h3_bg1.gif" alt="" />登陆</h3>
		  		<div class="error ${param.error == true ? '' : 'hide'}">
				  ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
				</div>
				<form action="j_spring_security_check" method="POST">
					<table  class="table_normal" style="margin: 0px;">
						<tr>
							<td>用户名：</td>
							<td>
								<input class="text" type="text" name="j_username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"/>
							</td>
						</tr>
						<tr>
							<td>密码：</td>
							<td>
								<input class="text" type="password" name="j_password"><br>
								<input type="hidden" name="randomNum" value="<%=new Random().nextInt() %>"/>
							</td>
						</tr>
					</table>
					<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
		                <input type="submit" value="登录"/>
						<input type="reset" value="重置"/>
		            </div>
				</form>
		  </div>
	</div>
	<div>
		<table style="margin-top: 50px;">
			<thead>
				<tr>
					<th colspan="2" style="text-align: center;font-size: 24px;font-weight: bold;">公司简介</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2" style="padding-left:20px; text-indent: 24px;">
						无锡市金业科技有限公司成立于2000年3月，（原称：无锡市创新机电工程安装公司,成立于1995年）。无锡经济的快速发展使公司的业绩也得到不断的提高。 我公司现主要经营范围：工厂中央空调系统
						的设计、按装及改造；电子工业厂房净化工程的设计和施工；工厂辅助生产（安全）设备的采购、安装、调试及改造；机电设备
						的采购和安装。我公司现新增了空调通风系统中使用的风管生产线，该生产线是目前国内较先进的生产线，生产
						的风管在通风工程中得到广泛使用。 我公司拥有经验丰富的管理人员、设计师、工程师和销售人员。质量和信誉是我公司的宗旨
						，我们为客户提供专业的顾问服务，无论是设计理念或实际的安装工作，我们都是会为不同的客户构思切合其要求的方案。
					</td>
				</tr>
				<tr style="font-weight: bold;">
					<td style="padding-left:20px; ">主要服务：</td>
					<td>
						<p style="padding-top: 10px;">
							各种机电设备; 无法兰风管; 中央空调机组; 工程安装; 
						</p>
					</td>
				</tr>
			</tbody>
		</table>
		<div style="text-align: center;font-size: 16px; font-weight: bold;margin-top: 20px;">
			<a href="http://www.google.cn/chrome/intl/zh-CN/landing_chrome.html?hl=zh_cn&brand=CHMA&utm_campaign=zh_cn&utm_source=zh_cn-ha-apac-zh_cn-bk&utm_medium=ha" target="_blank">为获得更好的操作及界面效果，推荐使用Google Chrome浏览器。</a>
		</div>
	</div>
</div>
<div class="footer" style="margin-bottom: 0px;">
&nbsp;&nbsp;&nbsp;&nbsp;
</div>
</body>
</html>