<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无锡金业科技自动化管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery-1.4.2.js"></script>
<script language="javascript">
function getMessager()
{
	var sFeatures = "height=300, width=520, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no";
	jQuery('a[rel="external"]').click( function() {
		window.open( jQuery(this).attr('href'), '消息提示', sFeatures );
		return false;
	}).click();
}
/*
jQuery(document).ready(function() {
	window.setInterval("getMessager()",60*60*1000);//1小时刷新1次
});
*/

</script>
</head>

<body>
<a href="attendance/listApplyOfWaitingForApproval.action" rel="external" style="display: none;" target="_blank">消息提示</a>
<div class="footer">
&nbsp;&nbsp;&nbsp;&nbsp;
</div>
</body>
</html>
