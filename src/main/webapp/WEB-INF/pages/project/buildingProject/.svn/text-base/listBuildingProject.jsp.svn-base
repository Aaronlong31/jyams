<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增项目</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/convertCurrency.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<STYLE type="text/css">
#searchDiv{position: fixed;display: none;top:67px;left:10px;}
</STYLE>
</head>
<body>
<div class="content">
	<div class="main_panel">
		<h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">项目</a>在建项目列表
		</h3>
		<div class="main_content">
			<div class="pagehead">
         		<button id="alertSearch" class="btn">点此搜索</button>
         		<span>共<b style="color:red;" id="totalCount">${totalCount}</b>条记录，每页<b style="color:red;">${pageSize}</b>条</span>
			</div>
			<j:pages currentPage="%{currentPage}" cssClass="pagnation" totalPage="%{totalPage}" url="" showPageNumber="5">
				<s:param name="companyPrincipalName" value="companyPrincipalName"/>
				<s:param name="clientName" value="clientName"/>
				<s:param name="clientPrincipalName" value="clientPrincipalName"/>
				<s:param name="status" value="status"/>
			</j:pages>  
			<div class="table_head">
			<table class="table_normal" style="width: 1740px;">
				<tfoot>
					<tr>
						<th style="width:80px;text-align:center;">项目编号</th>
						<th style="width:240px;text-align:center;">项目名称</th>
						<th style="width:240px;text-align:center;">客户商</th>
						<th style="width:100px;text-align:center;">客户负责人</th>
						<th style="width:100px;text-align:center;">公司负责人</th>
						<th style="width:100px;text-align:center;">施工地点</th>
						<th style="width:100px;text-align:center;">订单编号</th>
						<th style="width:100px;text-align:center;">要求完工日</th>
						<th style="width:100px;text-align:center;">可延后时间</th>
						<th style="width:130px;text-align:center;">预估成本（RMB）</th>
						<th style="width:130px;text-align:center;">实际成本（RMB）</th>
						<th style="width:130px;text-align:center;">合同总价（RMB）</th>
						<th style="width:90px;text-align:center;">利润率</th>
						<th style="width:100px;text-align:center;">操作</th>
					</tr>
				</tfoot>
			</table>
			</div>  
			<div class="tableBox absoluteTable">
			<table class="table_normal" style="width: 1740px;">
				<thead>
					<tr>
						<th style="width:80px;text-align:center;"></th>
						<th style="width:240px;text-align:center;"></th>
						<th style="width:240px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:130px;text-align:center;"></th>
						<th style="width:130px;text-align:center;"></th>
						<th style="width:130px;text-align:center;"></th>
						<th style="width:90px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="buildingProjects">
						<tr>
							<td style="text-align: center;">
								<a href="view.action?projectId=${projectId}">${projectId}</a>
							</td>
							<td style="text-align: left;"><s:property value="project.projectName"/></td>
							<td style="text-align: left;"><s:property value="project.clientName"/></td>
							<td style="text-align: center;"><s:property value="project.clientPrincipalName"/></td>
							<td style="text-align: center;"><s:property value="project.companyPrincipalName"/></td>
							<td style="text-align: center;"><s:property value="project.constructPlace"/></td>
							<td style="text-align: center;"><s:property value="project.orderId"/></td>
							<td style="text-align: center;"><j:intDayFormat value="project.requiredCompletionDate"/></td>
							<td style="text-align: right;"><s:property value="project.canDelayDay"/>天</td>
							<td style="text-align: right;"><s:property value="estimateCost"/>元</td>
							<td style="text-align: right;"><s:property value="actualCost"/>元</td>
							<td style="text-align: right;"><s:property value="contractPrice"/>元</td>
							<td style="text-align: right;"><s:property value="margin"/></td>
							<td style="text-align: center;">
								<a href="input.action?projectId=${projectId}">修改</a>
							</td>
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
				<td align="right"><label for="companyPrincipalName">施工负责人：</label></td>
				<td>
					<select name="companyPrincipalName" id="companyPrincipalName">
		        		<option value="">请选择</option>
		        	</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label for="clientName">客户商：</label></td>
				<td>
					<select name="clientName" id="clientName">
		        		<option value="">请选择</option>
		        	</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label for="clientPrincipalName">客户负责人：</label></td>
				<td>
					<select name="clientPrincipalName" id="clientPrincipalName">
		        		<option value="">请选择</option>
		        	</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label for="status">状态：</label></td>
				<td>
					<select name="status" id="status">
		        		<option value="">请选择</option>
		        		<option value="1">在建</option>
		        		<option value="2">延后</option>
		        		<option value="4">报警</option>
		        		<option value="8">超支</option>
		        		<option value="16">完工</option>
		        		<option value="32">已开票</option>
		        		<option value="64">已收款</option>
		        	</select>
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
$(function() {
	$.postJSON("../person/listPersonsSimple_JSON.action",function(data){
		$.each(data.persons, function(i,person){
			$("<option value="+person.personName+">"+person.personName+"</option>").appendTo($("#companyPrincipalName"));
		});
	});
	$.postJSON("../client/listClients_JSON.action",{},function(data){
		$.each(data.clients, function(i,client){
			$("<option value="+client.clientName+">"+client.clientName+"</option>").appendTo($("#clientName")).data("principals", client.principals);
		});
		$("#clientName").change(function(){
			$("#clientPrincipalName").empty().append($("<option value=''>请选择</option>"));
			var principals = $("#clientName option:selected").data("principals");
			if(principals instanceof Array){
				$.each(principals, function(i, principal){
					if(principal.name != null){
						$("<option value="+principal.name+">"+principal.name+"</option>").appendTo($("#clientPrincipalName"));
					}
				});
			}
		});
	});	
	$.postJSON("../project/listProjectIds_JSON.action",function(data){
		$.each(data.projectIds, function(i,projectId){
			$("<option value="+projectId+">"+projectId+"</option>").appendTo($("#projectId"));
		});
	});
	$("#status").children().each(function(){
		if($(this).val() == "${status}"){
			$(this).attr("selected", "selected");
		}
	});
});

</script>
</html>