<%--
	名称：项目合同结算单-奖励
	作者：曹水涛
	创建日期：2017-03-16
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目合同结算单-奖励</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/css/workflow/form/Form261EntriesView.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="Form261EntriesViewJianglxx.js"></script>
</head>
<body>
	<input id="dataId" type="hidden" value="<%=request.getParameter("dataId") %>"/>
	<table id="dg" class="easyui-datagrid" style="width: 100%; min-height: 100px;">
		<thead>
	        <tr>
	            <th data-options="field:'jiangldbm'">奖励单编号</th>
	            <th data-options="field:'jiangldmc'">奖励单名称</th>
	            <th data-options="field:'bib'">币别</th>
	            <th data-options="field:'jine'">金额</th>
	            <th data-options="field:'zhuangt'">状态</th>
	            <th data-options="field:'shifyjl'">是否已奖励</th>
	            <th data-options="field:'faffs'">发放方式</th>
	            <th data-options="field:'jianglsx'">奖励事项</th>
	            <th data-options="field:'zhidr'">制单人</th>
	            <th data-options="field:'zhidsj'">制单时间</th>
	        </tr>
	    </thead>
	</table>
<body>
</html>