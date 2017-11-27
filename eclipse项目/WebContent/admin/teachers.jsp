<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.struts2.action.Teacher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>

<%@page import="database.DatabaseConnection"%>
<%@page import="admin.AdminAction"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有教师列表</title>
</head>
<body>

	<table cellspacing="1" cellpadding="1" border="1" width="80%">
		<caption>教师列表</caption>
		<tr>
			<th>序号</th>
			<th>账号</th>	
			<th>姓名</th>
			<th>职位</th>
		</tr>
		<%
			Connection conn;	
			Statement stmt;	
			ResultSet rs;
		
			try {
				conn=DatabaseConnection.getConnection();
				 stmt=conn.createStatement();
				 String sql="select *from teacher ";
				 rs=stmt.executeQuery(sql);
				 ArrayList<Teacher>list=new ArrayList<>();
				 int i=0;
				 while(rs.next()) {
				 i++;
		  %>
		  		<tr>
					<th><%=i%></th>
				<th><%=rs.getString("TeacherAccount")%></th>
				<th><%=rs.getString("TeacherName")%></th>
				<th><%=rs.getString("Position")%></th>
				
				<th><img src="img/abduwaili.jpg" alt="阿杜" title="阿杜" style="height:50px; width:50px"/></th>
				
				</tr>	 
			<% 
				 }
				
			}catch (Exception e) {
				out.print("查询失败");
			}
		%>
	</table>
	
</body>
</html>