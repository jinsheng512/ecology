<%--
	名称：违约信息
	作者：曹水涛
	创建日期：2016-12-25
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>违约金</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/css/workflow/form/Form261EntriesView.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/workflow/form/Form261EntriesViewWeiyxx.js"></script>
</head>
<body>
	<input id="dataId" type="hidden" value="<%=request.getParameter("dataId") %>"/>
	<table id="dg" class="easyui-datagrid" style="width: 100%; min-height: 100px;">
		<thead>
	        <tr>
	            <th data-options="field:'weiyjbh'">违约金编号</th>
	            <th data-options="field:'weiyjmc'">违约金名称</th>
	            <th data-options="field:'zhuangt'">状态</th>
	            <th data-options="field:'bib'">币别</th>
	            <th data-options="field:'weiylx'">违约类型</th>
	            <th data-options="field:'jine'">金额</th>
	            <th data-options="field:'shifwy', formatter:fmtShifwy">是否违约</th>
	            <th data-options="field:'koukfs'">扣款方式</th>
	            <th data-options="field:'zhidr'">制单人</th>
	            <th data-options="field:'zhidsj'">制单日期</th>
	        </tr>
	    </thead>
	</table>
<body>
</html>