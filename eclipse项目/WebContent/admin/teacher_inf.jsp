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
	<link rel="stylesheet" type="text/css" href="css/style1.css">
	<script type="text/javascript" src="js/adjs.js"></script>
	<script src="../js/jquery-3.2.1.js"></script>
    <script src="../js/jquery-3.2.1.min.js"></script>
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
				<div align="center" style="margin-top:50px;margin-bottom: 100px">
				<form action="" method="post" accept-charset="utf-8">
					<table cellpadding="10" cellspacing="10"  border="0" width="1000" class="table">
						<caption><%=rs.getString("TeacherName")+"的个人信息"%></caption>			
							<tr>
								<td width="100" rowspan="5" class="tab0">
									<img src=<%="../"+rs.getString("Picture") %> title=<%=rs.getString("TeacherName") %> style="height:250px; width:200px"/>
								</td>
								<td width="100" class="tab1">账号</td>
								<td class="tab2" id="TeacherAccount"><%=rs.getString("TeacherAccount") %></td>
							</tr>
							<tr>
								<td class="tab1">姓名</td>
								<td class="tab2"><%=rs.getString("TeacherName")%></td>
							</tr>
							<tr>
								<td class="tab1">学院</td>
								<td class="tab2"> <%=rs.getString("College") %></td>
							</tr>
							<tr>
								<td class="tab1">职位</td>
								<td class="tab2"><%=rs.getString("Position") %></td>
							</tr>
							<tr>
								<td class="tab1">研究方向</td>
								<td class="tab2"><%=rs.getString("ResearchDirection") %></td>
							</tr>
							<tr>
								<td width="300" rowspan="5" class="tab0">
								<td class="tab1">履历</td>
								<td class="tab2"><%=rs.getString("Record") %></td>
							</tr>
							<tr>
								<td class="tab1">基金</td>
								<td class="tab2"><%=rs.getString("Money") %></td>
							</tr>
							<tr>
								<td class="tab1">科研成就</td>
								<td class="tab2"><%=rs.getString("FSRA") %></td>
							</tr>
							<tr>
								<td class="tab1">电话</td>
								<td class="tab2"><%=rs.getString("Phone") %></td>
							</tr>
							<tr>
								<td class="tab1">邮箱</td>
								<td class="tab2"><%=rs.getString("Email") %></td>
							</tr>
							<tr>
									<td height="40px"></td>
									<td class="tab1" height="40px">个人主页</td>
									<td class="tab2" height="40px">
										<input type="text" name="baike" value=<%=rs.getString("Baike")%> style="width: 400px;height: 40px" id="baike">
										<input type="submit" onclick="AddBaike(<%=rs.getString("TeacherAccount")%>)"  id="submit" value="更改" style="height:40px;">
									</td>
							</tr>
					</table>
					</form>
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