<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>顶部框架</title>
	<link rel="stylesheet" type="text/css" href="css/style1.css">
		<style type="text/css">
		body{
			text-align: center;
			background-color: #4A4A4A;
			heigth:20px;
		}
	</style>
</head>
<body>
	<%
		String s=(String)session.getAttribute("AdminLogin")+"";
		if(s.equals("Yes")==false)
		{
			response.sendRedirect("admin_login.jsp");	
		}
	%>
	<div class="top1">
		<!-- <a href="admin_login.jsp" target="_blank" >登陆</a> -->
		<form action="SiginOut" style="display: inline;">	
			<input type="submit" name="SiginOut" value="退出" style="background-color: transparent;">
		</form>
	</div>
	
</body>
</html>