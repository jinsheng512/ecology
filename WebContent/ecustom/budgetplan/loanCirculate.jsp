<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>借还统计表</title>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="loanCirculate.js"></script>
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

.right1 {
	text-align: right;
}

.center {
	text-align: center;
}
</style>
</head>

<body>
	<input type="hidden" id="year" name="year" value="<%=request.getParameter("year") %>"/>

	
<div align="right" style="padding-bottom: 10px;padding-right:80px;padding-top:10px">
  	<span style="padding-right: 10px;font-size: 15px;font-weight : bold;">年份</span>
 <select id="choose" name="choose" style="font-size: 15px;font-weight : bold;" >
    <option value="0">请选择</option>  
    <option value="1">2019</option>  
    <option value="2">2020</option>
    <option value="3">2021</option>
    <option value="4">2022</option>
    <option value="5">2023</option>
</select>
  
  	<div id="dd" align="right" style="padding-bottom: 10px;padding-right:0px;padding-top:10px">
		<input id="btnExport" value="导出Excel" type="button" />
	</div>
  
</div>	


	
	<div id="fundsdiv" align="center">
		<table id="tbFunds" cellpadding="0" cellspacing="0" style="width:90%">
		
			<tr>
				<th colspan="9" style="font-size: 18px; padding-top: 10px; padding-bottom: 10px;">借还统计表</th>
			</tr>
			<tr>
				<th id="curYear" colspan="1"></th>
				<th colspan="4" style="text-align: center;">借</th>
				<th id="years" colspan="4">还</th>
				
			</tr>
			<tr>
				<th>月份</th>
				<th>日期</th>
				<th>银行</th>
				<th>金额（万元）</th>
				<th>项目</th>
				<th>日期</th>
				<th>银行</th>
				<th>金额（万元）</th>
				<th>备注</th>
			</tr>
		 <tbody>
			<tr id="trBuss1122">
				<td style="text-align: center; font-weight : bold;">1月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss1" >
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn1Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut1Dra">&nbsp;</td>
                <td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss22">
				<td style="text-align: center;font-weight : bold;">2月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
				<tr id="trBuss2">
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn2Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut2Dra">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss33">
				<td style="text-align: center;font-weight : bold;">3月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
				<tr id="trBuss3">
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn3Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut3Dra">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss44">
				<td style="text-align: center;font-weight : bold;">4月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			
				<tr id="trBuss4">
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn4Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut4Dra">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss55">
				<td style="text-align: center;font-weight : bold;">5月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
				<tr id="trBuss5">
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn5Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut5Dra">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss66">
				<td style="text-align: center;font-weight : bold;">6月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
				<tr id="trBuss6">
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn6Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut6Dra">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss77">
				<td style="text-align: center;font-weight : bold;">7月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
				<tr id="trBuss7">
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn7Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut7Dra">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss88">
				<td style="text-align: center;font-weight : bold;">8月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss8">
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn8Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut8Dra">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss99">
				<td style="text-align: center;font-weight : bold;">9月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss9">
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn9Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut9Dra">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss110">
				<td style="text-align: center;font-weight : bold;">10月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss10">
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn10Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut10Dra">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss111">
				<td style="text-align: center;font-weight : bold;">11月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
		<tr id="trBuss11">
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn11Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut11Dra">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			
			<tr id="trBuss112">
				<td style="text-align: center;font-weight : bold;">12月</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr id="trBuss12">
				<td style="text-align: center;">小计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 incomeDra" id="bussIn12Dra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1 spendDra" id="bussOut12Dra">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			

			<tr id="cashbal">
				<td style="text-align: center;">合计</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1" id="monthEndInDra">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="right1" id="monthEndOutDra">&nbsp;</td>
				<td>&nbsp;</td>
				
			</tr>
			</tbody>     
		</table>
	</div>
</body>

<form id="formExport" action="/greedc/servlets/InventoryReport-download.xls" method="post">
	<input id="exportData" name="exportData" type="hidden"/>
	<input id="fileName" name="fileName" type="hidden"/>
</form>

</html>
