<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BP11-集团资金计划汇总表</title>
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="groupSummary.js"></script>
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

    <div align="right" style="padding-bottom: 10px;">
	<span style="padding-right: 10px;">单位：万元</span>
	</div>	

	<div id="fundsdiv" align="center">
		<table id="dg" class="easyui-datagrid" cellpadding="0" cellspacing="0" style="width: 100%;">
			<thead>
            <tr>
                <th field="lastYearMonth" data-options="width: '9%', align: 'center'" rowspan="2">期间</th>
                <th colspan="2">现金期初余额</th>
                <th colspan="2">现金收入</th>
                <th colspan="2">现金支出</th>
                <th colspan="2">现金期末余额</th>
                <th field="operate" data-options="width: '19%', align: 'center', formatter: fmt_operate" rowspan="2">操作</th>
            </tr>
            <tr>
                <th field="thisMonthPlan" data-options="width: '9%', align: 'right', formatter: toMoney">计划</th>
                <th field="lastMonthBegin" data-options="width: '9%', align: 'right', formatter: toMoney">发生</th>
                <th field="inPlan" data-options="width: '9%', align: 'right', formatter: toMoney">计划</th>
                <th field="inHappend" data-options="width: '9%', align: 'right', formatter: toMoney">发生</th>
                <th field="exPlan" data-options="width: '9%', align: 'right', formatter: toMoney">计划</th>
                <th field="exHappend" data-options="width: '9%', align: 'right', formatter: toMoney">发生</th>
                <th field="lastMonthendPln" data-options="width: '9%', align: 'right', formatter: toMoney">计划</th>
                <th field="lastMonthendHap" data-options="width: '9%', align: 'right', formatter: toMoney">发生</th>
            </tr>
        </thead>
		</table>
	</div>
</body>
</html>
