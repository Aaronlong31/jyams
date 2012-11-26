<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门详细信息</title>
</head>
<body>
<table>
		<tr>
			<td>部门名称:<s:property value=department.departmentName/>
				
			</td>
		</tr>
		
		<tr>
			<td>上级部门名称:<s:property value=department.superName/>
				
			</td>
		</tr>
		
		<tr>
			<td>部门负责人姓名:<s:property value=department.principalName/>
				
			</td>
		</tr>
		
		</table>
</body>
</html>