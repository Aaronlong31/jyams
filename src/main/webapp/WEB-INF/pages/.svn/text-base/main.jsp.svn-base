<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无锡金业科技自动化管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/convertCurrency.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/easywidgets/jquery.easywidgets.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/easywidgets/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.jclock.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$('#full_time').jclock({
		format: '%H:%M',
	    utcOffset: 0,
	    utc: false,
	    fontFamily: '',
	    fontSize: '',
	    foreground: '',
	    background: '',
	    seedTime: Number('${todayTime}'),
	    timeout: 1000 // 1000 = one second, 60000 = one minute
	});
	$('#short_time').jclock({
		format: '%Y-%m-%d %A %H:%M:%S',
	    utcOffset: 0,
	    utc: false,
	    fontFamily: '',
	    fontSize: '',
	    foreground: '',
	    background: '',
	    seedTime: Number('${todayTime}'),
	    timeout: 1000 // 1000 = one second, 60000 = one minute
	});
});
$(function(){
	$.fn.EasyWidgets({
	  	behaviour : {
	      useCookies : true
	    },
		i18n : {
	      editText : '编辑',
	      closeText : '关闭',
	      extendText : '展开',
	      collapseText : '折叠',
	      cancelEditText : '取消'
	    },
	 	callbacks : {
	  	  onChangePositions:function(positions){
						}
	  	}
	});
});
</script>
<style type="text/css">
.widget-place{
height:auto;
min-height:1000px;
}
/*这个是拖动的时候用来占位的样式*/
.widget-placeholder {
border:1px dashed #FF99CC;
}
#widget-place-1 span#full_time,#widget-place-2 span#short_time{ display: none;}
#widget-place-1 .index_clock b span{font-size: 40px;white-space: nowrap;}
</style>
</head>

<body>
<div class="content">
       	<div class="main_panel" style=" border:none">
               <div class="main_content" style="padding: 0px">  
                  <div class="index_left widget-place" id="widget-place-1">
                   <div class="sub_panel widget movable" id="identifierwidget-1"" >
                      <h4 class="bulletin widget-header"><div class="tittle">超支项目<div class="tittle_end"></div></div></h4>
                      <div class="main_content widget-content" style=" border:none; padding:0px">
                      	<s:if test="overrunProjects.size > 0">
                      		<div class="tableBox">
							<table class="table_normal" style="width: 800px;">
								<thead>
									<tr>
										<th style="width:60%;text-align:center;">项目名称</th>
										<th style="width:12%;text-align:center;">公司负责人</th>
										<th style="width:10%;text-align:center;">客户商</th>
										<th style="width:10%;text-align:center;">实际成本</th>
										<th style="width:10%;text-align:center;">预估成本</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="overrunProjects">
										<tr>
											<td style="text-align: left;">
												<a href="project/view.action?projectId=${projectId}&flag=1">${project.projectName}</a>
											</td>
											<td style="text-align: left;">${project.companyPrincipalName}</td>
											<td style="text-align: left;">${project.clientName}</td>
											<td style="text-align: left;">${actualCost}</td>
											<td style="text-align: left;">${estimateCost}</td>
										</tr>
									</s:iterator>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="11">
											<div style=" float:left; line-height:20px">
												<span>共<b style="color:red;" id="totalCount"><s:property value="overrunProjects.size"/></b>条记录 </span>
											</div>  
										</td>
									</tr>
								</tfoot>
							</table>
							</div>
                      	</s:if>
                      	<s:else>
							<div class="main_content_none">
		                     	<span>暂无记录！</span>
		                    </div>
                      	</s:else>		
					</div>
                  </div>
                   <div class="sub_panel widget movable" id="identifierwidget-2"" >
                      <h4 class="bulletin widget-header"><div class="tittle">延期项目<div class="tittle_end"></div></div></h4>
                      <div class="main_content widget-content" style=" border:none; padding:0px">
	                      <s:if test="delayProjects.size > 0">
                      		<div class="tableBox">
							<table class="table_normal" style="width: 800px;">
								<thead>
									<tr>
										<th style="width:50%;text-align:center;">项目名称</th>
										<th style="width:12%;text-align:center;">公司负责人</th>
										<th style="width:10%;text-align:center;">客户商</th>
										<th style="width:14%;text-align:center;">要求完工日</th>
										<th style="width:15%;text-align:center;">可延后时间</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="delayProjects">
										<tr>
											<td style="text-align: left;">
												<a href="project/view.action?projectId=${projectId}&flag=1"><s:property value="project.projectName"/></a>
											</td>
											<td style="text-align: left;"><s:property value="project.companyPrincipalName"/></td>
											<td style="text-align: left;"><s:property value="project.clientName"/></td>
											<td style="text-align: left;"><j:intDayFormat value="project.requiredCompletionDate"/></td>
											<td style="text-align: left;"><s:property value="project.canDelayDay"/></td>
										</tr>
									</s:iterator>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="11">
											<div style=" float:left; line-height:20px">
												<span>共
													<b style="color:red;" id="totalCount">
													<s:property value="delayProjects.size"/>
													</b>条记录 
												</span>
											</div>  
										</td>
									</tr>
								</tfoot>
							</table>
							</div>
                      	</s:if>
                      	<s:else>
							<div class="main_content_none">
		                     	<span>暂无记录！</span>
		                    </div>
                      	</s:else>
                      </div>
                  </div>
                   <div class="sub_panel widget movable" id="identifierwidget-3"" >
                      <h4 class="bulletin widget-header"><div class="tittle">报警项目<div class="tittle_end"></div></div></h4>
                      <div class="main_content widget-content" style=" border:none; padding:0px">
	                      <s:if test="alarmProjects.size > 0">
                      		<div class="tableBox">
							<table class="table_normal" style="width: 800px;">
								<thead>
									<tr>
										<th style="width:60%;text-align:center;">项目名称</th>
										<th style="width:12%;text-align:center;">公司负责人</th>
										<th style="width:10%;text-align:center;">客户商</th>
										<th style="width:10%;text-align:center;">实际成本</th>
										<th style="width:10%;text-align:center;">预估成本</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="alarmProjects">
										<tr>
											<td style="text-align: left;">
												<a href="project/view.action?projectId=${projectId}&flag=1">${project.projectName}</a>
											</td>
											<td style="text-align: left;">${project.companyPrincipalName}</td>
											<td style="text-align: left;">${project.clientName}</td>
											<td style="text-align: left;">${actualCost}</td>
											<td style="text-align: left;">${estimateCost}</td>
										</tr>
									</s:iterator>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="11">
											<div style=" float:left; line-height:20px">
												<span>共<b style="color:red;" id="totalCount"><s:property value="alarmProjects.size"/></b>条记录 </span>
											</div>  
										</td>
									</tr>
								</tfoot>
							</table>
							</div>
                      	</s:if>
                      	<s:else>
							<div class="main_content_none">
		                     	<span>暂无记录！</span>
		                    </div>
                      	</s:else>
                      </div>
                  </div>
                 
                  <div class="sub_panel widget movable" id="identifierwidget-4" >
                      <h4 class="pending widget-header"><div class="tittle">未到货物料<div class="tittle_end"></div></div></h4>
                      <div class="main_content widget-content" style=" border:none; padding:0px;">
                             <div class="main_content_none">
  	                               <span>暂无记录！</span>
                             </div>
                      </div>
                  </div>
                 
                  <div class="sub_panel widget movable" id="identifierwidget-5">
                      <h4 class="schedule  widget-header"><div class="tittle">每日派工情况<div class="tittle_end"></div></div></h4>
                      <div class="main_content widget-content" style="border:none; padding:0px">
	                      <s:if test="dailyDispatchs.size > 0">
							<div class="tableBox">
							<table class="table_normal" style="width: 800px;">
								<thead>
									<tr>
										<th style="width:9%;text-align:center;">员工名称</th>
										<th style="width:9%;text-align:center;">项目名称</th>
										<th style="width:9%;text-align:center;">员工类型</th>
										<th style="width:9%;text-align:center;">派工类型</th>
										<th style="width:9%;text-align:center;">施工开始时间</th>
										<th style="width:9%;text-align:center;">施工结束时间</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="dailyDispatchs" status="status">
										<s:iterator value="dispatchWorks">
											<tr>
												<td style="text-align: left;"><s:property value="personName"/></td>
												<td style="text-align: left;"><s:property value="projectName"/></td>
												<td style="text-align: left;"><s:property value="personTypeString"/></td>
												<td style="text-align: left;"><s:property value="dispatchTypeString"/></td>
												<td style="text-align: left;"><j:minuteFormat value="startTime"/></td>
												<td style="text-align: left;"><j:minuteFormat value="endTime"/></td>
											</tr>
										</s:iterator>
									</s:iterator>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="11">
											<div style=" float:left; line-height:20px"><span>共<b style="color:red;" id="totalCount">${totalCount}</b>条记录                        每页<b style="color:red;">${pageSize}</b></span>条</div>  
											<j:pages currentPage="%{currentPage}" cssClass="pagnation" totalPage="%{totalPage}" url="" showPageNumber="5">
											</j:pages>                               
										</td>
									</tr>
								</tfoot>
							</table>
							</div>
                      	</s:if>
                      	<s:else>
							<div class="main_content_none">
		                     	<span>暂无记录！</span>
		                    </div>
                      	</s:else>
                      </div>
                  </div>
                 </div>
                 <div class="index_right widget-place" id="widget-place-2">
            	  <div class="sub_panel widget movable" id="identifierwidget-6" >
                      <div class="main_content" style="padding:4px; margin-bottom:4px">
                          <div class="index_clock widget-header">
                          	<b><span id="full_time"></span><span id="short_time"></span></b>
                          </div>    
                          <div class="index_data widget-content">
                          	<j:intDayFormat pattern="yyyy年MM月dd日" displayLunarCalendar="true"/>&nbsp;
                          </div>                                 
                      </div>
                  </div>
                 
                  <div class="sub_panel widget movable" id="identifierwidget-7">
                      <h4  class="tool widget-header"><div class="tittle">常用工具<div class="tittle_end"></div></div></h4>
                      <div class="main_content widget-content" style=" border:none; padding:0px">
              				<ul class="toolbox">
              					<li><a href="reportExcel.action?model=1">施工流程报表</a></li>
              					<li><a href="reportExcel.action?model=2">员工信息报表</a></li>
              					<li><a href="reportExcel.action?model=4">采购（周）报表</a></li>
                            </ul>
                      </div>
                  </div>
           	 </div>            	                                                                
             </div>
           </div>
       </div>	  				                         
</body>
</html>
