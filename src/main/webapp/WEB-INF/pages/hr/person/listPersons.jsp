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
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script></head>
<title>人员列表</title>
</head>
<body>
<div class="content">
	<div class="main_panel">
		<h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a>员工列表
		</h3>
		<div class="main_content">
			<div class="sub_panel">
				<h4><div class="tittle">搜索选项<div class="tittle_end"></div></div><div class="close_panel"></div></h4>
				<div class="main_content">
					<form  action="list.action" method="post" id="searchForm">
	                    <ul class="search_bar">
		                    <li>
		                    	<label for="departmentName">部门名称：</label>
		                    	<select id="departmentName" name="departmentName" >
		                    		<option value="">请选择</option>
									<s:iterator value="departments" var="department">
										<option value="<s:property value="#department.departmentName"/>">
											<s:property value="#department.departmentName"/>
										</option>
									</s:iterator>
								</select>
		                    </li>
		                    <li>
		                    	<label for="personNameLike">人员名称：</label>
		                    	<input name="personNameLike" class="text" id="personNameLike" value="<s:property value="personNameLike"/>"/>
		                    </li>
		                    <li>
		                    	<label for="status">状态：</label>
		                    	<select id="status" name="status">
		                    		<option value="1" <s:if test="status == 1">selected="selected"</s:if>>在职</option>
		                    		<option value="2" <s:if test="status == 2">selected="selected"</s:if>>离职</option>
		                    	</select>
		                    </li>
		                     <li>
		                    	<label for="salaryType">薪资形式：</label>
		                    	<select id="salaryType" name="salaryType">
		                    		<option value="">请选择</option>
		                    		<option value="1" <s:if test="salaryType == 1">selected="selected"</s:if>>日薪</option>
		                    		<option value="2" <s:if test="salaryType == 2">selected="selected"</s:if>>月薪</option>
		                    	</select>
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
						<th style="width:6%;text-align:center;">员工姓名</th>
						<th style="width:6%;text-align:center;">部门名称</th>
						<th style="width:8%;text-align:center;">手机号码</th>
						<th style="width:8%;text-align:center;">薪资形式</th>
						<th style="width:8%;text-align:center;">工种</th>
						<th style="width:8%;text-align:center;">状态</th>
						<th style="width:20%;text-align:center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="persons" status="status" var="person">
						<tr>
							<td style="text-align: center;"><s:property value="%{#status.index + 1 + (currentPage - 1) * pageSize}"/></td>
							<td style="text-align: center;">
								<a href="view.action?personId=${personId}&flag=1"><s:property value="personName"/></a>
							</td>
							<td style="text-align: center;"><s:property value="departmentName"/></td>
							<td style="text-align: center;"><s:property value="mobilePhone"/></td>
							<td style="text-align: center;"><s:property value="salaryTypeString"/></td>
							<td style="text-align: center;"><s:property value="jobType"/></td>
							<td style="text-align: center;" id="td${personId}">${statusString}</td>
							<td style="text-align: center;">
								<a href="input.action?personId=${personId}">修改</a>
								<s:if test="%{(status & 1) == 1}">
		                    		<a href="javascript:void(0);" onclick="dimission(${personId})">离职</a>
		                    	</s:if>
								<a href="../dispatch/listMonthDispatchWorks.action?personId=${personId}">查看当月派工</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="8">
							<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount">${totalCount}</b>条记录                        每页<b style="color:red;">${pageSize}</b></span>条</div>  
							<j:pages currentPage="%{currentPage}" cssClass="pagnation" totalPage="%{totalPage}" url="" showPageNumber="5">
								<s:param name="departmentName" value="departmentName"/>
								<s:param name="personNameLike" value="personNameLike"/>
								<s:param name="status" value="status"/>
								<s:param name="salaryType" value="salaryType"/>
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
function dimission(personId){
	if(confirm("确认要离职该员工？")){
		window.location.href = "dimission.action?personId=" + personId;
	}
}
</script>
</html>