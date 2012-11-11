<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加人员</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
<style type="text/css">
ul,li{list-style-type:square;padding: 1px 0 3px 10px;}
.checkbox{margin-right: 3px;}
</style>
</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a>修改密码
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<form action="modifyPassword.action" method="post" id="modifyPasswordForm">
				        <table class="table_normal">
							<tr>
								<td class="td_first">
									<label for="password">旧密码：</label>
								</td>
								<td>
									<input name="password" class="text" id="password" type="password"/>
									<label class="error"><s:fielderror fieldName="password"/></label>
								</td>
							</tr>
							<tr>
								<td class="td_first">
									<label for="newPassword">新密码：</label>
								</td>
								<td>
									<input name="newPassword" class="text" id="newPassword" type="password"/>
								</td>
							</tr>
							<tr>
								<td class="td_first">
									<label for="confirmPassword">确认密码：</label>
								</td>
								<td>
									<input name="confirmPassword" class="text" id="confirmPassword" type="password"/>
								</td>
							</tr>
				        </table>
						<div style=" text-align:center; margin-top:10px;margin-bottom:10px;">
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
		$("#modifyPasswordForm").validate({
			rules:{
				"password":{
					required: true,
					minlength : 6,
					maxlength : 16
				},
				"newPassword":{
					required: true,
					minlength : 6,
					maxlength : 16
				},
				"confirmPassword":{
					equalTo : "#newPassword"
				}
			},
			messages :{
				"password":{
					required: "请输入旧密码",
					minlength : "最短6位",
					maxlength : "最长16位"
				},
				"newPassword":{
					required: "请输入新密码",
					minlength : "最短6位",
					maxlength : "最长16位"
				},
				"confirmPassword":{
					equalTo : "确认密码需与新密码一致"
				}
			}
		});
	});
</script>
</html>