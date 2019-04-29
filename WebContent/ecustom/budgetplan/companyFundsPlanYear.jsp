<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>格力地产资金计划年报表</title>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="companyFundsPlanYear.js"></script>
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
	<input type="hidden" id="companyId" name="companyId" value="<%=request.getParameter("companyId") %>"/>
	<input type="text" id="year" name="year" value="<%=request.getParameter("year") %>"/>
	<input id="btnExportExcel" value="导出" type="button"/>
	<div id="fundsdiv" align="center">
		<table id="tbFunds" cellpadding="0" cellspacing="0">
			<tr>
				<th colspan="12" style="font-size: 18px; padding-top: 10px; padding-bottom: 10px;">格力地产资金计划年报表</th>
			</tr>
			<tr>
				<th>公司</th>
				<th colspan="7" style="text-align: left;">海洋板块</th>
				<th>年份</th>
				<th id="yearAndMonth" colspan="2"><%=request.getParameter("year").substring(0, 4) %></th>
				<th>单位：万元</th>
			</tr>
			<tr>
				<th>序号</th>
				<th>收款</th>
				<th>本年实际额</th>
				<th>本年差额</th>
				<th>本年差额率</th>
				<th>下年计划额</th>
				<th>序号</th>
				<th>付款</th>
				<th>本年实际额</th>
				<th>本年差额</th>
				<th>本年差额率</th>
				<th>下年计划额</th>
			</tr>
			<tr>
				<td class="center">1</td>
				<td>现金年初余额</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr id="trBuss">
				<td class="center">2</td>
				<td>外部经营性收入</td>
				<td class="right incomeHap" id="bussInHap">&nbsp;</td>
				<td class="right incomeDam" id="bussInDam">&nbsp;</td>
				<td class="right incomeDra" id="bussInDra">&nbsp;</td>
				<td class="right incomeNpl" id="bussInNpl">&nbsp;</td>
				<td class="center">5</td>
				<td>外部经营性支出</td>
				<td class="right spendHap" id="bussOutHap">&nbsp;</td>
				<td class="right spendDam" id="bussOutDam">&nbsp;</td>
				<td class="right spendDra" id="bussOutDra">&nbsp;</td>
				<td class="right spendNpl" id="bussOutNpl">&nbsp;</td>
			</tr>
			<tr id="trFnaIn">
				<td class="center">3</td>
				<td>筹资收入</td>
				<td class="right incomeHap" id="FnaInHap">&nbsp;</td>
				<td class="right incomeDam" id="FnaInDam">&nbsp;</td>
				<td class="right incomeDra" id="FnaInDra">&nbsp;</td>
				<td class="right incomeNpl" id="FnaInNpl">&nbsp;</td>
				<td class="center">6</td>
				<td>项目投资支出</td>
				<td class="right spendHap" id="ProjInHap">&nbsp;</td>
				<td class="right spendDam" id="ProjInDam">&nbsp;</td>
				<td class="right spendDra" id="ProjInDra">&nbsp;</td>
				<td class="right spendNpl" id="ProjInNpl">&nbsp;</td>
			</tr>
			
			
			<tr id="trFnaOut">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="center">7</td>
				<td>筹资支出</td>
				<td class="right spendHap" id="FnaOutHap">&nbsp;</td>
				<td class="right spendDam" id="FnaOutDam">&nbsp;</td>
				<td class="right spendDra" id="FnaOutDra">&nbsp;</td>
				<td class="right spendNpl" id="FnaOutNpl">&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr id="cash">
				<td class="center">4</td>
				<td>现金收入(2+3)</td>
				<td class="right" id="cashInHap">&nbsp;</td>
				<td class="right" id="cashInDam">&nbsp;</td>
				<td class="right" id="cashInDra">&nbsp;</td>
				<td class="right" id="cashInNpl">&nbsp;</td>
				<td>8</td>
				<td>现金支出(5+6+7)</td>
				<td class="right" id="cashOutHap">&nbsp;</td>
				<td class="right" id="cashOutDam">&nbsp;</td>
				<td class="right" id="cashOutDra">&nbsp;</td>
				<td class="right" id="cashOutNpl">&nbsp;</td>
			</tr>
			<tr id="cashbal">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="center">9</td>
				<td>现金年末余额</td>
				<td class="right" id="monthEndHap">&nbsp;</td>
				<td class="right" id="monthEndDam">&nbsp;</td>
				<td class="right" id="monthEndDra">&nbsp;</td>
				<td class="right" id="monthEndNpl">&nbsp;</td>
			</tr>
		</table>
	</div>
</body>

<form id="formExport" action="/greedc/servlets/InventoryReport-download.xls" method="post">
	<input id="exportData" name="exportData" type="hidden"/>
	<input id="fileName" name="fileName" type="hidden"/>
</form>

</html>
