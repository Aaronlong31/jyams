<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<style type="text/css">
option{
height:20px;
margin-top: 2px;
}
label.error{
	display:inline;
}
</style>
<title>人员列表</title>
</head>
<body>
<div class="content">
	<div class="main_panel">
		<h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a>用户列表
		</h3>
		<div class="main_content">
			<div class="sub_panel">
				<h4><div class="tittle">搜索选项<div class="tittle_end"></div></div><div class="close_panel"></div></h4>
				<div class="main_content">
					<form  action="list.action" method="post" id="searchForm">
	                    <ul class="search_bar">
		                    <li>
		                    	<label for="usernameLike">用户名：</label>
		                    	<input name="usernameLike" class="text" id="usernameLike" value="<s:property value="usernameLike"/>"/>
		                    </li>
		                    <li>
		                    	<label for="status">状态：</label>
		                    	<select id="status" name="status">
		                    		<option value="">请选择</option>
		                    		<option value="1">激活</option>
		                    		<option value="2">未激活</option>
		                    	</select>
		                    </li>
	                    </ul>
	                    <div style="float:left;margin-top:10px;"><input type="submit" class="btn" value="搜索"/></div>
                    </form>
				</div>
			</div>
			<div class="table_bar">
				<form action="add.action" method="post" id="selectPersonForm">
	            	<input name="user.username" id="username" type="hidden" value=""/>
	            	<label for="userId">选择员工：</label>
	            	<select name="user.userId" id="userId">
	            		<option value="">请选择</option>
	            	</select>
	            	<button type="submit" class="btn4">添加</button>
				</form>
            </div>
			<table class="table_normal">
				<thead>
					<tr>
						<th style="width:15%;text-align:center;">用户编号</th>
						<th style="width:15%;text-align:center;">用户名</th>
						<th style="width:15%;text-align:center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="users">
						<tr>
							<td style="text-align: center;"><a href="view.action?userId=${userId}&flag=1"><s:property value="userId"/></a></td>
							<td style="text-align: center;"><s:property value="username"/></td>
							<td style="text-align: center;">
								<a href="input.action?userId=${userId}">修改</a>
								<a href="delete.action?userId=${userId}">删除</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount">${totalCount}</b>条记录                        每页<b style="color:red;">${pageSize}</b></span>条</div>  
							<j:pages currentPage="%{currentPage}" cssClass="pagnation" totalPage="%{totalPage}" url="" showPageNumber="5">
								<s:param name="usernameLike" value="usernameLike"/>
								<s:param name="status" value="status"/>
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
$(function(){
	$.postJSON("listNotUserPersons.action",function(data){
		var persons = data.persons;
		$.each(persons, function(i, person){
			$("<option value="+person.personId+">"+person.personName+"</option>").appendTo($("#userId"));
		});
		$("#userId").change(function(){
			$("#username").val($("#userId option:selected").text());	
		});
	});
	$("#selectPersonForm").validate({
		rules:{
			"user.userId":{
				required:true
			}
		},
		messages:{
			"user.userId":{
			required:"请选择员工！"
			}
		}
	});
});
</script>
</html>