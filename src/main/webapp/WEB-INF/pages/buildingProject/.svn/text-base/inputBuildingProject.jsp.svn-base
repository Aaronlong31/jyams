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
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">项目</a>编辑在建项目
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<form action="save.action" method="post" id="addBuildingProjectForm">
						<input type="hidden" name="buildingProject.projectId" value='<s:property value="buildingProject.projectId"/>'>
				        <table class="table_normal">
				       		<tr>
								<td colspan="4" class="errorMessage" id="message">
									<s:actionerror/>
								</td>
							</tr>
							<tr>
								<td class="td_first">项目编号:</td>
								<td><s:property value="buildingProject.projectId" /></td>
								<td class="td_first">项目名称:</td>
								<td><s:property value="buildingProject.project.projectName" /></td>
							</tr>
							<tr>
								<td class="td_first">预估成本（元）:<span class="red_star">*</span></td>
								<td> 
									<input name="buildingProject.estimateCost" id="estimateCost" class="text" value='<s:property value="buildingProject.estimateCost"/>'/>
								</td>
								<td class="td_first">合同总价（元）：<span class="red_star">*</span></td>
								<td>
									<input name="buildingProject.contractPrice" id="contractPrice" class="text"  value='<s:property value="buildingProject.contractPrice"/>'/>
								</td>
							</tr>
							<tr>
								<td class="td_first">已付货款（元）:<span class="red_star">*</span></td>
								<td> 
									<input name="buildingProject.paidMoney" id="paidMoney" class="text" value='<s:property value="buildingProject.paidMoney"/>'/>
								</td>
								<td class="td_first">未付货款（元）：<span class="red_star">*</span></td>
								<td>
									<input name="buildingProject.unpaidMoney" id="unpaidMoney" class="text"  value='<s:property value="buildingProject.unpaidMoney"/>'/>
								</td>
							</tr>
				        </table>
						<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
							<button type="submit" class="btn" id="addProjectBut">确定</button>
							<button type="button" class="btn" id="cancel">取消</button>
						</div>
					</form>
		        </div>
			</div>
		</div>
</div>
</body>
<script language="javascript">
	$(function() {
		$("#addBuildingProjectForm").validate({
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