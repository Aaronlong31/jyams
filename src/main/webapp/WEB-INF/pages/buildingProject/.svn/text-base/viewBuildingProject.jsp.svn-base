<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看在建项目</title>
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
			当前位置：<a href="#">首页</a><a href="#">项目</a>查看在建项目
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<table class="table_normal">
						<tr>
							<td class="td_first">项目编号:</td>
							<td><s:property value="buildingProject.projectId" /></td>
							<td class="td_first">项目名称:</td>
							<td><s:property value="buildingProject.project.projectName" /></td>
						</tr>
						<tr>
							<td class="td_first">公司负责人:</td>
							<td><s:property value="buildingProject.project.companyPrincipalName" /></td>
							<td class="td_first">项目状态:</td>
							<td><s:property value="buildingProject.statusString" /></td>
						</tr>
						<tr>
							<td class="td_first">客户:</td>
							<td><s:property value="buildingProject.project.clientName" /></td>
							<td class="td_first">客户负责人:</td>
							<td><s:property value="buildingProject.project.clientPrincipalName" /></td>
						</tr>
						<tr>
							<td class="td_first">施工地点:</td>
							<td><s:property value="buildingProject.project.constructPlace" /></td>
							<td class="td_first">出订单日:</td>
							<td><s:property value="buildingProject.project.orderDate" /></td>
						</tr>
						<tr>
							<td class="td_first">订单编号:</td>
							<td><s:property value="buildingProject.project.orderId" /></td>
							<td class="td_first">报价单编号:</td>
							<td><s:property value="buildingProject.project.quoteId" /></td>
						</tr>
						<tr>
							<td class="td_first">要求完工日:</td>
							<td><s:property value="buildingProject.project.requiredCompletionDate" /></td>
							<td class="td_first">可延后时间:</td>
							<td><s:property value="buildingProject.project.canDelayDay" />天</td>
						</tr>
						<tr>
							<td class="td_first">实际成本（RMB）:</td>
							<td><s:property value="buildingProject.actualCost" />元</td>
							<td class="td_first">预估成本（RMB）:</td>
							<td><s:property value="buildingProject.estimateCost" />元</td>
						</tr>
						<tr>
							<td class="td_first">利润率:</td>
							<td><s:property value="buildingProject.margin" /></td>
							<td class="td_first">合同总价（RMB）:</td>
							<td><s:property value="buildingProject.contractPrice" />元</td>
						</tr>
						<tr>
							<td class="td_first">已付款（RMB）:</td>
							<td><s:property value="buildingProject.paidMoney" /></td>
							<td class="td_first">未付款（RMB）:</td>
							<td><s:property value="buildingProject.unpaidMoney" /></td>
						</tr>
						<tr>
							<td class="td_first">创建:</td>
							<td><s:property value="buildingProject.project.creatorName" />&nbsp;
								<j:dateTimeFormat value="buildingProject.project.createdTimestamp" />
							</td>
							<td class="td_first">最后修改:</td>
							<td>
								<s:property value="buildingProject.lastModifierName" />&nbsp;
								<j:dateTimeFormat value="buildingProject.lastModifiedTimestamp" />
							</td>
						</tr>
						<tr>
							<td class="td_first">开票:</td>
							<td>
								<s:property value="buildingProject.invoicerName" />&nbsp;
								<j:dateTimeFormat value="buildingProject.invoiceTimestamp" />
							</td>
							<td class="td_first">完工:</td>
							<td>
								<s:property value="buildingProjectcompletionPersonName" />&nbsp;
								<j:dateTimeFormat value="buildingProject.completionTimestamp" />
							</td>
						</tr>
						<tr>
							<td class="td_first">订单内容:</td>
							<td colspan="3"><s:property value="buildingProject.project.orderContent" /></td>
						</tr>
					</table>
				</div>
			</div>
				<div class="main_content">
					<s:if test="%{(buildingProject.status & 1)== 1}">
					<p class="table_bar">
		            	<button type="button" id="addDetail" class="btn4">添加明细</button>
		            </p>
		            <div id="addDetailDiv" style="display: none">
		            	<label class="error"><s:actionmessage/></label><s:actionmessage/>
		            	<form action="addDetail.action" method="post" id="addDetailForm">
							<input type="hidden" name="buildingProjectDetail.projectId" value='<s:property value="buildingProject.projectId"/>'>
					        <table class="table_normal">
					        	<tr>
					        		<td class="td_first">花费类型：<span class="red_star">*</span></td>
					        		<td>
					        			<input type="radio" name="buildingProjectDetail.costType" value="1" checked="checked"/><label>材料花费</label>
					        			<input type="radio" name="buildingProjectDetail.costType" value="4"/><label>其他花费</label>
					        		</td>
					        	</tr>
					        	<tr>
					        		<td class="td_first"><label for="cost">花费金额：<span class="red_star">*</span></label></td>
					        		<td><input name="buildingProjectDetail.cost" id="cost" class="text"/></td>
					        	</tr>
					        </table>
					        <div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
								<button type="submit" class="btn" id="addProjectBut">确定</button>
								<button type="button" class="btn" id="cancel">取消</button>
							</div>
					    </form>
		            </div>
		            </s:if>
		            <div class="tableBox">
					<table class="table_normal" style="width: 1000px;">
						<thead>
							<tr>
								<th style="width:4%;text-align:center;">序号</th>
								<th style="width:13%;text-align:center;">日期</th>
								<th style="width:13%;text-align:center;">经办人</th>
								<th style="width:13%;text-align:center;">材料花费（RMB）</th>
								<th style="width:13%;text-align:center;">订单花费（RMB）</th>
								<th style="width:13%;text-align:center;">人工花费（RMB）</th>
								<th style="width:13%;text-align:center;">其他花费（RMB）</th>
								<th style="width:13%;text-align:center;">关联纪录</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="buildingProject.buildingProjectDetails" status="sta">
								<tr>
									<td style="text-align: center;"><s:property value="%{#sta.index + 1}"/></td>
									<td style="text-align: center;"><j:dateTimeFormat value="createdTimestamp"/></td>
									<td style="text-align: center;"><s:property value="personName"/></td>
									<td style="text-align: right;">
										<s:if test="costType == 1">
											<s:property value="cost"/>元
										</s:if>
									</td>
									<td style="text-align: right;">
										<s:if test="costType == 2">
											<s:property value="cost"/>元
										</s:if>
									</td>
									<td style="text-align: right;">
										<s:if test="costType == 3">
											<s:property value="cost"/>元
										</s:if>
									</td>
									<td style="text-align: right;">
										<s:if test="costType == 4">
											<s:property value="cost"/>元
										</s:if>
									</td>
									<td style="text-align: center;">
										<s:if test="costType == 1">
											<a href='../purchase/viewPurchaseFromPurchaseItem.action?purchaseItemId=${referId}'>查看采购表</a>
										</s:if>
										<s:if test="costType == 3">
											<a href='../dispatch/view.action?dispatchId=${referId}'>查看派工详情</a>
										</s:if>
									</td>
								</tr>
							</s:iterator>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="11">
									<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount"><s:property value="buildingProject.buildingProjectDetails.size"/></b>条记录 </span></div>  
								</td>
							</tr>
						</tfoot>
					</table>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
$(function(){
	$("#addDetail").toggle(function(){
		$("#addDetailDiv").show();
	}, function(){
		$("#addDetailDiv").hide();
	});
	$("#addDetailForm").validate({
		rules:{
			"buildingProjectDetail.cost":{
				required: true,
				number:true
			}
		},
		messages:{
			"buildingProjectDetail.cost":{
				required: "请输入花费金额！",
				number:"花费金额必须为数字！"
			}
		}
	});
});
</script>
</html>