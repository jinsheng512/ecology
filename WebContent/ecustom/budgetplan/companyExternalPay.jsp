<%@page import="ecustom.util.CustomUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BP07-外部经营性支出主表</title>
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="companyExternalPay.js"></script>
</head>
<%
	Integer year = CustomUtil.getInteger(request.getParameter("year"), null);
	if (year == null) {
		year = Calendar.getInstance().get(Calendar.YEAR);
	}
	String companyId = request.getParameter("companyId");
	if (companyId == null) {
		companyId = "";
	}
	String blockId = request.getParameter("blockId");
	if (blockId == null) {
		blockId = "";
	}
%>
<body>
	<input type="hidden" id="companyId" name="companyId" value="<%=companyId %>"/>
	<input type="hidden" id="blockId" name="blockId" value="<%=blockId %>"/>
    <div style="text-align: right;">
        <a id="btnCancel" href="#" class="easyui-linkbutton" style="display: none">取消</a>
        <a id="btnSave" href="#" class="easyui-linkbutton" style="display: none">保存</a>
        <a id="btnEdit" href="#" class="easyui-linkbutton" style="display: none">编辑</a>
        <a id="btnActive" href="#" class="easyui-linkbutton" style="display: none">生效</a>
       
    </div>
	<div style="padding-top: 5px;">
	    <table class="tblMain" style="width: 100%;" cellpadding="0" cellspacing="0">
	    	<tr>
	    		<th style="width: 13%;">&nbsp;公司名称</th>
	    		<td style="width: 20%;">&nbsp;<span id="companyName"></span></td>
	    		<th style="width: 13%;">&nbsp;年度</th>
	    		<td style="width: 20%;">&nbsp;
	    			<select id="cmbYear">
	    				<option value="2020" <%=year == 2020 ? "selected" : "" %>>2020</option>
	    				<option value="2019" <%=year == 2019 ? "selected" : "" %>>2019</option>
	    				<option value="2018" <%=year == 2018 ? "selected" : "" %>>2018</option>
	    			</select>
	    		</td>
	    		<th style="width: 13%;">&nbsp;状态</th>
	    		<td style="width: 20%;">&nbsp;<span id="status"></span></td>
	    	</tr>
	    </table>
	</div>
	<div>
	    <table id="dg" class="easyui-datagrid" style="width:100%;height:250px">
        <thead>
            <tr>
                <th data-options="field:'id', width: '5%'">科目ID</th>
                <th data-options="field:'accountName',width:'19%'">科目名称</th>
                <th data-options="field:'season1',width:'19%',align:'right',editor:{type:'numberbox',options:{precision:2, required: true}}, formatter: toMoney">第一季度</th>
                <th data-options="field:'season2',width:'19%',align:'right',editor:{type:'numberbox',options:{precision:2, required: true}}, formatter: toMoney">第二季度</th>
                <th data-options="field:'season3',width:'19%',align:'right',editor:{type:'numberbox',options:{precision:2, required: true}}, formatter: toMoney">第三季度</th>
                <th data-options="field:'season4',width:'19%',align:'right',editor:{type:'numberbox',options:{precision:2, required: true}}, formatter: toMoney">第四季度</th>
            </tr>
        </thead>
    </table>
	</div>
</body>
</html>