<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无锡金业科技自动化管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/convertCurrency.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/linkPage.js"></script>
</head>
<body style="background:#fff" onselectstart="return false" oncontextmenu= "return false">

<security:authorize ifAllGranted="ROLE_添加项目">
</security:authorize>

<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
</security:authorize>

<div class="sidebar">

	<security:authorize ifAnyGranted="ROLE_查看施工流程,ROLE_添加施工流程,ROLE_施工流程报表,ROLE_查看在建项目,ROLE_查看在建项目状态,ROLE_在建项目报表,ROLE_添加派工,ROLE_查看派工,ROLE_查看我的当月派工">
	<div class="main_panel">
		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />项目管理<div class="open_panel"></div></h3>
		<ul class="sidebar_menu">
			<security:authorize ifAnyGranted="ROLE_查看施工流程, ROLE_添加施工流程, ROLE_施工流程报表">
			<li class="open_menu">
				<span class="link">施工流程</span>
				<ul class="sidebar_sub_menu">
					<security:authorize ifAllGranted="ROLE_查看施工流程">
					<li onclick="linkPages('project/list.action','content');"><span class="link">施工流程列表</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_添加施工流程">
					<li onclick="linkPages('project/input.action','content');"><span class="link">添加施工流程</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_施工流程报表">
					<li><span class="link"><a href="reportExcel.action?model=1">施工流程报表</a></span></li>
					</security:authorize>
					
					<li onclick="linkPages('project/listStatus.action','content');"><span class="link">施工流程状态控制</span></li>
				</ul>
			</li>
			</security:authorize>
			
			<security:authorize ifAnyGranted="ROLE_查看在建项目,ROLE_查看在建项目状态,ROLE_在建项目报表">
			<li class="open_menu">
				<span class="link">在建项目管理</span>
				<ul class="sidebar_sub_menu">
					<security:authorize ifAllGranted="ROLE_查看在建项目">
					<li onclick="linkPages('buildingProject/list.action','content');"><span class="link">在建项目列表</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_查看在建项目状态">
					<li onclick="linkPages('buildingProject/listStatus.action','content');"><span class="link">在建项目状态控制</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_查看在建项目状态">
					<li onclick="window.open('buildingProject/listHidden.action?status=128','content');"><span class="link">隐藏项目管理</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_在建项目报表">
					<li><span class="link"><a href="reportExcel.action?model=5">在建项目报表</a></span></li>
					</security:authorize>
				</ul>
			</li>
			</security:authorize>
			
			<security:authorize ifAnyGranted="ROLE_添加派工,ROLE_查看派工,ROLE_查看我的当月派工">
			<li class="open_menu">
				<span class="link">派工</span>
				<ul class="sidebar_sub_menu">
					<security:authorize ifAllGranted="ROLE_添加派工">
					<li onclick="linkPages('dispatch/input.action','content');"><span class="link">添加派工</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_查看派工">
					<li onclick="linkPages('dispatch/list.action','content');"><span class="link">派工列表</span></li>
					</security:authorize>
					<!-- <security:authorize ifAllGranted="ROLE_查看我的当月派工">
					</security:authorize> -->
					<li onclick="linkPages('dispatch/listMyMonthlyDispatchWorks.action','content');"><span class="link">查看每月派工</span></li>
				</ul>
			</li>
			</security:authorize>
		</ul>
	</div>
	</security:authorize>
	
	<security:authorize ifAnyGranted="ROLE_添加报价单,ROLE_添加报价单,ROLE_查看报价单">
	<div class="main_panel">
		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />报价管理<div class="open_panel"></div></h3>
		<ul class="sidebar_menu">
			<security:authorize ifAllGranted="ROLE_添加报价单">
			<li onclick="linkPages('quote/input.action','content');"><span class="link">填写报价单</span></li>
			</security:authorize>
			<security:authorize ifAllGranted="ROLE_添加报价单">
			<li onclick="linkPages('quote/toImport.action','content');"><span class="link">从excel导入报价单</span></li>
			</security:authorize>
			<security:authorize ifAllGranted="ROLE_查看报价单">
			<li onclick="linkPages('quote/list.action','content');"><span class="link">报价列表</span></li>
			</security:authorize>
		</ul>
	</div>
	</security:authorize>
	
	<security:authorize ifAnyGranted="ROLE_查看部门,ROLE_添加部门,ROLE_查看员工,ROLE_添加员工,ROLE_员工报表">
	<div class="main_panel">
		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />行政/人事管理<div class="open_panel"></div></h3>
		<ul class="sidebar_menu">
			<security:authorize ifAnyGranted="ROLE_查看部门,ROLE_添加部门">
			<li class="open_menu">
				<span class="link">部门</span>
				<ul class="sidebar_sub_menu">
				
					<security:authorize ifAllGranted="ROLE_查看部门">
					<li onclick="linkPages('department/list.action','content');"><span class="link">部门列表</span></li>
					</security:authorize>
					
					<security:authorize ifAllGranted="ROLE_添加部门">
					<li onclick="linkPages('department/input.action','content');"><span class="link">添加部门</span></li>
					</security:authorize>
				</ul>
			</li>
			</security:authorize>
			
			<security:authorize ifAnyGranted="ROLE_查看员工,ROLE_添加员工,ROLE_员工报表">
			<li class="open_menu">
				<span class="link">员工</span>
				<ul class="sidebar_sub_menu">
				
					<security:authorize ifAllGranted="ROLE_查看员工">
					<li onclick="window.open('person/list.action?status=1','content');"><span class="link">员工列表</span></li>
					</security:authorize>
					
					<security:authorize ifAllGranted="ROLE_添加员工">
					<li onclick="linkPages('person/input.action','content');"><span class="link">添加员工</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_员工工资列表">
					<li onclick="linkPages('salary/salary.action','content');"><span class="link">员工工资列表</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_员工报表">
					<li><span class="link"><a href="reportExcel.action?model=2">员工报表</a></span></li>
					</security:authorize>
				</ul>
			</li>
			</security:authorize>
			
			<!--<security:authorize ifAllGranted="ROLE_采购供应商信息查看">-->
			<li onclick="linkPages('system/inputMultiDay.action','content');"><span class="link">三工日设置</span></li>
			<!--</security:authorize>-->
		</ul>
	</div>
	</security:authorize>
	
	<security:authorize ifAnyGranted="ROLE_物料采购申请,ROLE_物料采购复核,ROLE_物料采购一审,ROLE_物料采购二审,ROLE_物料实际采购,ROLE_公司采购申请,ROLE_公司采购一审,ROLE_公司采购二审,ROLE_外包订单申请,ROLE_外包订单申请,ROLE_外包订单一审,ROLE_外包订单二审,ROLE_采购周报表,ROLE_采购月报表,ROLE_查看我的采购单,ROLE_采购单状态查看,ROLE_采购供应商信息查看">
	<div class="main_panel">
		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />采购管理<div class="open_panel"></div></h3>
		<ul class="sidebar_menu">
			<security:authorize ifAnyGranted="ROLE_物料采购申请,ROLE_物料采购复核,ROLE_物料采购一审,ROLE_物料采购二审,ROLE_物料实际采购">
			<li class="open_menu">
				<span class="link">物料采购</span>
				<ul class="sidebar_sub_menu">
					<security:authorize ifAllGranted="ROLE_物料采购申请">
					<li onclick="linkPages('purchase/material/input.action','content');"><span class="link">申请</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_物料采购复核">
					<li onclick="linkPages('purchase/material/reviewList.action','content');"><span class="link">复核</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_物料采购一审">
					<li onclick="linkPages('purchase/material/approveList.action','content');"><span class="link">审批</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_物料采购二审">
					<li onclick="linkPages('purchase/material/secondApproveList.action','content');"><span class="link">二审</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_物料实际采购">
					<li onclick="linkPages('purchase/material/arriveList.action','content');"><span class="link">实际采购</span></li>
					</security:authorize>
				</ul>
			</li>
			</security:authorize>
			
			<security:authorize ifAnyGranted="ROLE_公司采购申请,ROLE_公司采购一审,ROLE_公司采购二审">
			<li class="open_menu">
				<span class="link">公司采购</span>
				<ul class="sidebar_sub_menu">
					<security:authorize ifAllGranted="ROLE_公司采购申请">
					<li onclick="linkPages('purchase/company/input.action','content');"><span class="link">申请</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_公司采购一审">
					<li onclick="linkPages('purchase/company/approveList.action','content');"><span class="link">审批</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_公司采购二审">
					<li onclick="linkPages('purchase/company/secondApproveList.action','content');"><span class="link">二审</span></li>
					</security:authorize>
				</ul>
			</li>
			</security:authorize>
			
			<security:authorize ifAnyGranted="ROLE_外包订单申请,ROLE_外包订单一审,ROLE_外包订单二审">
			<li class="open_menu">
				<span class="link">外包订单</span>
				<ul class="sidebar_sub_menu">
					<security:authorize ifAllGranted="ROLE_外包订单申请">
					<li onclick="linkPages('purchase/order/input.action','content');"><span class="link">申请</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_外包订单一审">
					<li onclick="linkPages('purchase/order/approveList.action','content');"><span class="link">审批</span></li>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_外包订单二审">
					<li onclick="linkPages('purchase/order/secondApproveList.action','content');"><span class="link">二审</span></li>
					</security:authorize>
				</ul>
			</li>
			</security:authorize>
			
			<security:authorize ifAllGranted="ROLE_采购周报表">
			<li onclick="linkPages('purchase/listMyPurchases.action','content');"><span class="link">周报</span></li>
			</security:authorize>
			<security:authorize ifAllGranted="ROLE_采购月报表">
			<li onclick="linkPages('purchase/listMyPurchases.action','content');"><span class="link">月报</span></li>
			</security:authorize>
			<security:authorize ifAllGranted="ROLE_查看我的采购单">
			<li onclick="linkPages('purchase/listMyPurchases.action','content');"><span class="link">我的采购单</span></li>
			</security:authorize>
			<security:authorize ifAllGranted="ROLE_采购单状态查看">
			<li onclick="linkPages('purchase/listPurchaseStatus.action','content');"><span class="link">采购单状态表</span></li>
			</security:authorize>
			<security:authorize ifAllGranted="ROLE_采购供应商信息查看">
			<li onclick="linkPages('purchase/listSuppliers.action','content');"><span class="link">采购供应商信息</span></li>
			</security:authorize>
		</ul>
	</div>
	</security:authorize>
	
	<security:authorize ifAnyGranted="ROLE_查看客户,ROLE_添加客户">
	<div class="main_panel">
		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />客户管理<div class="open_panel"></div></h3>
		<ul class="sidebar_menu">
			<security:authorize ifAllGranted="ROLE_查看客户">
			<li onclick="linkPages('client/list.action','content');"><span class="link">客户列表</span></li>
			</security:authorize>
			<security:authorize ifAllGranted="ROLE_添加客户">
			<li onclick="linkPages('client/input.action','content');"><span class="link">添加客户</span></li>
			</security:authorize>
		</ul>
	</div>
	</security:authorize>
	
	<security:authorize ifAnyGranted="ROLE_查看操作员,ROLE_修改密码,ROLE_查看操作日志">
	<div class="main_panel">
		<h3><img src="images/sidebar_h3_bg1.gif" alt="" /><img src="images/icon_human.gif" alt="" class="h3_img" />系统管理<div class="open_panel"></div></h3>
		<ul class="sidebar_menu">
			<security:authorize ifAnyGranted="ROLE_查看操作员,ROLE_修改密码">
			<li class="open_menu">
				<span class="link">操作员管理</span>
				<ul class="sidebar_sub_menu">
				
					<security:authorize ifAllGranted="ROLE_查看操作员">
					<li onclick="linkPages('user/list.action','content');"><span class="link">操作员列表</span></li>
					</security:authorize>
					
					<security:authorize ifAllGranted="ROLE_修改密码">
					<li onclick="linkPages('user/toModifyPassword.action','content');"><span class="link">修改密码</span></li>
					</security:authorize>
					
				</ul>
			</li>
			</security:authorize>
			
			<security:authorize ifAllGranted="ROLE_查看操作日志">
			<li onclick="linkPages('operateLog/list.action','content');"><span class="link">操作日志</span></li>
			</security:authorize>
		</ul>
	</div>
	</security:authorize>
</div>
</body>
</html>