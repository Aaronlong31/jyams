<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.ui.datepicker.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery-ui-1.8.2.custom.css" />
<script language="javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>

<script language="javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.core.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.datepicker.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.widget.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.bgiframe.min.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.ajaxQueue.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.autocomplete.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>


<title>派工人员考勤列表</title>
</head>
<body>

<div class="content">
	<div class="main_panel">
		<h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a>查询施工人员考勤信息
		</h3>
		<div class="main_content">
			<div class="sub_panel">
				<h4><div class="tittle">搜索选项<div class="tittle_end"></div></div><div class="close_panel"></div></h4>
				<form  action="list.action" method="post" id="searchForm">
	                    <ul class="search_bar">
		                    <li>
		                    	<span>项目名称：</span>
		                    		<input name="projectName" id="projectName" class="text"/>
									<input type="hidden" name="projectId" id="projectId"/>
		                    </li>
		                    </ul>
		                    <div style="float:left;margin-top:10px;"><input type="submit" class="btn" value="搜索"/></div>
		                    </form>
			</div>
		</div>
		
		<p class="table_bar">
				<a href="../reportExcel.action?model=3">生成报表</a>
            </p>
            
            <table class="table_normal">
				<thead>
					<tr>
						<th style="width:11%;text-align:center;">项目名称</th>
						<th style="width:11%;text-align:center;">负责人姓名</th>
						<th style="width:11%;text-align:center;">施工人姓名</th>
						<th style="width:11%;text-align:center;">施工人类型</th>
						<th style="width:11%;text-align:center;">施工类型</th>
						<th style="width:11%;text-align:center;">施工日期</th>
						<th style="width:11%;text-align:center;">施工开始时间</th>
						<th style="width:11%;text-align:center;">施工结束时间</th>
						<th style="width:11%;text-align:center;">临时员工薪水</th>
					</tr>
				</thead>
				
				<tbody>
					<s:iterator value="dispatchs" status="status" id = "d">
						<tr>
							<td style="text-align: left;">
								${projectName}
							</td>
							<td style="text-align: left;">
								${principalName}
							</td>
							<td style="text-align: left;">${personName}</td>
							<td style="text-align: left;">${personTypeString}</td>
							<td style="text-align: left;">${dispatchTypeString}</td>
							<td style="text-align: left;">${dispatchDay}</td>
							<td style="text-align: left;">${startTime}</td>
							<td style="text-align: left;">${endTime}</td>
							<td style="text-align: left;">
								<s:if test="#d.personType == 1">
									公司内部员工
								</s:if>
								<s:elseif test="#d.personType == 2">
									${tempPersonSalary}
								</s:elseif>
							</td>
							
							<td>
								<input type="hidden" name="projectId" value="${projectId}">
							</td>
						</tr>
					</s:iterator>
					
					</tbody>
				</table>
				
		</div>
</body>

<script>
$(function(){
	$.postJSON("../project/listBuilding_JSON.action", function(data){
		$("#projectName").autocomplete(data.projects,{
			selectFirst : true,
			autoFill: true,
			minChars : 0,
			formatItem: function(row){
				return "客户：" + ((row.clientName.length > 0) ? row.clientName : "(无)")
					 + "; 项目名称：" + row.projectName;
			},
			formatResult: function(row){
				return row.projectName;
			},
			formatMatch : function(row){
				return row.projectName;
			}
		}).result(function(event, data, formatted){
			$("#projectId").val( data ? data.projectId : "");
		});
	});
})
</script>
</html>