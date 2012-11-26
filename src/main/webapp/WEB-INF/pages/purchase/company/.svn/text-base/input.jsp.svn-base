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
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<style type="text/css">
label{font-weight: bold;font-size:13px;margin: 7px 0 3px 0;}
input.text, select {width:200px;height:25px;}
fieldset { padding:0; border:0; margin-top:15px; }
h1 { font-size: 1.2em; margin: .6em 0; }
dt{float:left;width:100px;height:25px;line-height: 25px;text-align: right;padding-right: 10px;margin-bottom: 10px;}
dd{float:left;width:210px;margin-bottom: 10px;}
#templateItem{display: none}
</style>
</head>

<body class="bodyStyle">
<div class="content contentwidth">
        	<div class="main_panel">
            	<h3 class="crumbs">
                	<img src="../images/sidebar_h3_bg1.gif" alt="" />
                	当前位置：<a href="#">首页</a>编辑采购单
                </h3>
                <form action="save.action" method = "post" id="saveCompanyPurchase"> 
                	<input type="hidden" name="purchase.purchaseId" value="${purchase.purchaseId}"/> 
                	<span class="error"><s:actionerror/></span>
	                <div class="main_content two_rows">
	                        <div class="main_content">
	                            <table class="table_normal">
	                            	<thead>
	                            		<tr>
	                            			<td colspan="6" style="text-align: center;font-weight: bolder;font-size: larger;">物料申购单</td>
	                            		</tr>
	                            	</thead>
	                            	<tr>
	                                	<td class="td_first">申购人：<span class="red_star">*</span></td>
	                                	<td>
											<s:property value="loginUsername"/>
										</td>
										<td class="td_first">指定到货日期：<span class="red_star">*</span></td>
	                                	<td>
	                                		<input type="text" class="text" value="<j:dateTimeFormat value="purchase.arrivalDate" pattern="yyyy-MM-dd"/>" id="arrivalDate" name="arrivalDate" readonly/>
										</td>
										<td class="td_first"><span class="red_star">&nbsp;</span></td>
	                                	<td>
										</td>
	                                </tr>
	                                <tr>
	                                	<td class="td_first">到货地：<span class="red_star">*</span></td>
	                                	<td>
	                                		<input type="text" class="text" value="<s:property value="purchase.deliveryAddress"/>" id="deliveryAddress" name="purchase.deliveryAddress" maxlength="251"/>
										</td>
	                                    <td class="td_first">指定收货：<span class="red_star">*</span></td>
	                                	<td>
	                                		<select name="consignee" id="consignee">
	                                			<option value="">请选择</option>
	                                			<s:iterator value="persons">
	                                				<option value="${personId}_<s:property value="personName"/>"><s:property value="personName"/></option>
	                                			</s:iterator>
	                                		</select>
										</td>
										<td class="td_first">采购经办：<span class="red_star">&nbsp;</span></td>
	                                	<td>
	                                		<input type="text" class="text" value="<s:property value="purchase.purchaserName"/>" id="purchaserName" name="purchase.purchaserName" disabled="disabled"/>
										</td>
	                                </tr>
	                            </table>
	                        </div>
	                        <div class="main_content">
	                        	<p class="table_bar">
	                                <button type="button" class="addPurchaseItemBtn">添加清单</button>
	                                <label id="addPurchaseItemError" style="color: red;"></label>
	                            </p>
								<table class="table_normal">
	                            	<thead>
	                                	<tr>
	                                		<th style="width:10%;text-align:center;">品名</th>
	                                		<th style="width:10%;text-align:center;">规格/型号</th>
	                                		<th style="width:10%;text-align:center;">数量</th>
	                                        <th style="width:5%;text-align:center;">单位</th>
	                                		<th style="width:10%;text-align:center;">单价</th>
	                                		<th style="width:10%;text-align:center;">金额</th>
	                                        <th style="width:15%;text-align:center;">备注</th>
	                                        <th class="td_center" style="width:12%">操作</th>
	                                    </tr>
	                                </thead> 
	                                <tbody id="purchaseDetail">
	                                	<tr id="templateItem">
                                			<td><input style="width:99%" name="materialName" class="text materialName" value="" disabled="disabled"/></td>
                                			<td><input style="width:99%" name="specifications" class="text specifications" value="" disabled="disabled"/></td>
                                			<td><input style="width:99%" name="count" class="text count" value="" disabled="disabled"/></td>
                                			<td><input style="width:99%" name="unit" class="text unit" value="" disabled="disabled"/></td>
                                			<td><input style="width:99%" name="unitPrice" class="text unitPrice" value="" disabled="disabled"/></td>
                                			<td><input style="width:99%" name="cost" class="text cost" value="" disabled="disabled"/></td>
                                			<td><input style="width:99%" name="remark" class="text remark" value="" disabled="disabled"/>
                                				<input type="hidden" name="paymentType" class="paymentType" value=""/>
                                				<input type="hidden" name="supplier" class="supplier" value=""/>
                                				<input type="hidden" name="telephone" class="telephone" value=""/>
                                				<input type="hidden" name="fax" class="fax" value=""/>
                                				<input type="hidden" name="purchaseItemId" class="purchaseItemId" value=""/>
                                			</td>
                                			<td align="center">
                                				<button class="updateItem">修改</button>
                                				<button class="deleteItem">删除</button>
                                			</td>
                                		</tr>
	                                	<s:iterator value="purchase.purchaseItems" status="index">
                                		<tr id="<s:property value="purchaseItemId"/>">
                                			<td><input style="width:99%;" name="materialName" class="text materialName myrequired" value="<s:property value="materialName"/>"/></td>
                                			<td><input style="width:99%" name="specifications" class="text specifications myrequired" value="<s:property value="specifications"/>"/></td>
                                			<td><input style="width:99%" name="count" class="text count myrequired mynumber" value="<s:property value="count"/>"/></td>
                                			<td><input style="width:99%" name="unit" class="text unit myrequired" value="<s:property value="unit"/>"/></td>
                                			<td><input style="width:99%" name="unitPrice" class="text unitPrice myrequired" value="<s:property value="unitPrice"/>"/></td>
                                			<td><input style="width:99%" name="cost" class="text cost myrequired" value="<s:property value="cost"/>"/></td>
                                			<td>
                                				<input style="width:99%" name="remark" class="text remark" value="<s:property value="remark"/>"/>
                                				<input type="hidden" name="paymentType" class="paymentType" value="<s:property value="paymentType"/>"/>
                                				<input type="hidden" name="supplier" class="supplier" value="<s:property value="supplier"/>"/>
                                				<input type="hidden" name="telephone" class="telephone" value="<s:property value="telephone"/>"/>
                                				<input type="hidden" name="fax" class="fax" value="<s:property value="fax"/>"/>
                                				<input type="hidden" name="status" class="status" value="<s:property value="status"/>"/>
                                				<input type="hidden" name="purchaseItemId" class="purchaseItemId" value="<s:property value="purchaseItemId"/>"/>
                                			</td>
                                			<td align="center">
                                				<button class="updateItem">修改</button>
                                				<button class="deleteItem">删除</button>
                                			</td>
                                		</tr>
	                                	</s:iterator>
                                		
	                                </tbody>
	                            </table>
	                             <button type="button" class="addPurchaseItemBtn">添加清单</button>
	                        </div>
	                </div>
	                <div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
	                	<button type="submit" id="addPurchaseBtn">保存为草稿</button>
	                	<button type="submit" id="addAndPublishPurchaseBtn">申请</button>
	                </div>
	                <input type="hidden" id="saveType" value="save" name="saveType" />
	                <input type="hidden" id="type" value="2" name="purchase.type" />
                </form>  
            </div>
        </div>
<div id="addItemFormDiv" title="填写采购单项信息">
	<form id="addItemForm">
		<fieldset>
			<dl>
				<dt><label for="materialName">品名：</label></dt>
				<dd><input id="materialName" name="materialName" value="" class="text"/></dd>
				<dt><label for="specifications">规格/型号：</label></dt>
				<dd><input id="specifications" name="specifications" value="" class="text"/></dd>
				<dt><label for="count">数量：</label></dt>
				<dd><input id="count" name="count" value="" class="text"/></dd>
				<dt><label for="unit">单位：</label></dt>
				<dd><input id="unit" name="unit" value="" class="text"/></dd>
				<dt><label for="unitPrice">单价：</label></dt>
				<dd><input id="unitPrice" name="unitPrice" class="text"/></dd>
				<dt><label for="paymentType">支付类型：</label></dt>
				<dd>
					<select id="paymentType" name="paymentType" class="text">
						<option value="1">现金</option>
						<option value="2">支票</option>
						<option value="3">本票</option>
						<option value="4">电汇</option>
						<option value="5">待付</option>
						<option value="6">其他</option>
					</select>
				</dd>
				<dt><label for="supplier">供应商：</label></dt>
				<dd><input id="supplier" name="supplier" class="text"/></dd>
				<dt><label for="telephone">电话：</label></dt>
				<dd><input id="telephone" name="telephone" class="text"/></dd>
				<dt><label for="fax">传真：</label></dt>
				<dd><input id="fax" name="fax" class="text"/></dd>
				<dt><label for="remark">备注：</label></dt>
				<dd><input id="remark" name="remark" class="text"/></dd>
			</dl>
			<input type="hidden" id="isAdd" value="1"/>
		</fieldset>
	</form>
</div>
</body>
<script type="text/javascript">
$(function(){

	$("#consignee").val("${purchase.consigneeId}_${purchase.consigneeName}");
	$("#arrivalDate").datepicker({ 
		showButtonPanel:true,
		showClearButton:true,
		clearText: '清除', 
		closeText: '关闭', 
		currentText: '今天',
		yearRange:'-10:+10',
		changeYear:true,
		changeMonth:true,
		dayNamesMin: ['日','一', '二', '三', '四', '五', '六' ],
        dateFormat:'yy-mm-dd',
        monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
    } );  
	$("#saveCompanyPurchase").validate({
		rules:{
			"arrivalDate":{
				required:true,
				dateISO:true
			},
			"purchase.deliveryAddress":{
				required:true,
				maxlength:250
			},
			"consignee":{
				required:true
			}
		},
		submitHandler: function(form){
			$("#purchaseDetail tr:visible, #purchaseDetail tr.flagDeleted").each(function(i){
				$(this).find("input").attr("name", function(j, name){
					return "purchase.purchaseItems[" + i + "]." + name;	
				});
			});
			form.submit();
		}
	});
	$("#addItemForm").validate({
		rules : {
			"materialName" : {
				required : true,
				rangelength : [1, 100]
			},
			"specifications" : {
				required : true,
				rangelength : [1, 10]
			},
			"count" : {
				required : true,
				rangelength : [1,10],
				range : [0, 100000]
			},
			"unit" : {
				required : true,
				rangelength : [1, 10]
			},
			"unitPrice" : {
				required : true,
				range : [0, 1000000]
			},
			"paymentType" : {
				required : true
			},
			"supplier" : {
				required : true,
				rangelength : [1, 100]
			},
			"telephone" : {
				required : true,
				number : true,
				rangelength : [11, 12]
			},
			"fax" : {
				number : true,
				rangelength : [11, 12]
			},
			"remark" : {
				rangelength : [0, 100]
			}
		},
		submitHandler : function(){
			$("#addItemFormDiv").dialog("close");
			var $item;
			if($("#isAdd").val() == 1){
				$item = $("#templateItem").clone(true).removeAttr("id");
				$item.find(":input").removeAttr("disabled")
					.attr("readonly", "readonly").end()
					.find(".status").val("0").end()
					.appendTo($("#purchaseDetail"));
			} else {
				$item = $("tr.selected");
			}
			$item.find(".cost").val($("#count").val() * $("#unitPrice").val());
			$("#addItemForm").find(":input").each(function(i){
				$item.find("."+this.id).val(this.value);
				this.value = "";
			});
			$("tr.selected").removeClass("selected");
			return false;
		}
	});

	$(".addPurchaseItemBtn").click(function(){
		$("#isAdd").val(1);
		$("#addItemFormDiv" ).dialog("open");
		return false;
	});
	$(".updateItem").click(function(){
		$("#isAdd").val(0);
		var $item = $(this).parent().parent().addClass("selected");
		$("#addItemForm").find(":input").each(function(i){
			this.value = $item.find("."+this.id).val();
		});
		$("#addItemFormDiv" ).dialog("open");
		return false;
	});
	$(".deleteItem").click(function(){
		$(this).parent().parent().remove();
		return false;
	});
	$("#addItemFormDiv").dialog({
		autoOpen: false,
        modal: true,
        resizable: false,
        width : 380,
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
			"确定": function(){
        		$("#addItemForm").submit();
			},
			"取消": function(){
				 $(this).dialog("close");
				 $("tr.selected").removeClass("selected");
			}
        }
	});
});
</script>
</html>

