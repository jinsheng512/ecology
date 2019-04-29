<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>生成OA流程需要信息</title>
	<link rel="stylesheet" type="text/css" href="/ecustom/css/ecustom.css">
	<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
	<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
	<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/ecustom/js/easyui/datagrid-groupview.js"></script>
	<script type="text/javascript" src="/ecustom/js/workflow/ws/GenerateDescription.js"></script>
	<style type="text/css">
		.fldname {
			border-right: 1px solid #C3D9E0;
			border-bottom: 1px solid #C3D9E0;
			background-color: #DAEEF5;
		}
	</style>
</head>
<body>
<input id="workflowId" type="hidden" value="<%=request.getParameter("workflowId") %>"/>

<select id="cc" class="easyui-combobox" name="workflowId" style="width:200px;">
	<option value="122">人事业务 - 内部异动申请流程</option>
	<option>bitem2</option>
	<option>bitem3</option>
	<option>ditem4</option>
	<option>eitem5</option>
</select>

<table id="pg" class="easyui-propertygrid" style="width:100%;"></table>

<div style="margin-top: 10px;">
<table id="dg" class="easyui-datagrid" style="width: 100%; min-height: 300px;">
	<thead>
		<tr>
			<th data-options="field:'fieldId',align:'center'">字段ID</th>
			<th data-options="field:'fieldName'">字段名</th>
			<th data-options="field:'tableName', hidden: true">表名</th>
			<th data-options="field:'paramLabelName',formatter:fmt.paramLabelName">字段中文名</th>
			<th data-options="field:'paramDesc'">传值说明</th>
			<th data-options="field:'remarks'">备注</th>
		 </tr>
	 <thead>
</table>
</div>
</body>
</html>