<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>格力地产资金计划明细表</title>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="groupFundsPlan.js"></script>
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
<%
	String yearMonth = request.getParameter("yearMonth");
	String reportType = request.getParameter("reportType");
	
	String year = yearMonth.substring(0, 4);
	int month = Integer.parseInt(yearMonth.substring(4, 6));
	
	String title = "", hapTitle = "本月实际额", nplTitle = "下月计划额";
	if ("1".equals(reportType)) {
		title = year +"年" + month + "月";
		hapTitle = month + "月实际额";
	} else if ("2".equals(reportType)) {
		title = year +"年" + ((month - 1) / 3 + 1) + "季度";
		hapTitle = "本季度实际额";
		nplTitle = "本季度计划额";
	} else if ("3".equals(reportType)) {
		title = year +"年";
		hapTitle = "本年度实际额";
		nplTitle = "本年度计划额";
	}
%>
<body>
	<input type="hidden" id="yearMonth" name="yearMonth" value="<%=yearMonth %>"/>
	<input type="hidden" id="reportType" name="yearMonth" value="<%=reportType %>"/>
	<div id="dd" style="padding-bottom: 10px; width:80%; text-align:right; margin: 0 auto;">
		<input id="btnExportExcel" value="导出" type="button" />
	</div>
	<div id="fundsdiv" >
		<table id="tbFunds" cellpadding="0" cellspacing="0" align="center" style="width:80%">
			<tr>
				<th colspan="8" style="font-size: 18px; padding-top: 10px; padding-bottom: 10px;">
				<span><%=title %>格力地产资金计划明细表</span>
			</th>
			</tr>
			<tr>
				<th>&nbsp;</th>
				<th colspan="6">&nbsp;</th>
				<th>单位：万元</th>
			</tr>
			<tr>
				<th>序号</th>
				<th>收款</th>
				<th><%=hapTitle %></th>
				<th><%=nplTitle %></th>
				<th>序号</th>
				<th>付款</th>
				<th><%=hapTitle %></th>
				<th><%=nplTitle %></th>
			</tr>
			<tr>
				<td class="center">1</td>
				<td>现金月初余额</td>
				<td class="right" id="hapAmountCashBegin">&nbsp;</td>
				<td class="right" id="nplAmountCashBegin">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr id="trBuss">
				<td class="center">2</td>
				<td>外部经营性收入</td>
				<td class="right incomeHap" id="hapAmountBussIn">&nbsp;</td>
				<td class="right incomeDam" id="nplAmountBussIn">&nbsp;</td>
				<td class="center">5</td>
				<td>外部经营性支出</td>
				<td class="right incomeHap" id="hapAmountBussEx">&nbsp;</td>
				<td class="right incomeNpl" id="nplAmountBussEx">&nbsp;</td>
			</tr>
			<tr id="trFnaIn">
				<td class="center">3</td>
				<td>筹资收入</td>
				<td class="right incomeHap" id="hapAmountFinaIn">&nbsp;</td>
				<td class="right incomeNpl" id="nplAmountFinaIn">&nbsp;</td>
				<td class="center">6</td>
				<td>项目投资支出</td>
				<td class="right incomeHap" id="hapAmountProjEx">&nbsp;</td>
				<td class="right incomeNpl" id="nplAmountProjEx">&nbsp;</td>
			</tr>
			
			
			<tr id="trFnaOut">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="center">7</td>
				<td>筹资支出</td>
				<td class="right incomeHap" id="hapAmountFinaEx">&nbsp;</td>
				<td class="right incomeNpl" id="nplAmountFinaEx">&nbsp;</td>
			</tr>
			<tr id="cash">
				<td class="center">4</td>
				<td>现金收入(2+3)</td>
				<td class="right incomeHap" id="hapAmountCashIn">&nbsp;</td>
				<td class="right incomeNpl" id="nplAmountCashIn">&nbsp;</td>
				<td class="center">8</td>
				<td>现金支出(5+6+7)</td>
				<td class="right incomeHap" id="hapAmountCashEx">&nbsp;</td>
				<td class="right incomeDam" id="nplAmountCashEx">&nbsp;</td>
			</tr>
			<tr id="cashbal">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="center">9</td>
				<td>现金月末余额</td>
				<td class="right" id="hapAmountCashEnd">&nbsp;</td>
				<td class="right" id="nplAmountCashEnd">&nbsp;</td>
			</tr>
		</table>
	</div>
</body>

<form id="formExport" action="/greedc/servlets/InventoryReport-download.xls" method="post">
	<input id="exportData" name="exportData" type="hidden"/>
	<input id="fileName" name="fileName" type="hidden"/>
</form>

</html>
