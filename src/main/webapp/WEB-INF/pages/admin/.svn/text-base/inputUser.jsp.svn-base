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
			当前位置：<a href="#">首页</a>编辑用户
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<form action="save.action" method="post" id="addUserForm">
						<input type="hidden" name="user.userId" value='<s:property value="user.userId"/>'/>
				        <table class="table_normal">
							<tr>
								<td class="td_first">
									<label for="username">用户名：</label>
								</td>
								<td>
									<s:property value="user.username"/>
									<input name="user.username" type="hidden" class="text" id="username" value="<s:property value="user.username"/>"/>
								</td>
							</tr>
							<tr>
								<td class="td_first">
									<label for="password">密码：</label>
								</td>
								<td>
									<input name="user.password" class="text" id="password" type="password"/>
									<label>若不填写密码，则表示不修改密码。</label>
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
							<tr>
								<td class="td_first">
									<label>选择状态：</label>
								</td>
								<td>
									<input name="user.status" id="activeStatus" value="1" type="radio"/><label for="activeStatus">激活</label>
									<input name="user.status" id="inActiveStatus" value="2" type="radio"/><label for="inActiveStatus">未激活</label>
								</td>
							</tr>
							<tr>
								<td class="td_first"><label>选择权限：</label></td>
								<td>
									<div>
										<span id="userAuthorityIds" style="display: none;">
											<s:if test="user.authorities.size > 0">
												<s:property value="user.authorities.{authorityId}"/>
											</s:if>
										</span>
										<!-- 
										 <ul style="list-style-type: none;">
											<s:iterator value="authorities">
												<li style="float: left;width: 30%;">
													<input type="checkbox" id='authorityId<s:property value="authorityId"/>' name="authorityIds" value="${authorityId}" style="margin-right: 3px;"/><label><s:property value="name"/></label>
												</li>
											</s:iterator>
										</ul>
										 -->
										<ul>
											<s:iterator value="modules" var="suModule">
											<li>
												<input type="checkbox" class="checkbox"/><s:property value="#suModule.ModuleName"/>
												<ul>
													<s:iterator value="#suModule.modules">
													<li>
														<input type="checkbox" class="checkbox"/><s:property value="moduleName"/>
														<ul>
															<s:iterator value="authorities">
															<li>
																<input type="checkbox" id='authorityId<s:property value="authorityId"/>' name="authorityIds" value="${authorityId}" class="checkbox"/><s:property value="name"/>
															</li>
															</s:iterator>
														</ul>
													</li>
													</s:iterator>
													<s:iterator value="#suModule.authorities">
													<li>
														<input type="checkbox" id='authorityId<s:property value="authorityId"/>' name="authorityIds" value="${authorityId}"  class="checkbox"/><s:property value="name"/>
													</li>
													</s:iterator>
												</ul>
											</li>
											</s:iterator>
										</ul>
									</div>
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
		$("input:checkbox").click(function(){
			if(this.checked){
				$(this).next().find(":checkbox").attr("checked", "checked");
				$(":checkbox").each(function(){
					if($(this).next().find(":checked").size() > 0){
						this.checked = true;
					}
				});
			} else {
				$(this).next().find(":checkbox").attr("checked", "");
			}
		});
		$("#addUserForm").validate({
			rules:{
				"user.password":{
					minlength : 6,
					maxlength : 16
				},
				"confirmPassword":{
					equalTo : "#password"
				}
			},
			messages :{
				"user.password":{
					minlength : "最短6位",
					maxlength : "最长16位"
				},
				"confirmPassword":{
					equalTo : "确认密码需与密码一致"
				}
			}
		});
		$("[name='user.status'][value='${user.status}']").attr("checked", "checked");
		$("#cancel").click(function(){
			history.back();
		});
		if($("#userAuthorityIds").text() != ""){
			$.each(eval("(" + $("#userAuthorityIds").text() + ")"), function(i, authorityId){
				$("#authorityId" + authorityId).attr("checked", "checked");
			});
			$(":checkbox").each(function(){
				if($(this).next().find(":checked").size() > 0){
					this.checked = true;
				}
			});
		}
		
	});
</script>
</html>