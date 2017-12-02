<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.struts2.action.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>

<%@page import="database.DatabaseConnection"%>
<%@page import="com.admin.action.AdminAction"%>

<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=request.getParameter("StudentAccount")%></title>
</head>
<body>
	<%
		String s=(String)session.getAttribute("AdminLogin")+"";
		if(s.equals("Yes")==false)
		{
			response.sendRedirect("admin_login.jsp");	
		}
	%>
	<%
		String StudentAccount=request.getParameter("StudentAccount");
		Connection conn;	
		Statement stmt;	
		ResultSet rs;	
	
		 try {
			 conn=DatabaseConnection.getConnection();
			 stmt=conn.createStatement();
			 String sql="select *from student where StudentAccount='"+StudentAccount+"'";
			 rs=stmt.executeQuery(sql);
			 Student student=new Student();
			 if(rs.next())
			 {
				student.setStudentAccount(rs.getString("StudentAccount"));
				student.setStudentName(rs.getString("StudentName"));
				student.setMajor(rs.getString("Major"));
				student.setCollege(rs.getString("College"));
				student.setGrade(rs.getString("Grade"));
				student.setPhone(rs.getString("Phone"));
				student.setEmail(rs.getString("Email"));
				student.setPicture(rs.getString("Picture"));
	%>
				<div align="center">
					<table cellpadding="10" cellspacing="10"  border="1" width="1000" class="table">
						<caption><%=student.getStudentName()+"的个人信息"%></caption>			
							<tr>
								<td width="200" rowspan="8">
									<img src=<%="../"+student.getPicture() %> title=<%=student.getStudentName() %> style="height:250px; width:200px"/>
								</td>
								<td width="50">账号</td>
								<td><%=student.getStudentAccount() %></td>
							</tr>
							<tr>
								<td>姓名</td>
								<td><%=student.getStudentName() %></td>
							</tr>
							<tr>
								<td>学院</td>
								<td><%=student.getCollege() %></td>
							</tr>
							<tr>
								<td>专业</td>
								<td><%=student.getMajor() %></td>
							</tr>
							<tr>
								<td>年级</td>
								<td><%=student.getGrade() %></td>
							</tr>
							<tr>
								<td>电话</td>
								<td><%=student.getPhone() %></td>
							</tr>
							<tr>
								<td>邮箱</td>
								<td><%=student.getEmail() %></td>
							</tr>
					</table>
				</div>
	<% 	 
			 }else {
				out.print("页面出现错误！无法查找!");
			}			 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	%>
	
</body>
</html>