<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加客户商</title>
<link rel="stylesheet" type="text/css" href="../css/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.ui.datepicker.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery-ui-1.8.2.custom.css" />
<script language="javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.core.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.datepicker.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.widget.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.bgiframe.min.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.ajaxQueue.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/thickbox-compressed.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.autocomplete.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>

</head>

<body>
<div>
<h2>增加客户</h2>
<s:form action="save" method="post" id="addClientForm">
	<table>
		<tr>
			<td>客户商名称:
				<input name="client.clientName" id="clientName"></input>
			</td>
		</tr>
		
		<tr>
			<td>地址:
				<input name="client.address" id="address"></input>
			</td>
		</tr>
		
		<tr>
			<td>联系电话:
				<input name="client.phone" id="phone"></input>
			</td>
		</tr>
		
		<tr>
			<td>邮箱:
				<input name="client.email" id="email" ></input>
			</td>
		</tr>
		
		<tr>
			<td>
				<input type="submit" name="submit" id="add" value="增加"></input>
				<input type="button" name = "reset" value="重置"></input>
			</td>
		</tr>
		</table>
		</s:form>
		</div>
</body>
<script language="javascript">
$(function() {
	$("#addClientForm").validate({
		onfocusout:true,
		rules:{
			"client.clientName" : {
				required : true,
				minlength : 2
			},
			"client.address": {
				required:true,
	  			minlength:6
			},
			"client.phone":{
				required:true,
				number:true,
				minlength:11
			},
			"client.email":{
				required:true,
				email:true
			}
			
		},
		messages:{
			"client.clientName":{
		  		required : '客户商名称是必须的',
		  		minlength : "客户名称至少要2个字符"
	  		},
	  		"client.address":{
	  			required: '地址是必须的',
	  			minlength : "地址至少要6个字符"
	  		},
	  		"client.phone":{
	  			required:'手机号是必须的',
				number:'手机格式必须是数字',
				length:'手机长度是11位'
	  		},
	  		"client.email":{
				required:'邮箱是必须的',
				email:'请输入正确的邮箱格式，比如myemail@163.com'
	  		}
		}		
	});
	
});
</script>
</html>