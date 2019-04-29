<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebService运行日志查询</title>
	<link rel="stylesheet" type="text/css" href="/ecustom/css/ecustom.css">
	<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
	<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
	<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/ecustom/js/webservice/WebServiceLogView.js"></script>
</head>
<body>
<div style="margin-top: 10px;">
<table id="dg" class="easyui-datagrid" style="width: 100%; min-height: 300px;">
	<thead>
		<tr>
			<th data-options="field:'seqNo'">序号</th>
			<th data-options="field:'pathInfo'">接口名称</th>
			<th data-options="field:'requestBody',width:80,align:'center',formatter:fmt.wsBody">请求报文</th>
			<th data-options="field:'responseBody',width:80,align:'center',formatter:fmt.wsBody">响应报文</th>
			<th data-options="field:'requestTime'">请求时间</th>
		 </tr>
	 <thead>
</table>

<div id="win" class="easyui-window" data-options="modal:true,closed:true" style="width: 800px; height: 500px;">
	<div id="divWSBody"></div>
</div>

</div>
</body>
</html>