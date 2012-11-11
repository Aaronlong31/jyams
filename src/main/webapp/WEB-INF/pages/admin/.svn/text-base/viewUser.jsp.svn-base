<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script>
<title>人员详细信息</title>
</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a>查看操作员
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<table class="table_normal">
						<tr>
							<td class="td_first">操作员姓名:</td>
							<td><s:property value="user.username" /></td>
						</tr>
						<tr>
							<td class="td_first">状态:</td>
							<td><s:property value="user.statusString" /></td>
						</tr>
						<tr>
							<td class="td_first">角色：</td>
							<td>
								<ul style="list-style-type: none;">
									<s:iterator value="user.roles">
										<li style="float: left;width: 30%;"><s:property value="name"/></li>
									</s:iterator>
								</ul>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#modifyPersonBtn").click(function(){
			location="input.action?personId=${person.personId}";
		});
	});
	$(document).ready(function(){
		$("#dimissionPersonBtn").click(function(){
			location="dimission.action?personId=${person.personId}";
		});
	});
</script>
</html>