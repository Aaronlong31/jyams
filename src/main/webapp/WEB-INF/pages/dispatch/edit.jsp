<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${ctitle} - 新增派工</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<script data-main="${ctx}/js/dispatch/edit.js" src="${ctx}/js/require-jquery.js"></script>
<style type="text/css">
#projectPager_right{width:0}
.uneditable-input{cursor: pointer;}
.box{
	height: 400px;
	text-align: center;
}
ul{
	list-style-type: none;
	border: 1px solid #ddd;
	-webkit-border-radius: 4px 0 4px 0;
	-moz-border-radius: 4px 0 4px 0;
	border-radius: 4px 0 4px 0;
	margin:0px;
	overflow-y : auto;
	height: 317px;
}
li{
	height : 30px;
	cursor:move;
	text-align :center;
	color : #4F4F4F;
	background-color: #F5F5F5;
	border: 1px solid #ddd;
	margin: 2px;
	line-height: 30px;
	padding-left: 5px;
}
ul.unDispatched, ul.dispatched{padding: 20px;background-color: #ddd}
div.departments{
	text-align: center;
}
ul.dispatched li span{width:120px;margin-right: 10px;}
</style>
</head>
<body>
<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<h4 id="title">
                    <c:if test="${not empty dispatch.dispatchId}">
                        编辑派工
                    </c:if>
                    <c:if test="${empty dispatch.dispatchId}">
                        新建派工
                    </c:if>
				</h4>
			</div>
		</div>
	</div>
	<div id="message" class="alert alert-error hide"></div>
	<form action="" class="form-horizontal ">
		<table class="table table-condensed">
	    	<tr>
				<td colspan="2">
					<div class="control-group">
						<label class="control-label" for="projectName">项目<span class="red_star">*</span></label>
						<div class="controls" style="width:100%">
							<span id="projectId" class="input-small uneditable-input">${dispatch.projectId}</span>
							<span id="projectName" class="span5 uneditable-input">${dispatch.projectName}</span>
							<a class="btn btn-primary" id="selectProjectBtn">选择项目</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="dispatchType">派工类型<span class="red_star">*</span></label>
						<div class="controls">
							<div class="btn-group" data-toggle="buttons-radio">
								<input type="hidden" id="dispatchType" value="${dispatch.dispatchType}"/>
								<button type="button" name="dispatchType" value="1" class="btn dispatchType">正常派工</button>
								<button type="button" name="dispatchType" value="2" class="btn dispatchType">加班派工</button>
							</div>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="dispatchDay">派工日期</label>
						<div class="controls">
							<input name="dispatchDayString" id="dispatchDay" value="${dispatch.dispatchDayString}" type="text" class="input-big"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<hr/>
		<div class="control-group departments">
	    	<div class="btn-group" data-toggle="buttons-radio">
	    		<button type="button" class="btn department active" value="0">全部</button>
	    		<c:forEach items="${dp1}" var="d">
	    			<button type="button" class="btn department" value="${d.departmentId}">${d.departmentName}</button>
	    		</c:forEach>
	    	</div>
    	</div>
    	<div class="alert alert-success">
    		<span class="label label-info">提示：</span>
    		可以将左侧的员工拖动到右侧方框以添加派工，将右侧的员工拖动到左侧方框以取消派工。
    	</div>
		<div class="container-fluid">
		  <div class="row-fluid">
		    <div id="undispatched" class="span4 box ">
		    	<h4>选择员工</h4>
		    	<ul class="unDispatched" onselectstart="return false">
			    	<c:forEach items="${dp1}" var="d">
			    		<c:forEach items="${d.persons}" var="p">
			    			<li personId="${p.personId }" departmentId="${d.departmentId}"><span>${d.departmentName} - ${p.personName}</span></li>
			    		</c:forEach>
		    		</c:forEach>
			    </ul>
		    </div>
		    <div id="dispatched" class="span8 box ">
		    	<h4>已选择员工</h4>
		    	<ul class="dispatched" onselectstart="return false">
			    	<c:forEach items="${dispatchWorks}" var="d" >
		    			<li personId="${d.key.personId }" 
		    				startTime="${d.key.startTimeString}" endTime="${d.key.endTimeString}" 
		    				departmentId="${d.value.departmentId}">
		    				<span>${d.value.departmentName} - ${d.key.personName}</span>
		    			</li>
			    	</c:forEach>
			    </ul>
		    </div>
		  </div>
		</div>
	</form>
	<div class="form-actions center">
		<button type="submit" class="btn btn-primary" id="addDispatchBtn" data-loading-text="保存中......">保存</button>
		<button type="button" class="btn" id="cancel">取消</button>
    </div>
</div>
</body>
</html>