<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="database.DatabaseConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户反馈</title>
	<style>
		body{
			margin:0px auto;
			padding:0px auto;
		
		} 
		.main{
			margin-top:40px;
			margin-bottom:100px;
		}
		.main2{
			margin:0px auto;
			padding:0px auto;
			width:90%;
			height: 60px auto;
			word-wrap: break-word;
			word-break: break-all;
			
		}
	</style>
	<script type="text/javascript" src="js/adjs.js"></script>
	<script src="../js/jquery-3.2.1.js"></script>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div class="main" >
		<table cellspacing="0" cellpadding="0" border="1" class="main2">
			<caption> <h1>用户反馈列表</h1> </caption>
			<tr>
				<th width="5%">序号</th>
				<th width="10%">姓名</th>
				<th width="10%">邮箱</th>
				<th width="10%">题目</th>
				<th width="55%">反馈信息</th>
				<th width="10%">操作</th>
			</tr>
			<%	
				Connection conn;	
				Statement stmt;	
				ResultSet rs;
				try {
					conn=DatabaseConnection.getConnection();
					 stmt=conn.createStatement();
					 String sql="select *from feedback ";
					 rs=stmt.executeQuery(sql);
					 int i=0;
					 while(rs.next()) {
						 if(rs.getString("View").equals("True"))
						 {
							 i++;
			 %>
						  	<tr>
						  		<th><%=i%></th>
								<th><%=rs.getString("Name")%></th>
								<th><%=rs.getString("Email")%></th>
								<th><%=rs.getString("Subject")%></th>
								<th><%=rs.getString("Datails")%></th>
								<!-- <th><%=rs.getString("View")%></th> -->
								<th> <button onclick="HiddenFeedback(<%=rs.getString("Id")%>)" style="margin: auto;width: 90%" id=<%=rs.getString("Id")%>>以后不再显示</button> </th>
							</tr>		
									 
			<% 			 }
					 }
				}catch (Exception e) {
					out.print("查询失败");
				}
			%>

		</table>
	</div>

</body>
</html>