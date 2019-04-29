<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ecustom.util.CustomUtil" %>
<%
	int requestId = CustomUtil.getInt(request.getParameter("requestId"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目付款明细表</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/datagrid-filter.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/ecustom/workflow/form/Form50DetailView.js?time=201801111559"></script>
    <script type="text/javascript" src="/ecustom/js/jQuery.formatMoney.js"></script>
</head>
<body style="padding: 0px; margin: 0px;">
	<input id="requestId" type="hidden" value="<%=request.getParameter("requestId") %>"/>
    <table id="dg" class="easyui-datagrid" title="项目付款明细表" style="width:100%;min-height:150px">
    	<thead>
    		<tr>
    			<th data-options="field:'seqNo',hidden:true">序列号</th>
    			<th data-options="field:'requestId',hidden:true">RequestId</th>
    			<th data-options="field:'projectName'">项目名称</th>
    			<th data-options="field:'payCo'">付款单位</th>
    			<th data-options="field:'accPayable',align:'right',formatter:$.formatMoney">本月应付合计</th>
    			<th data-options="field:'proportion',align:'right'">本月付款比例</th>
    			<th data-options="field:'payTotal',align:'right',formatter:$.formatMoney">上月付款合计</th>
    			<th data-options="field:'conNum'">合同数量</th>
    			<th data-options="field:'statisticalDept',hidden:true">统计部门</th>
    			<th data-options="field:'year',hidden:true">年</th>
    			<th data-options="field:'month',hidden:true">月</th>
    		</tr>
    	<thead>
    </table>
    
	<div id="win" class="easyui-window" title="项目付款计划明细表" style="top:0px; left:0px; width:850px;height:280px;" data-options="closed:true">
	<table>
		<tr>
			<td style="width:100px">汇总部门</td>
			<td colspan="4" id="statisticalDept"></td>
		</tr>
		<tr>
			<td>项目名称</td>
			<td style="width:200px" id="projectName">&nbsp;</td>
			<td style="width:100px">付款单位</td>
			<td style="width:200px" id="payCo" colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td>编制期间</td>
			<td><span id="year"></span>年<span id="month"></span>月</td>
			<td>勾选状态</td>
			<td>
				<select id="goux" class="easyui-combobox" data-options="panelHeight:'auto',onChange:ECSTM.mainGouxChange">
					<option value="-1">全部</option>
					<option value="0">会计驳回</option>
					<option value="1">复核</option>
					<option value="2">修改</option>
					<option value="3">执行</option>
				</select>
			</td>
			<td style="padding-left: 150px;">
				<a id="btnSave" href="javascript:void(0);" class="easyui-linkbutton"
			 		data-options="iconCls:'icon-save'">保存修改</a>
			 </td>
		</tr>
	</table>
	<table id="dgDetail" class="easyui-datagrid" title="费用计划清单（单位：元）" style="width:100%;height:260px">
    	<thead>
    	<tr>
    		<th rowspan="2" data-options="field:'seqNo',hidden:true">分录信息序列号</th>
    		<th rowspan="2" data-options="field:'mainSeqNo',hidden:true">主表序列号</th>
    		<th rowspan="2" data-options="field:'bianmxh'">编码序号</th>
    		<th rowspan="2" data-options="field:'goux',formatter:ECSTM.fmtGoux,editor:ECSTM.editorGoux,width:80">勾选</th>
    		<th rowspan="2" data-options="field:'gongcxm'">工程项目</th>
    		<th rowspan="2" data-options="field:'hetbm'">合同编码</th>
    		<th rowspan="2" data-options="field:'hetmc'">合同名称</th>
    		<th colspan="2">合同金额</th>
    		<th rowspan="2" data-options="field:'xingxjdms'">形象进度描述</th>
    		<th colspan="2">截止本月初累计进度</th>
    		<th rowspan="2" data-options="field:'benywcjd'">本月完成进度</th>
    		<th rowspan="2" data-options="field:'benyjdfk',align:'right',formatter:$.formatMoney">本月进度付款</th>
    		<th colspan="2">已支付工程款</th>
    		<th rowspan="2" data-options="field:'benyjhfke',align:'right',formatter:$.formatMoney">本月计划付款额</th>
    		<th rowspan="2" data-options="field:'leijbl',align:'right'">累计比例</th>
    		<th rowspan="2" data-options="field:'kuanxlb'">款项类别</th>
    		<th rowspan="2" data-options="field:'beiz'">备注</th>
    		<th rowspan="2" data-options="field:'shenpryj'">审批人意见</th>
    		<th rowspan="2" data-options="field:'shoukdwqc'">收款单位全名（施工单位）</th>
    		<th rowspan="2" data-options="field:'shijskdw'">实际收款单位</th>
    	</tr>
    	<tr>
    		<th data-options="field:'hetje',align:'right',formatter:$.formatMoney">合同金额</th>
    		<th data-options="field:'tiaozhje',align:'right',formatter:$.formatMoney">调整后金额</th>
    		<th data-options="field:'yuecljje',align:'right',formatter:$.formatMoney">累计金额</th>
    		<th data-options="field:'yuecljbl',align:'right'">累计比例%</th>
    		<th data-options="field:'je',align:'right',formatter:$.formatMoney">金额</th>
    		<th data-options="field:'yifkbl',align:'right'">已支付比例%</th>
    	</tr>
    	</thead>
	</table>
    </div>
</body>
</html>