<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.Random"
    pageEncoding="UTF-8"%>

<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ctitle}-登录</title>
<script type="text/javascript">
if(window.location.href != window.top.location.href){
	window.top.location.href = window.location.href;
}
</script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/validator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/validator/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<form action="login" method="POST" class="form-horizontal">
			<fieldset>登录</fieldset>
			<div class="control-group">
				<label class="control-label" for="username"></label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="username" name="username"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password"></label>
				<div class="controls">
					<input type="password" class="input-xlarge" id="password" name="password"/>
					<input type="hidden" name="randomNum" value="<%=new Random().nextInt() %>"/>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-primary" id="addProjectBut">确定</button>
		    </div>
		</form>
	</div>
</body>
</html>