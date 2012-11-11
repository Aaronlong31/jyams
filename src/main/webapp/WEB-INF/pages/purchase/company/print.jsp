<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司申购单</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<style type="text/css">
@MEDIA print {
	input,.deleted {display:none}
	table{border-collapse:collapse;border-spacing:0;}
	thead{display:table-header-group;}
	tfoot {display: table-footer-group;}
	td,.title{border: 1px #000 solid;font-weight: normal;}
	th{font-weight: normal;}
	th.title{font-weight: bold;}
}
.deleted {display:none}
table{border-collapse:collapse;border-spacing:0;}
thead{display:table-header-group;}
tfoot {display: table-footer-group;}
td,.title{border: 1px #000 solid;font-weight: normal;}
th{font-weight: normal;}
th.title{font-weight: bold;}
</style>
</head>
<body onload="pagesetup_null()">
<input type=button value=" 打 印 " onclick="javascript: window.print();"/>
<input type=button value=" 返 回 " onclick="javascript: history.back();"/>
<div style="vertical-align: middle;text-align: center;">
	<table style="margin: 0 auto;width:800px;">
		<thead>
			<tr>
				<th colspan="6" style="font-size: large;">无锡市金业科技有限公司</th>
			</tr>
			<tr>
				<th colspan="6" style="font-size: large;">WUXI JINYE TECHNOLOGY CO.,LTD</th>
			</tr>
			<tr>
				<th colspan="6" style="font-weight: bold;height:100px;font-size: 25px;vertical-align: middle;">物料申购单</th>
			</tr>
			<tr>
				<th colspan="6">
					<table width="100%">
						<tr>
							<th style="font-weight: bold;">申购人：</th>
							<th style="border-bottom: 1px #000 solid;"><s:property value="purchase.applierName" /></th>
							<th style="font-weight: bold;">申购日期：</th>
							<th style="border-bottom: 1px #000 solid;"><j:dateTimeFormat value="purchase.applyTimestamp" pattern="yyyy-MM-dd"/></th>
							<th style="font-weight: bold;">到货日期：</th>
							<th style="border-bottom: 1px #000 solid;"><j:dateTimeFormat value="purchase.arrivalDate" pattern="yyyy-MM-dd"/></th>
						</tr>
						<tr>
							<th style="font-weight: bold;">到货地：</th>
							<th style="border-bottom: 1px #000 solid;"><s:property value="purchase.deliveryAddress" /></th>
							<th style="font-weight: bold;">指定收货：</th>
							<th style="border-bottom: 1px #000 solid;"><s:property value="purchase.consigneeName" /></th>
							<th style="font-weight: bold;">采购经办：</th>
							<th style="border-bottom: 1px #000 solid;"><s:property value="purchase.purchaserName" /></th>
						</tr>
						<tr>
							<th colspan="6">&nbsp;</th>
						</tr>
					</table>
				</th>
			</tr>
			<tr id="head">
				<th class="title" style="width: 70px">序号</th>
				<th class="title" style="width: 250px">品名</th>
				<th class="title" style="width: 150px">规格/型号</th>
				<th class="title" style="width: 100px">数量</th>
				<th class="title" style="width: 100px">单位</th>
				<th class="title" style="width: 200px">备注</th>
			</tr>
		</thead>
		<tbody id="content">
			<s:iterator value="purchase.purchaseItems" status="sta">
				<!-- style="<s:if test="(#sta.index+1)%10 == 0">page-break-after: always;</s:if>" -->
				<tr class="<s:if test="status == 4">deleted</s:if>">
					<td style="text-align: center;"><s:property value="%{#sta.index + 1}"/></td>
					<td style="text-align: left;;"><s:property value="materialName"/></td>
					<td style="text-align: center;"><s:property value="specifications"/></td>
					<td style="text-align: center;"><s:property value="count"/></td>
					<td style="text-align: center;"><s:property value="unit"/></td>
					<td style="text-align: left;"><s:property value="remark"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>
</body>
<script type="text/javascript">
$(function(){
	$(".title").resizable({
		resize: function(event, ui){
			$('#content tr').height($('#head').height());
		}
	});
});
//设置网页打印的页眉页脚为空 
var hkey_root,hkey_path,hkey_key ;
hkey_root="HKEY_CURRENT_USER" ;
hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\" ;
//设置网页打印的页眉页脚为空
function pagesetup_null() { 
	try {
		var RegWsh = new ActiveXObject("WScript.Shell");
		//hkey_key = "header";
		//RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
		hkey_key="footer" ;
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"&b日期：&d  页码：&p/&P") ;
		hkey_key = "margin_bottom";
		RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.75000");
		hkey_key = "margin_left";
		RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.75000");
		hkey_key = "margin_right";
		RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.75000");
		hkey_key = "margin_top";
		RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "0.39370");
	} catch (e) {
		//alert(e.message);
	}
}
</script>
</html>