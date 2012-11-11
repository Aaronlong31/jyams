<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="j" uri="/WEB-INF/jyams-tags.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无锡金业科技自动化管理系统</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
</head>

<body class="bodyStyle">
<div class="content contentwidth">
      	<div class="main_panel">
          	  <h3 class="crumbs">
               	<img src="../images/sidebar_h3_bg1.gif" alt="" />
               	当前位置：<a href="#">首页</a>采购供应商信息
              </h3>
              <div class="main_content">
                          <table class="table_normal">
                            	<thead>
                                	<tr>
                                		<th style="width:20%;text-align:center;">供应商名称</th>
                                        <th style="width:15%;text-align:center;">电话</th>
                                        <th style="width:15%;text-align:center;">传真</th>
                                        <th style="width:20%;text-align:center;">联系人</th>
                                        <th style="width:30%;text-align:center;">主营业务</th>
                                    </tr>
                                </thead> 
                                <tbody id="purchaseDetail">
                                  <s:iterator value="suppliers">
	                                    <tr>
	                                      <td style="text-align:center;" id="">
	                                      	<s:property value="supplier"/>
										  </td>
	                                      <td style="text-align:center;" id="">
	                                      	<s:property value="telephone"/>
	                                      </td>
	                                      <td style="text-align:center;" id="">
	                                      	<s:property value="fax"/>
	                                      </td>
	                                      <td style="text-align:center;" id="">
	                                      </td>
	                                      <td style="text-align:center;" id="">
	                                      </td>
	                                    </tr>
                                  </s:iterator>                              	
                                </tbody>
                                <tfoot>
                                	<tr>
                                    	<td colspan="5">
                                        </td>
                                    </tr>
                                </tfoot> 
                          </table>
                      </div>
          </div>
      </div>
<SCRIPT type="text/javascript">
$(function(){
});
</SCRIPT>
</body>
</html>
