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
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${ctx}/js/convertCurrency.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<style type="text/css">
#templateItem{display: none;}
#purchaseDetail td{padding: 0;}
tbody.groupItem tr:first-child{text-align: center;font-weight: bold;}
fieldset { padding:0; border:0; margin-top:15px; }
.flagDeleted{display: none}
#addItemFormDiv{display: none;}
</style>
</head>

<body class="bodyStyle">
<div class="content contentwidth">
        	<div class="main_panel">
            	<h3 class="crumbs">
                	<img src="../images/sidebar_h3_bg1.gif" alt="" />
                	当前位置：<a href="#">首页</a>编辑采购单
                </h3>
                <form action="save.action" method = "post" id="addPurchaseForm"> 
                	<input type="hidden" name="purchase.purchaseId" value="${purchase.purchaseId}"/> 
                	<span class="error"><s:actionerror/></span>
	                <div class="main_content two_rows">
	                <div style="text-align: center;font-size: 25px;">外包订单</div>
	                <hr/>
	                        <div class="main_content">
	                        	<p class="table_bar">
	                                <button type="button" class="addPurchaseItemBtn">添加清单</button>
	                                <label id="addPurchaseItemError" style="color: red;"></label>
	                            </p>
								<table class="table_normal">
	                            	<thead>
	                                	<tr>
	                                		<th style="width:9%;text-align:center;"></th>
	                                		<!-- <th style="width:8%;text-align:center;">客户</th> -->
	                                        <th style="width:7%;text-align:center;">施工编号</th>
	                                        <th style="width:35%;text-align:center;">施工内容</th>
	                                		<th style="width:10%;text-align:center;">外包商</th>
	                                		<th style="width:10%;text-align:center;">外包工作内容</th>
	                                		<th style="width:5%;text-align:center;">数量</th>
	                                        <th style="width:5%;text-align:center;">单位</th>
	                                        <th style="width:5%;text-align:center;">单价</th>
	                                        <th class="td_center" style="width:12%">操作</th>
	                                    </tr>
	                                </thead> 
	                                <tbody id="purchaseDetail">
	                                	<tr id="templateItem">
                                			<td><button class="selectProjectBtn">选择项目</button></td>
                                			<td><input style="width:99%;border:none;" name="projectId" class="text projectId myrequired" value="<s:property value="projectId"/>" readonly="readonly" disabled="disabled"/></td>
                                			<td><input style="width:99%;border:none;" name="projectName" class="text projectName myrequired" value="<s:property value="projectName"/>" readonly="readonly" disabled="disabled"/></td>
                                			<td><input style="width:99%" name="supplier" class="text supplier myrequired" value="<s:property value="supplier"/>" disabled="disabled"/></td>
                                			<td><input style="width:99%" name="materialName" class="text materialName myrequired" value="<s:property value="materialName"/>" disabled="disabled"/></td>
                                			<td><input style="width:99%" name="count" class="text count myrequired mynumber" value="<s:property value="count"/>" disabled="disabled"/></td>
                                			<td><input style="width:99%" name="unit" class="text unit myrequired" value="<s:property value="unit"/>" disabled="disabled"/></td>
                                			<td><input style="width:99%" name="unitPrice" class="text unitPrice myrequired mynumber" value="<s:property value="unitPrice"/>" disabled="disabled"/>
                                				<input type="hidden" name="purchaseItemId" class="purchaseItemId" value="<s:property value="purchaseItemId"/>"/>
                                			</td>
                                			<td align="center"><button class="deleteItem">删除</button></td>
                                		</tr>
	                                	<s:iterator value="purchase.purchaseItems" status="index">
                                		<tr id="<s:property value="purchaseItemId"/>">
                                			<td><button class="selectProjectBtn">选择项目</button></td>
                                			<td><input style="width:99%;border:none;" name="projectId" class="text projectId myrequired" value="<s:property value="projectId"/>" readonly="readonly"/></td>
                                			<td><input style="width:99%;border:none;" name="projectName" class="text projectName myrequired" value="<s:property value="projectName"/>" readonly="readonly"/></td>
                                			<td><input style="width:99%" name="supplier" class="text supplier myrequired" value="<s:property value="supplier"/>"/></td>
                                			<td><input style="width:99%" name="materialName" class="text materialName myrequired" value="<s:property value="materialName"/>"/></td>
                                			<td><input style="width:99%" name="count" class="text count myrequired mynumber" value="<s:property value="count"/>"/></td>
                                			<td><input style="width:99%" name="unit" class="text unit myrequired" value="<s:property value="unit"/>"/></td>
                                			<td>
                                				<input style="width:99%" name="unitPrice" class="text unitPrice myrequired mynumber" value="<s:property value="unitPrice"/>"/>
                                				<input type="hidden" name="status" class="status" value="<s:property value="status"/>"/>
                                				<input type="hidden" name="purchaseItemId" class="purchaseItemId" value="<s:property value="purchaseItemId"/>"/>
                                			</td>
                                			<td align="center"><button class="deleteItem">删除</button></td>
                                		</tr>
	                                	</s:iterator>
                                		
	                                </tbody>
	                                <tfoot>
	                                	<tr>
	                                    	<td colspan="9">
	                                        </td>
	                                    </tr>
	                                </tfoot> 
	                            </table>
	                             <button type="button" class="addPurchaseItemBtn">添加清单</button>
	                        </div>
	                </div>
	                <div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
	                	<button type="submit" id="addPurchaseBtn">保存为草稿</button>
	                	<button type="submit" id="addAndPublishPurchaseBtn">申请</button>
	                </div>
	                <input type="hidden" id="saveType" value="save" name="saveType" />
	                <input type="hidden" id="type" value="3" name="purchase.type" />
                </form>  
            </div>
        </div>
<div id="selectProjectDiv" title="选择施工项目">
	<input type="hidden" id="selectedRow"/>
	<table id="projectList"></table>
	<div id="projectPager"></div>
</div>
<div id="addItemFormDiv" title="填写外包订单项信息">
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
	$("#addPurchaseForm").validate({
		rules:{
			/*"applier":{
				required:true
			},*/
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
		messages:{
			/*"applier":{
				required:"请选择申购人"
			},*/
			"arrivalDate":{
				required:"请填写到货日期",
				dateISO:"日期格式不正确，格式：yyyy-MM-dd"
			},
			"purchase.deliveryAddress":{
				required:"请输入到货地",
				maxlength:"到货地不超过250个字符"
			},
			"consignee":{
				required:"请选择收货人"
			}
		},
		submitHandler: function(form){

			if(validateItem()){
				$("#purchaseDetail tr:visible, #purchaseDetail tr.flagDeleted").each(function(i){
					$(this).find("input").attr("name", function(j, name){
						return "purchase.purchaseItems[" + i + "]." + name;	
					});
				});
				form.submit();
			} else {
				return false;
			}
		}
	});
	$("#addPurchaseBtn").click(function(){
		$("#saveType").val("save");
	});
	$("#addAndPublishPurchaseBtn").click(function(){
		$("#saveType").val("addAndPublish");
	});
	$(".deleteItem").click(function(){
		var $tr = $(this).parent().parent();
		if($tr.attr("id")){
			$tr.find(".status").val(10).end().addClass("flagDeleted");
		} else {
			$tr.addClass("hidden").find("input").attr("disabled", "disabled");
		}
		return false;
	});

	$(".addPurchaseItemBtn").click(function(){

		if(validateItem()){
			$("#purchaseDetail tr:not(.hidden):last").clone(true).removeAttr("id")
				.find("[readonly!='readonly']").val("").end()
				.find(":input").removeAttr("disabled").end()
				.find(".status").val("0").end()
				.find(".purchaseItemId").attr("disabled", "disabled").end()
				.appendTo($("#purchaseDetail"));
			
		}
		
	});
	$("#selectProjectDiv").dialog({
		autoOpen: false,
        modal: true,
        resizable: true,
        width: "auto",
        height: "auto",
		buttons : {
			"选择" : function () {
				selectProject($("#" + $("#selectedRow").val()));
			}
		}
	});
	$(".selectProjectBtn, .projectId, .projectName").click(function(){
		$(this).parent().parent().addClass("inSelect");
		$("#selectProjectDiv").dialog("open");
		return false;
	});
	$("#projectList").jqGrid({
		url : "${ctx}/project/listBasicProject.action",
		datatype : "json",
		colNames : ["客户", "项目编号", "项目名称"],
		colModel : [
			{name : "clientName", index : "clientName", width : 100, search: true,searchoptions :{sopt:["eq", "cn"]}},
			{name : "projectId", key : true, index : "projectId", width : 90, search: true,searchoptions :{sopt:["eq"]}, searchrules : {integer: true}},
			{name : "projectName", index : "projectName", width : 600, search: true,searchoptions :{sopt:["eq", "cn"]}},
		],
		rowNum : 20,
		width: "auto",
		height: 250,
		rowList : [10, 20, 30],
		pager : "#projectPager",
		viewRecords : true,
		sortorder: "desc",
		jsonReader: {
			repeatitems : false
		},
		ondblClickRow : function(rowid, iRow, iCol, e){
			var $tr = $($("#projectList").find("tr").get(iRow));
			selectProject($tr);
		},
		onSelectRow : function(rowid, status){
			$("#selectedRow").val(rowid);
		}
	});
	$("#projectList").jqGrid('navGrid','#projectPager',{edit:false,add:false,del:false,search:false});
	$("#projectList").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true});
	function selectProject($tr){
		$(".inSelect")//.find(".client").val($($tr.find("td").get(0)).text()).end()
			.find(".projectId").val($($tr.find("td").get(1)).text()).end()
			.find(".projectName").val($($tr.find("td").get(2)).text());
		$(".inSelect").removeClass("inSelect");
		$("#selectProjectDiv").dialog("close");
	}
});
function validateItem(){
	var valid = true;
	$("#purchaseDetail input:visible").each(function(i){
		if($(this).hasClass("myrequired")&&!validateNotBlank(this.value)){
			$("#addPurchaseItemError").text("施工内容、外包商、外包工作内容、数量	、单位、单价不能为空！");
			valid = false;
			return false;
		}
		if($(this).hasClass("mynumber")&&!validateNumber(this.value)){
			$("#addPurchaseItemError").text("数量需为数字！");
			valid = false;
			return false;
		}
	});
	if(valid){
		$("#addPurchaseItemError").text("");
	}
	
	return valid;
}
</script>
</html>

