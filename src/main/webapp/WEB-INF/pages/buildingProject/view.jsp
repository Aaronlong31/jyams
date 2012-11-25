<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-ui-1.9.1.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.js"></script>
<title>${ctitle} - 在建项目详情</title>
<style type="text/css">
td.td_first{
	text-align: right;
	font-weight: bold;
}
</style>
</head>
<body>
<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<h4>在建项目详情</h4>
			</div>
		</div>
	</div>
	<hr/>
	<a class="btn btn-warning" href="${ctx}/buildingProject/toEdit/${buildingProject.projectId}">编辑在建项目</a>
	<div id="message" class="alert alert-success">${message}</div>
	<table class="table table-striped table-condensed">
		<tr>
			<td class="td_first">项目编号:</td>
			<td>${buildingProject.projectId}</td>
			<td class="td_first">项目名称:</td>
			<td>${buildingProject.project.projectName}</td>
		</tr>
		<tr>
			<td class="td_first">公司负责人:</td>
			<td>${buildingProject.project.companyPrincipalName}</td>
			<td class="td_first">项目状态:</td>
			<td>${buildingProject.statusString}</td>
		</tr>
		<tr>
			<td class="td_first">客户:</td>
			<td>${buildingProject.project.clientName}</td>
			<td class="td_first">客户负责人:</td>
			<td>${buildingProject.project.clientPrincipalName}</td>
		</tr>
		<tr>
			<td class="td_first">施工地点:</td>
			<td>${buildingProject.project.constructPlace}</td>
			<td class="td_first">出订单日:</td>
			<td>${buildingProject.project.orderDate}</td>
		</tr>
		<tr>
			<td class="td_first">订单编号:</td>
			<td>${buildingProject.project.orderId}</td>
			<td class="td_first">报价单编号:</td>
			<td>${buildingProject.project.quoteId}</td>
		</tr>
		<tr>
			<td class="td_first">要求完工日:</td>
			<td>${buildingProject.project.requiredCompletionDate}</td>
			<td class="td_first">可延后时间:</td>
			<td>${buildingProject.project.canDelayDay}天</td>
		</tr>
		<tr>
			<td class="td_first">实际成本（RMB）:</td>
			<td>${buildingProject.actualCost}元</td>
			<td class="td_first">预估成本（RMB）:</td>
			<td>${buildingProject.estimateCost}元</td>
		</tr>
		<tr>
			<td class="td_first">利润率:</td>
			<td>${buildingProject.margin}</td>
			<td class="td_first">合同总价（RMB）:</td>
			<td>${buildingProject.contractPrice}元</td>
		</tr>
		<tr>
			<td class="td_first">已付款（RMB）:</td>
			<td>${buildingProject.paidMoney}</td>
			<td class="td_first">未付款（RMB）:</td>
			<td>${buildingProject.unpaidMoney}</td>
		</tr>
		<tr>
			<td class="td_first">创建:</td>
			<td>${buildingProject.project.creatorName}&nbsp;
				${buildingProject.project.createdTimestamp}
			</td>
			<td class="td_first">最后修改:</td>
			<td>
				${buildingProject.lastModifierName}&nbsp;
				${buildingProject.lastModifiedTimestamp}
			</td>
		</tr>
		<tr>
			<td class="td_first">开票:</td>
			<td>
				${buildingProject.invoicerName}&nbsp;
				${buildingProject.invoiceTimestamp}
			</td>
			<td class="td_first">完工:</td>
			<td>
				${buildingProjectcompletionPersonName}&nbsp;
				${buildingProject.completionTimestamp}
			</td>
		</tr>
		<tr>
			<td class="td_first">订单内容:</td>
			<td colspan="3">${buildingProject.project.orderContent}</td>
		</tr>
	</table>
	<hr>
	<h4><a name="detailList">在建项目明细列表</a></h4>
	<div id="message_box">
	<div id="message_template" class="alert"><a class="close" data-dismiss="alert">x</a><span class="msg"></span></div>
	</div>
	<a class="btn btn-primary" data-toggle="modal" href="#addDetailDiv">添加在建项目明细</a>
	<div id="detailDiv">
		<table id="detailList" title="在建项目明细列表"></table>
		<div id="detailPager"></div>
	</div>
	<div class="modal hide fade in" id="addDetailDiv">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">x</a>
			<h3>添加在建项目明细</h3>
		</div>
		<div class="modal-body">
			<div id="error-message"></div>
			<form action="#" class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="costType">花费类型</label>
					<div class="controls">
						<label class="radio"><input type="radio" name="costType" value="1" checked="checked"/>材料花费</label>
					    <label class="radio"><input type="radio" name="costType" value="4"/>其他花费</label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="cost">金额</label>
					<div class="controls">
						<input type="text" name="cost" class="input-big" id="cost" />
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<a href="#detailList" id="addDetail" class="btn btn-primary" >添加</a>
			<a href="#detailList" id="cancelAdd" class="btn">取消</a>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
$(function(){
	if($("#message").text() == ""){
		$("#message").hide();
	}
	$('#addDetailDiv').modal({
	    backdrop:true,
	    keyboard:true,
	    show : false
	});
	$("#message_template").hide();
	$('#cancelAdd').click(function(){
		$('#addDetailDiv').modal('hide');
	});
	var detailListGrid = $("#detailList").jqGrid({
		url : "${ctx}/buildingProject/${buildingProject.projectId}/detail",
		datatype : "json",
		colNames : ["日期", "经办人", "材料花费", "订单花费", "人工花费", "其他花费", "查看关联记录"],
		caption : "施工流程列表",
		colModel : [
			{name : "createdTimestamp", width : 100, align : "center"},
			{name : "personName",  width : 150, align : "center"},
			{name : "cost", width : 150, align : "center"},
			{name : "cost", width : 150, align : "center"},
			{name : "cost", width : 150, align : "center"},
			{name : "cost", width : 150, align : "center"},
			{name : "view", width : 150, align : "center"}
		],
		rowNum : 20,
		autowidth: true,
		rownumbers: true,
		gridview: true,
		height: 300,
		rowList : [10, 20, 30],
		pager : "#detailPager",
		emptyrecords: "没有记录！",
		viewrecords : true,
		sortorder: "desc",
		jsonReader: {
			repeatitems : false
		},
		gridComplete : function(){
			var ids = $("#projectList").jqGrid('getDataIDs');
			$.each(ids, function(i, id){
				var approval = "<a href=\"${ctx}/project/"+id+"\">查看</a>";
				$("#projectList").jqGrid('setRowData', id, {act:approval});
			});
		}
	}).jqGrid('navGrid','#detailPager',{edit:false,add:false,del:false,search:false});
	$('#addDetail').click(function(){
		$.ajax({
			url : "${ctx}/buildingProject/${buildingProject.projectId}/detail",
			async : false,
			data : {costType : $('input[name=costType]:checked').val(), cost : $('#cost').val()},
			type : "POST",
			dataType : "json",
			success : function(data, textStatus, jqXHR){
				detailListGrid.trigger("reloadGrid");
				$('#message_template').clone().addClass("alert-success").
					find(".msg").text('添加明细成功！').end().appendTo($("#message_box"));
				$('#addDetailDiv').modal('hide')
			},
			error : function(jqXHR, textStatus, errorThrown){
				$('#message_template').clone().addClass("alert-error")
					.find(".msg").text('添加明细失败：' + jqXHR).appendTo($("#error-message"));
			}
			
		});
	});
	
});
</script>
</html>