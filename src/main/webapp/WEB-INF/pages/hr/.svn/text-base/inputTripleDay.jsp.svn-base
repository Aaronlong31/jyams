<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置三工日</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.8.6.custom.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/ui/jquery-ui-1.8.6.custom.min.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script></head>
<style>
.daySpan{display:block;float:left;width:20%;}
</style>
<body>

<div class="content">
<div class="main_panel">
	    <h3 class="crumbs">
			<img src="../images/sidebar_h3_bg1.gif" alt="" />
			当前位置：<a href="#">首页</a>设置三工日
		</h3>
		<div class="main_content two_rows">
				<div class="main_content">
					<form action="saveMultiDay.action" method="post">
						<table  class="table_normal">
							<tr>
								<td class="td_first"><label for="departmentName">添加三工日:<span class="red_star">*</span></label></td> 
								<td>
									<div id="tripleDay"></div>
								</td>
							</tr>
							<tr>
								<td class="td_first"><label for="principalName">已选择的三工日：</label> </td>
								<td>
									<span id="tripleDays">
										<s:iterator value="multiDays">
											<span class='daySpan'>  <input type="checkbox" checked='checked' onclick='removeDay(this)' name="days" class="day" value="${day}"/> ${day}</span>
										</s:iterator>
									</span>
								</td>
							</tr>
						</table>
						<div style=" text-align:center; margin-top:10px;margin-bottom: 10px;">
							<button type="submit" class="btn" id="saveTripleDay">确定</button>
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
	$("#tripleDay").datepicker({
		//showButtonPanel : true,
		//showClearButton : true,
		clearText : '清除',
		closeText : '关闭',
		currentText : '今天',
		changeYear : true,
		changeMonth : true,
		selectOtherMonths:true,
		stepMonths:3,
		yearRange : '-0:+2',
		numberOfMonths:3,
		dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
		dateFormat : 'yy-mm-dd',
		monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月',
				'八月', '九月', '十月', '十一月', '十二月' ],
		monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月',
				'七月', '八月', '九月', '十月', '十一月', '十二月' ],
		onSelect : function(dateText, inst){
			if($(":checkbox[value='"+dateText+"']").length > 0){
				return;
			}
			var leftDay = "";
			var rightDay = "";
			$(".day").each(function(){
				if(this.value > dateText){
					rightDay = this.value;
					return false;
				}
			});
			var $day = $("<span class='daySpan'>  <input type='checkbox' checked='checked' onclick='removeDay(this)' name='days' class='day' value='" +dateText+ "'/>  " + dateText + "</span>");
			if(rightDay == "")
				$day.appendTo($("#tripleDays")).fadeIn("slow");
			else 
				$day.insertBefore($(":checkbox[value='"+rightDay+"']").parent()).fadeIn("slow");
		}
	});		
	
});
function removeDay(checkbox){
		$(checkbox).parent().fadeOut("slow", function(){
			$(this).remove();
		});;
	};
</script>
</html>