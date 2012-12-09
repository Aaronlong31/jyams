<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${ctitle} - 查看派工</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<script data-main="${ctx}/js/dispatch/view" src="${ctx}/js/require-jquery.js"></script>
</head>
<body>
<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<h4 id="title">查看派工</h4>
			</div>
		</div>
	</div>
    <hr/>
    <a class="btn btn-warning" href="${ctx}/dispatch/${dispatch.dispatchId}/edit">编辑派工</a>
    <table class="table table-striped table-condensed">
        <tr>
            <td class="td_first">项目编号:</td>
            <td>${dispatch.projectId }</td>
            <td class="td_first">项目名称:</td>
            <td>${dispatch.projectName}</td>
        </tr>
        <tr>
            <td class="td_first">派工人:</td>
            <td>${dispatch.principalName}</td>
            <td class="td_first">派工类型:</td>
            <td>${dispatch.dispatchTypeString}</td>
        </tr>
        <tr>
            <td class="td_first">派工日期:</td>
            <td>${dispatch.dispatchDayString}</td>
            <td class="td_first">创建时间:</td>
            <td>${dispatch.createdTimestampString}</td>
        </tr>
    </table>
    <hr/>
    <h4>施工人员列表</h4>
    <table class="table table-striped table-condensed">
        <tr>
            <th class="center">施工人员</th>
            <th class="center">开始时间</th>
            <th class="center">结束时间</th>
            <th class="center">有效工时</th>
        </tr>
        <c:forEach items="${dispatch.dispatchWorks}" var="dw">
            <tr>
                <td class="center">${dw.personName}</td>
                <td class="center">${dw.startTimeString}</td>
                <td class="center">${dw.endTimeString}</td>
                <td class="center">${dw.hours}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>