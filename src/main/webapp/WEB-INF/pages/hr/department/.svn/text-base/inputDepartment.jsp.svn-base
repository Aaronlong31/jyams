<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加部门</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.autocomplete.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.bgiframe.min.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.ajaxQueue.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.simpletip.js"></script>
<body>

<div class="content">
<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">部门</a>编辑部门
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<form action="save.action" method="post" id="addDepartment">
						<input type="hidden" name="department.departmentId" value='<s:property value="department.departmentId"/>'/>
						<table  class="table_normal">
							<tr>
								<td class="td_first"><label for="departmentName">部门名称:<span class="red_star">*</span></label></td> 
								<td>
									<input name="department.departmentName" id="departmentName" class="text" 
										value='<s:property value="department.departmentName"/>'/>
								</td>
							</tr>
					
							<!-- 
							<tr>
								<td class="td_first"><label for="superId">上级部门:</label></td> 
								<td>
									<select name="department.superId">
										<s:iterator value="departments">
											<option value="${departmentId}">${departmentName}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
					 		-->
							<tr>
								<td class="td_first"><label for="principalName">部门负责人:</label> </td>
								<td>
									<!-- 
										<select name="department.principalId" id="principalId">
										<s:iterator value="persons">
											<s:if test="%{personId == department.principalId}">
												<option value="${personId}" selected="selected">${personName}</option>
											</s:if>
											<s:else>
												<option value="${personId}">${personName}</option>
											</s:else>
										</s:iterator>
									</select>
									 -->
									<input name="department.principalName" id="principalName" class="text"
										value='<s:property value="department.principalName"/>'/>
									<input type="hidden" name="department.principalId" id="principalId" 
										value='<s:property value="department.principalId"/>'/>
								</td>
							</tr>
						</table>
						<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
							<button type="submit" class="btn" id="addPersonBut">确定</button>
							<button type="button" class="btn" id="cancel">取消</button>
						</div>
					</form>
				</div>
			</div>
    </div>
</div>
</body>

<script language="javascript">
$(function() {


	$("#addDepartment").validate( {
		rules : {
			"department.departmentName" : {
				required : true,
				minlength : 2
			}
		},
		messages : {
			"department.departmentName" : {
				required : '部门名称不为空！',
				minlength : $.format("部门名称至少要{0}个字符")
			}
		}
	});
			
	$("#principalName").val($("#principalId option:selected").text());
	$("#principalId").change(function(){
		$("#principalName").val($("#principalId option:selected").text());
	});
	$("#cancel").click(function(){
		history.back();
	});
	$.postJSON("../person/listPersonsSimple_JSON.action",function(data){
		$("#principalName").autocomplete(data.persons,{
			matchContains : true,
			autoFill: true,
			minChars : 0,
			formatItem : function (row){
				return row.personName;
			},
			formatResult : function(row){
				return row.personName;
			}
		}).result(function(event,data,formatted){
			if(data){
				$("#principalId").val(data.personId);
			} else {
				$("#principalId").val("");
			}
		});
	});
});
</script>
</html>