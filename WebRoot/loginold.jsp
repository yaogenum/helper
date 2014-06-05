<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta content="noindex,nofollow" name="robots">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="jquery/jquery.js"></script>
  </head>

  <body>
  
  	<p>
	
 <!-- 	<s:fielderror name="noTtrue"></s:fielderror>
	<s:fielderror name="username"></s:fielderror>-->
  	</p>
  	
	<s:fielderror name="nottrue" />
	<s:fielderror name="username" />
	<table>
  	<s:form action="userlogin" namespace="/send" >
			<tr>
				<s:textfield name="username" key="用户名" />
			</tr>
			
			<tr>
			<s:textfield name="userpassword" key="用户密码" />
			</tr>
			<tr>
				<s:submit key="login" />
			</tr>
	</s:form>
	
	</table>
  </body>
</html>
