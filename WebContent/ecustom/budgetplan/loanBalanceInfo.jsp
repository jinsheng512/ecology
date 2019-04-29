<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	<%@page import="java.util.Calendar"%>

<%@ page import="java.util.*, ecustom.util.*" %>


<%
	String startDate = CustomUtil.dateFormat(new Date(), "yyyy-MM-dd");
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>余额明细表</title>
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="loanBalanceInfo.js"></script>
<script type="text/javascript" src="/ecustom/js/My97DatePicker/WdatePicker.js"></script>

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

   <div align="left" style="padding-Top: 10px;">
   <span style="padding: 20px;text-align: center; font-size: 20px; font-weight: 700;">余额明细表</span>
   </div>
   
    <div align="right" style="padding-bottom: 10px;">
	
			    <span align="right" style="padding-right: 10px;">
				<span>日期：</span>
				<input id="startDate" class="Wdate" type="text" value="<%=startDate %>" style="width: 90px;"/>
				</span>
				
				 <button id="btnSearch">查询</button>
		  </div>		
   
    <div align="right" style="padding-bottom: 10px;">
	<span style="padding-right: 10px;">单位：元</span>
	</div>	
	
	<div style="text-align: right;padding-bottom: 10px;">
	<button id="btnExport">导出Excel</button>
   </div>

	<div id="fundsdiv" align="center">
		<table id="dg" class="easyui-datagrid" data-options="fitColumns:false" style="width:auto;  height: 900; padding: 5px"  rownumbers="true" 
                singleselect="false">
			<thead>
            <tr>
                <th field="subcompanyname" data-options="width: '20%', align: 'center'" rowspan="2" >公司</th>
                <th field="kaihh" data-options="width: '18%', align: 'center'" rowspan="2" >开户行</th>
                <th field="yinhzh" data-options="width: '18%', align: 'center'" rowspan="2" >银行账户</th>
                
                <th field="benwb" data-options="width: '10%', align: 'center',formatter: toMoney" rowspan="2" >本位币</th>
                <th field="yuanb" data-options="width: '10%', align: 'center',formatter: toMoney" rowspan="2" >原币</th>
                <th field="yewrq" data-options="width: '10%', align: 'center'" rowspan="2">业务日期</th>
                <th field="lastname" data-options="width: '8%', align: 'center'" rowspan="2">出纳</th>
                
                <th field="bankmc" data-options="width: '10%', align: 'center'" rowspan="2" >板块</th>
                <th field="jinrjg" data-options="width: '10%', align: 'center'" rowspan="2">金融机构</th>
                

                <th field="zhanghxzmc" data-options="width: '8%', align: 'center'" rowspan="2">账户性质</th>
                <th field="currencydesc" data-options="width: '5%', align: 'center'" rowspan="2">币种</th>
                
                <th field="shifjgh" data-options="width: '7%', align: 'center',formatter: toChange" rowspan="2">是否监管户</th>
                <th field="shifdkjajhzyh" data-options="width: '13%', align: 'center',formatter: toChange1" rowspan="2">是否贷款及按揭合作银行</th>
                <th field="yongt" data-options="width: '20%', align: 'center'" rowspan="2">用途</th>
            </tr>
            <tr>
            </tr>
        </thead>
		</table>
	</div>
</body>
</html>
