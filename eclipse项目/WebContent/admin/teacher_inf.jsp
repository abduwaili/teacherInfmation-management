<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.struts2.action.Teacher"%>
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
<title><%=request.getParameter("TeacherAccount")%></title>
</head>
<body>
	<%
		String TeacherAccount=request.getParameter("TeacherAccount");
		Connection conn;	
		Statement stmt;	
		ResultSet rs;	
					
		 try {
			 conn=DatabaseConnection.getConnection();
			 stmt=conn.createStatement();
			 String sql="select *from teacher where TeacherAccount='"+TeacherAccount+"'";
			 rs=stmt.executeQuery(sql);
			 Teacher teacher=new Teacher();
			 if(rs.next())
			 {
				 teacher.setTeacherAccount(rs.getString("TeacherAccount"));
				 teacher.setTeacherName(rs.getString("TeacherName"));
			
				 teacher.setCollege(rs.getString("College"));
				 teacher.setPosition(rs.getString("Position"));
				 teacher.setPhone(rs.getString("Phone"));
				 teacher.setEmail(rs.getString("Email"));
				 teacher.setPicture(rs.getString("Picture"));
				 teacher.setResearchDirection(rs.getString("ResearchDirection"));
				 teacher.setRecord(rs.getString("Record"));
				 teacher.setRecord(rs.getString("Money"));
				 teacher.setFSRA(rs.getString("FSRA"));
				 
	%>
				<div align="center">
					<table cellpadding="10" cellspacing="10" border="1" width="600"  style="text-align:left;">
						<caption><%=teacher.getTeacherName()+"的个人信息"%></caption>			
							<tr>
								<td width="200" rowspan="10">
									<img src=<%="../"+teacher.getPicture() %> title=<%=teacher.getTeacherName() %> style="height:250px; width:200px"/>
								</td>
								<td width="50">账号</td>
								<td><%=teacher.getTeacherAccount() %></td>
							</tr>
							<tr>
								<td>姓名</td>
								<td><%=teacher.getTeacherName() %></td>
							</tr>
							<tr>
								<td>学院</td>
								<td><%=teacher.getCollege() %></td>
							</tr>
							<tr>
								<td>职位</td>
								<td><%=teacher.getPosition() %></td>
							</tr>
							<tr>
								<td>研究方向</td>
								<td><%=teacher.getResearchDirection() %></td>
							</tr>
							<tr>
								<td>履历</td>
								<td><%=teacher.getRecord() %></td>
							</tr>
							<tr>
								<td>基金</td>
								<td><%=teacher.getMoney() %></td>
							</tr>
							<tr>
								<td>科研成就</td>
								<td><%=teacher.getFSRA() %></td>
							</tr>
							<tr>
								<td>电话</td>
								<td><%=teacher.getPhone() %></td>
							</tr>
							<tr>
								<td>邮箱</td>
								<td><%=teacher.getEmail() %></td>
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