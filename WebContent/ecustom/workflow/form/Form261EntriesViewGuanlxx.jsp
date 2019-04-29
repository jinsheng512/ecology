<%--
	名称：关联合同页签
	作者：曹水涛
	创建日期：2016-12-25
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>关联合同页签</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/css/workflow/form/Form261EntriesView.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/workflow/form/Form261EntriesViewGuanlxx.js"></script>
</head>
<body>
	<input id="dataId" type="hidden" value="<%=request.getParameter("dataId") %>"/>
	<table id="dg" class="easyui-datagrid" style="width: 100%; min-height: 100px;">
		<thead>
	        <tr>
	            <th data-options="field:'yewrq'">业务日期</th>
	            <th data-options="field:'qij'">期间</th>
	            <th data-options="field:'zhuangt'">状态</th>
	            <th data-options="field:'yijs',formatter:fmtYijs">已结算</th>
	            <th data-options="field:'hetlx'">合同类型</th>
	            <th data-options="field:'bianm'">编码</th>
	            <th data-options="field:'mingc'">名称</th>
	            <th data-options="field:'bib'">币别</th>
	            <th data-options="field:'yuanbje'">原币金额</th>
	            <th data-options="field:'benwbje'">本位币金额</th>
	            <th data-options="field:'jiaf'">甲方</th>
	            <th data-options="field:'yif'">乙方</th>
	            <th data-options="field:'zerbm'">责任部门</th>
	            <th data-options="field:'zerr'">责任人</th>
	            <th data-options="field:'zhidr'">制单人</th>
	            <th data-options="field:'zhidsj'">制单时间</th>
	        </tr>
	    </thead>
	</table>
<body>
</html>