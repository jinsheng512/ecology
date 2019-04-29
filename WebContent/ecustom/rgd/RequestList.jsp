<%@page import="java.io.*, java.net.*, weaver.hrm.*, weaver.general.*, weaver.file.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
</head>
<body>
<%
	User user = HrmUserVarify.checkUser(request, response);
	String sessionId = request.getSession().getId();
	String token = new MD5().getMD5ofStr(request.getSession().getId());
	String userName = user.getLoginid();
	String pageSize = request.getParameter("pagesize");
	String gopage = request.getParameter("gopage");
	String hostPort = Prop.getPropValue("ecustom", "rgd.ecology.url");
	
	String urlString = hostPort + "/ecustom/e8/servlets/Login-saveSysSession.json?sys=greedcoa";
	urlString += "&username=" + userName;
	urlString += "&sessionid=" + sessionId;
	URL url = new URL(urlString);
	HttpURLConnection connection=(HttpURLConnection) url.openConnection();
	connection.setDoInput(true);	//设置可输入
	connection.setDoOutput(true);//设置可以输出
	connection.setRequestProperty("Content-type","application/x-java-serialized-object");
	connection.setRequestMethod("GET");//设置请求方式//必须是大写
	connection.setConnectTimeout(5000);//设置连接超时时间
	connection.setReadTimeout(5000);//设置读数据的超时时间
	connection.setUseCaches(true); 
	connection.connect();//连接服务器
	BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String data;
    StringBuilder builder=new StringBuilder();
    while((data=reader.readLine()) != null){
        builder.append(data);
    }
    String result = builder.toString();
    System.out.println("RequestList.jsp, result = " + result);
%>
</body>
<script type="text/javascript">
$(document).ready(function(){
	var url = '<%=hostPort%>/ecustom/e8/servlets/Request-ssoRequestList.html';
	url += '?sys=greedcoa';
	url += '&pagesize=<%=pageSize %>';
	url += '&token=<%=token %>';
	url += '&username=<%=userName %>';
	url += '&gopage=<%=gopage %>';
	window.location.href = url;
	// console.log(url);
});
</script>
</html>