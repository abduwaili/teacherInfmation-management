<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>左边导航框架</title>
	<link rel="stylesheet" type="text/css" href="css/style1.css">
	</head>
<body style="background-color:#CCC">
	<%
		String s=(String)session.getAttribute("AdminLogin")+"";
		if(s.equals("Yes")==false)
		{
			response.sendRedirect("admin_login.jsp");	
		}
	%>
	<div class="style1">
		<ul >
			<li><a href="admin_inf.jsp" target="main_frame">我的个人信息</a></li>
			<li><a href="students.jsp" target="main_frame">所有学生</a></li>
			<li><a href="teachers.jsp" target="main_frame">所有教师</a></li>
			<li><a href="FeedBack.jsp" target="main_frame">用户反馈</a></li>
			<li><a href="authstr.jsp" target="main_frame">待审核注册用户</a></li>
		</ul>
	</div>

</body>
</html>