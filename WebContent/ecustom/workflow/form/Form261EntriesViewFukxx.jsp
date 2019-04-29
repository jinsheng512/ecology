<%--
	名称：付款信息
	作者：曹水涛
	创建日期：2016-12-25
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>付款信息</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/css/workflow/form/Form261EntriesView.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/workflow/form/Form261EntriesViewFukxx.js"></script>
</head>
<body>
	<input id="dataId" type="hidden" value="<%=request.getParameter("dataId") %>"/>
	<table id="dg" class="easyui-datagrid" style="width: 100%; min-height: 100px;">
		<thead>
	        <tr>
	            <th data-options="field:'zhuangt'">状态</th>
	            <th data-options="field:'fukdbm'">付款编码</th>
	            <th data-options="field:'shenqdbm'">申请单编码</th>
	            <th data-options="field:'pingz'">凭证号</th>
	            <th data-options="field:'bib'">币别</th>
	            <th data-options="field:'yuanbje'">原币金额</th>
	            <th data-options="field:'huil'">汇率</th>
	            <th data-options="field:'beiwbje'">本位币金额</th>
	            <th data-options="field:'faphm'">发票号码</th>
	            <th data-options="field:'fapje'">发票金额</th>
	            <th data-options="field:'yewrq'">业务日期</th>
	            <th data-options="field:'fukrq'">付款日期</th>
	            <th data-options="field:'shoukdw'">收款单位</th>
	            <th data-options="field:'shijskdw'">实际收款单位</th>
	            <th data-options="field:'zhidr'">制单人</th>
	            <th data-options="field:'zhidrq'">制单日期</th>
	            <th data-options="field:'fuksm'">付款说明</th>
	            <th data-options="field:'shoukyh'">收款银行</th>
	            <th data-options="field:'shoukzh'">收款账户</th>
	            <th data-options="field:'shenpr'">审批人</th>
	            <th data-options="field:'shenprq'">审批日期</th>
	        </tr>
	    </thead>
	</table>
<body>
</html>