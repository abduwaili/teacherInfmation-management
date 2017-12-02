<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.awt.RadialGradientPaint"%>
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
<title>待审核注册用户</title>
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
	<div align="center">
		<table cellspacing="0" cellpadding="0" border="1" width="80%">
			<caption>待审核注册用户列表</caption>
			<tr>
				<th>序号</th>
				<th>用户类型</th>
				<th>照片</th>
				<th>用户名</th>
				<th>真实姓名</th>
				<th>联系方式</th>
				<th>更多信息</th>
				<th>审核状态</th>
				<th>通过审核</th>
			</tr>
			<%
			Connection conn,conn1;	
			Statement stmt,stmt1;	
			ResultSet rs,rs1;
			try {
				 conn=DatabaseConnection.getConnection();
				 conn1=DatabaseConnection.getConnection();
				 stmt=conn.createStatement();
				 stmt1=conn1.createStatement();
				 String sql="select *from teacher ";
				 String sql1="select *from student ";
				 rs=stmt.executeQuery(sql);
				 rs1=stmt1.executeQuery(sql1);
				 int i=0;
				 while(rs.next())
				 {				 
					 if((rs.getString("IsSiginUp")).equals("False"))
					 {
						 i++;
			 %>
						<tr>
							<th><%=i %></th>
							<th><%="教师"%></th>
							<th><img src=<%="../"+rs.getString("Picture")%>  title="<%=rs.getString("TeacherName")%>" style="height:100px; width:100px"/></th>
							<th><%=rs.getString("TeacherAccount")%></th>
							<th><%=rs.getString("TeacherName")%></th>
							<th><%=rs.getString("Email")%><br/><%=rs.getString("Phone")%></th>
							<th><a href="teacher_inf.jsp?TeacherAccount=<%=rs.getString("TeacherAccount")%>">更多</a></th>
							<th><%=rs.getString("IsSiginUp")%></th>
							<th>
								<button onclick="AuditTearchSigin($(this).val())" type="submit" style="height:100%;width:100%;border:0;background-color:white;" id=<%=rs.getString("TeacherAccount")+"t0"%> value=<%=rs.getString("TeacherAccount")%>>通过</button>
								<button onclick="NoAuditTearchSigin($(this).val())"  type="submit" style="height:100%;width:100%;border:0;background-color:white;" id=<%=rs.getString("TeacherAccount")+"t1"%> value=<%=rs.getString("TeacherAccount")%>>拒绝</button>
							</th>
						</tr>
			 <%
					 }
				}
				while(rs1.next()) 
				{
					if((rs1.getString("IsSiginUp")).equals("False"))
					{
					 	i++;
			 %>	 
						 <tr>
							<th><%=i%></th>
							<th><%="学生"%></th>
							<th><img src=<%="../"+rs1.getString("Picture")%> title="<%=rs1.getString("StudentName")%>" style="height:100px; width:100px"/></th>
							<th><%=rs1.getString("StudentAccount")%></th>
							<th><%=rs1.getString("StudentName")%></th>
							<th><%=rs1.getString("Email")%><br/><%=rs1.getString("Phone")%></th>
							<th><a href="student_inf.jsp?StudentAccount=<%=rs1.getString("StudentAccount")%>">更多</a></th>
							<th><%=rs1.getString("IsSiginUp")%></th>
							<th>
								<button onclick="AuditStudentSigin($(this).val())" type="submit" style="height:100%;width:100%;border:0;background-color:white;" id=<%=rs1.getString("StudentAccount")+"s0"%> value=<%=rs1.getString("StudentAccount")%>>通过</button>
								<button onclick="NoAuditStudentSigin($(this).val())"  type="submit" style="height:100%;width:100%;border:0;background-color:white;" id=<%=rs1.getString("StudentAccount")+"s1"%> value=<%=rs1.getString("StudentAccount")%>>拒绝</button>
							</th>
						</tr>
			<%		}
				}
			}catch (Exception e) {out.print("查询失败");	}
			 %>
		</table>
	</div>
</body>
</html>