<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加人员</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script language="javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.bgiframe.min.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.ajaxQueue.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/autoComplete/jquery.autocomplete.js"></script>
<script language="javascript" src="${ctx}/js/jquery/plugins/formValidator/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script></head>


</head>
<body>
<div class="content">
	<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a>编辑员工
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<form action="save.action" method="post" id="addPersonForm">
						<input type="hidden" name="person.personId" id="personId" value='<s:property value="person.personId"/>'/>
				        <table class="table_normal">
							<tr>
								<td class="td_first"><label for="personName">员工姓名:<span class="red_star">*</span></label></td>
								<td><input name="person.personName" id="personName" class="text noModify" value='<s:property value="person.personName"/>'/></td>
								<td class="td_first"><label for="departmentId">部门名称:<span class="red_star">*</span></label></td>
								<td>	
									<select name="person.departmentId" id="departmentId">
										<s:iterator value="departments">
											<option value="${departmentId}"  <s:if test="person.departmentId == departmentId">selected="selected"</s:if>>${departmentName}</option>
										</s:iterator>
									</select>
									<input type="hidden" name="person.departmentName" id="departmentName" value='<s:property value=""/>'/>
								</td>
							</tr>
							<tr>
								<td class="td_first"><label for="salaryType">薪资形式:<span class="red_star">*</span></label></td>
								<td>
									<select name="person.salaryType" id="salaryType">
										<s:if test="person.salaryType == 1"></s:if>
										<option value="1" <s:if test="person.salaryType == 1">selected="selected"</s:if>>日薪</option>
										<option value="2" <s:if test="person.salaryType == 2">selected="selected"</s:if>>月薪</option>
									</select>
								</td>
								<td class="td_first"><label for="salary">薪水：<span class="red_star">*</span></label></td>
								<td><input name="person.salary" id="salary" class="text" value='<s:property value="person.salary"/>'/></td>
							</tr>
							<tr>
								<td class="td_first"><label for="mobilePhone">手机号码:</label></td>
								<td><input name="person.mobilePhone" id="mobilePhone"  class="text" value='<s:property value="person.mobilePhone"/>'/></td>
								<td class="td_first"><label for="shortNumber">短号码:</label></td>
								<td><input name="person.shortNumber" id="shortNumber"  class="text" value='<s:property value="person.shortNumber"/>'/></td>
							</tr>
							<tr>
								<td class="td_first"><label for="shoeSize">鞋码:</label></td>
								<td><input name="person.shoeSize" id="shoeSize"  class="text" value='<s:property value="person.shoeSize"/>'/></td>
								<td class="td_first"><label for="overallsSize">工作服尺寸:</label></td>
								<td><input name="person.overallsSize" id="overallsSize"  class="text" value='<s:property value="person.overallsSize"/>'/></td>
							</tr>
							<tr>
								<td class="td_first"><label for="birthday">生日:</label></td>
								<td><input name="person.birthdayString" id="birthday"  class="text" value='<j:intDayFormat pattern="yyyy-MM-dd" value="person.birthday"/>'/></td>
								<td class="td_first"><label for="officeDate">就职时间：</label></td>
								<td><input name="person.officeDateString" id="officeDate" class="text" value='<j:intDayFormat pattern="yyyy-MM-dd" value="person.officeDate"/>'/></td>
							</tr>
					
							<tr>
								<td class="td_first"><label for="education">学历:</label></td>
								<td><input name="person.education" id="education" class="text" value='<s:property value="person.education"/>'/></td>
								<td class="td_first"><label for="jobType">工种:</label></td>
								<td><input name="person.jobType" id="jobType" class="text" value='<s:property value="person.jobType"/>'/></td>
							</tr>
							<tr>
								<td class="td_first"><label for="title">职称:</label></td>
								<td><input name="person.title" id="title" class="text" value='<s:property value="person.title"/>'/></td>
								<td class="td_first"><label for="birthPlace">籍贯:</label></td>
								<td><input name="person.birthPlace" id="birthPlace" class="text" value='<s:property value="person.birthPlace"/>'/></td>
							</tr>
					
							<tr>
								<td class="td_first"><label for="housePhone">住宅电话:</label></td>
								<td><input name="person.housePhone" id="housePhone" class="text" value='<s:property value="person.housePhone"/>'/></td>
								<td class="td_first"><label for="idCardCode">身份证号码:</label></td>
								<td><input name="person.idCardCode" id="idCardCode" class="text" value='<s:property value="person.idCardCode"/>'/></td>
							</tr>
							<tr>
								<td class="td_first"><label for="certificate">证书:</label></td>
								<td colspan="3">
									<textarea style="width: 700px;height: 50px;" id="certificate" name="person.certificate" class="text"><s:property value="person.certificate"/></textarea>
								</td>
							</tr>
							<tr>
								<td class="td_first"><label for="domicileLocus">户口所在地:</label></td>
								<td colspan="3">
									<textarea style="width: 700px;height: 50px;" name="person.domicileLocus" id="domicileLocus"><s:property value="person.domicileLocus"/></textarea>
								</td>
							</tr>
							<tr>
								<td class="td_first"><label for="currentAddress">现住地址:</label></td>
								<td colspan="3">
									<textarea style="width: 700px;height: 50px;" name="person.currentAddress" id="currentAddress"><s:property value="person.currentAddress"/></textarea>
								</td>
							</tr>
							<tr>
								<td class="td_first"><label>选择职位：</label></td>
								<td colspan="3">
									<div>
										<span id="personJobIds" style="display: none;"><s:property value="person.jobs.{jobId}"/></span>
										<ul style="list-style-type: none;">
											<s:iterator value="jobs">
												<li style="float: left;width: 30%;">
													<input type="checkbox" id='jobId<s:property value="jobId"/>' name="jobIds" value="${jobId}"/><label><s:property value="jobName"/></label>
												</li>
											</s:iterator>
										</ul>
									</div>
								</td>
							</tr>
				        </table>
						<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
							<button type="submit" class="btn" id="addPersonBut">确定</button>
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
		$("#departmentName").val($("#departmentId option:selected").text());
		$.postJSON("../person/listJobTypes_JSON.action", function(data){
			$("#jobType").autocomplete(data.jobTypes, {
				matchContains:true,
				minChars:0,
				formatItem:function(data){
					return data[0];
				}
			});
		});
		$("#birthday, #officeDate").datepicker({
					showButtonPanel : true,
					showClearButton : true,
					clearText : '清除',
					closeText : '关闭',
					currentText : '今天',
					maxDate : new Date(),
					yearRange : '-80:+0',
					changeYear : true,
					changeMonth : true,
					dateFormat : 'yy-mm-dd',
					dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
					dateFormat : 'yy-mm-dd',
					monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月',
							'八月', '九月', '十月', '十一月', '十二月' ],
					monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月',
							'七月', '八月', '九月', '十月', '十一月', '十二月' ]
				});
		$("#addPersonForm").validate( {
			rules : {
				"person.personName" : {
					required : true,
					minlength : 2
				},
				"person.departmentId" : {
					required : true
				},
				"person.salary" : {
					required : true,
					number : true
				},
				"person.mobilePhone" : {
					number:true,
					minlength:11
				},
				"person.housePhone" : {
					telephone : true
				},
				"person.idCardCode" : {
					minlength : 15,
					maxlength : 19
				}
			},
			messages : {
				"person.personName" : {
					required : '用户名不为空！',
					minlength : $.format("用户名至少要{0}个字符")
				},
				"person.departmentId" : {
					required : "部门不能为空！"
				},
				"person.salary" : {
					required : "薪水不能为空！",
					number : "薪水必须为数字！"
				},
				"person.mobilePhone" : {
					number:"手机号码必须是数字！",
					minlength:"手机长度必须是11位！"
				},
				"person.housePhone" : {
					telephone : "住宅电话不符合格式！"
				},
				"person.idCardCode" : {
					minlength : "至少15位",
					maxlength : "最多19位"
				}
			}
		});
		$("#cancel").click(function(){
			history.back();
		});
		$("#departmentId").change(function(){
			$("#departmentName").val($("#departmentId option:selected").text());
		});
		if($("#personJobIds").text() != ""){
			$.each(eval("(" + $("#personJobIds").text() + ")"), function(i, jobId){
				$("#jobId" + jobId).attr("checked", "checked");
			});
		}
		if($("#personId").val() > 0){
			$(".noModify").each(function(){
				$(this).attr("disabled", "disabled");
			});
		}
	});
</script>
</html>