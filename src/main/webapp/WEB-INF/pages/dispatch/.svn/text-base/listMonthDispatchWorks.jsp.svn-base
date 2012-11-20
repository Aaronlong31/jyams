<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看员工当月派工</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script>
</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">员工</a>查看员工当月派工
		</h3>
		<div class="main_content two_rows">
			</div>
				<div class="main_content">
					<div class="tableBox" style="top:48px">
					<table class="table_normal">
						<caption>员工<span style="color: red;font: inherit;"><s:property value="person.personName"/></span>的每月派工清单</caption>
						<thead>
							<tr>
								<th style="width:10%;text-align:center;">日期</th>
								<th style="width:15%;text-align:center;">项目编号</th>
								<th style="width:15%;text-align:center;">施工开始时间</th>
								<th style="width:15%;text-align:center;">施工结束时间</th>
								<th style="width:15%;text-align:center;">当日总时间</th>
								<th style="width:15%;text-align:center;">当日工资</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="dispatchWorks">
								<tr>
									<td style="text-align: center;"><j:intDayFormat value="dispatch.dispatchDay"/></td>
									<td style="text-align: center" title="<s:property value="dispatch.projectName"/>">
										<s:property value="dispatch.projectId"/>
									</td>
									<td style="text-align: center;"><j:minuteFormat value="startTime"/></td>
									<td style="text-align: center;"><j:minuteFormat value="endTime"/></td>
									<td style="text-align: right;"><s:property value="hours"/>小时</td>
									<td style="text-align: right;"><span class="cost"><s:property value="cost"/></span>元</td>
								</tr>
							</s:iterator>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
									<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount"><s:property value="dispatchWorks.size"/></b>条记录 </span></div>  
									<div id="costDiv" style=" float:right; line-height:20px;font-weight: bold;color: red">
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
$(function(){
	var cost = 0.0;
	$(".cost").each(function(){
		cost += parseFloat($(this).text());		
	});
	$("#costDiv").text("共计：" + cost + "元");
});
</script>
</html>