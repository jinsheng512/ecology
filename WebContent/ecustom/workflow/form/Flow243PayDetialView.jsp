<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目付款计划编制表</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/workflow/form/Flow243PayDetialView.js"></script>
</head>
<body style="padding: 0px; margin: 0px;">
	<input id="requestId" type="hidden" value="<%=request.getParameter("requestid") %>"/>
    <table id="dg" class="easyui-datagrid" title="薪酬发放明细" style="width:100%;height:300px"></table>
</body>
</html>