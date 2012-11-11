<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>新增派工信息</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script>
</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a><a href="#">项目</a>派工
		</h3>
		<form action="save.action" id="addDispatchForm" method="post">
			<div class="main_content two_rows">
					<div class="main_content">
						<table class="table_normal">
							<tr>
								<td colspan="4" class="errorMessage" id="message" style="color:red">
									<s:actionerror/>
									<s:actionmessage/>
									<s:fielderror></s:fielderror>
								</td>
							</tr>
							<tr>
								<td><label>选择项目：</label></td>
								<td>
									<select id="client">
										<option value="" id="allProject">选择客户</option>
										<s:iterator value="clients">
											<option id='client_${clientId}' value="${clientId}"><s:property value="clientName"/></option>
										</s:iterator>
									</select>
								</td>
								<td>
									<select name="dispatch.projectId" id="projectId">
										<option value="">选择项目编号</option>
										<s:iterator value="projects">
											<option value="${projectId}" clientId='${clientId}'>${projectId}</option>
										</s:iterator>
									</select>
								</td>
								<td>
									<select id="projectName" style="width: 300px;">
										<option value="">选择项目名称</option>
										<s:iterator value="projects">
											<option value="${projectId}" clientId='${clientId}'>${projectName}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="td_first"><label>派工类型：<span class="red_star">*</span></label></td>
								<td>
									<input type="radio" name="dispatch.dispatchType" id="dispatchType1" value="1" checked="checked"/>正常派工
									<input type="radio" name="dispatch.dispatchType" id="dispatchType2" value="2"/>加班派工<br>
								</td>
								<td class="td_first"><label for="dispatchDay">派工日期：<span class="red_star">*</span></label></td>
								<td><input name="dispatch.dispatchDayString" id="dispatchDay"/></td>
							</tr>
							<!-- 
							<tr>
								<td class="td_first">总花费（元）：</td>
								<td colspan="3"><span id="totalCost">0</span></td>
							</tr>
							 -->
						</table>
					</div>
					<div class="main_content">
						<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
							<s:iterator value="departments">
								<button type="button" class="btn2" name="departmentName" id="${departmentId}"><s:property value="departmentName"/></button>
							</s:iterator>
						</div>
						<s:iterator value="departments">
							<div id="department${departmentId}" style="display:none;" class="department">
								 <table class="table_normal">
								 	<thead>
								 		<tr>
											<th style="width:2%;text-align:center;">&nbsp;</th>
											<th style="width:12%;text-align:center;">员工姓名</th>
											<th style="width:14%;text-align:center;">开始时间</th>
											<th style="width:14%;text-align:center;">结束时间</th>
											<th style="width:12%;text-align:center;">个人小时数</th>
										</tr>
								 	</thead>
								 	<tbody>
								 		<s:iterator value="persons" >
											<tr height="33px">
												<td style="text-align: center;">
													<input type="checkbox" class="personId" name="personId" id="personId_${personId}" value="${personId}"/>
													<input type='hidden' name='personName' class="personName ncDisabled" value='<s:property value="personName"/>' disabled="disabled"/>
												</td>
												<td style="text-align: center;">
													<label for="personId_${personId}"><s:property value="personName"/></label>
												</td>
												<td style="text-align: right;">
													<select name="startTimeString" class="time startTime ncHide ncDisabled" disabled="disabled" style="display: none;"></select>
												</td>
												<td style="text-align: right;">
													<select name="endTimeString" class="time endTime ncHide ncDisabled" disabled="disabled" style="display: none;"></select>
													<span class="error" style="color: red;display: block;"></span>
												</td>
												<td style="text-align: right;">
													<span class="totalHours ncHide"></span>
												</td>
											</tr>
										</s:iterator>
								 	</tbody>
								 </table>
							</div>
						</s:iterator>
					</div>
					<div class="main_content" style="display: none;">
					<p class="table_bar">
	                    <button type="button" class="btn4" id="addTemp">添加临时员工</button>
	                </p>
						<table class="table_normal" id="tempPersonTable">
							<thead>
								<tr>
									<th style="width:10%;text-align:center;">姓名</th>
									<th style="width:15%;text-align:center;">身份证号码</th>
									<th style="width:10%;text-align:center;">日薪（元）</th>
									<th style="width:12%;text-align:center;">开始时间</th>
									<th style="width:12%;text-align:center;">结束时间</th>
									<th style="width:12%;text-align:center;">个人小时数（小时）</th>
									<th style="width:12%;text-align:center;">个人总花费（元）</th>
									<th style="width:10%;text-align:center;">操作</th>
								</tr>
							</thead>
							<tbody id="tempPesrsonDetail">
								<tr style="display: none;">
									<td><input name="personName" class="myrequired text" style="width: 100%;height: 23px;"/></td>
									<td><input name="idCardCode" class="myrequired text" style="width: 100%;height: 23px;"/></td>
									<td><input name="salary" class="myrequired mynumber salary text" style="width: 100%;height: 23px;"/></td>
									<td><select name="startTimeString" class="time startTime" style="width: 100%;height: 23px;"></select></td>
									<td>
										<select name="endTimeString" class="time endTime" style="width: 100%;height: 23px;"></select>
										<span class="error" style="color: red;"></span>
									</td>
									<td style="text-align:right;">
										<span class="totalHours"></span>
									</td>
									<td style="text-align:right;">
										<span class="totalCost"></span>
									</td>
									<td><a class="removeTemp" style="margin-right: 20px;"><img src="../images/btn_del.gif" alt="" />删除</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
				<button type="submit" class="btn" id="addPersonBut">确定</button>
				<button type="button" class="btn" id="cancel">取消</button>
			</div>
		</form>
	</div>
</div>

</body>
<script language="javascript">
	$(function() {

		// 将所有项目编号和项目名称保存起来
		var projects = $.map($("#projectName :not(:first-child)"),function(option){
			return {"id":option.value , "name":$(option).html(), "clientId":$(option).attr("clientId")};
		});
		$("#allProject").data("projects", projects);

		$.each($("#client :not(:first-child)"), function(index, client){
			var cps = $.map($("#projectName option[clientId='"+client.value+"']"), function(option){
				return {"id":option.value , "name":$(option).html(), "clientId":$(option).attr("clientId")};
			});
			if(cps == null || cps.length == 0)
				$(client).addClass("remove");
			else
				$(client).data("projects", cps);
		});
		$("#client .remove").remove();
		
		$("#client").change(function(){
			var ps = $(this).find(":selected").data("projects");
			var clientId = $(this).find(":selected").val();
			$("#projectId :not(:first-child)").remove();
			$("#projectName :not(:first-child)").remove();
			if(ps == null) return;
			$.each(ps, function(index, p){
				$("<option value='"+p.id+"' clientId='"+p.clientId+"'>" + p.id + "</option>").appendTo($("#projectId"));
				$("<option value='"+p.id+"' clientId='"+p.clientId+"'>" + p.name + "</option>").appendTo($("#projectName"));
			});
			projectChange();
		});

		function projectChange(){
			$("#projectId").change(function(){
				$("#projectName").val(this.value);
				$("#client").val($(this).find(":selected").attr("clientId"));
			});
			$("#projectName").change(function(){
				$("#projectId").val(this.value);
				$("#client").val($(this).find(":selected").attr("clientId"));
			});
		}

		projectChange();
		/*
		$("#client").change(function(){
			var ps = $("#client_"+this.value).data();
			if(ps == null){
				$.postJSON("listBuilding_JSON.action",{"clientId":this.value}, function(data){
					ps = $.map(data.projects,function(project){
						return {"id":project.projectId , "name":project.projectName};
					});
					$("#client_" + this.value).data(ps);
				});
			}
			$.each(ps, function(index, p){
				$("#projectId :not(:first-child)").remove();
				$("#projectName :not(:first-child)").remove();
				$("<option id='"+p.id+"'>" + p.id + "</option>").appendTo($("#projectId"));
				$("<option id='"+p.id+"'>" + p.name + "</option>").appendTo($("#projectName"));
				$("#projectId").change(projectIdChange);
			});
		});
		*/
		$("#dispatchDay").datepicker({ 
			showButtonPanel:true,
			showClearButton:true,
			clearText: '清除', 
			closeText: '关闭', 
			currentText: '今天',
			yearRange:'-80:+80',
			changeYear:true,
			changeMonth:true,
			dayNamesMin: ['日','一', '二', '三', '四', '五', '六' ],
	        dateFormat:'yy-mm-dd',
	        monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
	        monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
	        onSelect: function (dateText, inst){
	        	$.postJSON("../system/selectMultiDay.action", {"day":dateText},function(data){
		        	if(data.multiDay != null){
			        	$("#message").text(data.multiDay.day + "是" + data.multiDay.times + "工日，请谨慎派工！");
		        	} else {
		        		$("#message").text("");
		        	}
	        	});
			}
	    } ); 
		$("button[name='departmentName']").click(function(){
			var departmentId = $(this).attr("id");
			$("div.department").hide();
			$("#department" + departmentId).show();
		});
		var times = ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", 
		     		 "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", 
		     		 "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
		     		 "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
		     		 "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
		     		 "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "24:00"];
		$.each(times, function(i, time){
			$("<option value='" + time + "'>" + time + "</option>").appendTo($("#tempPesrsonDetail select.time"));
		});
		
		$(".startTime").val("08:00");
		$(".entTime").val("17:00");
		$("#addDispatchForm").validate({
			rules:{
				"dispatch.dispatchDayString" :{
					required : true,
					dateISO : true
				},
				"dispatch.projectId":{
					required :true
				}
			},
			messages:{
				"dispatch.dispatchDayString" :{
					required : "请选择派工日期",
					dateISO : "日期不合法，格式：yyyy-MM-dd"
				},
				"dispatch.projectId":{
					required : "请选择项目"
				}
			},
			submitHandler : function(form){
				// 修改checkbox的name
				$(".myrequired:visible").focusout();
				if($(".myrequired:visible").next().length <= 0){
					var index = -1;
					$("td:has(input:checkbox:checked)").each(function(i){
						$(this).parent().find(":input").each(function(){
							var fieldName = $(this).attr("name");
							$(this).attr("name", "dispatch.dispatchWorks[" + i + "]." + fieldName);
						});
						index = i;
					});
					$("#tempPesrsonDetail tr:visible").each(function(i){
						var personNo = index + i + 1;
						$(this).find(":input").each(function(index){
							var fieldName = $(this).attr("name");
							$(this).attr("name", "dispatch.dispatchWorks[" + personNo + "]." + fieldName);
						});
						index = personNo;
					});
					if(index > -1)
						form.submit();
					else 
						alert("请至少添加一个施工人员");
				}
			}
		});
		$("#cancel").click(function(){
			history.back();
		});
		$(".removeTemp").click(function(){
			$(this).parent().parent().remove();
		});
		$("#addTemp").click(function(){
			$("#tempPersonTable tr:hidden").clone(true).show().appendTo($("#tempPesrsonDetail"));
		});
		$("input:checkbox").click(function(){
			var personId = $(this).attr("id").replace("personId", "");
			var $tr = $(this).parent().parent();
			$tr.find("select.time:empty").html($("#tempPesrsonDetail select.time:first").html());
			if(this.checked){
				$tr.find(".ncHide").show().end().find(".ncDisabled").removeAttr("disabled");
				$(".startTime").val("08:00");
				$(".endTime").val("17:00");
				$tr.find(".endTime").change();
			} else {
				$tr.find(".ncHide").hide().end().find(".ncDisabled").attr("disabled", "disabled");
			}
		});
		$(".mynumber").focusout(function(){
			var intpatt = /^\d+\.?\d*$/;
			if(intpatt.test($(this).val()) == false){
				$("<label class='error'></label>").text("必须为数字！").insertAfter($(this));
			} else {
				var $tr = $(this).parent().parent();
				var $count = $tr.find("[name='count']").val();
				var $unitPrice = $tr.find("[name='unitPrice']").val();
				$tr.find(".totalPrice").text($unitPrice * $count);
			}
		}).focusin(function(){
			$(this).next().remove();
		});
		$(".myrequired").focusout(function(){
			if($.trim($(this).val()).length <= 0 ){
				$("<label class='error'></label>").text("不能为空！").insertAfter($(this));
			}
		}).focusin(function(){
			$(this).next().remove();
		});
		$("select.startTime, select.endTime").change(function(){
			var $tr = $(this).parent().parent();
			var startTime = $tr.find("select.startTime").get(0).value.split(":");
			var endTime = $tr.find("select.endTime").get(0).value.split(":");
			var startHours = (startTime[0] * 60 + startTime[1] * 1)/60;
			var endHours = (endTime[0] * 60 + endTime[1] * 1)/60;
			if( startHours >= endHours){
				$tr.find("span.error").text("选择时间错误");
				$tr.find(".totalHours").text("");
			} else {
				$tr.find("span.error").text("");
				var hours = 0;
				if($tr.find("select.startTime:visible").size() > 0){
					// 去掉中午12点到13点时间
					hours = Math.max(12 - startHours, 0) + Math.max(endHours - 13, 0) 
						- Math.max(startHours - 13, 0) - Math.max(12 - endHours, 0);
				}
				$tr.find(".totalHours").text(hours);
			}
		});
	});
</script>
</html>