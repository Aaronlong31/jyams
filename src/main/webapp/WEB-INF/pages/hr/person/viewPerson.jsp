<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script></head>
<title>人员详细信息</title>
</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a>查看员工
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<p class="table_bar">
                    	<button type="button" class="btn" id="modifyPersonBtn"><img src="../images/btn_edit.gif" alt="" />修改</button>
                    	<s:if test="%{(person.status & 1) == 1}">
                    		<button type="button" class="btn" id="dimissionPersonBtn"><img src="../images/btn_edit.gif" alt="" />离职</button>
                    	</s:if>
                    </p>
					<table class="table_normal">
						<tr>
							<td class="td_first">员工姓名:</td>
							<td><s:property value="person.personName" /></td>
						</tr>
						<tr>
							<td class="td_first">部门名称:</td>
							<td><s:property value="person.departmentName" /></td>
						</tr>
						<tr>
							<td class="td_first">手机号码:</td>
							<td><s:property value="person.mobilePhone" /></td>
						</tr>
						<tr>
							<td class="td_first">短号码:</td>
							<td><s:property value="person.shortNumber" /></td>
						</tr>
						<tr>
							<td class="td_first">鞋码:</td>
							<td><s:property value="person.shoeSize" /></td>
						</tr>
						<tr>
							<td class="td_first">工作服尺寸:</td>
							<td><s:property value="person.overallsSize" /></td>
						</tr>
						<tr>
							<td class="td_first">生日:</td>
							<td><s:property value="person.birthday" /></td>
						</tr>
						<tr>
							<td class="td_first">学历:</td>
							<td><s:property value="person.education" /></td>
						</tr>
						<tr>
							<td class="td_first">工种:</td>
							<td><s:property value="person.jobType" /></td>
						</tr>
						<tr>
							<td class="td_first">证书:</td>
							<td><s:property value="person.certificate" /></td>
						</tr>
						<tr>
							<td class="td_first">职称:</td>
							<td><s:property value="person.title" /></td>
						</tr>
						<tr>
							<td class="td_first">籍贯:</td>
							<td><s:property value="person.birthPlace" /></td>
						</tr>
						<tr>
							<td class="td_first">住宅电话:</td>
							<td><s:property value="person.housePhone" /></td>
						</tr>
						<tr>
							<td class="td_first">身份证号码:</td>
							<td><s:property value="person.idCardCode" /></td>
						</tr>
						<tr>
							<td class="td_first">户口所在地:</td>
							<td><s:property value="person.domicileLocus" /></td>
						</tr>
						<tr>
							<td class="td_first">现住地址:</td>
							<td><s:property value="person.currentAddress" /></td>
						</tr>
						<tr>
							<td class="td_first">薪资形式:</td>
							<td><s:property value="person.salaryTypeString" /></td>
						</tr>
						<tr>
							<td class="td_first">状态:</td>
							<td><s:property value="person.statusString" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#modifyPersonBtn").click(function(){
			location="input.action?personId=${person.personId}";
		});
	});
	$(document).ready(function(){
		$("#dimissionPersonBtn").click(function(){
			location="dimission.action?personId=${person.personId}";
		});
	});
</script>
</html>