<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理员个人信息</title>
		<link rel="stylesheet" type="text/css" href="css/style1.css">
		<style>
			body{
				margin:0xp;
				padding:0px;
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
	<div align="center" style="margin-top:40px"><h1><!-- #BeginDate format:Ch2 -->2017年11月30日 <!-- #EndDate --></h1></div>
	<div align="center" class="right" style="margin-top:40px">
		<table border="1" width="90%" cellpadding="0" cellspacing="0">
			<caption><h1>我的个人信息</h1></caption>
			<tr>
				<th>ID</th>
				<th>姓名</th>
				<th>管理员账号</th>
				<th>手机</th>
				<th>邮箱</th>
			</tr>
			<tr>
				<th>1</th>
				<th>阿杜</th>
				<th>abduwaili</th>
				<th>17745135600</th>
				<th>2696272844@qq.com</th>
			</tr>
		</table>
	</div>
	
</body>
</html>