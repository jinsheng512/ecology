<%--
	名称：项目合同结算单明细信息集合。
	作者：曹水涛
	创建日期：2016-12-25
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目合同结算单明细信息</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/css/workflow/form/Form261EntriesView.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/workflow/form/Form261EntriesView.js"></script>
</head>
<body>
<input id="requestId" type="hidden" value="<%=request.getParameter("requestId") %>">
<div id="tabs" class="easyui-tabs" style="width:100%;height:400px;padding:0px;">
	<div title="合同信息" style="padding:2px">
		<iframe id="frmHetxx" src="" style="width: 100%; height:360px; border: 0px; padding: 0px;" onload="resizeTabWidth()"></iframe>
	</div>
	<div title="变更信息" style="padding:2px">
		<iframe id="frmBiangxx" src="" style="width: 100%; height:360px; border: 0px; padding: 0px;"></iframe>
	</div>
	<div title="成本信息" style="padding:2px">
		<iframe id="frmChengbxx" src="" style="width: 100%; height:360px; border: 0px; padding: 0px;"></iframe>
	</div>
	<div title="付款信息" style="padding:2px">
		<iframe id="frmFukxx" src="" style="width: 100%; height:360px; border: 0px; padding: 0px;"></iframe>
	</div>
	<div title="付款申请" style="padding:2px">
		<iframe id="frmFuksqxx" src="" style="width: 100%; height:360px; border: 0px; padding: 0px;"></iframe>
	</div>
	<!-- <div title="关联合同页签" style="padding:2px">
		<iframe id="frmGuanlxx" src="" style="width: 100%; height:360px; border: 0px; padding: 0px;"></iframe>
	</div> -->
	<!-- <div title="违约金" style="padding:2px">
		<iframe id="frmWeiyxx" src="" style="width: 100%; height:360px; border: 0px; padding: 0px;"></iframe>
	</div> -->
	<div title="扣款" style="padding:2px">
		<iframe id="frmKoukxx" src="" style="width: 100%; height:360px; border: 0px; padding: 0px;"></iframe>
	</div>
	<div title="奖励" style="padding:2px">
		<iframe id="frmJianglxx" src="" style="width: 100%; height:360px; border: 0px; padding: 0px;"></iframe>
	</div>
	<div title="工程付款情况表" style="padding:2px">
		<iframe id="frmGongcfkxxb" src="" style="width: 100%; height:360px; border: 0px; padding: 0px;"></iframe>
	</div>
</div>
<body>
</html>