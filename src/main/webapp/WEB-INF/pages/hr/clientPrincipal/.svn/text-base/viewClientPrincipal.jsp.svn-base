<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
<title>客户商详细信息</title>
</head>
<body>
<s:form action="modify" namespace="/client" method="post" id="modifyclientForm">
<table>
	<tr>
		<td>客户商名称:</td>
		<s:if test="flag == 1">
		<td><s:property value="client.clientName" /></td>
		</s:if>
		<s:elseif test="flag == 2">
			<td><input type="text" name = "client.clientName" value="${client.clientName}" readonly="readonly" /></td>
		</s:elseif>
	</tr>
	<tr>
		<td>地址:</td>
		<s:if test="flag == 1">
		<td><s:property value="client.address" /></td>
		</s:if>
		<s:elseif test="flag == 2">
			<td>
				<input type="text" name = "client.address" value="${client.address}" readonly="readonly" />
			</td>
		</s:elseif>
	</tr>
	<tr>
		<td>联系电话:</td>
		<s:if test="flag == 1">
		<td><s:property value="client.phone" /></td>
		</s:if>
		<s:elseif test="flag == 2">
			<td><input type="text" name = "client.phone" value="${client.phone}" /></td>
		</s:elseif>
	</tr>
	<tr>
		<td>邮箱:</td>
		<s:if test="flag == 1">
		<td><s:property value="client.email" /></td>
		</s:if>
		<s:elseif test="flag == 2">
			<td><input type="text" name = "client.email" value="${client.email}" /></td>
		</s:elseif>
	</tr>
	<tr>
		<td>热度:</td>
		<s:if test="flag == 1">
		<td><s:property value="client.priority" /></td>
		</s:if>
		<s:elseif test="flag == 2">
			<td><input type="text" name = "client.priority" value="${client.priority}" readonly="readonly" /></td>
		</s:elseif>
	</tr>
	
	
	<tr>
		<td align="center">
			<s:if test="flag == 2">
				<input type="submit" name="modify" id="modify" value="修改"/>
			</s:if>
		</td>
	</tr>
</table>
</s:form>
</body>
<script type="text/javascript">
	$(function(){
		$("tr td:empty").parent().remove();

		var selectId = $("#departmentName").val();
		$(".selectId").each(function(){
			
			if($(this).text() == selectId)
			{
				$(this).attr("selected", "selected");
			}
		});
		$("#departmentName").val($("#departmentId option:selected").text());
		$.postJSON("listJobTypes_JSON.action", function(data){
			var jobTypes = eval("(" + data.jobTypesJSON + ")");
			$("#jobType").autocomplete(jobTypes, {
				matchContains:true,
				minChars:0,
				formatItem:function(data){
					return data[0];
				}
			});
		});
		$("#departmentId").change(function(){
			$("#departmentName").val($("#departmentId option:selected").text());
		});

		$("#modifyPersonForm").validate( {
			rules : {
				"person.mobilePhone" : {
					mobilePhone : true
				},
				"person.housePhone" : {
					telephone : true
				}
			},
			messages : {
				"person.mobilePhone" : {
					mobilePhone : "手机号码不符合格式！"
				},
				"person.housePhone" : {
					telephone : "住宅电话不符合格式！"
				}
			}
		});
			
	});
</script>
</html>