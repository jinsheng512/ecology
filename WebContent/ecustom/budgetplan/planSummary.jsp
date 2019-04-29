<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*, ecustom.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资金计划汇总表</title>
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="planSummary.js"></script>
<style type="text/css">
* {
	font-size: 12px;
}

table {
	border-top: 1px solid #90badd;
	border-left: 1px solid #90badd;
}

tr {
	height: 25px;
}

td, th {
	border-bottom: 1px solid #000000;
	border-right: 1px solid #000000;
	padding-right: 3px;
	padding-left: 3px;
	font-weight: 400;
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
   <div id="dd" align="right" style="padding-bottom: 10px;">
	<input id="companyId" type="hidden" value="<%=request.getParameter("company") %>"/>
			<span style="padding-right: 10px;">
				<span>选择期间：</span>
				<input id="monthYear"  type="number"  style="width: 90px;"/>
			</span>
			<span style="padding-right: 10px;">
			<input id="btnSearch" value="查询" type="button"/>
			</span>
			
	
		
	</div>	
	
	<div align="right" style="padding-bottom: 10px;">
	<span style="padding-right: 10px;">单位：万元</span>
	 </div>	
	
	<div id="fundsdiv" align="center">
		<table id="dg" class="easyui-datagrid" cellpadding="0" cellspacing="0" style="width: 100%">
			<thead>
			
            <tr>
                <th field="yearMonth" data-options="width: '6%', align: 'center'" rowspan="2">期间</th>
                <th colspan="2">现金期初余额</th>
                <th colspan="2">现金收入</th>
                <th colspan="2">现金支出</th>
                <th colspan="2">现金期末余额</th>
                <th field="submitName" data-options="width: '7%', align: 'center'" rowspan="2">填报人</th>
                <th field="submitDate" data-options="width: '9%', align: 'center'" rowspan="2">填报时间</th>
                <th field="operate" data-options="width: '6%', align: 'center', formatter: fmt_operate" rowspan="2">操作</th>
            </tr>
            <tr>
                <th field="lastMonthBegin" data-options="width: '9%', align: 'right', formatter: toMoney">计划</th>
                <th field="thisMonthPlan" data-options="width: '9%', align: 'right', formatter: toMoney">发生</th>
                <th field="inPlan" data-options="width: '9%', align: 'right', formatter: toMoney">计划</th>
                <th field="inHappend" data-options="width: '9%', align: 'right', formatter: toMoney">发生</th>
                <th field="exPlan" data-options="width: '9%', align: 'right', formatter: toMoney">计划</th>
                <th field="exHappend" data-options="width: '9%', align: 'right', formatter: toMoney">发生</th>
                <th field="lastMonthEndPln" data-options="width: '9%', align: 'right', formatter: toMoney">计划</th>
                <th field="lastMonthEndHap" data-options="width: '9%', align: 'right', formatter: toMoney">发生</th>
            </tr>
        </thead>
		</table>
	</div>
</body>
</html>
