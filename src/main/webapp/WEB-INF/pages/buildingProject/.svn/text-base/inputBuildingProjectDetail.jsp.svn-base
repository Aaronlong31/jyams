<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增项目</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.ui.datepicker.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery-ui-1.8.2.custom.css" />
<script language="javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.core.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.datepicker.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.widget.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.bgiframe.min.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.ajaxQueue.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.autocomplete.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script>
</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">项目</a>添加在建项目明细
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<form action="save.action" method="post" id="addProjectForm">
						<input type="hidden" name="project.projectId" value='<s:property value="project.projectId"/>'>
				        <table class="table_normal">
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
								<td class="td_first">预估成本（RMB）:<span class="red_star">*</span></td>
								<td> 
									<input name="project.estimateCost" id="estimateCost" class="text" value='<s:property value="project.estimateCost"/>'/>
								</td>
								<td class="td_first">公司负责人:<span class="red_star">*</span></td>
								<td> 
									<input name="project.companyPrincipalName" id="companyPrincipalName" class="text"  value='<s:property value="project.companyPrincipalName"/>'/>
									<input type="hidden" name="project.companyPrincipalId" id="companyPrincipalId"  value='<s:property value="project.companyPrincipalId"/>'/>
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
								<td class="td_first">施工地点:</td>
								<td> 
									<input name="project.constructPlace" id="constructPlace" class="text"  value='<s:property value="project.constructPlace"/>'/>
								</td>
								<td class="td_first">订单编号:</td>
								<td> 
									<input name="project.orderId" id="orderId" class="text"  value='<s:property value="project.orderId"/>'/>
								</td>
							</tr>
							<tr>
								<td class="td_first">出订单日:</td>
								<td> 
									<input name="project.orderDateString" id="orderDate"  class="text"  value='<s:property value="project.orderDateString"/>'/>
								</td>
								<td class="td_first">要求完工日:<span class="red_star">*</span></td>
								<td> 
									<input name="project.requiredCompletionDateString" id="requiredCompletionDate" class="text"  value='<s:property value="project.requiredCompletionDateString"/>'/>
								</td>
							</tr>
							<tr>
								<td class="td_first">可延后时间（天）:<span class="red_star">*</span></td>
								<td> 
									<input name="project.canDelayDay" id="canDelayDay" class="text"  value='<s:property value="project.canDelayDay"/>'/>
								</td>
								<td class="td_first">合同总价（RMB）：<span class="red_star">*</span></td>
								<td>
									<input name="project.contractPrice" id="contractPrice" class="text"  value='<s:property value="project.contractPrice"/>'/>
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
</html>