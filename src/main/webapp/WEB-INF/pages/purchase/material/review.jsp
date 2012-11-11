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
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/convertCurrency.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<style type="text/css">
tbody.groupItem tr:first-child{text-align: center;font-weight: bold;}
label, input { display:block; }
label{font-weight: bold;font-size:13px;margin: 7px 0 3px 0;}
input.text,select {width:95%;height:25px;}
fieldset { padding:0; border:0; margin-top:15px; }
h1 { font-size: 1.2em; margin: .6em 0; }
#discarded tr{background-color: #b0b0b0}
#totalCost{float:right;font-weight: bold;}
</style>
</head>
<body>
	<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">采购</a>采购复核
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<table class="table_normal">
						<tr>
							<td class="td_first">采购单编号:</td>
							<td><s:property value="purchase.purchaseCode" /></td>
							<td class="td_first">申购人:</td>
							<td><s:property value="purchase.applierName" /></td>
						</tr>
						<tr>
							<td class="td_first">申购日期:</td>
							<td><j:dateTimeFormat value="purchase.applyTimestamp"/></td>
							<td class="td_first">指定收货人:</td>
							<td><s:property value="purchase.consigneeName" /></td>
						</tr>
						<tr>
							<td class="td_first">到货地:</td>
							<td><s:property value="purchase.deliveryAddress" /></td>
							<td class="td_first">指定到货日期:</td>
							<td><j:dateTimeFormat value="purchase.arrivalDate" /></td>
						</tr>
					</table>
				</div>
			</div>
				<div class="main_content">
		            <div class="tableBox">
					<table class="table_normal" style="width: 1000px;">
						<thead>
							<tr>
								<th style="width:5%;text-align:center;">项目编号</th>
								<th style="width:13%;text-align:center;">项目内容</th>
								<th style="width:5%;text-align:center;">品名</th>
								<th style="width:5%;text-align:center;">规格型号</th>
								<th style="width:5%;text-align:center;">数量</th>
								<th style="width:5%;text-align:center;">单位</th>
								<th style="width:5%;text-align:center;">单价</th>
								<th style="width:5%;text-align:center;">金额</th>
								<th style="width:13%;text-align:center;">操作</th>
							</tr>
						</thead>
						<tbody class="groupItem" id="submited">
							<tr><td colspan="10">未复核的采购单项</td></tr>
							<s:iterator value="purchase.purchaseItems.{?#this.status == 1}" status="sta">
								<tr id="<s:property value="purchaseItemId"/>_tr">
									<td style="text-align: center;" class="projectId"><s:property value="projectId"/></td>
									<td style="text-align: center;"><s:property value="projectName"/></td>
									<td style="text-align: center;"><s:property value="materialName"/></td>
									<td style="text-align: center;"><s:property value="specifications"/></td>
									<td style="text-align: center;" class="count"><s:property value="count"/></td>
									<td style="text-align: center;"><s:property value="unit"/></td>
									<td style="text-align: right;">
										<span class="unitPrice"><s:property value="unitPrice"/></span>
										<input type="hidden" class="paymentType" value="<s:property value="paymentType"/>"/>
										<input type="hidden" class="supplier" value="<s:property value="supplier"/>"/>
										<input type="hidden" class="telephone" value="<s:property value="telephone"/>"/>
										<input type="hidden" class="fax" value="<s:property value="fax"/>"/>
									</td>
									<td style="text-align: right;" class="cost"><s:property value="cost"/></td>
									<td style="text-align: center;">
										<input type="hidden" class="purchaseItemId" name="purchaseItemId" value="<s:property value="purchaseItemId"/>"/>
										<a class="passReview submited subspended">复核</a>
										<a class="passReview reviewed">重新复核</a>
										<a class="subspend submited reviewed">暂不复核</a>
										<a class="discard submited reviewed subspended">删除</a>
										<a class="revert discarded" >恢复</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
						<tbody class="groupItem" id="subspended">
							<tr><td colspan="10">暂不复核的采购单项</td></tr>
							<s:iterator value="purchase.purchaseItems.{?#this.status == 3}" status="sta">
								<tr id="<s:property value="purchaseItemId"/>_tr">
									<td style="text-align: center;" class="projectId"><s:property value="projectId"/></td>
									<td style="text-align: center;"><s:property value="projectName"/></td>
									<td style="text-align: center;"><s:property value="materialName"/></td>
									<td style="text-align: center;"><s:property value="specifications"/></td>
									<td style="text-align: center;" class="count"><s:property value="count"/></td>
									<td style="text-align: center;"><s:property value="unit"/></td>
									<td style="text-align: right;">
										<span class="unitPrice"><s:property value="unitPrice"/></span>
										<input type="hidden" class="paymentType" value="<s:property value="paymentType"/>"/>
										<input type="hidden" class="supplier" value="<s:property value="supplier"/>"/>
										<input type="hidden" class="telephone" value="<s:property value="telephone"/>"/>
										<input type="hidden" class="fax" value="<s:property value="fax"/>"/>
									</td>
									<td style="text-align: right;" class="cost"><s:property value="cost"/></td>
									<td style="text-align: center;">
										<input type="hidden" class="purchaseItemId" name="purchaseItemId" value="<s:property value="purchaseItemId"/>"/>
										<a class="passReview submited subspended">复核</a>
										<a class="passReview reviewed">重新复核</a>
										<a class="subspend submited reviewed">暂不复核</a>
										<a class="discard submited reviewed subspended">删除</a>
										<a class="revert discarded" >恢复</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
						<tbody class="groupItem" id="reviewed">
							<tr><td colspan="10">已复核的采购单项</td></tr>
							<s:iterator value="purchase.purchaseItems.{?#this.status == 2}" status="sta">
								<tr id="<s:property value="purchaseItemId"/>_tr">
									<td style="text-align: center;" class="projectId"><s:property value="projectId"/></td>
									<td style="text-align: center;"><s:property value="projectName"/></td>
									<td style="text-align: center;"><s:property value="materialName"/></td>
									<td style="text-align: center;"><s:property value="specifications"/></td>
									<td style="text-align: center;" class="count"><s:property value="count"/></td>
									<td style="text-align: center;"><s:property value="unit"/></td>
									<td style="text-align: right;">
										<span class="unitPrice"><s:property value="unitPrice"/></span>
										<input type="hidden" class="paymentType" value="<s:property value="paymentType"/>"/>
										<input type="hidden" class="supplier" value="<s:property value="supplier"/>"/>
										<input type="hidden" class="telephone" value="<s:property value="telephone"/>"/>
										<input type="hidden" class="fax" value="<s:property value="fax"/>"/>
									</td>
									<td style="text-align: right;" class="cost"><s:property value="cost"/></td>
									<td style="text-align: center;">
										<input type="hidden" class="purchaseItemId" name="purchaseItemId" value="<s:property value="purchaseItemId"/>"/>
										<input type="hidden" name="purchaseItemId"/>
										<a class="passReview submited subspended">复核</a>
										<a class="passReview reviewed">重新复核</a>
										<a class="subspend submited reviewed">暂不复核</a>
										<a class="discard submited reviewed subspended">删除</a>
										<a class="revert discarded" >恢复</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
						<tbody class="groupItem" id="discarded">
							<tr><td colspan="10">已删除的采购单项</td></tr>
							<s:iterator value="purchase.purchaseItems.{?#this.status == 4}" status="sta">
								<tr id="<s:property value="purchaseItemId"/>_tr">
									<td style="text-align: center;" class="projectId"><s:property value="projectId"/></td>
									<td style="text-align: center;"><s:property value="projectName"/></td>
									<td style="text-align: center;"><s:property value="materialName"/></td>
									<td style="text-align: center;"><s:property value="specifications"/></td>
									<td style="text-align: center;" class="count"><s:property value="count"/></td>
									<td style="text-align: center;"><s:property value="unit"/></td>
									<td style="text-align: right;">
										<span class="unitPrice"><s:property value="unitPrice"/></span>
										<input type="hidden" class="paymentType" value="<s:property value="paymentType"/>"/>
										<input type="hidden" class="supplier" value="<s:property value="supplier"/>"/>
										<input type="hidden" class="telephone" value="<s:property value="telephone"/>"/>
										<input type="hidden" class="fax" value="<s:property value="fax"/>"/>
									</td>
									<td style="text-align: right;" class="cost"><s:property value="cost"/></td>
									<td style="text-align: center;">
										<input type="hidden" class="purchaseItemId" name="purchaseItemId" value="<s:property value="purchaseItemId"/>"/>
										<a class="passReview submited subspended">复核</a>
										<a class="passReview reviewed">重新复核</a>
										<a class="subspend submited reviewed">暂不复核</a>
										<a class="discard submited reviewed subspended">删除</a>
										<a class="revert discarded" >恢复</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="10">
									<div style=" float:left; line-height:20px; width:100%;"><span>共<b style="color:red;" id="totalCount"><s:property value="purchase.purchaseItems.size"/></b>条记录 </span><span id="totalCost"></span></div>  
								</td>
							</tr>
						</tfoot>
					</table>
					<div style="text-align: center;margin-top: 10px">
						<button id="upgradePurchaseBtn">修改采购单</button>
						<button id="printPurchaseBtn">打印采购单</button>
						<button id="backBtn">返回</button>
					</div>
					</div>
				</div>
			</div>
		</div>
		<div id="reviewFormDiv" title="填写复核信息">
			<form id="reviewForm">
				<fieldset>
					<label for="paymentType">支付类型</label>
					<select id="paymentType" name="paymentType" class="text">
						<option value="1">现金</option>
						<option value="2">支票</option>
						<option value="3">本票</option>
						<option value="4">电汇</option>
						<option value="5">待付</option>
						<option value="6">其他</option>
					</select>
					<label for="unitPrice">单价</label>
					<input id="unitPrice" name="unitPrice" class="text"/>
					<label for="supplier">供应商</label>
					<input id="supplier" name="supplier" class="text"/>
					<label for="telephone">电话</label>
					<input id="telephone" name="telephone" class="text"/>
					<label for="fax">传真</label>
					<input id="fax" name="fax" class="text"/>
					<input type="hidden" id="purchaseItemId" name="purchaseItemId"/>
					<input type="hidden" id="projectId" name="projectId"/>
				</fieldset>
			</form>
		</div>
</body>
<script type="text/javascript">
$(function(){

	var validator = $("#reviewForm").validate({
		debug : true,
		rules : {
			"unitPrice" : {
				required : true,
				number : true
			}
		},
		messages : {
			"unitPrice" : {
				required : "请填写单价",
				number : "单价需为数字"
			}
		}
	});

	$("#upgradePurchaseBtn").click(function(){
		window.location.href = "toUpgrade.action?purchaseId=${purchase.purchaseId}";
	});
	$("#printPurchaseBtn").click(function(){
		//window.location.href = "print.action?purchaseId=${purchase.purchaseId}";
		window.open("print.action?purchaseId=${purchase.purchaseId}", "_blank", "channelmode=yes,location=no,menubar=no,status=no,toolbar=no");
	});
	$("#backBtn").click(function(){
		window.location.href = "reviewList.action";
	});
	repair();
	// 操作链接触发事件
	$("td a").click(function(){
		var dest;	// 当操作成功后，采购单项要移动的目标tobody
		var status;
		var purchaseItemId = $(this).siblings(".purchaseItemId").val();
		var projectId = $(this).parent().siblings(".projectId").text();
		if($(this).hasClass("passReview")){
			// 如果是复核，则弹出复核对话框，填写复核信息
			$("#purchaseItemId").val(purchaseItemId);
			$("#reviewFormDiv" ).dialog("open");
			return;
		} 
		// 否则不需要弹出对话框
		else if($(this).hasClass("subspend")){
			dest = $("#subspended");
			status = 3;
		} else if($(this).hasClass("discard")){
			dest = $("#discarded");
			status = 4;
		} else if($(this).hasClass("revert")){
			dest = $("#submited");
			status = 1;
		} else {
			return;
		}
		var $this = $(this);
		// 向服务器端发送复核信息，成功后将单项移到指定位置
		$.post("review.action", {"purchaseItem.status":status,
			"purchaseItem.purchaseId":"${purchase.purchaseId}",
			"purchaseItem.purchaseItemId":purchaseItemId,
			"purchaseItem.projectId":projectId}, function(success){
			if(success){
				$this.parent().parent().appendTo(dest);
				repair();
			} else {
				alert("操作失败！");
			}
		});
	});

	// 当单项列移动位置后，需要调整显示和隐藏的链接（即操作）
	function repair(){
		$("td a").each(function(i){
			if($(this).hasClass($(this).parent().parent().parent().attr("id"))){
				$(this).show();
			} else {
				$(this).hide();
			}
		});
		$("tbody tr:only-child").parent().hide();
		$("tbody tr:not(:only-child)").parent().show();
		var totalCost = 0.0;
		$("#reviewed .cost").each(function(j){
			totalCost += Number($(this).text());
		});
		$('#totalCost').text("总金额：" + totalCost + "￥");
	}
	$("#reviewFormDiv").dialog({
		autoOpen: false,
        modal: true,
        resizable: false,
        open: function(){
        	// 打开对话框时，从采购单项列中获取单项属性，填充到对话框表单中
			var $tr = $("#" + $("#purchaseItemId").val() + "_tr");
			$("#unitPrice").val($tr.find(".unitPrice").text());
			$("#paymentType").val($tr.find(".paymentType:hidden").val());
			$("#supplier").val($tr.find(".supplier:hidden").val());
			$("#telephone").val($tr.find(".telephone:hidden").val());
			$("#fax").val($tr.find(".fax:hidden").val());
			$("#projectId").val($tr.children(".projectId").text());
        },
        buttons: {
			"复核通过": function(){
				if(!$("#reviewForm").valid()){
					return;
				}
			
				$.post("review.action", {
						"purchaseItem.status":2,
						"purchaseItem.purchaseItemId":$("#purchaseItemId").val(),
						"purchaseItem.paymentType":$("#paymentType").val(),
						"purchaseItem.unitPrice":$("#unitPrice").val(),
						"purchaseItem.supplier":$("#supplier").val(),
						"purchaseItem.telephone":$("#telephone").val(),
						"purchaseItem.fax":$("#fax").val(),
						"purchaseItem.purchaseId":"${purchase.purchaseId}",
						"purchaseItem.projectId":$("#projectId").val()
					}, 
					function(success){
						if(success){
							// 填充单价和总价信息
							var $tr = $("#" +$("#purchaseItemId").val() + "_tr");
							$tr.find(".unitPrice").text($("#unitPrice").val()).end()
								.find(".cost").text($("#unitPrice").val() * $tr.find(".count").text());
							$tr.appendTo($("#reviewed"));
							repair();
						} else {
							alert("操作失败！");
						}
						$("#reviewFormDiv").dialog("close");
					}
				);
				// 将表单值填充到采购单项列的相应位置
				$("#" +$("#purchaseItemId").val() + "_tr")
					.find(".paymentType:hidden").val($("#paymentType").val()).end()
					.find(".supplier:hidden").val($("#supplier").val()).end()
					.find(".telephone:hidden").val($("#telephone").val()).end()
					.find(".fax:hidden").val($("#fax").val());
			},
			"取消": function(){
				 $(this).dialog("close");
				 validator.resetForm();
			}
        }
	});
});
</script>
</html>