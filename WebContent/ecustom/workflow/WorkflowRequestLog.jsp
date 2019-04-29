<%--
	名称：签字意见历史记录。
	作者：曹水涛
	创建日期：2017-03-18
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="greedc.services.*, greedc.vo.*, java.util.*"%>
<%
	String requestId = request.getParameter("requestId");
	List<RequestLogVo> vos = new RequestLogService().listByBillCode(requestId);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>签字意见历史记录</title>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/jQuery.openWindow.js"></script>
    <style type="text/css">
    a {
    	color: #099cff;
    	text-decoration: none;
    }
    table {
    	width: 100%;
    }
    div {
    	font-size: 9pt;
    }
    div.bottomline {
    	border-bottom: 1px #D6D6D6 solid;
    	padding-top: 5px;
    	padding-bottom: 5px;
    }
    div.blue {
    	color: #099cff;
    }
    div.grey {
    	color: #9b9b9b;
   	}
    </style>
</head>
<body>
	<div style="display: <%=(vos.size() > 0 ? "none" : "block") %>">该单据没有历史审批记录</div>
	<%
	for (RequestLogVo vo : vos) {
	%>
	<div class="bottomline">
	<table>
		<colgroup>
			<col width="200" />
			<col width="*" />
			<col width="300" />
		</colgroup>
		<tr>
			<td>
				<div class="blue"><a target="_blank" href="javascript:$.openFullWindow('/hrm/HrmTab.jsp?_fromURL=HrmResource&id=<%=vo.getOperator() %>')"><%=vo.getName() %></a></div>
				<div class="grey"><%=vo.getDeptName() %></div>
			</td>
			<td>
				<div><%=vo.getLogContent() %></div>
				<div class="grey">接收人：<%=vo.getReNames() %></div>
			</td>
			<td>
				<div class="grey"><%=vo.getOpDate() %> <%=vo.getOpTime() %></div>
				<div class="blue">[<%=vo.getNodeName() %> / <%=vo.getOpTypeName() %> ]</div>
			</td>
		</tr>
	</table>
	</div>
	<%}%>
<body>
</html>