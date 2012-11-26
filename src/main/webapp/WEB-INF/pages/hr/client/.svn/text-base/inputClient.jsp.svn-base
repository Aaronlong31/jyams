<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加客户商</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>

</head>

<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">部门/人员管理</a>编辑客户
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<form action="save.action" method="post" id="addClientForm">
						<input type="hidden" name="client.clientId" value='<s:property value="client.clientId"/>'/>
						<input type="hidden" name="client.priority" value='<s:property value="client.priority"/>'/>
				        <table class="table_normal">
							<tr>
								<td class="td_first"><label for="clientName">客户商名称:<span class="red_star">*</span></label></td>
								<td>
									<input class="text" name="client.clientName" id="clientName" value='<s:property value="client.clientName"/>'/>
								</td>
							</tr>
							<tr>
								<td class="td_first"><label for="address">地址:</label></td>
								<td>
									<input class="text" name="client.address" id="address" value='<s:property value="client.address"/>'/>
								</td>
							</tr>
							<tr>
								<td class="td_first"><label for="phone">联系电话:</label></td>
								<td>
									<input class="text" name="client.phone" id="phone" value='<s:property value="client.phone"/>'/>
								</td>
							</tr>
							<tr>
								<td class="td_first"><label for="email">邮箱:</label></td>
								<td>
									<input class="text" name="client.email" id="email"  value='<s:property value="client.email"/>'/>
								</td>
							</tr>
						</table>
						<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
							<button type="submit" class="btn" id="addPersonBut">确定</button>
							<button type="button" class="btn" id="cancel">取消</button>
						</div>
					</form>
			    </div>
			</div>
		</div>
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
			"client.phone":{
				minlength:3
			},
			"client.email":{
				email:true
			}
		},
		messages:{
			"client.clientName":{
		  		required : '客户商名称是必须的',
		  		minlength : "客户名称至少要2个字符"
	  		},
	  		"client.phone":{
				length:'手机长度是3位'
	  		},
	  		"client.email":{
				email:'请输入正确的邮箱格式，比如myemail@163.com'
	  		}
		}		
	});
	$("#cancel").click(function(){
		history.back();
	});
});
</script>
</html>