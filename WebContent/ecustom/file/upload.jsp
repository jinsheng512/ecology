<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
	<form method="post" action="/greedc/servlets/FileUpload-upload.json" enctype="multipart/form-data">
		<div>file: <input type="file" name="file"/></div>
		<div>fileName: <input type="text" name="fileName" value="合同名称" /></div>
		<div>fileType: <input type="text" name="fileType" value="xls" /></div>
		<div>empCode: <input type="text" name="empCode" value="002486" /></div>
		<div>workflowId: <input type="text" name="workflowId" value="241" /></div>
		<div><input type="submit" value="Submit" /></div>
	</form>
</body>
</html>