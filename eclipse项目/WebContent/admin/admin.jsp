<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理员首页</title>
</head>
	<%
		String s=(String)session.getAttribute("AdminLogin")+"";
		if(s.equals("Yes")==false)
		{
			response.sendRedirect("admin_login.jsp");	
		}
		else{
	%>
	
		<frameset rows="24,*">
			<frame src="top_frame.jsp" name="top_frame" noresize="noresize" frameborder="0" scrolling="no" >
			<frameset cols="180,*">
				<frame src="left_frame.jsp" name="left_frame" noresize="noresize" frameborder="0" />
				<frame src="admin_inf.jsp" name="main_frame" noresize="noresize"  frameborder="0" />
			</frameset>
		</frameset>
	<% }%>
<body>
	
</body>
</html>