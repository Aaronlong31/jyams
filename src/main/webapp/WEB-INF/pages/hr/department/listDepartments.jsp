<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门列表</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
</head>

<body>
<div class="content">
<div class="main_panel">
		<h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">部门</a>部门列表
		</h3>
		<div class="main_content">
			<p class="table_bar">
            	<button type="button" onclick="window.open('input.action', 'content')" class="btn4">添加部门</button>
            </p>
			<table  class="table_normal">
				<thead>
					<tr>
						<th style="width: 4%;text-align: center">序号</th>
						<th style="width: 30%;text-align: center">部门名称</th>
						<!-- <th style="width: 30%;text-align: center">上级部门名称</th> -->
						<th style="width: 30%;text-align: center">部门负责人</th>
						<th style="width: 30%;text-align: center">操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="departments" status="sta">
						<tr>
						    <td style="text-align: center;"><s:property value="%{#sta.index + 1}"/></td>
						    <td style="text-align: center;"><s:property value="departmentName"/></td>
							<td style="text-align: center;"><s:property value="principalName"/></td>
							<td style="text-align: center;">
								<a href="input.action?departmentId=${departmentId}">修改</a>
								<a href="javascript:void(0);" onclick="deleteDepart(${departmentId})">删除</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount"><s:property value="departments.size"/></b>条记录 </span></div>  
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function deleteDepart(departmentId){
	if(confirm("确认要删除该部门？")){
		window.location.href = "delete.action?departmentId=" + departmentId;
	}
}
</script>
</html>