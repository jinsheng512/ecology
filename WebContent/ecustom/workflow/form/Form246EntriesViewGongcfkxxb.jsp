<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>工程付款情况表</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/css/workflow/form/Form261EntriesView.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="Form246EntriesViewGongcfkxxb.js"></script>
</head>
<body>
	<input id="dataId" type="hidden" value="<%=request.getParameter("dataId") %>"/>
	<table class="e-table" cellspacing="0" cellpadding="0">
		<!-- 1 -->
		<tr>
			<td class="fName">合同名称</td>
			<td colspan="5"><div id="proPay0_1"></div></td>
			<td class="fName">合同造价原币</td>
			<td colspan="2"><div id="proPay0_7"></div></td>
			<td class="fName">合同造价本币</td>
			<td colspan="2"><div id="proPay0_10"></div></td>
		</tr>
		<!-- 2 -->
		<tr>
			<td class="fName">变更指令金额原币</td>
			<td colspan="2"><div id="proPay1_1"></div></td>
			<td class="fName">变更指令金额本币</td>
			<td colspan="2"><div id="proPay1_4"></div></td>
			<td class="fName">最新造价原币</td>
			<td colspan="2"><div id="proPay1_7"></div></td>
			<td class="fName">最新造价原币</td>
			<td colspan="2"><div id="proPay1_10"></div></td>
		</tr>
		<!-- 3 -->
		<tr>
			<td class="fName">结算金额原币</td>
			<td colspan="2"><div id="proPay2_1"></div></td>
			<td class="fName">结算金额本币</td>
			<td colspan="2"><div id="proPay2_4"></div></td>
			<td class="fName">本申请单已付原币</td>
			<td colspan="2"><div id="proPay2_7"></div></td>
			<td class="fName">本申请单已付本币</td>
			<td colspan="2"><div id="proPay2_11"></div></td>
		</tr>
		<!-- 4 -->
		<tr>
			<td rowspan="2" class="fName">付款次数</td>
			<td rowspan="2"><div id="proPay3_1"></div></td>
			<td colspan="2" class="fName" align="center">截至上次累计实付</td>
			<td colspan="2" class="fName" align="center">截至上次累计申请</td>
			<td colspan="2" class="fName" align="center">本次付款</td>
			<td colspan="2" class="fName" align="center">截至本次累计申请</td>
			<td colspan="2" class="fName" align="center">截至本次累计实付</td>
		</tr>
		<!-- 5 -->
		<tr>
			<td class="fName" align="center">原币</td>
			<td class="fName" align="center">本币</td>
			<td class="fName" align="center">原币</td>
			<td class="fName" align="center">本币</td>
			<td class="fName" align="center">原币</td>
			<td class="fName" align="center">本币</td>
			<td class="fName" align="center">原币</td>
			<td class="fName" align="center">本币</td>
			<td class="fName" align="center">原币</td>
			<td class="fName" align="center">本币</td>
		</tr>
		<!-- 6 -->
		<tr>
			<td rowspan="3" class="fName">进度款项</td>
			<td class="fName">合同内工程款</td>
			<td><div id="proPay5_2"></div></td>
			<td><div id="proPay5_3"></div></td>
			<td><div id="proPay5_4"></div></td>
			<td><div id="proPay5_5"></div></td>
			<td><div id="proPay5_6"></div></td>
			<td><div id="proPay5_7"></div></td>
			<td><div id="proPay5_8"></div></td>
			<td><div id="proPay5_9"></div></td>
			<td><div id="proPay5_10"></div></td>
			<td><div id="proPay5_11"></div></td>
		</tr>
		<!-- 7 -->
		<tr>
			<td class="fName">奖励</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><div id="proPay8_6"></div></td>
			<td><div id="proPay8_7"></div></td>
			<td><div id="proPay8_8"></div></td>
			<td><div id="proPay8_9"></div></td>
			<td><div id="proPay8_10"></div></td>
			<td><div id="proPay8_11"></div></td>
		</tr>
		<!-- 8 -->
		<tr>
			<td class="fName">违约金</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><div id="proPay9_8"></div></td>
			<td><div id="proPay9_9"></div></td>
			<td><div id="proPay9_10"></div></td>
			<td><div id="proPay9_11"></div></td>
		</tr>
		<!-- 9 -->
		<tr>
			<td rowspan="5" class="fName">应扣款项</td>
			<td class="fName">水费</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><div id="proPay10_4"></div></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><div id="proPay10_8"></div></td>
			<td><div id="proPay10_9"></div></td>
			<td><div id="proPay10_10"></div></td>
			<td><div id="proPay10_11"></div></td>
		</tr>
		<!-- 10 -->
		<tr>
			<td class="fName">电费</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><div id="proPay11_4"></div></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><div id="proPay11_8"></div></td>
			<td><div id="proPay11_9"></div></td>
			<td><div id="proPay11_10"></div></td>
			<td><div id="proPay11_11"></div></td>
		</tr>
			<!-- 11 -->
		<tr>
			<td class="fName">罚款</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><div id="proPay12_4"></div></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><div id="proPay12_8"></div></td>
			<td><div id="proPay12_9"></div></td>
			<td><div id="proPay12_10"></div></td>
			<td><div id="proPay12_11"></div></td>
		</tr>
		<!-- 12 -->
		<tr>
			<td class="fName">配合费</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><div id="proPay13_4"></div></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><div id="proPay13_8"></div></td>
			<td><div id="proPay13_9"></div></td>
			<td><div id="proPay13_10"></div></td>
			<td><div id="proPay13_11"></div></td>
		</tr>
		<!-- 13 -->
		<tr>
			<td class="fName">小计</td>
			<td><div id="proPay14_2"></div></td>
			<td><div id="proPay14_3"></div></td>
			<td><div id="proPay14_4"></div></td>
			<td><div id="proPay14_5"></div></td>
			<td><div id="proPay14_6"></div></td>
			<td><div id="proPay14_7"></div></td>
			<td><div id="proPay14_8"></div></td>
			<td><div id="proPay14_9"></div></td>
			<td><div id="proPay14_10"></div></td>
			<td><div id="proPay14_11"></div></td>
		</tr>
		<!-- 14 -->
		<tr>
			<td colspan="2" class="fName">应扣押供材款</td>
			<td><div id="proPay15_2"></div></td>
			<td>&nbsp;</td>
			<td><div id="proPay15_4"></div></td>
			<td><div id="proPay15_5"></div></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><div id="proPay15_8"></div></td>
			<td><div id="proPay15_9"></div></td>
			<td><div id="proPay15_10"></div></td>
			<td><div id="proPay15_11"></div></td>
		</tr>
		<!-- 15 -->
		<tr>
			<td colspan="2" class="fName">实付款</td>
			<td><div id="proPay16_2"></div></td>
			<td><div id="proPay16_3"></div></td>
			<td><div id="proPay16_4"></div></td>
			<td><div id="proPay16_5"></div></td>
			<td><div id="proPay16_6"></div></td>
			<td><div id="proPay16_7"></div></td>
			<td><div id="proPay16_8"></div></td>
			<td><div id="proPay16_9"></div></td>
			<td><div id="proPay16_10"></div></td>
			<td><div id="proPay16_11"></div></td>
		</tr>
		<!-- 16 -->
		<tr>
			<td colspan="10" class="fName">余款</td>
			<td><div id="proPay17_10"></div></td>
			<td><div id="proPay17_11"></div></td>
		
		</tr>
		<!-- 17 -->
		<tr>
			<td class="fName">本期计划付款</td>
			<td>&nbsp;</td>
			<td class="fName">本期欠付款</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td colspan="7">&nbsp;</td>
		</tr>
		<!-- 18 -->
		<tr>
			<td class="fName">本次申请%</td>
			<td><div id="proPay19_1"></div></td>
			<td class="fName">累计申请%</td>
			<td><div id="proPay19_4"></div></td>
			<td class="fName">形象进度%</td>
			<td colspan="7">&nbsp;</td>
		</tr>
		<!-- 19 -->
		<tr>
			<td class="fName">应付申请%</td>
			<td><div id="proPay20_1"></div></td>
			<td class="fName">累计应付申请%</td>
			<td><div id="proPay20_4"></div></td>
			<td>&nbsp;</td>
			<td colspan="7">&nbsp;</td>
		</tr>
	</table>
<body>
</html>