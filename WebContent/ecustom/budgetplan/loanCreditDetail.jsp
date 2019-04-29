<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.Calendar"%>

<%@ page import="java.util.*, ecustom.util.*" %>


<%
	String endDate = CustomUtil.dateFormat(new Date(), "yyyy-MM-dd");
	Calendar canlendar = Calendar.getInstance();
	canlendar.add(Calendar.MONTH, -1);
	String startDate = CustomUtil.dateFormat(canlendar.getTime(), "yyyy-MM-dd");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借款明细表</title>
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/ecustom/js/My97DatePicker/WdatePicker.js"></script>

<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="loanCreditDetail.js"></script>
<style type="text/css">
* {
	font-size: 12px;
}

.right {
	text-align: right;
}

.center {
	text-align: center;
}
</style>



</head>
<body>


   <div align="left" style="padding-Top: 30px;">
   <span style="padding: 10px;text-align: center; font-size: 20px; font-weight: 700;">授信统计明细表</span>
   </div>
   
    <div align="right" style="padding-bottom: 10px;">
	<span style="padding-right: 10px;">单位：元</span>
	</div>	

	<div style="text-align: right;padding-bottom: 10px;">
	<button id="btnExport">导出Excel</button>
   </div>


<div id="fundsdiv" align="center">
		<table id="dg" class="easyui-datagrid" data-options="fitColumns:false" style="width:auto;  height: 900; padding: 5px"  rownumbers="true" 
                singleselect="false">
			<thead>
          
            <tr>
            </tr>
        </thead>
		</table>
	</div>
</body>
</html>