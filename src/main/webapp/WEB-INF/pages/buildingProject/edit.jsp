<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>新增项目</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.ui.datepicker.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery-ui-1.8.2.custom.css" />
<script language="javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.core.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.datepicker.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/datepicker/jquery.ui.widget.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.bgiframe.min.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.ajaxQueue.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.autocomplete.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script>

</head>
<body>
<div class="container">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<h4>修改施工流程</h4>
			</div>
		</div>
	</div>
	<hr/>
	<form class="form-horizontal" action="${ctx}/buildingProject/${buildingProject.projectId}" method="POST" id="editBuildingProjectForm">
	  <fieldset>
	    <table class="table table-striped table-condensed">
	    	<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="projectId">项目编号<span class="red_star">*</span></label>
						<div class="controls">
							${buildingProject.projectId }
							<input type="hidden" name="_method" value="PUT"/>
							<input type="hidden" name="projectId" value="${buildingProject.projectId}"/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="projectName">项目名称<span class="red_star">*</span></label>
						<div class="controls">
							${buildingProject.project.projectName}
						</div>
					</div>
				</td>
			</tr>
	    	<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="estimateCost">预估成本<span class="red_star">*</span></label>
						<div class="controls">
							<input type="text" class="input-big" id="estimateCost" name="estimateCost" value="${buildingProject.estimateCost}">
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="contractPrice">合同总价</label>
						<div class="controls">
							<input type="text" class="input-big" id="contractPrice" name="contractPrice" value="${buildingProject.quoteId}"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="paidMoney">已付货款<span class="red_star">*</span></label>
						<div class="controls">
							<input type="text" class="input-big" id="paidMoney" name="paidMoney" value="${buildingProject.paidMoney}">
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="unpaidMoney">未付货款</label>
						<div class="controls">
							<input type="text" class="input-big" id="unpaidMoney" name="unpaidMoney" value="${buildingProject.unpaidMoney}"/>
						</div>
					</div>
				</td>
			</tr>
	      </table>
	    <div class="form-actions">
			<button type="submit" class="btn btn-primary">确定修改</button>
			<button type="button" class="btn" id="cancel">取消</button>
	    </div>
	  </fieldset>
	</form>
</div>
</body>
<script language="javascript">
	$(function() {
		$("#editBuildingProjectForm").validate({
			rules:{
				"buildingProject.estimateCost":{
					number:true
				},
				"buildingProject.contractPrice":{
					number:true
				},
				"buildingProject.estimateCost":{
					number:true
				},
				"buildingProject.contractPrice":{
					number:true
				}
			},
			messages:{
				"buildingProject.estimateCost":{
					number:"预估成本必须为数字！"
				},
				"buildingProject.contractPrice":{
					number: "合同总价必须为数字！"
				},
				"buildingProject.paidMoney":{
					number:"已付货款必须为数字！"
				},
				"buildingProject.unpaidMoney":{
					number: "未付货款必须为数字！"
				}
			}
		});
		$("#cancel").click(function(){
			history.back();
		});
	});
</script>
</html>