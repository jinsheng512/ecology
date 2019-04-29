<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>科目统计表</title>
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="loanSubject.js"></script>
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

   <div align="left" style="padding-Top: 30px;">
   <span style="padding: 10px;text-align: left; font-size: 20px; font-weight: 700;">科目统计表</span>
   </div>
   
  <div align="right" style="padding-bottom: 10px;">
  	<span style="padding-right: 10px;font-size: 15px;font-weight : bold;">统计项</span>
 <select id="choose" name="choose" style="font-size: 15px;font-weight : bold;" onchange="show_sub(this.options[this.options.selectedIndex].value)">
    <option value="0">请选择</option>    
    <option value="1">借款公司</option>
    <option value="2">贷款类型</option>
    <option value="3">担保方式</option>
</select>
  
  </div>	
  
  
  </div>	
  
    <div align="right" style="padding-bottom: 10px;">
	<span style="padding-right: 10px;">单位：元</span>
	</div>	
	
	<div style="text-align: right;padding-bottom: 10px;">
	<button id="btnExport">导出Excel</button>
   </div>

	<div id="fundsdiv" align="center">
		<table id="dg" class="easyui-datagrid" cellpadding="0"  rownumbers="true"  cellspacing="0" style="width: 100%;">
			<thead>
            <tr>
                <th field="jkdwmc" data-options="width: '20%', align: 'center'" rowspan="2">统计项</th>
                <th field="duanjk" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">短期借款</th>
                <th field="changqjk" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">长期借款</th>
                <th field="yingfzq" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">应付债券</th>
                <th field="feildfz" data-options="width: '14%', align: 'center',formatter: toMoney" rowspan="2">一年内到期的非流动负债</th>
                <th field="qityfk" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">其他应付款</th>
                <th field="qitfldfz" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">其他非流动负债</th>
                <th field="total" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">合计</th>
            </tr>
            <tr>
            </tr>
        </thead>
		</table>
	</div>
	
	<div id="fundsdiv1" align="center" style="display:none">
		<table id="dg1" class="easyui-datagrid" cellpadding="0" cellspacing="0" style="width: 100%;">
			<thead>
            <tr>
                <th field="daiklx" data-options="width: '20%', align: 'center'" rowspan="2">统计项</th>
                <th field="duanjk" data-options="width: '11%', align: 'center' ,formatter: toMoney" rowspan="2">短期借款</th>
                <th field="changqjk" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">长期借款</th>
                <th field="yingfzq" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">应付债券</th>
                <th field="feildfz" data-options="width: '14%', align: 'center',formatter: toMoney" rowspan="2">一年内到期的非流动负债</th>
                <th field="qityfk" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">其他应付款</th>
                <th field="qitfldfz" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">其他非流动负债</th>
                <th field="total" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">合计</th>
            </tr>
            <tr>
            </tr>
        </thead>
		</table>
	</div>
	
	<div id="fundsdiv2" align="center" style="display:none">
		<table id="dg2" class="easyui-datagrid" cellpadding="0" cellspacing="0" style="width: 100%;">
			<thead>
            <tr>
                <th field="danbfs" data-options="width: '20%', align: 'center'" rowspan="2">统计项</th>
                <th field="duanjk" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">短期借款</th>
                <th field="changqjk" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">长期借款</th>
                <th field="yingfzq" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">应付债券</th>
                <th field="feildfz" data-options="width: '14%', align: 'center',formatter: toMoney" rowspan="2">一年内到期的非流动负债</th>
                <th field="qityfk" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">其他应付款</th>
                <th field="qitfldfz" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">其他非流动负债</th>
                <th field="total" data-options="width: '11%', align: 'center',formatter: toMoney" rowspan="2">合计</th>
            </tr>
            <tr>
            </tr>
        </thead>
		</table>
	</div>
</body>
</html>
