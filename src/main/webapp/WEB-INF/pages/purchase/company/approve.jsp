<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无锡金业科技自动化管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/convertCurrency.js"></script>
<STYLE type="text/css">
.actionMessage{color: red;}
</STYLE>
</head>

<body class="bodyStyle">
<div class="content contentwidth">
        	<div class="main_panel">
            	<h3 class="crumbs">
                	<img src="../images/sidebar_h3_bg1.gif" alt="" />
                	当前位置：<a href="#">首页</a>审批采购
                </h3>
	   			<!--<div style="margin-left: 40px;margin-top: 10px;">
	   				需要修改此采购单请点击<a href="viewPurchase.action?purchaseId=${purchase.purchaseId}&version=${purchase.version}">这里</a>
	   			</div>
                -->
                <div class="main_content two_rows">
                        <div class="main_content">
                        	<s:actionmessage/>
                            <table class="table_normal">
                            	<thead>
                            		<tr>
                            			<td colspan="6" style="text-align: center;font-weight: bolder;font-size: larger;">物料申购单
                                   		</td>
                            		</tr>
                            	</thead>
                            	<tbody class="viewPurchase">
                            		<tr>
	                                	<td class="td_first">申购人：<span class="red_star">&nbsp;</span></td>
	                                	<td>
	                                		<s:property value="purchase.applierName"/>
										</td>
	                                    <td class="td_first">申购日期：<span class="red_star">&nbsp;</span></td>
	                                	<td>
	                                		<j:dateTimeFormat pattern="yyyy年MM月dd日 HH时mm分" value="purchase.applyTimestamp"></j:dateTimeFormat>
										</td>
										<td class="td_first">到货日期：<span class="red_star">&nbsp;</span></td>
	                                	<td>
	                                		<j:dateTimeFormat pattern="yyyy年MM月dd日 " value="purchase.arrivalDate"></j:dateTimeFormat>
										</td>
	                                </tr>
	                                <tr>
	                                	<td class="td_first">到货地：<span class="red_star">&nbsp;</span></td>
	                                	<td>
	                                		<s:property value="purchase.deliveryAddress"/>
										</td>
										<td class="td_first">指定收货：<span class="red_star">&nbsp;</span></td>
	                                	<td>
	                                		<s:property value="purchase.consigneeName"/>
										</td>
										<td class="td_first">采购经办：<span class="red_star">&nbsp;</span></td>
	                                	<td>
	                                		<input type="text" class="text" value="<s:property value="purchase.purchaserName"/>" id="purchaserName" name="purchase.purchaserName" disabled="disabled"/>
										</td>
	                                </tr>
	                                <tr>
	                                	<td class="td_first">采购编号：<span class="red_star">&nbsp;</span></td>
	                                	<td colspan="5">
	                                		<s:property value="purchase.purchaseCode"/>
										</td>
	                                </tr>
                            	</tbody>
                            </table>
                        </div>
                    
                        <div class="main_content">
							<table class="table_normal">
                            	<thead>
                                	<tr>
                                        <th style="width:5%;text-align:center;">序号</th>
                                		<th style="width:10%;text-align:center;">品名</th>
                                		<th style="width:10%;text-align:center;">规格/型号</th>
                                		<th style="width:10%;text-align:center;">数量</th>
                                        <th style="width:5%;text-align:center;">单位</th>
                                        <th style="width:15%;text-align:center;">备注</th>
                                        <th style="width:10%;text-align:center;">单价</th>
	                                    <th style="width:10%;text-align:center;">金额</th>
                                    </tr>
                                </thead> 
                                <tbody id="purchaseDetail">
                                    <s:iterator value="purchase.purchaseItems" status="status">
                               			<tr class="viewPurchase purchaseItemId_${purchaseItemId}" purchaseItemId="${purchaseItemId}">
                                		  	<td style="text-align:center;" id="">
                                		  		<s:property value="#status.index+1"/>
	                                      	</td>
	                                      	<td style="text-align:center;padding: 0;" id="">
	                                      		<s:property value="materialName"/>
	                                      	</td>
	                                      	<td style="text-align:center;padding: 0;" id="">
	                                      		<s:property value="specifications"/>
	                                      	</td>
	                                     	<td style="text-align:center;padding: 0;" id="" class="count">
	                                      		<s:property value="count"/>
	                                      	</td>
	                                      	<td style="text-align:center;padding: 0;" id="">
	                                      		<s:property value="unit"/>
	                                      	</td>
	                                      	<td style="text-align:center;padding: 0;" id="">
	                                      		<s:property value="remark"/>
	                                      	</td>
	                                      	<td style="text-align:center;padding: 0;" id="" class="unitPrice">
	                                      		<s:property value="unitPrice"/>
	                                      	</td>
	                                      	<td style="text-align:center;padding: 0;" id="" class="priceOfItem">
	                                      	</td>
	                                    </tr>
                               		</s:iterator>
                                </tbody>
                                <tfoot>
                                	<tr>
                                		<td colspan="2" style="text-align: right;">
                                			合计（RMB）大写：
                                        </td>
                                    	<td colspan="3" class="capitalRMB">
                                        </td>
                                        <td colspan="2" style="text-align: right;">
                                			合计（RMB）：
                                        </td>
                                    	<td colspan="3" class="lowerRMB">
                                        </td>
                                    </tr>
                                </tfoot> 
                            </table>
                    </div>
                    <form action="approve.action?purchaseId=${purchase.purchaseId}" method="post" id="approvePurchaseForm">  
                       	<div class="main_content">
                       		<h4>填写审核意见：</h4>
                       		<textarea style="width:800px ;height:100px ;margin-left: 80px;" id="approvalOpinion" name="approvalOpinion"><s:property value="approvalOpinion"></s:property></textarea>
	                		<input type="hidden" id="approvalType" value="${approveNum}" name="approvalType" />
	                		<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
	                			<button type="submit" class="btn4" id="approvedBtn">通过</button>
	                			<button type="submit" class="btn" id="disapprovedBtn">不通过</button>
	                		</div>
                       	</div>
	                </form>
                </div>
            </div>
        </div>
<script type="text/javascript">
	$(function(){
		$(".priceOfItem").each(function(){
			var count = $(this).parent().find(".count").text();
			var unitPrice = $(this).parent().find(".unitPrice").text();
			$(this).text(Math.round(Number(count)*Number(unitPrice) * 100)/100);
		});
		var totalAmount = 0 ;
		$("#purchaseDetail").find("tr").each(function(){
			totalAmount += Number($(this).find("td:last").text());
		});
		totalAmount = Math.round(totalAmount * 100)/100;
		$(".lowerRMB").text(totalAmount);
		$(".capitalRMB").text(convertCurrency(totalAmount));
		$("#disapprovedBtn").click(function(){
			$("#approvalType").val(3);
		});
	});
</script>
</body>
</html>

