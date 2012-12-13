require(["jquery", 
         "../bootstrap", 
         "../common", 
         "../jquery/jquery-ui-1.9.1.custom",
         "../i18n/grid.i18n",
         "../jquery/jqgrid/jquery.jqGrid"], function($) {
	$(function(){
		var ctx = getContextPath();
		$("#loggedUserList").jqGrid({
			url : ctx + "/security/loggedUser",
			datatype : "json",
			colNames : ["", "用户名", "登录IP", "登录时间"],
			caption : "已登录用户列表",
			colModel : [
			    {name : "userId", key : true, hidden : true, index : "userId", width : 150, align : "center"},
				{name : "username", index : "username", width : 150, align : "center"},
				{name : "ip", index : "ip", width : 150, align : "center"},
				{name : "loginTime", index : "loginTime", width : 150, align : "center"}
			],
			autowidth: true,
			rownumbers: true,
			gridview: true,
			height: 300,
			pager : "#loggedUserPager",
			viewrecords : true,
			sortorder: "desc",
			jsonReader: {
				repeatitems : false
			}
		});
	});
});
