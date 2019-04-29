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
<title>还款明细表</title>
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="loanRepaySummary.js?time=201805282239"></script>
<script type="text/javascript" src="/ecustom/js/My97DatePicker/WdatePicker.js"></script>

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

	<div  width="*" style="border: 0px; text-align: left;">
	
			<span style="padding-right: 10px;">
				<span>录入日期范围：</span>
				<input id="startDate" class="Wdate" type="text" value="<%=startDate %>" style="width: 90px;"/>
				<span>至</span>
				<input id="endDate" class="Wdate" type="text" value="<%=endDate %>" style="width: 90px;"/>
				 <button id="btnSearch">查询</button>
			</span>
			
	       
            </div>
			
		</div>

<table id="dg" cellspacing="0" cellpadding="0" style="width: 100%;margin-top: 10px">
	<tr>
		<td id="title" colspan="16" style="border-bottom: 1px solid #FFFFFF; text-align: center; font-size: 20px; font-weight: 700;">还款明细表</td>
	</tr>
	<tr>
	

		<td colspan="16">单位：元</td>
	</tr>
	<tr>
		<td rowspan="2">序号</td>
		<td rowspan="2">公司名称</td>
		<td rowspan="2">金融机构</td>
		<td rowspan="2">授信额度</td>
		<td rowspan="2">借款金额</td>
		<td rowspan="2">起始日期</td>
		<td rowspan="2">终止日期</td>
		<td rowspan="2">合同利率</td>
		<td rowspan="2">总成本</td>
		<td rowspan="2">借款单号</td>
		<td rowspan="2">预计还款日</td>
		<td rowspan="2">实际还款日</td>
		<td rowspan="2">还款金额</td>
		<td rowspan="2">还款方式</td>
		<td rowspan="2">业务单据</td>
		<td rowspan="2">备注</td>
	</tr>
	<tr class="summary_head">
	</tr>
	
	

</table>
</body>
</html>