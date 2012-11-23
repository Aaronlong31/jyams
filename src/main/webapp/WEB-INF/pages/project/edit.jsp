<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Calendar"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ctitle} - 修改施工流程</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/validator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/validator/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.js"></script>
<style type="text/css">
.controls{width:250px}
.form-horizontal .control-group{margin-bottom:0}
.control-label{font-weight: bold;}
.error{color:#B94A48}
</style>
</head>
<body>
<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<h4>修改施工流程</h4>
			</div>
		</div>
	</div>
	<hr/>
	<form class="form-horizontal" action="${ctx}/project" method="POST" id="modifyProjectForm">
	  <fieldset>
	    <table class="table table-striped table-condensed">
	    	<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="projectName">项目编号<span class="red_star">*</span></label>
						<div class="controls">
							<input type="text" class="input-big" id="projectId" readonly="readonly" name="projectId" value="${project.projectId}">
							<input type="hidden" name="_method" value="PUT"/>
						</div>
					</div>
				</td>
			</tr>
	    	<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="projectName">项目名称<span class="red_star">*</span></label>
						<div class="controls">
							<input type="text" class="input-big" id="projectName" name="projectName" value="${project.projectName}">
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="quoteId">报价单编号</label>
						<div class="controls">
							<input type="text" class="input-big" id="quoteId" name="quoteId" value="${project.quoteId}"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="companyPrincipalId">施工负责人<span class="red_star">*</span></label>
						<div class="controls">
							<select  name="companyPrincipalId" id="companyPrincipalId" class="input-big">
								<option value="">请选择</option>
								<c:forEach items="${persons }" var="person">
								<option value="${person.personId}">${person.personName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<input type="hidden" id="companyPrincipalId" value="${project.companyPrincipalId}"/>
					<input type="hidden" name="companyPrincipalName" id="companyPrincipalName" value="${project.companyPrincipalName}"/>
				</td>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="constructPlace">施工地点</label>
						<div class="controls">
							<input type="text" class="input-big" id="constructPlace" name="constructPlace" value="${project.constructPlace}">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="clientName">客户商</label>
						<div class="controls">
							<input type="text" class="input-big" id="clientName" name="clientName" value="${project.clientName }">
						</div>
					</div>
				</td>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="clientPrincipalName">客户负责人</label>
						<div class="controls">
							<input type="text" class="input-big" id="clientPrincipalName" name="clientPrincipalName" value="${project.clientPrincipalName }">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="orderId">订单编号</label>
						<div class="controls">
							<input type="text" class="input-big" id="orderId" name="orderId" value="${project.orderId}">
						</div>
					</div>
				</td>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="orderDate">出订单日</label>
						<div class="controls">
							<input type="text" class="input-big" readonly="readonly" id="orderDate" name="orderDateString" value="${project.orderDateString }">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="requiredCompletionDate">要求完工日<span class="red_star">*</span></label>
						<div class="controls">
							<input type="text" class="input-big" readonly="readonly" id="requiredCompletionDate" name="requiredCompletionDateString" value="${project.requiredCompletionDateString}">
						</div>
					</div>
				</td>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="canDelayDay">可延后时间（天）<span class="red_star">*</span></label>
						<div class="controls">
							<input type="text" class="input-big uneditable-input" id="canDelayDay" name="canDelayDay" value="${project.canDelayDay}">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td  colspan="2" class="td_first">
					<div class="control-group">
						<label class="control-label" for="orderContent">订单内容<span class="red_star">*</span></label>
						<div class="controls">
							<textarea name="orderContent" id="orderContent" style="width: 500px;" >${project.orderContent}</textarea>
						</div>
					</div>
					
				</td>
			</tr>
	      </table>
	    <div class="form-actions">
			<button type="submit" class="btn btn-primary" id="addProjectBut">确定修改</button>
			<button type="button" class="btn" id="cancel">取消</button>
	    </div>
	  </fieldset>
	</form>
</div>
</body>
<script language="javascript">
$(function() {
	
	$("#companyPrincipalId option[value=${project.companyPrincipalId}]").attr("selected", "selected");
	$("#companyPrincipalId").change(function(){
		$("#companyPrincipalName").val($("#companyPrincipalId option:selected").text());
	});
	$("#requiredCompletionDate, #orderDate").datepicker({ 
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
        monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
    } );   
	/* $.postJSON("../client/listClients_JSON.action",{},function(data){
		$("#clientName").autocomplete(data.clients,{
			selectFrist:false,
			matchContains: true,
	        minChars: 0,
			formatItem:function(row){
				return row.clientName;
			},
			formatResult:function(row){
				return row.clientName;
			}
		}).result(function(event,data,formatted){
			$("#clientPrincipalName").val("").autocomplete(data.principals, {
				selectFrist:false,
				matchContains: true,
		        minChars: 0,
				formatItem:function(row){
					return row.name;
				}
			});
		});
	});	
	$.postJSON("../quote/listIds_JSON.action",{},function(data){
		$("#quoteId").autocomplete(data.ids,{
			selectFrist:false,
			matchContains: true,
			mustMatch:true,
	        minChars: 0
		});
	}); */
	$("#addProjectForm").validate({
		rules:{
			"projectName":{
				required:true,
				rangelength: [2, 100]
			},
			"companyPrincipalId":{
				required:true
			},
			"requiredCompletionDate":{
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