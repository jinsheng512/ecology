<%@ page import="java.util.*, ecustom.util.*, weaver.proj.util.*, weaver.file.*, weaver.hrm.*" %><%--
  * User: William
  * Date: 2018-3-25
  * 打开人工岛待办
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
User user = HrmUserVarify.getUser(request, response);
String loginId = user.getLoginid();
long timestamp = System.currentTimeMillis();
// Mobile6管理员登录--客户端管理--系统设置--链接密钥
String rgdUrl = Prop.getPropValue("ecustom", "rgd.emobile.url");
String secrect = Prop.getPropValue("ecustom", "rgd.emobile.secrect");
String loginToken = CodeUtil.hexSHA1(secrect + loginId + timestamp);
String ssoUrl = rgdUrl + "/customVerifyLogin.do"
		+ "?loginid=" + loginId + "&timestamp=" + timestamp
		+ "&loginTokenFromThird=" + loginToken;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<script type="text/javascript">
		window.location.href = '<%=ssoUrl %>';
	</script>
</head>
<body>
</body>
</html>