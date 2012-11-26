<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/convertCurrency.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>>
</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">报价单</a>查看报价单
		</h3>
		<div class="main_content two_rows">
				<div class="main_content" style="overflow-x:auto">
					<p class="table_bar">
                   		<button type="button" class="btn" id="modifyQuoteBtn"><img src="../images/btn_edit.gif" alt="" />修改</button>
                 	</p>
                 	<table class="table_normal" style="width: 770px;">
                 		<caption>QUOTATION<br/>报价单</caption>
                 		<tr>
                 			<td colspan="6">WUXI JINYE TECHNOLOGY CO.，LTD.</td>
                 		</tr>
                 		<tr>
                 			<td colspan="6">416, RuiBeiTe Commercial Centre No.8 XinGuangRoad, WX.JS.CN.</td>
                 		</tr>
                 		<tr>
                 			<td colspan="6">无锡市金业科技有限公司</td>
                 		</tr>
                 		<tr>
                 			<td colspan="6">江苏无锡新区新光路8号瑞贝特商贸中心内</td>
                 		</tr>
                 		<tr>
                 			<td style="width: 10%;font-weight: bold;">TO：</td>
                 			<td colspan="3"><s:property value="quote.clientName" /></td>
                 			<td style="width: 14%;font-weight: bold;">Our Ref：</td>
                 			<td style="width: 14%;"><s:property value="quote.id" /></td>
                 		</tr>
                 		<tr>
                 			<td style="font-weight: bold;">Attn：</td>
                 			<td colspan="3"><s:property value="quote.attnName" /></td>
                 			<td style="font-weight: bold;">Date：</td>
                 			<td><j:dateTimeFormat pattern="yyyy-MM-dd" value="quote.createdTimestamp"/></td>
                 		</tr>
                 		<tr>
                 			<td style="font-weight: bold;">CC：</td>
                 			<td colspan="3"><s:property value="quote.ccName" /></td>
                 			<td style="font-weight: bold;">Total Page(s)：</td>
                 			<td>1</td>
                 		</tr>
                 		<tr>
                 			<td style="font-weight: bold;">From：</td>
                 			<td colspan="5"><s:property value="quote.quoterName" /></td>
                 		</tr>
                 		<tr style="font-weight: bold;">
                 			<td>Subject：</td>
                 			<td colspan="5"><s:property value="quote.subject" /></td>
                 		</tr>
                 		<tr>
                 			<th>S/NO</th>
                 			<th>Description of Item</th>
                 			<th colspan="2">Quantity<br>(数量|单位)</th>
                 			<th>Unit price (RMB)</th>
                 			<th>Total Amount  (RMB)</th>
                 		</tr>
                 		<tr>
                 			<s:iterator value="quote.quoteItems" status="sta">
								<tr>
									<td style="text-align: center;width: 5%;"><s:property value="%{#sta.index + 1}"/></td>
									<td style="text-align: left;width: 40%;"><s:property value="description"/></td>
									<td style="text-align: center;width: 6%;"><s:property value="count"/></td>
									<td style="text-align: center;width: 6%;"><s:property value="unit"/></td>
									<td style="text-align: right;width: 20%;"><s:property value="unitPrice"/></td>
									<td style="text-align: right;width: 20%;"><s:property value="totalPrice"/></td>
								</tr>
							</s:iterator>
                 		</tr>
						<tr style="font-weight: bold;">
							<td colspan="4">
								<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount"><s:property value="quote.quoteItems.size"/></b>条报价单项 </span></div>  
							</td>
							<td>TOTAL:</td>
							<td style="text-align: right;"><s:property value="quote.totalPrice" /></td>
						</tr>
						<tr>
							<td colspan="6">*提供工程发票。</td>
						</tr>
						<tr>
							<td colspan="6">*此报价有效期为壹个月。</td>
						</tr>
						<tr>
							<td colspan="6">*乙方完工后在每月末开票给甲方，甲方在收到发票六十天后付款。</td>
						</tr>
						<tr>
							<td colspan="6">谢谢合作!</td>
						</tr>
						<tr>
							<td colspan="6">Thanks&Best Regards,</td>
						</tr>
						<tr>
							<td colspan="6">Manager</td>
						</tr>
						<tr>
							<td colspan="6">for WX JINYE Technology Co.,Ltd</td>
						</tr>
						<tr>
							<td colspan="6">Tel: 82239915&nbsp;&nbsp;&nbsp;Tel: 82239966&nbsp;&nbsp;&nbsp;Fax:82239916</td>
						</tr>
						<tr>
							<td colspan="6">E-mail: wx-jinye@wx-jinye.com　　　   </td>
						</tr>
                 	</table>
				</div>
			</div>
		</div>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#modifyQuoteBtn").click(function(){
			location="input.action?quoteId=${quote.quoteId}&version=${quote.version}";
		});
		$("#approvalQuoteBtn").click(function(){
			location="approval.action?quoteId=${quote.quoteId}&version=${quote.version}";
		});
		$("#disApprovalQuoteBtn").click(function(){
			location="disApproval.action?quoteId=${quote.quoteId}&version=${quote.version}";
		});
	});
</script>
</html>