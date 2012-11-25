<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${ctitle} - 新增派工</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.9.1.custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/validator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/validator/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.js"></script>
<style type="text/css">
#projectPager_right{width:0}
</style>
</head>
<body>
<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<h4>新增派工</h4>
			</div>
		</div>
	</div>
	<hr/>
	<div id="message" class="alert alert-error"></div>
	<form action="" class="form-horizontal ">
		<table class="table table-striped table-condensed">
	    	<tr>
				<td colspan="2">
					<div class="control-group">
						<label class="control-label" for="projectName">项目<span class="red_star">*</span></label>
						<div class="controls" style="width:100%">
							<span id="projectId" class="input-small"></span>
							<span id="projectName" class="span5"></span>
							<a class="btn btn-primary" data-toggle="modal" href="#selectProjectDiv">选择项目</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="companyPrincipalId">派工类型<span class="red_star">*</span></label>
						<div class="controls">
							<div class="btn-group" data-toggle="buttons-radio">
							  <button type="button" name="dispatchType" value="1" class="btn btn-primary active">正常派工</button>
							  <button type="button" name="dispatchType" value="2" class="btn">加班派工</button>
							</div>
						</div>
					</div>
				</td>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="constructPlace">派工日期</label>
						<div class="controls">
							<input name="dispatchDayString" id="dispatchDay" type="text" class="input-big"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<div class="modal hide fade in"  id="selectProjectDiv">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">x</a>
			<h3>选择施工项目</h3>
		</div>
		<div class="modal-body">
			<input type="hidden" id="selectedRow"/>
			<table id="projectList"></table>
			<div id="projectPager"></div>
		</div>
		<div class="modal-footer">
			<a href="#detailList" id="addDetail" class="btn btn-primary" >选择</a>
			<a href="#detailList" id="cancel" class="btn">取消</a>
		</div>
	</div>
</div>
</body>
<script language="javascript">
	$(function() {
		if($("#message").text() == ""){
			$("#message").hide();
		}
		$('#selectProjectDiv').modal({
		    backdrop:true,
		    keyboard:true,
		    show : false
		});
		$("#cancel").click(function(){
			$('#selectProjectDiv').modal("hide");
		});
		$("#selectProjectBtn, #projectId, #projectName").click(function(){
			$(this).parent().parent().addClass("inSelect");
			$("#selectProjectDiv").dialog("open");
			return false;
		});
		var projectListGrid = $("#projectList").jqGrid({
			url : "${ctx}/project/basic",
			datatype : "json",
			colNames : ["客户", "项目编号", "项目名称"],
			colModel : [
				{name : "clientName", index : "clientName", width : 100, search: true,searchoptions :{sopt:["eq", "cn"]}},
				{name : "projectId", key : true, index : "projectId", width : 90, search: true,searchoptions :{sopt:["eq"]}, searchrules : {integer: true}},
				{name : "projectName", index : "projectName", width : 322, search: true,searchoptions :{sopt:["eq", "cn"]}},
			],
			rowNum : 20,
			width: "auto",
			height: 300,
			rowList : [10, 20, 30],
			pager : "#projectPager",
			viewRecords : true,
			sortorder: "desc",
			jsonReader: {
				repeatitems : false
			}
		}).jqGrid('navGrid','#projectPager',{edit:false,add:false,del:false,search:false})
		.jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false})
		.navButtonAdd('#projectPager', {
			caption : "${searchYear - 1}",
			id : "preYear",
			buttonicon : "ui-icon-triangle-1-w",
			onClickButton : function (){
				updateSearchYear(-1);
			}
		})
		.navButtonAdd('#projectPager', {
			caption : "${searchYear}",
			id : "curYear"
		})
		.navButtonAdd('#projectPager', {
			caption : "${searchYear + 1}",
			id : "nextYear",
			buttonicon : "ui-icon-triangle-1-e",
			onClickButton : function (){
				updateSearchYear(1);
			}
		});
		
	function updateSearchYear(incre){
		$.ajax({
			data : {"increYear" : incre, "_method" : "PUT"},
			url : "${ctx}/project/updateSearchYear",
			type : "POST",
			success : function (data){
				$("#preYear, #curYear, #nextYear").each(function(){
					$(this).find(".ui-pg-div").html(Number($(this).text()) + incre);
				});
				projectListGrid.trigger("reloadGrid");
			}
		});
	}
	});
</script>
</html>