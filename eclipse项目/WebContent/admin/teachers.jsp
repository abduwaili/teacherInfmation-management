<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.struts2.action.Student"%>
<%@page import="java.awt.List"%>
<%@page import="com.struts2.action.Teacher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>

<%@page import="database.DatabaseConnection"%>
<%@page import="com.admin.action.AdminAction"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有教师列表</title>
	<script type="text/javascript" src="js/adjs.js"></script>
	<script src="../js/jquery-3.2.1.js"></script>
            <script src="../js/jquery-3.2.1.min.js">
            </script>
</head>
<body>
	<div align="center">
		<table cellspacing="0" cellpadding="0" border="1" width="80%">
			<caption>教师列表</caption>
			<tr>
				<th>序号</th>
				<th>照片</th>
				<th>账号</th>	
				<th>姓名</th>
				<th>学院</th>
				<th>职位</th>
				<th>详细信息</th>
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
					 ArrayList<Student>list=new ArrayList<>();
					 int i=0;
					 while(rs.next()) {
						 i++;
			 %>
		 	 <tr>
				<th><%=i%></th>
				<th><img src=<%="../"+rs.getString("Picture")%> alt="阿杜" title="阿杜" style="height:100px; width:100px"/></th>
				<th><%=rs.getString("TeacherAccount")%></th>
				<th><%=rs.getString("TeacherName")%></th>
				<th><%=rs.getString("College")%></th>
				<th><%=rs.getString("Position")%></th>
				<th><a href="#">更多</a></th>
		 	 </tr>
		 	 <% 	 
			  }	
				}catch (Exception e) {
					out.print("查询失败");	
				}
			 %>
		</table> 
 	</div>

	
</body>
</html>