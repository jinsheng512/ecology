<%@ page import="java.util.*, ecustom.util.*" %><%--
  User: William
  Date: 2017-9-6
  库存报表-户型
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String endDate = CustomUtil.dateFormat(new Date(), "yyyy-MM-dd");
	Calendar canlendar = Calendar.getInstance();
	canlendar.add(Calendar.MONTH, -1);
	String startDate = CustomUtil.dateFormat(canlendar.getTime(), "yyyy-MM-dd");
%>
<html>
<head>
	<title>库存报表（户型）</title>
	<link type="text/css" rel="stylesheet" href="easreport.css">
	<script type="text/javascript" src="/ecustom/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easreport.js?time=20170928"></script>
	<script type="text/javascript" src="InventoryReportByRoomModel.js?time=20170928"></script>
</head>
<body>

<table cellspacing="0" cellpadding="0" style="border: 0px; width: 100%;">
	<tr>
		<td style="line-height: 27px;height: 28px; border: 0px; min-width: 1px; width: 360px;">
			<div class="tabSelect">格力地产库存报表（户型）</div>
			<div class="tabUnSelect" onclick="window.location.href='InventoryReportByBuilding.jsp'">格力地产库存报表（楼栋）</div>
		</td>
		<td width="*" style="border: 0px; text-align: left;">
			<span style="padding-right: 10px;">
				<span>项目：</span>
				<select id="project"></select>
			</span>
			<span style="padding-right: 10px;">
				<span>选择日期范围：</span>
				<input id="startDate" class="Wdate" type="text" value="<%=startDate %>" style="width: 90px;"/>
				<span>至</span>
				<input id="endDate" class="Wdate" type="text" value="<%=endDate %>" style="width: 90px;"/>
			</span>
			<span style="padding-right: 10px;">
				<button id="btnExportExcel">导出Excel</button>
			</span>
		</td>
	</tr>
</table>

<table id="dg" border="0" cellpadding="0" cellspacing="0" style="margin-top: 10px;">
	<tr>
		<th rowspan="2">项目 </th>
		<th rowspan="2">区</th>
		<th rowspan="2">产品类型</th>
		<th rowspan="2">户型</th>
		<th colspan="2">整体规划</th>
		<th colspan="4">销售量</th>
		<th colspan="2">预留</th>
		<th colspan="2">库存</th>
	</tr>
	<tr>
		<th>总套数</th>
		<th>总面积</th>
		<th>销售套数</th>
		<th>销售面积</th>
		<th>销售金额</th>
		<th>均价</th>
		<th>保留套数</th>
		<th>保留面积</th>
		<th>库存套数</th>
		<th>库存面积</th>
	</tr>
</table>

<form id="formExport" action="/greedc/servlets/InventoryReport-download.xls" method="post">
	<input id="exportData" name="exportData" type="hidden"/>
	<input id="fileName" name="fileName" type="hidden"/>
</form>

</body>
</html>