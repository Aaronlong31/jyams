<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>500 - 系统内部错误</title>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#showMessage").toggle(
			function(){
				$("#errorMessage").show();
			},
			function(){
				$("#errorMessage").hide();
		});
	});
</script>
</head>

<body>
<div>
<h3>500</h3>
<h1>系统发生内部错误.</h1>
<hr/>
	<div>这通常是由于网站的漏洞造成的，请尽快联系网站开发人员解决问题。<a href="#" id="showMessage">点此查看详细错误信息</a>，建议将错误信息随邮件发送。</div>
	
	开发人员：
	<ul>
		<li>张龙
			<ul>
				<li>新浪邮箱:<a href="mailto:aaron198809@sina.com">aaron198809@sina.com</a></li>
				<li>网易邮箱:<a href="mailto:0540306131@163.com">0540306131@163.com</a></li>
				<li><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=574553107&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:574553107:50" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></li>
			</ul>
		</li>
	</ul>
	<div style="display: none;" id="errorMessage">
		<s:property value="exception"/>
		<hr />
		<s:iterator value="exception.stackTrace">
			<s:property/>
		</s:iterator>
	</div>
</div>
</body>

</html>
