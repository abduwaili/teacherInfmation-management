<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.struts2.action.Student"%>
<%@page import="com.struts2.action.Teacher"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上传头像功能</title>
<style type="text/css" media="screen">
	body{
		 background: linear-gradient(45deg, #000033, #330033);
		 text-align: center;
		 margin: 0 auto;
		 padding: 0 auto;
	}
	.div1{
	/* 	background-image: url(images/display/background2.jpg); */
		background-color: white;
		margin: auto;
		width:400px;
		margin-top:100px;
		padding-bottom:40px;
		background-color: #003333;
		
	}
	.div2{

		padding:20px;
	}
	.div3{
		margin-top: 40px;
		
	}
	.form1{
		width:200px;
		background-color: white;
		
	}
	.sunmit1{
		width:70px;
	}
	
</style>
</head>
<body>
<div class="div1" >
	
	<div class="div2">
		<%
			Teacher teacher = (Teacher)session.getAttribute("teacher");
		    Student student = (Student)session.getAttribute("student");
		%>
			
		<%
			if((session.getAttribute("isTeacher")+"").equals("true"))
			{
		%>
			<img src=<%=teacher.getPicture() %> alt="" style="height:250px; width:200px">
			<div class="div3">
				<form action="TeacherUploadPhoto" method="post" enctype="multipart/form-data" name="form1" >
				    <input type="file" name="myFile"  class="form1" />  
				  	<input type="submit" name="button" id="button" value="上传" class="sunmit1">    
				</form>
			</div>
		<%}else if((session.getAttribute("isStudent")+"").equals("true")){ %>
			<img src=<%=student.getPicture() %> alt="" style="height:250px; width:200px">
			<div class="div3">
				<form action="StudentAdminAction" method="post" enctype="multipart/form-data" name="form1" >
				    <input type="file" name="myFile"  class="form1" />  
				  	<input type="submit" name="button" id="button" value="上传" class="sunmit1">    
				</form>
			</div>
		<%}else{
			response.sendRedirect("index.jsp");
		}
		%>
	</div>
</div>
</body>
</html>