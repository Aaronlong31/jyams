<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑报价单</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/convertCurrency.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">报价单</a>编辑报价单
		</h3>
		<form action="save.action" id="addQuoteForm" method="post">
			<div class="main_content two_rows">
					<div class="main_content" style="overflow-x:auto">
						<table class="table_normal" style="width: 800px;">
	                 		<caption>QUOTATION<br/>报价单</caption>
	                 		<tr>
								<td colspan="4" class="errorMessage" id="message">
									<s:actionerror/>
								</td>
							</tr>
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
	                 			<td style="width: 9%;font-weight: bold;"><label for="clientName">TO：</label></td>
	                 			<td style="width: 51%; padding: 0;" colspan="3"><input name="quote.clientName" class="text" id="clientName" value="<s:property value="quote.clientName"/>" style="width: 99%;"/></td>
	                 			<td style="width: 15%;font-weight: bold;">Our Ref：</td>
	                 			<td style="width: 18%;"><s:property value="quote.id" /></td>
	                 		</tr>
	                 		<tr>
	                 			<td style="font-weight: bold;"><label for="attnId">Attn：</label></td>
	                 			<td colspan="3" style="padding: 0;"><input name="quote.attnName" id="attnName" class="text" value="<s:property value="quote.attnName"/>" style="width: 99%;"/></td>
	                 			<td style="font-weight: bold;">Date：</td>
	                 			<td><j:dateTimeFormat pattern="yyyy-MM-dd"/></td>
	                 		</tr>
	                 		<tr>
	                 			<td style="font-weight: bold;"><label for="ccId">CC：</label></td>
	                 			<td colspan="3" style="padding: 0;"><input name="quote.ccName" id="ccName" class="text" value="<s:property value="quote.ccName"/>" style="width: 99%;"/></td>
	                 			<td style="font-weight: bold;">Total Page(s)：</td>
	                 			<td>1</td>
	                 		</tr>
	                 		<tr>
	                 			<td style="font-weight: bold;"><label for="quoterId">From：<span class="red_star">*</span></label></td>
	                 			<td colspan="5" style="padding: 0;">
									<select name="quote.quoterId" id="quoterId">
										<option value="">请选择</option>
										<s:iterator value="quoters">
											<option value="${personId}"><s:property value="personName"/></option>
										</s:iterator>
									</select>
									<input type="hidden" name="quote.quoterName" id="quoterName"  value="<s:property value="quote.quoterName"/>"/>
									<input type="hidden" name="quote.quoteId"  value="<s:property value="quote.quoteId"/>"/>
								</td>
	                 		</tr>
	                 		<tr style="font-weight: bold;">
	                 			<td><label for="subject">Subject:<span class="red_star">*</span></label></td>
	                 			<td colspan="5" style="padding: 0;"><input name="quote.subject" id="subject" class="text" style="width: 700px;" value="<s:property value="quote.subject"/>"/></td>
	                 		</tr>
	                 		<tr>
	                 			<th style="width: 77px;">S/NO</th>
	                 			<th style="width: 310px;">Description of Item</th>
	                 			<th colspan="2" style="width: 80px;">Quantity<br>(数量|单位)</th>
	                 			<th>Unit price (RMB)</th>
	                 			<th>Total Amount  (RMB)</th>
	                 			<th><img id="add" src="../images/btn_add.gif" title="添加报价单项" /></th>
	                 		</tr>
	                 		<tbody id="itemBody">
	                 		<tr style="display: none;" id="itemTemplate" class="quoteItem">
								<td class="index" style="text-align:center;">0</td>
								<td style="padding: 0;"><input name="description" id="description" class="text myrequired"/></td>
								<td style="padding: 0;width: 40px;"><input name="count" id="count" class="text myrequired mynumber" style="width: 99%;"/></td>
								<td style="padding: 0;width: 40px;"><input name="unit" id="unit" class="text" style="width: 99%;"/></td>
								<td style="padding: 0;"><input name="unitPrice" id="unitPrice" class="text myrequired mynumber" style="width: 99%;"/></td>
								<td style="text-align: right"><label class="totalPrice"></label></td>
								<td style="text-align: center;">
									<img class="removeItem" src="../images/btn_del.gif" title="删除报价单项" />
								</td>
							</tr>
							<s:iterator value="quote.quoteItems" status="status">
								<tr class="quoteItem">
									<td class="index" style="text-align:center;">${status.index + 1}</td>
									<td style="padding: 0;"><input name="description" id="description" class="text myrequired" value="<s:property value="description"/>" style="width: 99%;"/></td>
									<td style="padding: 0;width: 30px;"><input name="count" id="count" class="text myrequired mynumber" value="<s:property value="count"/>" style="width: 99%;"/></td>
									<td style="padding: 0;width: 30px;"><input name="unit" id="unit" class="text" value="<s:property value="unit"/>" style="width:  99%;"/></td>
									<td style="padding: 0;"><input name="unitPrice" id="unitPrice" class="text myrequired mynumber" value="<s:property value="unitPrice"/>" style="width: 99%;"/></td>
									<td style="text-align: right"><label class="totalPrice">${totalPrice}</label></td>
									<td  style="text-align: center;">
										<img class="removeItem" src="../images/btn_del.gif" title="删除报价单项" />
									</td>
								</tr>
							</s:iterator>
							</tbody>
							<tr style="font-weight: bold;">
								<td colspan="4">
									<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount"><s:property value="quote.quoteItems.size"/></b>条报价单项 </span></div>  
								</td>
								<td>TOTAL:</td>
								<td style="text-align: right;" id="total"><s:property value="quote.totalPrice" /></td>
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
			<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
				<button type="submit" class="btn" id="addQuoteBut">确定</button>
				<button type="button" class="btn" id="cancel">取消</button>
			</div>
		</form>
	</div>	
</div>
</body>
<script type="text/javascript">
$(function(){
	$("#quoterId option[value=${quote.quoterId}]").attr("selected", "selected");
	$("#quoterId").change(function(){
		$("#quoterName").val($("#quoterId option:selected").text());
	});
	$("#add").click(function(){
		$("#itemTemplate").clone(true).show()
			.appendTo($("#itemBody"));
		createIndex();
		updateTotalPrice();
	});
	
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
		});
	});	
	$("#addQuoteForm").validate({
		rules:{
			"quote.quoterId" : {
				required : true
			},
			"quote.subject" : {
				required : true
			}
		},
		messages:{
			"quote.quoterId" : {
				required : "请选择报价人！"
			},
			"quote.subject" : {
				required : "请输入报价主题！"
			}
		},
		submitHandler : function(form){
			$(".myrequired:visible").focusout();
			if($(".myrequired:visible").next().length <= 0){
				var count = -1;
				$("#itemBody tr:visible").each(function(i){
					$(this).find("input").each(function(index){
						var fieldName = $(this).attr("name");
						$(this).attr("name", "quote.quoteItems[" + i + "]." + fieldName);
					});
					count = i;
				});
				if(count >= 0)
					form.submit();
				else 
					alert("请至少添加一个报价单项！");
			}
		}
	});
	function createIndex(){
		$("#itemBody tr .index").each(function(i){
			$(this).text(i);
		});
		$("#totalCount").text($("#itemBody tr .index").size() - 1);
	}
	function updateTotalPrice(){
		var total = 0.0;
		$("label.totalPrice:visible").each(function(){
			total += parseFloat($(this).text());
		});
		$("#total").text(total);
	}
	$(".removeItem").click(function(){
		$(this).parent().parent().remove();
		createIndex();
		updateTotalPrice();
	});

	$(".myrequired").focusout(function(){
		if($.trim($(this).val()).length <= 0 ){
			$("<label class='error'></label>").text("不能为空！").insertAfter($(this));
		}
	}).focusin(function(){
		$(this).next().remove();
	});
	$(".mynumber").focusout(function(){
		var intpatt = /^\d+\.?\d*$/;
		if(intpatt.test($(this).val()) == false){
			$("<label class='error'></label>").text("必须为数字！").insertAfter($(this));
		} else {
			var $tr = $(this).parent().parent();
			var $count = $tr.find("[name='count']").val();
			var $unitPrice = $tr.find("[name='unitPrice']").val();
			$tr.find(".totalPrice").text($unitPrice * $count);
			updateTotalPrice();
		}
	}).focusin(function(){
		$(this).next().remove();
	});
	$("#cancel").click(function(){
		history.back();
	});
});
</script>
</html>