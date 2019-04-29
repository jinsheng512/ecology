<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司科目</title>
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="companyAccount.js"></script>
<style type="text/css">
* {
	font-size: 12px;
}

th {
	width: 200px;
	background-color: #E7F3FC;
}

#tblCompany {
	border-top: 1px #90BADD solid;
	border-left: 1px #90BADD solid;
}

#tblCompany>tbody>tr {
	height: 30px;
}

#tblCompany>tbody>tr>th {
	border-right: 1px #90BADD solid;
	border-bottom: 1px #90BADD solid;
	width: 15%;
}

#tblCompany>tbody>tr>td {
	border-right: 1px #90BADD solid;
	border-bottom: 1px #90BADD solid;
	width: 35%;
}

.tree-node {
	height: 25px;
}

.tree-folder-open, .tree-file, .tree-expanded, .tree-folder, .tree-collapsed {
	background: url('') no-repeat -224px 0;
}
</style>
</head>
<body>
	<input type="hidden" id="companyId" name="companyId" value="<%=request.getParameter("companyId") %>"/>
    <div style="text-align: right;">
        <a href="javascript:void()" class="easyui-linkbutton" onclick="save()">保存</a>
    </div>
	<div style="padding-top: 5px;">
	    <table id="tblCompany" style="width: 100%;" cellpadding="0" cellspacing="0">
	    	<tr>
	    		<th>&nbsp;板块名称</th>
	    		<td>&nbsp;<span id="blockName"></span></td>
	    		<th>&nbsp;公司名称</th>
	    		<td>&nbsp;<span id="companyName"></span></td>
	    	</tr>
	    </table>
	</div>
	<div>
	    <table style="width: 100%;">
			<tr>
				<td valign="top" style="width: 50%;">
					<div style="padding-left: 20px; padding-top: 10px; font-size: 14px; font-weight: 700;">勾选收款科目</div>
					<div style="padding-left: 20px; padding-top: 10px;">
						<ul id="treeInAccount" class="easyui-tree" data-options="checkbox:true"></ul>
					</div>
				</td>
				<td valign="top" style="width: 50%;">
					<div style="padding-left: 20px; padding-top: 10px; font-size: 14px; font-weight: 700;">勾选付款科目</div>
					<div style="padding-left: 20px; padding-top: 10px;">
						<ul id="treeOutAccount" class="easyui-tree" data-options="checkbox:true"></ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>