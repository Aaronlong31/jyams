<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Calendar"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ctitle} - 新建施工流程</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/validator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/i18n/validator.i18n.js"></script>
<script type="text/javascript" src="${ctx}/js/i18n/datepicker.i18n.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<h4>新建施工流程</h4>
			</div>
		</div>
	</div>
	<hr/>
	<div id="message" class="alert alert-error"><form:errors path="*"/></div>
	<form:form cssClass="form-horizontal" modelAttribute="addProjectForm" method="POST" 
		action="${ctx}/project" id="addProjectForm">
	    <table class="table table-striped table-condensed">
	    	<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="projectName">项目名称<span class="red_star">*</span></label>
						<div class="controls">
							<form:input path="projectName" cssClass="input-big" cssErrorClass="error"/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="quoteId">报价单编号</label>
						<div class="controls">
							<form:input path="quoteId" cssClass="input-big" cssErrorClass="error"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="companyPrincipalId">施工负责人<span class="red_star">*</span></label>
						<div class="controls">
							<form:select path="companyPrincipalId" items="${persons}" itemLabel="personName" itemValue="personId"/>
						</div>
					</div>
				</td>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="constructPlace">施工地点</label>
						<div class="controls">
							<form:input path="constructPlace" cssClass="input-big" cssErrorClass="error"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="clientName">客户商</label>
						<div class="controls">
							<form:input path="clientName" cssClass="input-big" cssErrorClass="error"/>
						</div>
					</div>
				</td>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="clientPrincipalName">客户负责人</label>
						<div class="controls">
							<form:input path="clientPrincipalName" cssClass="input-big" cssErrorClass="error"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="orderId">订单编号</label>
						<div class="controls">
							<form:input path="orderId" cssClass="input-big" cssErrorClass="error"/>
						</div>
					</div>
				</td>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="orderDate">出订单日</label>
						<div class="controls">
							<form:input path="orderDateString" cssClass="input-big" cssErrorClass="error" readonly="true"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="requiredCompletionDate">要求完工日<span class="red_star">*</span></label>
						<div class="controls">
							<form:input path="requiredCompletionDateString" cssClass="input-big" cssErrorClass="error" readonly="true"/>
						</div>
					</div>
				</td>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="canDelayDay">可延后时间（天）<span class="red_star">*</span></label>
						<div class="controls">
							<form:input path="canDelayDay" cssClass="input-big" cssErrorClass="error"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td  colspan="2" class="td_first">
					<div class="control-group">
						<label class="control-label" for="orderContent">订单内容<span class="red_star">*</span></label>
						<div class="controls">
							<form:textarea path="orderContent" cssStyle="width: 500px;"/>
						</div>
					</div>
					
				</td>
			</tr>
	      </table>
	    <div class="form-actions">
			<button type="submit" class="btn btn-primary" id="addProjectBut">新建</button>
			<button type="button" class="btn" id="cancel">取消</button>
	    </div>
	</form:form>
</div>
</body>
<script language="javascript">
$(function() {
	if($("#message").text() == ""){
		$("#message").hide();
	}
	$("#requiredCompletionDateString, #orderDateString").datepicker();   
	$("#addProjectForm").validate({
		rules:{
			"projectName":{
				required:true,
				rangelength: [2, 100]
			},
			"companyPrincipalId":{
				required:true
			},
			"requiredCompletionDateString":{
				required:true
			},
			"canDelayDay":{
				required:true,
				range:[0, 100]
			},
			"orderContent" :{
				rangelength:[0, 1000]
			}
		},
		messages:{
			"projectName":{
				required:"请输入项目名称！",
				rangelength:"项目名称为2-100字！"
			},
			"companyPrincipalId":{
				required:"请选择公司负责人！"
			},
			"requiredCompletionDateString":{
				required:"请选择要求完工日期！"
			},
			"canDelayDay":{
				required:"请输入项目可延后日期！",
				range:$.format("可延后日期要在{0}-{1}之间！")
			},
			"orderContent" :{
				rangelength:"项目内容不能操作1000字！"
			}
		},
		highlight: function(element, errorClass){
			$(element).parent().parent().addClass("error");
		},
		unhighlight: function(element, errorClass){
			$(element).parent().parent().removeClass("error");
		}
	});
	$("#cancel").click(function(){
		history.back();
	});
});
</script>
</html>