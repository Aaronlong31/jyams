<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户商列表</title>
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
			当前位置：<a href="#">首页</a><a href="#">客户管理</a>客户列表
		</h3>
		<div class="main_content">
			<div class="sub_panel">
				<h4><div class="tittle">搜索选项<div class="tittle_end"></div></div><div class="close_panel"></div></h4>
				<div class="main_content">
				<form  action="list.action" method="post" id="searchForm">
				 <ul class="search_bar">
					<li>
		                <label for="clientNameLike">客户商名称：</label>
		                <input name="clientNameLike" id="clientNameLike" class="text" value="<s:property value="clientNameLike"/>"/>
		            </li>
		         </ul>
		         <div style="float:left;margin-top:10px;"><input type="submit" class="btn" value="搜索"/></div>
		        </form>
				</div>
			</div>
			<p class="table_bar">
            	<button type="button" onclick="window.open('input.action', 'content')" class="btn4">添加</button>
            </p>
			<table class="table_normal">
				<thead>
					<tr>
						<th style="width:4%;text-align:center;">序号</th>
						<th style="width:16%;text-align:center;">客户商名称</th>
						<th style="width:24%;text-align:center;">地址</th>
						<th style="width:16%;text-align:center;">联系电话</th>
						<th style="width:20%;text-align:center;">邮箱</th>
						<th style="width:16%;text-align:center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="clients" status="status">
						<tr>
						    <td style="text-align: center;">
								<s:property value="%{#status.index + 1}"/>
							</td>
						    <td style="text-align: left;">
								<a href="view.action?clientId=${clientId}">${clientName}</a>
							</td>
							<td style="text-align: left;">
								<s:property value="address"/>
							</td>
							<td style="text-align: center;">
								<s:property value="phone"/>
							</td>
							<td style="text-align: left;">
								<s:property value="email"/>
							</td>
							<td style="text-align: center;">
								<a href="input.action?clientId=${clientId}" style="margin-right: 20px;"><img src="../images/btn_edit.gif" alt="" />修改</a>
								<a href="javascript:void(0);" onclick="deleteClient(${clientId})"  style="margin-right: 20px;"><img src="../images/btn_del.gif" alt="" />删除</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
							<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount">${totalCount}</b>条记录                        每页<b style="color:red;">${pageSize}</b></span>条</div>  
							<j:pages currentPage="%{currentPage}" cssClass="pagnation" totalPage="%{totalPage}" url="" showPageNumber="5">
								<s:param name="clientNameLike" value="clientNameLike"/>
							</j:pages>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>

</body>
<script type="text/javascript">
function deleteClient(clientId){
	if(confirm("确认要删除该客户？删除后，与客户相关联的项目中的客户将置空。")){
		$.getJSON("delete.action", {"clientId":clientId}, function(data){
			if(data){
				alert("删除成功！");
				window.location.reload();
			}
		});
	}
}
</script>
</html>