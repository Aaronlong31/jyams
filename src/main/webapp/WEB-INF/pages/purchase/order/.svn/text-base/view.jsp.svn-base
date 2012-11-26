<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>复核采购单</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
</head>
<body>
	<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">采购</a>采购单详情
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<table class="table_normal">
						<tr>
							<td class="td_first">采购单编号:</td>
							<td><s:property value="purchase.purchaseCode" /></td>
							<td class="td_first">采购单类型:</td>
							<td><s:property value="purchase.typeString" /></td>
						</tr>
						<tr>
							<td class="td_first">申购人:</td>
							<td><s:property value="purchase.applierName" /></td>
							<td class="td_first">申购日期:</td>
							<td><j:dateTimeFormat value="purchase.applyTimestamp"/></td>
						</tr>
						<tr>
							<td class="td_first">采购单状态:</td>
							<td><s:property value="purchase.statusString" /></td>
							<td class="td_first">创建时间:</td>
							<td><j:dateTimeFormat value="purchase.createdTimestamp"/></td>
						</tr>
					</table>
				</div>
			</div>
				<div class="main_content">
		            <div class="tableBox" style="top:155px">
					<table class="table_normal" style="width: 1000px;">
						<thead>
							<tr>
								<th style="width:4%;text-align:center;">序号</th>
								<th style="width:13%;text-align:center;">品名</th>
								<th style="width:10%;text-align:center;">规格型号</th>
								<th style="width:10%;text-align:center;">数量</th>
								<th style="width:10%;text-align:center;">单位</th>
								<th style="width:10%;text-align:center;">单价</th>
								<th style="width:10%;text-align:center;">金额</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="purchase.purchaseItems" status="sta">
								<tr>
									<td style="text-align: center;"><s:property value="%{#sta.index + 1}"/></td>
									<td style="text-align: right;"><s:property value="materialName"/></td>
									<td style="text-align: right;"><s:property value="specifications"/></td>
									<td style="text-align: right;"><s:property value="count"/></td>
									<td style="text-align: right;"><s:property value="unit"/></td>
									<td style="text-align: right;"><s:property value="unitPrice"/></td>
									<td style="text-align: right;"><s:property value="cost"/></td>
								</tr>
							</s:iterator>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="7">
									<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount"><s:property value="purchase.purchaseItems.size"/></b>条采购单项 </span></div>  
								</td>
							</tr>
						</tfoot>
					</table>
					</div>
				</div>
				<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
				<s:if test="(purchase.status == 0) && (loginUserId == purchase.applierId)">
					<button class="btn4" id="submit">提交采购单</button>
					<button class="btn4" id="modify">修改采购单</button>
				</s:if>
				<button id="printPurchaseBtn">打印采购单</button>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
$(function(){
	$("#submit").click(function(){
		window.location.href = "${ctx}/purchase/submitDraft.action?purchaseId=${purchase.purchaseId}";
	});
	$("#modify").click(function(){
		window.location.href = "${ctx}/purchase/order/input.action?purchaseId=${purchase.purchaseId}&type=1";
	});
	$("#printPurchaseBtn").click(function(){
		//window.location.href = "${ctx}/purchase/order/print.action?purchaseId=${purchase.purchaseId}";
		window.open("${ctx}/purchase/order/print.action?purchaseId=${purchase.purchaseId}", "_blank", "location=no,menubar=no,status=no,toolbar=no");
	});
});
</script>
</html>