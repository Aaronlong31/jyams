<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增项目</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script>
<STYLE type="text/css">
#searchDiv{position: fixed;display: none;top:67px;left:10px;}
</STYLE>
</head>

<body>
<div class="content">
	<div class="main_panel">
		<h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt=""/>
			当前位置：<a href="#">项目</a>派工列表
		</h3>
		<div class="main_content">
			<div class="pagehead">
         		<button id="alertSearch" class="btn">点此搜索</button>
         		<span>共<b style="color:red;" id="totalCount">${totalCount}</b>条记录，每页<b style="color:red;">${pageSize}</b>条</span>
			</div>
			<j:pages currentPage="%{currentPage}" cssClass="pagnation" totalPage="%{totalPage}" url="" showPageNumber="10">
				<s:param name="projectId" value="projectId"/>
				<s:param name="dispatchDay" value="dispatchDay"/>
				<s:param name="personName" value="personName"/>
				<s:param name="month" value="month"/>
			</j:pages>   
			<div class="table_head">
			<table class="table_normal" style="width: 1000px;">
				<tfoot>
					<tr>
						<th style="width:50px;text-align:center;">序号</th>
						<th style="width:100px;text-align:center;">日期</th>
						<th style="width:100px;text-align:center;">员工名称</th>
						<th style="width:100px;text-align:center;">项目编号</th>
						<th style="width:300px;text-align:center;">项目名称</th>
						<th style="width:100px;text-align:center;">派工类型</th>
						<th style="width:100px;text-align:center;">施工开始时间</th>
						<th style="width:100px;text-align:center;">施工结束时间</th>
						<th style="width:100px;text-align:center;">有效时间</th>
					</tr>
				</tfoot>
			</table>
			</div>  
            <div class="tableBox">
			<table class="table_normal" style="width: 1000px;">
				<thead>
					<tr>
						<th style="width:50px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:300px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="dispatchWorks" status="status">
						<tr title="<s:property value="projectName"/>">
							<td style="text-align: center;"><s:property value="%{#status.index + 1 + (currentPage - 1) * pageSize}"/></td>
							<td style="text-align: center;"><j:intDayFormat value="dispatch.dispatchDay"/></td>
							<td style="text-align: center;"><s:property value="personName"/></td>
							<td style="text-align: center;"><s:property value="dispatch.projectId"/></td>
							<td style="text-align: left;"><s:property value="dispatch.projectName"/></td>
							<td style="text-align: center;"><s:property value="dispatch.dispatchTypeString"/></td>
							<td style="text-align: center;"><j:minuteFormat value="startTime"/></td>
							<td style="text-align: center;"><j:minuteFormat value="endTime"/></td>
							<td style="text-align: center;"><s:property value="hours"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			</div>
		</div>
	</div>
</div>
<div id="searchDiv" class="sub_panel">
	 <h4 class="pending widget-header"><div class="tittle">搜索选项<div class="tittle_end"></div></div><div class="close_search"></div></h4>
	<form  action="list.action" method="post" id="searchForm">
		<table class="table_normal">
			<tr>
				<td align="right"><label for="projectId">项目编号：</label></td>
				<td>
					<select name="projectId" id="projectId">
                   		<option value="">请选择</option>
                   	</select>
        		</td>
			</tr>
			<tr>
				<td align="right"><label for="dispatchDay">派工日期：</label></td>
				<td>
					<input name="dispatchDay" id="dispatchDay" class="text" value='<s:property value="dispatchDay"/>'/>
				</td>
			</tr>
			<tr>
				<td align="right"><label for="personName">员工：</label></td>
				<td>
					<select name="personName" id="personName">
                   		<option value="">请选择</option>
                   	</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label for="month">派工月份<br/>（选择这个月中任一天即可）：</label></td>
				<td>
					<input name="month" id="month" class="text" value='<s:property value="month"/>'/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" class="btn" value="搜索"/>
				</td>
			</tr>
		</table>
      </form>
</div>
</body>
<script type="text/javascript">
$(function(){
	$("#dispatchDay").datepicker({ 
		showButtonPanel:true,
		showClearButton:true,
		clearText: '清除', 
		closeText: '关闭', 
		currentText: '今天',
		yearRange:'-80:+80',
		changeYear:true,
		changeMonth:true,
        dateFormat:'yy-mm-dd',
        onSelect: function(){
			$("#month").val("");
		}
    }); 
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
		dateFormat : 'yy-mm',
        onSelect: function(){
			$("#dispatchDay").val("");
		}
    }); 
	$.postJSON("../person/listPersonsSimple_JSON.action",function(data){
		$.each(data.persons, function(i,person){
			$("<option value="+person.personName+">"+person.personName+"</option>").appendTo($("#personName"));
		});
	});
	$.postJSON("../project/listProjectIds_JSON.action",function(data){
		$.each(data.projectIds, function(i,projectId){
			$("<option value="+projectId+">"+projectId+"</option>").appendTo($("#projectId"));
		});
	});
});
</script>
</html>