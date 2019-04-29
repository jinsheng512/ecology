<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目汇总表</title>
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="companyProjectSummary.js?time=201805282239"></script>
<style type="text/css">
* {
	font-size: 12px;
}

table {
	border-top: 1px solid #000000;
	border-left: 1px solid #000000;
}

tr {
	height: 25px;
}

tr.tr_block, tr.tr_total {
	background-color: #FCD5B4;
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
<input id="year" name="year" type="hidden" value="<%= Calendar.getInstance().get(Calendar.YEAR) %>">
<div style="text-align: right;">
	<button id="btnExport">导出Excel</button>
</div>
<table id="dg" cellspacing="0" cellpadding="0">
	<tr>
		<td id="title" colspan="28" style="border-bottom: 1px solid #FFFFFF; text-align: center; font-size: 20px; font-weight: 700;">项目资金需求情况表</td>
	</tr>
	<tr>
		<td colspan="28">单位：万元</td>
	</tr>
	<tr>
		<td rowspan="2">序号</td>
		<td rowspan="2">所属板块</td>
		<td rowspan="2">所属公司</td>
		<td rowspan="2">项目名称</td>
		<td colspan="2" align="center">1月</td>
		<td colspan="2" align="center">2月</td>
		<td colspan="2" align="center">3月</td>
		<td colspan="2" align="center">4月</td>
		<td colspan="2" align="center">5月</td>
		<td colspan="2" align="center">6月</td>
		<td colspan="2" align="center">7月</td>
		<td colspan="2" align="center">8月</td>
		<td colspan="2" align="center">9月</td>
		<td colspan="2" align="center">10月</td>
		<td colspan="2" align="center">11月</td>
		<td colspan="2" align="center">12月</td>
	</tr>
	<tr class="summary_head">
		<td>预计发生</td>
		<td>实际支出</td>
		<td>预计发生</td>
		<td>实际支出</td>
		<td>预计发生</td>
		<td>实际发生</td>
		<td>预计发生</td>
		<td>实际发生</td>
		<td>预计发生</td>
		<td>实际发生</td>
		<td>预计发生</td>
		<td>实际发生</td>
		<td>预计发生</td>
		<td>实际发生</td>
		<td>预计发生</td>
		<td>实际发生</td>
		<td>预计发生</td>
		<td>实际发生</td>
		<td>预计发生</td>
		<td>实际发生</td>
		<td>预计发生</td>
		<td>实际发生</td>
		<td>预计发生</td>
		<td>实际发生</td>
	</tr>
	<tr class="tr_total">
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>合计</td>
		<td class="summary_total_plan_01">&nbsp;</td>
		<td class="summary_total_hppn_01">&nbsp;</td>
		<td class="summary_total_plan_02">&nbsp;</td>
		<td class="summary_total_hppn_02">&nbsp;</td>
		<td class="summary_total_plan_03">&nbsp;</td>
		<td class="summary_total_hppn_03">&nbsp;</td>
		<td class="summary_total_plan_04">&nbsp;</td>
		<td class="summary_total_hppn_04">&nbsp;</td>
		<td class="summary_total_plan_05">&nbsp;</td>
		<td class="summary_total_hppn_05">&nbsp;</td>
		<td class="summary_total_plan_06">&nbsp;</td>
		<td class="summary_total_hppn_06">&nbsp;</td>
		<td class="summary_total_plan_07">&nbsp;</td>
		<td class="summary_total_hppn_07">&nbsp;</td>
		<td class="summary_total_plan_08">&nbsp;</td>
		<td class="summary_total_hppn_08">&nbsp;</td>
		<td class="summary_total_plan_09">&nbsp;</td>
		<td class="summary_total_hppn_09">&nbsp;</td>
		<td class="summary_total_plan_10">&nbsp;</td>
		<td class="summary_total_hppn_10">&nbsp;</td>
		<td class="summary_total_plan_11">&nbsp;</td>
		<td class="summary_total_hppn_11">&nbsp;</td>
		<td class="summary_total_plan_12">&nbsp;</td>
		<td class="summary_total_hppn_12">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>2018年实际发生</td>
		<td colspan="24" class="summary_year_hppn">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>2018年预计发生</td>
		<td colspan="24" class="summary_year_plan">&nbsp;</td>
	</tr>
</table>
</body>
</html>