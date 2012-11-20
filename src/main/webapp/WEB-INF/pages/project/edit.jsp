<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Calendar"%><html>
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

</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">项目</a>添加项目
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<form action="save.action" method="post" id="addProjectForm">
						<input type="hidden" id="projectId" name="project.projectId" value='<s:property value="project.projectId"/>'>
				        <table class="table_normal">
				        	<tr>
								<td colspan="4" class="errorMessage" id="message"><s:actionerror/></td>
							</tr>
				        	<tr id="newYearTr" style="display: none">
				        		<td></td>
				        		<%
				        		int year = Calendar.getInstance().get(Calendar.YEAR) % 100;
				        		%>
				        		<td colspan="3">当前项目编号年份为:<%=year %>，如果要从<%=year + 1 %>年开始重新编号，请打钩：
				        			<input type="checkbox" class="text" id="isNewYear" name="isNewYear" value="true"/>
				        		</td>
				        	</tr>
							<tr>
								<td class="td_first">项目名称:<span class="red_star">*</span></td>
								<td> 
									<input name="project.projectName" id="projectName" class="text" value='<s:property value="project.projectName"/>'/>
								</td>
								<td class="td_first">
									<label for="quoteId">报价单编号</label>
								</td>
								<td>
									<input name="project.quoteId" id="quoteId" value='<s:property value="project.quoteId"/>' class="text"/>
								</td>
							</tr>
							<tr>
							<!-- 
								<td class="td_first">预估成本（元）:<span class="red_star">*</span></td>
								<td> 
									<input name="project.estimateCost" id="estimateCost" class="text" value='<s:property value="project.estimateCost"/>'/>
								</td>
							 -->
								<td class="td_first">施工负责人:<span class="red_star">*</span></td>
								<td>
									<select  name="project.companyPrincipalId" id="companyPrincipalId">
										<option value="">请选择</option>
										<s:iterator value="persons">
											<option value="${personId}"><s:property value="personName"/></option>
										</s:iterator>
									</select>
									<input type="hidden" name="project.companyPrincipalName" id="companyPrincipalName"  value='<s:property value="project.companyPrincipalName"/>'/>
								</td>
								<td class="td_first">施工地点:</td>
								<td> 
									<input name="project.constructPlace" id="constructPlace" class="text"  value='<s:property value="project.constructPlace"/>'/>
								</td>
							</tr>
							<tr>
								<td class="td_first">客户商:</td>
								<td> 
									<input name="project.clientName" id="clientName" class="text"  value='<s:property value="project.clientName"/>'/>
								</td>
								<td class="td_first">客户负责人:</td>
								<td> 
									<input name="project.clientPrincipalName" id="clientPrincipalName" class="text"  value='<s:property value="project.clientPrincipalName"/>'/>
								</td>
							</tr>
							<tr>
								
								<td class="td_first">订单编号:</td>
								<td> 
									<input name="project.orderId" id="orderId" class="text"  value='<s:property value="project.orderId"/>'/>
								</td>
								<td class="td_first">出订单日:</td>
								<td> 
									<input name="project.orderDateString" id="orderDate"  class="text"  value='<s:property value="project.orderDateString"/>'/>
								</td>
							</tr>
							<tr>
								<td class="td_first">要求完工日:<span class="red_star">*</span></td>
								<td> 
									<input name="project.requiredCompletionDateString" id="requiredCompletionDate" class="text"  value='<s:property value="project.requiredCompletionDateString"/>'/>
								</td>
								<td class="td_first">可延后时间（天）:<span class="red_star">*</span></td>
								<td> 
									<input name="project.canDelayDay" id="canDelayDay" class="text"  value='<s:property value="project.canDelayDay"/>'/>
								</td>
							</tr>
							<tr>
								<td class="td_first">订单内容:</td>
								<td colspan="3"> 
									<textarea name="project.orderContent" style="width: 700px;" id="orderContent" cols="50" rows="4"><s:property value="project.orderContent"/></textarea>
								</td>
							</tr>
				        </table>
						<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
							<button type="submit" class="btn" id="addProjectBut">确定</button>
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

		if($("#projectId").val() > 0){
			$("#newYearTr").hide();
		}
		
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
		$.postJSON("../client/listClients_JSON.action",{},function(data){
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
		});
		$("#addProjectForm").validate({
			rules:{
				"project.projectName":{
					required:true,
					rangelength: [2, 100]
				},
				"project.companyPrincipalId":{
					required:true
				},
				"project.requiredCompletionDateString":{
					required:true
				},
				"project.canDelayDay":{
					required:true,
					range:[0, 100]
				},
				"project.orderContent" :{
					rangelength:[0, 1000]
				}
			},
			messages:{
				"project.projectName":{
					required:"请输入项目名称！",
					rangelength:"项目名称不能超过100字！"
				},
				"project.companyPrincipalId":{
					required:"请选择公司负责人！"
				},
				"project.requiredCompletionDateString":{
					required:"请选择要求完工日期！"
				},
				"project.canDelayDay":{
					required:"请输入项目可延后日期！",
					range:$.format("可延后日期要在{0}-{1}之间！")
				},
				"project.orderContent" :{
					rangelength:"项目内容不能操作1000字！"
				}
			}
		});
		$("#cancel").click(function(){
			history.back();
		});
	});
</script>
</html>