<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报价单</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/convertCurrency.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</head>

<body>

<div class="content">
	<div class="main_panel">
		<h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">报价单</a>报价单列表
		</h3>
		<div class="main_content">
			<div class="sub_panel">
				<h4><div class="tittle">搜索选项<div class="tittle_end"></div></div><div class="close_panel"></div></h4>
				<div class="main_content">
					<form  action="list.action" method="post" id="searchForm">
	                    <ul class="search_bar">
		                    <li>
		                    	<label for="quoterName">报价人：</label>
		                    	<select id="quoterName" name="quoterName">
		                    		<option value="">请选择</option>
		                    		<s:iterator value="quoters">
		                    			<option value="${personId}"><s:property value="personName"/></option>
		                    		</s:iterator>
		                    	</select>
		                    </li>
		                    <li>
		                    	<label for="id">报价单编号：</label>
		                    	<input id="id" name="id" class="text"/>
		                    </li>
		                    <li>
		                    	<label>联系人：</label>
		                    	<input id="attnName" name="attnName" value="" class="text"/>
		                    </li>
		                    <li>
		                    	<label>开始时间：</label>
		                    	<input id="startTime" name="startTime" value="" class="text"/>
		                    </li>
		                    <li>
		                    	<label>结束时间：</label>
		                    	<input id="endTime" name="endTime" value="" class="text"/>
		                    </li>
	                    </ul>
	                    <div style="float:left;margin-top:10px;"><input type="submit" class="btn" value="搜索"/></div>
                    </form>
				</div>
			</div>
			<p class="table_bar">
            	<button type="button" onclick="window.open('input.action', 'content')" class="btn4">填写报价单</button>
            	<button type="button" onclick="window.open('toImport.action', 'content')" class="btn4">导入报价单</button>
            </p>
			<table class="table_normal">
				<thead>
					<tr>
						<th style="width:6%;text-align:center;">报价单编号</th>
						<th style="width:25%;text-align:center;">标题</th>
						<th style="width:16%;text-align:center;">单位</th>
						<th style="width:16%;text-align:center;">联系人</th>
						<th style="width:7%;text-align:center;">报价人</th>
						<th style="width:10%;text-align:center;">报价时间</th>
						<th style="width:9%;text-align:center;">总价</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="quotes">
						<tr>
							<td style="text-align: center;"><a href="view.action?quoteId=${quoteId}&version=${version}"><s:property value="id"/></a></td>		
							<td style="text-align: left;"><s:property value="subject"/></td>		
							<td style="text-align: left;"><s:property value="clientName"/></td>		
							<td style="text-align: left;"><s:property value="attnName"/></td>		
							<td style="text-align: center;"><s:property value="quoterName"/></td>		
							<td style="text-align: center;"><j:dateTimeFormat type="long" pattern="yyyy-MM-dd" value='createdTimestamp'/></td>		
							<td style="text-align: right;"><s:property value="totalPrice"/>元</td>		
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount">${totalCount}</b>条记录                        每页<b style="color:red;">${pageSize}</b></span>条</div>  
							<j:pages currentPage="%{currentPage}" cssClass="pagnation" totalPage="%{totalPage}" url="" showPageNumber="5">
								<s:param name="quoterName" value="quoterName"/>
								<s:param name="id" value="id"/>
								<s:param name="startTime" value="startTime"/>
								<s:param name="endTime" value="endTime"/>
								<s:param name="attnName" value="attnName"/>
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
$(function() {
	$.postJSON("../quote/listIds_JSON.action",function(data){
		$("#id").autocomplete(data.ids,{
			matchContains : true,
			autoFill: true,
			minChars : 0
		});
	});
	$("#startTime, #endTime").datepicker({
		showButtonPanel : true,
		showClearButton : true,
		clearText : '清除',
		closeText : '关闭',
		currentText : '今天',
		maxDate : new Date(),
		yearRange : '-20:+20',
		changeYear : true,
		changeMonth : true,
		dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
		dateFormat : 'yy-mm-dd',
		monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月',
				'八月', '九月', '十月', '十一月', '十二月' ],
		monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月',
				'七月', '八月', '九月', '十月', '十一月', '十二月' ]
	});
});
</script>
</html>