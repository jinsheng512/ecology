<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,greedc.entities.DatagridColumn,ecustom.util.CustomUtil" %>
<jsp:useBean id="datagridColumnService" class="greedc.services.DatagridColumnService" scope="page"></jsp:useBean>
<%
	int requestId = CustomUtil.getInt(request.getParameter("requestid"));
	List<DatagridColumn> colList = datagridColumnService.getForm44EntriesColumns(requestId);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>薪酬发放明细</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/workflow/form/Form163EntriesView.js"></script>
</head>
<body style="padding: 0px; margin: 0px;">
	<input id="requestId" type="hidden" value="<%=request.getParameter("requestid") %>"/>
	<input id="nodeId" type="hidden" value="<%=request.getParameter("nodeid") %>"/>
    <table id="dg" class="easyui-datagrid" title="薪酬发放明细" style="width:100%;height:300px">
    	<thead data-options="frozen:true">
    		<tr>
			<%
			String title = "";
			String field = "";
			
    		for (int i = 0; i < colList.size(); i++) {
    			title = colList.get(i).getTitle();
    			field = colList.get(i).getField();
    			
    			// 如果是姓名
    			if ("FPER001".equalsIgnoreCase(field)) {
    	    		break ;
    			}
    		}%>
    			<th data-options="field:'<%=field %>'"><%=title %></th>
			</tr>
		</thead>
    	<thead>
    		<tr>
    		<%
    		boolean isChuNa = "589".equals(request.getParameter("nodeid"));
    		String chuNaFields = "FPER001,FPER025,FSAL006";
    		
    		for (int i = 0; i < colList.size(); i++) {
    			title = colList.get(i).getTitle();
    			field = colList.get(i).getField();
    			
    			// 如果是出纳，只显示chuNaFields相关列
    			if (isChuNa && !chuNaFields.contains(field)) {
    				continue ;
    			}
    			
    			// 如果是姓名，跳过
    			if ("FPER001".equalsIgnoreCase(field)) {
    				continue ;
    			}
    			
    			String other = "";
    			if (CustomUtil.isNotBlank(colList.get(i).getFormatter())) {
    				other += ", formatter: " + colList.get(i).getFormatter();
    			}
    			if (colList.get(i).getWidth() != null && colList.get(i).getWidth().intValue() > 0) {
    				other += ", width: " + colList.get(i).getWidth();
    			}
    			if (CustomUtil.isNotBlank(colList.get(i).getAlign()) && !colList.get(i).getAlign().equalsIgnoreCase("left")) {
    				other += ", align: '" + colList.get(i).getAlign() + "'";
    			}
    		%>	<th data-options="field:'<%=field %>'<%=other %>"><%=title %></th>
    		<%} %></tr>
    	<thead>
    </table>
</body>
</html>