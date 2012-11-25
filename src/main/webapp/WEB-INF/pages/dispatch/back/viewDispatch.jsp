<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看派工</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script>
</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">项目</a>查看派工
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<table class="table_normal">
						<tr>
							<td class="td_first">项目编号:</td>
							<td><s:property value="dispatch.projectId" /></td>
							<td class="td_first">项目名称:</td>
							<td><s:property value="dispatch.projectName" /></td>
						</tr>
						<tr>
							<td class="td_first">项目类型:</td>
							<td><s:property value="dispatch.projectTypeString" /></td>
							<td class="td_first">派工类型:</td>
							<td><s:property value="dispatch.dispatchTypeString" /></td>
						</tr>
						<tr>
							<td class="td_first">派工负责人:</td>
							<td><s:property value="dispatch.principalName" /></td>
							<td class="td_first">派工总花费（RMB）:</td>
							<td><s:property value="dispatch.cost"/>元</td>
						</tr>
						<tr>
							<td class="td_first">派工日期:</td>
							<td><j:intDayFormat value="dispatch.dispatchDay" /></td>
							<td class="td_first">创建时间:</td>
							<td><j:dateTimeFormat value="dispatch.createdTimestamp"/></td>
						</tr>
					</table>
				</div>
			</div>
				<div class="main_content">
					<table class="table_normal">
						<thead>
							<tr>
								<th style="width:4%;text-align:center;">序号</th>
								<th style="width:15%;text-align:center;">施工人员</th>
								<th style="width:15%;text-align:center;">施工人员类型</th>
								<th style="width:15%;text-align:center;">身份证号码</th>
								<th style="width:15%;text-align:center;">日薪制薪水</th>
								<th style="width:15%;text-align:center;">施工开始时间</th>
								<th style="width:15%;text-align:center;">施工结束时间</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="dispatch.dispatchWorks" status="sta">
								<tr>
									<td style="text-align: center;"><s:property value="%{#sta.index + 1}"/></td>
									<td style="text-align: center"><s:property value="personName"/></td>
									<td style="text-align: center"><s:property value="personTypeString"/></td>
									<td style="text-align: center"><s:property value="idCardCode"/></td>
									<td style="text-align: right;"><s:property value="salary"/>元</td>
									<td style="text-align: center"><j:minuteFormat value="startTime"/></td>
									<td style="text-align: center"><j:minuteFormat value="endTime"/></td>
								</tr>
							</s:iterator>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="11">
									<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount"><s:property value="dispatch.dispatchWorks.size"/></b>条记录 </span></div>  
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
</body>
</html>