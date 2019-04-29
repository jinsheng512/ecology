<%--
	名称：合同信息
	作者：曹水涛
	创建日期：2016-12-25
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>合同信息</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/css/workflow/form/Form261EntriesView.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/workflow/form/Form261EntriesViewHetxx.js"></script>
</head>
<body>
	<input id="dataId" type="hidden" value="<%=request.getParameter("dataId") %>"/>
	<table class="e-table" cellspacing="0" cellpadding="0">
		<tr>
			<th>组织</th>
			<td colspan="2"><div id="zuz"></div></td>
			<th>工程项目</th>
			<td colspan="2"><div id="gongcxm"></div></td>
		</tr>
		<tr>
			<th>合同类型</th>
			<td colspan="2"><div id="hetlx"></div></td>
			<th>合同编号</th>
			<td colspan="2"><div id="hetbh"></div></td>
		</tr>
		<tr>
			<th>合同名称</th>
			<td colspan="2"><div id="hetmc"></div></td>
			<th>甲方</th>
			<td colspan="2"><div id="jiaf"></div></td>
		</tr>
		<tr>
			<th>乙方</th>
			<td colspan="2"><div id="yif"></div></td>
			<th>丙方</th>
			<td colspan="2"><div id="bingf"></div></td>
		</tr>
		<tr>
			<th>合同性质</th>
			<td><div id="hetxz"></div></td>
			<th>币别</th>
			<td><div id="bib"></div></td>
			<th>汇率</th>
			<td><div id="huil"></div></td>
		</tr>
		<tr>
			<th>签约日期</th>
			<td><div id="qianyrq"></div></td>
			<th>签约金额原币</th>
			<td><div id="qianyjeyb"></div></td>
			<th>签约金额本币</th>
			<td><div id="qianyjebb"></div></td>
		</tr>
		<tr>
			<th>责任部门</th>
			<td><div id="zerbm"></div></td>
			<th>原币金额</th>
			<td><div id="yuanbje"></div></td>
			<th>本币金额</th>
			<td><div id="bengbje"></div></td>
		</tr>
		<tr>
			<th>责任人</th>
			<td><div id="zerr"></div></td>
			<th>原币金额大写</th>
			<td><div id="yuanbjedx"></div></td>
			<th>本币金额大写</th>
			<td><div id="bengbjedx"></div></td>
		</tr>
		<tr>
			<th>业务日期</th>
			<td><div id="yewqj"></div></td>
			<th>保修金比例(%)</th>
			<td><div id="baoxjbl"></div></td>
			<th>保修金额</th>
			<td><div id="baoxje"></div></td>
		</tr>
		<tr>
			<th>订立期间</th>
			<td><div id="dinglrq"></div></td>
			<th>形成方式</th>
			<td><div id="xingcfs"></div></td>
			<th>变更提示比例(%)</th>
			<td><div id="biangtsbl"></div></td>
		</tr>
		<tr>
			<th>制单人</th>
			<td><div id="shenqr"></div></td>
			<th>造价性质</th>
			<td><div id="zaojxz"></div></td>
			<th>结算提示比例(%)</th>
			<td><div id="jiestsbl"></div></td>
		</tr>
		<tr>
			<th>制单时间</th>
			<td><div id="shenqrq"></div></td>
			<th><input id="jinrdtcb" type="checkbox" disabled="disabled"/> 进入动态成本</th>
			<th><input type="checkbox" disabled="disabled"/> 是否甲供材料合同</th>
			<th>付款提示比例(%)</th>
			<td><div id="fuktsbl"></div></td>
		</tr>
		<tr>
			<th>印花税率(%)</th>
			<td><div id="yinhsl"></div></td>
			<th>印花税金额</th>
			<td><div id="yinhsje"></div></td>
			<th>进度款付款比例(%)</th>
			<td><div id="jindkfkbl"></div></td>
		</tr>
		<!-- <tr>
			<th>合同范本</th>
			<td colspan="2"><div id="hetfb"></div></td>
			<th>附件列表</th>
			<td colspan="2">&nbsp;</td>
		</tr> -->
	</table>
	<div style="height: 10px;"></div>
	<table id="dgCon" class="easyui-datagrid" style="min-height: 100px; width: 900px;" data-options="
		title:'合同详细信息',singleSelect: true">
		<thead>
	        <tr>
	            <th data-options="field:'xiangxxx',width: 200">详细信息</th>
	            <th data-options="field:'neir',width: 300">内容</th>
	            <th data-options="field:'miaos',width: 300">描述</th>
	        </tr>
	    </thead>
	</table>
<body>
</html>