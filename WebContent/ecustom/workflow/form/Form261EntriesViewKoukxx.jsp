<%--
	名称：项目合同结算单-扣款
	作者：曹水涛
	创建日期：2016-12-25
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目合同结算单-扣款</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/css/workflow/form/Form261EntriesView.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/workflow/form/Form261EntriesViewKoukxx.js"></script>
</head>
<body>
	<input id="dataId" type="hidden" value="<%=request.getParameter("dataId") %>"/>
	<table id="dg" class="easyui-datagrid" style="width: 100%; min-height: 100px;">
		<thead>
	        <tr>
	            <th data-options="field:'koukdbm'">扣款单编码</th>
	            <th data-options="field:'zhuangt'">状态</th>
	            <th data-options="field:'koukdw'">扣款单位</th>
	            <th data-options="field:'kouklx'">扣款类型</th>
	            <th data-options="field:'koukje'">扣款金额</th>
	            <th data-options="field:'koukrq'">扣款日期</th>
	            <th data-options="field:'shenpr'">审批人</th>
	            <th data-options="field:'shenpsj'">审批日期</th>
	            <th data-options="field:'zhidr'">制单人</th>
	            <th data-options="field:'shidsj'">制单日期</th>
	        </tr>
	    </thead>
	</table>
<body>
</html>