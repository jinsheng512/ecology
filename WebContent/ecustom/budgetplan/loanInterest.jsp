<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>利息台账表</title>
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="loanInterest.js"></script>
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
   <span style="padding: 10px;text-align: left; font-size: 20px; font-weight: 700;">利息台账表</span>
   </div>
   
  <div align="right" style="padding-bottom: 10px;">
  	<span style="padding-right: 10px;font-size: 15px;font-weight : bold;">年份</span>
 <select id="choose" name="choose" style="font-size: 15px;font-weight : bold;">
    <option value="0">请选择</option>
    <option value="6">2016</option>    
    <option value="7">2017</option>
    <option value="8">2018</option>
    <option value="1">2019</option>
    <option value="2">2020</option>
    <option value="3">2021</option>
    <option value="4">2022</option>
    <option value="5">2023</option>
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
		<table id="dg" class="easyui-datagrid" data-options="fitColumns:false" style="width:auto;  height: 900; padding: 5px"  rownumbers="true" 
                singleselect="false">
			<thead>
          
        </thead>
		</table>
	</div>

</body>
</html>
