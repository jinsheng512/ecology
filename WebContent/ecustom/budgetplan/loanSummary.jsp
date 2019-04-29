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
<script type="text/javascript" src="loanSummary.js?time=201805282239"></script>
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
				<span>录入日期：</span>
				<input id="startDate" class="Wdate" type="text" value="<%=startDate %>" style="width: 90px;"/>
				<span>至</span>
				<input id="endDate" class="Wdate" type="text" value="<%=endDate %>" style="width: 90px;"/>
				</span>
				
				
			<span style="padding-right: 10px;">
				<span>金融机构：</span>
				<select id="jinrjg"  style="width: 120px; height:22px;">
				  <option value="">请选择</option>    
				</select>
			   </span>
				
			   <span style="padding-right: 10px;">
				<span>贷款类型：</span>
				<select id="daiklx"  style="width: 120px; height:22px;">
				  <option value="">请选择</option>    
				</select>
			   </span>
			   
			   <span style="padding-right: 10px;">
				<span>会计科目：</span>
				<select id="kuaijkm"  style="width: 120px; height:22px;">
				  <option value="">请选择</option>    
				</select>
			   </span>
				
				<span style="padding-right: 10px;">
				<span>项目名称：</span>
				<select id="project"  style="width: 120px; height:22px;">
				  <option value="">请选择</option>    
				</select>
			   </span>
				
			
			
			<div style="padding-top:10px;">
			
				<span style="padding-right: 10px;">
				<span>公司名称：</span>
				<select id="company"  style="width: 203px; height:22px;">
				  <option value="">请选择</option>    
				</select>
			   </span>
			
				<span style="padding-right: 10px;">
				<span>担保合同：</span>
				<select id="danbht"  style="width: 120px; height:22px;">
				  <option value="">请选择</option>    
				</select>
			   </span>
				
			   
			   <span style="padding-right: 10px;">
				<span>付息方式：</span>
				<select id="fuxfs"  style="width: 120px; height:22px;">
				  <option value="">请选择</option>    
				</select>
			   </span>
				
				<span style="padding-right: 10px;">
				<span>抵押合同：</span>
				<select id="diyht"  style="width: 120px; height:22px;">
				  <option value="">请选择</option>    
				</select>
			   </span>
			   
			   <span style="padding-right: 10px;">
				<span>授信合同：</span>
				<select id="shouxhth"  style="width: 120px; height:22px;">
				  <option value="">请选择</option>    
				</select>
			   </span>
				
				<input type="text" id="keyString"  style="width: 120px; height:18px;display:none;" placeholder="请输入查询内容"  name="year" value=""/>
				
				 <button id="btnSearch">查询</button>
			</span>
			
			 </div>
	       
            </div>
			
		</div>

<table id="dg" cellspacing="0" cellpadding="0" style="width: 100%;margin-top: 10px;overflow:scroll">
	<tr>
		<td id="title" colspan="18" style="border-bottom: 1px solid #FFFFFF; text-align: center; font-size: 20px; font-weight: 700;">借款明细表</td>
	</tr>
	<tr>
	

		<td colspan="18">单位：元</td>
	</tr>
	<tr>
		<td rowspan="2">序号</td>
		<td rowspan="2">公司名称</td>
		<td rowspan="2">金融机构</td>
		<td rowspan="2">授信额度</td>
		<td rowspan="2">贷款余额</td>
		<td rowspan="2">授信合同</td>
		<td rowspan="2">贷款类型</td>
		<td rowspan="2">起始日期</td>
		<td rowspan="2">终止日期</td>
		<td rowspan="2">借款利率</td>
		<td rowspan="2">顾问费</td>
		<td rowspan="2">总成本</td>
		<td rowspan="2">担保合同</td>
		<td rowspan="2">抵押合同</td>
		<td rowspan="2">付息方式</td>
		<td rowspan="2">项目</td>
		<td rowspan="2">会计科目</td>
		<td rowspan="2">借款单号</td>
	</tr>
	<tr class="summary_head">
	</tr>
	
	

</table>
</body>
</html>