<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${ctitle} - 每月派工列表</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<script data-main="${ctx}/js/dispatch/monthly" src="${ctx}/js/require-jquery.js"></script>
<style type="text/css">
#year, #month{width: 73px;height: 24px;color:red;border: none;cursor: pointer;}
.ui-datepicker-calendar,
.ui-datepicker-buttonpane{display: none}
</style>
</head>
<body>
<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<h4>员工${personName} 在
				<select id="year">
					<c:forEach var="y" begin="${beginYear}" end="${endYear}">
					<option value="${y}" <c:if test="${y eq endYear}">selected="selected"</c:if> >${y}</option>
					</c:forEach>
				</select>年
				<select id="month">
					<c:forEach var="m" begin="01" end="12">
					<option value="${m}" <c:if test="${m eq month}">selected="selected"</c:if> >${m}</option>
					</c:forEach>
				</select>月的派工清单 </h4>
			</div>
		</div>
	</div>
	<hr/>
	<div id="dispatchDiv">
		<table id="dispatchList" title="每月派工列表"></table>
		<div id="dispatchPager"></div>
	</div>     	
</div>
<input type="hidden" id="personId" value="${personId}"/>
</body>
</html>