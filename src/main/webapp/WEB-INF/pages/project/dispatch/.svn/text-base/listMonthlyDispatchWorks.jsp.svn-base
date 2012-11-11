<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看员工当月派工</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script>
<style type="text/css">
.ui-datepicker-calendar,
.ui-datepicker-buttonpane{display: none}
</style>
</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">派工</a>查看员工每月派工
		</h3>
		<div class="main_content two_rows">
			</div>
				<div class="main_content">
					<div class="tableBox" style="top:48px">
					<table class="table_normal">
						<caption>
							<span style="font-size: 20px;vertical-align: middle;">员工<s:property value="person.personName"/>的</span>
							<input id="month" name="month" style="width: 70px;font-size: 18px;height: 24px;color:red;border: none;cursor: pointer;" readonly="readonly" value="<s:property value="month"/>"/>
							<span style="font-size: 20px;vertical-align: middle;">派工清单</span>
							<br/>
							<span style="font-size: 12px;">（提示：要查看其他月份的派工，请点击上面的红色时间进行选择。）</span>
						</caption>
						<thead>
							<tr>
								<th>月工资：</th>
								<th colspan="5">${totalSalary}￥</th>
							</tr>
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
	$("#month").datepicker({
		showButtonPanel : true,
		showClearButton : true,
		//clearText : '清除',
		closeText : '关闭',
		currentText : '本月',
		maxDate : new Date(),
		yearRange : '-80:+0',
		changeYear : true,
		changeMonth : true,
        dateFormat:'yy-mm',
        monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        onChangeMonthYear : function (year, month, inst){
			var ym = year + "-" + (month > 9 ? month : "0" + month);
			if(ym != "${month}")
				window.location.href = "listMonthDispatchWorks.action?month=" + ym + "&personId=${person.personId}";
			$(this).datepicker( "hide");
		}
	});
});
</script>
</html>