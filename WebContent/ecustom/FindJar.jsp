<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-6-7
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String className = request.getParameter("className");
    String jarFilePath = "";
    if (className != null) {
        Class clazz = Class.forName(className);
        jarFilePath = clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
ClassName: <%=className %> <br/>
JarFilePath: <%=jarFilePath %>
</body>
</html>
