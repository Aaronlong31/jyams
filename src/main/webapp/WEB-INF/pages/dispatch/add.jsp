<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
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
.uneditable-input{cursor: pointer;}
.box{
	height: 500px;
	text-align: center;
}
ul{
	list-style-type: none;
	border: 1px solid #ddd;
	-webkit-border-radius: 4px 0 4px 0;
	-moz-border-radius: 4px 0 4px 0;
	border-radius: 4px 0 4px 0;
	margin:0px;
	overflow-y : auto;
	height: 456px;
}
li{
	height : 30px;
	cursor:move;
	text-align :center;
	color : #4F4F4F;
	background-color: #F5F5F5;
	border: 1px solid #ddd;
	margin: 2px;
	line-height: 30px;
	padding-left: 5px;
}
ul.department,ul.department-selected{padding: 20px;background-color: #ddd}
div.departments{
	text-align: center;
}
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
							<span id="projectId" class="input-small uneditable-input">${dispatch.projectId}</span>
							<span id="projectName" class="span5 uneditable-input">${dispatch.projectName}</span>
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
								<input type="hidden" id="dispatchType" value="${dispatch.dispatchType}"/>
								<button type="button" name="dispatchType" value="1" class="btn dispatchType">正常派工</button>
								<button type="button" name="dispatchType" value="2" class="btn dispatchType">加班派工</button>
							</div>
						</div>
					</div>
				</td>
				<td class="td_first">
					<div class="control-group">
						<label class="control-label" for="constructPlace">派工日期</label>
						<div class="controls">
							<input name="dispatchDayString" id="dispatchDay" value="${dispatch.dispatchDayString}" type="text" class="input-big"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div class="control-group departments">
	    	<div class="btn-group" data-toggle="buttons-radio">
	    		<button type="button" class="btn department active" value="0">全部</button>
	    		<c:forEach items="${departments}" var="d">
	    			<button type="button" class="btn department" value="${d.departmentId}">${d.departmentName}</button>
	    		</c:forEach>
	    	</div>
    	</div>
		<div class="container-fluid">
		  <div class="row-fluid">
		    <div class="span4 box">
			    <ul class="department" departmentId="0" onselectstart="return false">
		    	<c:forEach items="${departments}" var="d">
		    		<c:forEach items="${d.persons}" var="p">
		    			<li><span class="span3" personId="${p.personId }">${p.personName}</span></li>
		    		</c:forEach>
		    	</c:forEach>
			    </ul>
		    	<c:forEach items="${departments}" var="d">
			    	<ul class="department" style="display:none;" departmentId="${d.departmentId}" onselectstart="return false">
			    		<c:forEach items="${d.persons}" var="p">
			    			<li><span class="span3" personId="${p.personId }">${p.personName}</span></li>
			    		</c:forEach>
			    	</ul>
		    	</c:forEach>
		    </div>
		    <div class="span8 box">
		    	<ul class="department-selected" departmentId="0" onselectstart="return false">
			    </ul>
		    	<c:forEach items="${departments}" var="d">
			    	<ul class="department-selected" style="display:none;" departmentId="${d.departmentId}" onselectstart="return false">
			    	</ul>
		    	</c:forEach>
		    </div>
		  </div>
		</div>
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
		var times = ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", 
		     		 "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", 
		     		 "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
		     		 "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
		     		 "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
		     		 "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "24:00"];
		
		if($("#message").text() == ""){
			$("#message").hide();
		}
		$("ul.department").sortable({
            connectWith: ".department-selected",
            cursor: "move",
            receive: function( event, ui ) {
            	ui.item.find("select").remove();
            }
        });
		$("ul.department-selected").sortable({
            connectWith: ".department",
            cursor: "move",
            receive: function( event, ui ) {
			  	var startSelect = $("<select>").addClass("startTime span4");
			  	var endSelect = $("<select>").addClass("endTime span4");
			  	$.each(times, function(i, time){
					$("<option value='" + time + "'>" + time + "</option>").appendTo(startSelect);
					$("<option value='" + time + "'>" + time + "</option>").appendTo(endSelect);
				});
			  	startSelect.val("08:00");
			  	endSelect.val("17:00");
			  	ui.item.append(startSelect);
			  	ui.item.append(endSelect);
			}
        });
		$("button.department").click(function(){
			$("ul.department,ul.department-selected").hide();
			$("ul[departmentId=" + this.value + "]").show();
		});
		$('#selectProjectDiv').modal({
		    backdrop:true,
		    keyboard:true,
		    show : false
		});
		$(".dispatchType").click(function(){
			$(".dispatchType").removeClass("btn-primary");
			$(this).addClass("btn-primary");
			$.post("${ctx}/dispatch/session", {"_method" : "PUT", "dispatchType" : $(this).val()});
		});
		$(".dispatchType").each(function(){
			if(this.value == $("#dispatchType").val()){
				$(this).addClass("btn-primary active");
			}
		});
		
		$("#dispatchDay").change(function(){
			$.post("${ctx}/dispatch/session", {"_method" : "PUT", "dispatchDayString" : this.value});
		}).datepicker({ 
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
	        monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
	    } );  
		$("#cancel").click(function(){
			$('#selectProjectDiv').modal("hide");
		});
		$("#projectId, #projectName").click(function(){
			$('#selectProjectDiv').modal("show");
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
			},
			ondblClickRow : function(rowid, iRow, iCol, e){
				var $tr = $($("#projectList").find("tr").get(iRow));
				selectProject($tr);
			},
			onSelectRow : function(rowid, status){
				$("#selectedRow").val(rowid);
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
		$("#addDetail").click(function(){
			selectProject($("#" + $("#selectedRow").val()));
		});
		function selectProject($tr){
			var projectId = $($tr.find("td").get(1)).text();
			var projectName = $($tr.find("td").get(2)).text();
			$("#projectId").text(projectId).end()
			$("#projectName").text(projectName);
			$('#selectProjectDiv').modal("hide");
			saveDispatchProject(projectId, projectName)
		}
		function saveDispatchProject(projectId, projectName){
			$.post("${ctx}/dispatch/session", {"projectId" : projectId, "projectName" : projectName, "_method" : "PUT"});
		}
		function updateSearchYear(incre){
			$.post("${ctx}/project/updateSearchYear", {"increYear" : incre, "_method" : "PUT"}, function (data){
				$("#preYear, #curYear, #nextYear").each(function(){
					$(this).find(".ui-pg-div").html(Number($(this).text()) + incre);
				});
				projectListGrid.trigger("reloadGrid");
			});
		}
	});
</script>
</html>