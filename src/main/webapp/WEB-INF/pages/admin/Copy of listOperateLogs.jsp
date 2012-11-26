<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询操作日志</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.ui.datepicker.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery-ui-1.8.2.custom.css" />
<script language="javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.core.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.datepicker.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.widget.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script>
<STYLE type="text/css">
#searchDiv{position: fixed;display: none;top:67px;left:10px;}
</STYLE>
</head>

<body>
<div class="content">
	<div class="main_panel">
		<h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a>操作日志查询
		</h3>
		<div class="main_content">
			<div class="pagehead">
         		<button id="alertSearch" class="btn">点此搜索</button>
         		<span>共<b style="color:red;" id="totalCount">${totalCount}</b>条记录，每页<b style="color:red;">${pageSize}</b>条</span>
			</div>
			<j:pages currentPage="%{currentPage}" cssClass="pagnation" totalPage="%{totalPage}" url="list.action" showPageNumber="5">
				<s:param name="operateType" value="operateType"/>
				<s:param name="operateModule" value="operateModule"/>
				<s:param name="operateModel" value="operateModel"/>
				<s:param name="operatorId" value="operatorId"/>
				<s:param name="startTime" value="startTime"/>
				<s:param name="endTime" value="endTime"/>
			</j:pages>   
			<div class="table_head">
			<table class="table_normal" style="width: 1000px;">
				<tfoot>
					<tr>
						<th style="width:40px;text-align:center;">序号</th>
						<th style="width:100px;text-align:center;">操作员</th>
						<th style="width:200px;text-align:center;">时间</th>
						<th style="width:60px;text-align:center;">类型</th>
						<th style="width:300px;text-align:center;">信息</th>
						<th style="width:100px;text-align:center;">模块</th>
						<th style="width:100px;text-align:center;">对象</th>
						<th style="width:100px;text-align:center;">IP</th>
					</tr>
				</tfoot>
			</table>
			</div>  
            <div class="tableBox">
			<table class="table_normal" style="width: 1000px;">
				<thead>
					<tr>
						<th style="width:40px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:200px;text-align:center;"></th>
						<th style="width:60px;text-align:center;"></th>
						<th style="width:300px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
						<th style="width:100px;text-align:center;"></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="operateLogs" status="status">
						<tr>
							<td style="text-align: center;">
								<s:property value="%{#status.index + 1 + (currentPage - 1) * pageSize}"/>
							</td>
							<td style="text-align: center;"><s:property value="operatorName"/></td>
							<td style="text-align: center;"><s:property value="operateTimestamp"/></td>
							<td style="text-align: center;"><s:property value="operateTypeString"/></td>
							<td style="text-align: left;"><s:property value="operateInfo"/></td>
							<td style="text-align: center;"><s:property value="operateModuleString"/></td>
							<td style="text-align: center;"><s:property value="operateModelString"/></td>
							<td style="text-align: center;"><s:property value="ip"/></td>
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
				<td align="right"><label for="operateType">操作类型：</label></td>
				<td>
					<select name="operateType" id="operateType">
                  		<option value="">请选择</option>
                  		<option value="1">增加</option>
                  		<option value="2">修改</option>
                  		<option value="3">删除</option>
                  		<option value="4">登录</option>
                  	</select>
        		</td>
			</tr>
			<tr>
				<td align="right"><label for="operateModule">操作模块：</label></td>
				<td>
					<select name="operateModule" id="operateModule">
                   		<option value="">请选择</option>
                   		<option value="1">人力资源</option>
                   		<option value="2">项目</option>
                   		<option value="3">采购</option>
                   		<option value="4">安全</option>
                   	</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label for="operateModel">操作对象：</label></td>
				<td>
					<select name="operateModel" id="operateModel">
                   		<option value="">请选择</option>
                   		<option value="6">在建项目</option>
                   		<option value="10">项目</option>
                   		<option value="13">采购单</option>
                   		<option value="11">报价单</option>
                   		<option value="8">派工</option>
                   		<option value="3">部门</option>
                   		<option value="5">员工</option>
                   		<option value="1">客户</option>
                   		<option value="17">操作员</option>
                   		<!-- 
                   		<option value="2">客户负责人</option>
                   		<option value="4">员工角色</option>
                   		<option value="7">在建项目明细</option>
                   		<option value="9">派工工作</option>
                   		<option value="12">报价单项</option>
                   		<option value="14">采购单项</option>
                   		 -->
                   	</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label for="operatorId">操作员：</label></td>
				<td>
					<select name="operatorId" id="operatorId">
                   		<option value="">请选择</option>
                   	</select>
				</td>
			</tr>
			<tr>
				<td align="right"><label for="startTime">开始时间：</label></td>
				<td>
					<input name="startTime" id="startTime" class="text"/>
				</td>
			</tr>
			<tr>
				<td align="right"><label for="endTime">结束时间：</label></td>
				<td>
					<input name="endTime" id="endTime" class="text"/>
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
	$.postJSON("../user/listUser_JSON.action",function(data){
		$.each(data.users, function(i,user){
			$("<option value="+user.userId+">"+user.username+"</option>").appendTo($("#operatorId"));
		});
	});
	var dates = $("#startTime, #endTime").datepicker({ changeYear:true,
		showButtonPanel:true,
		showClearButton:true,
		clearText: '清除', 
		closeText: '关闭', 
		currentText: '今天',
		yearRange:'-80:+80',
		changeYear:true,
		changeMonth:true,
		dayNamesMin: ['日','一', '二', '三', '四', '五', '六' ],
        dateFormat:'yy-mm-dd',
        monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        onSelect: function(selectedDate) {
			var option = this.id == "startTime" ? "minDate" : "maxDate";
			var instance = $(this).data("datepicker");
			var date = $.datepicker.parseDate(instance.settings.dateFormat || 
				$.datepicker._defaults.dateFormat, selectedDate, instance.settings);
			dates.not(this).datepicker("option", option, date);
		}
	});
});

</script>
</html>