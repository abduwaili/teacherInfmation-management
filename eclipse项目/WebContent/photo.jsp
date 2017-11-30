<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上传头像功能</title>
</head>
<body>
<form action="UploadPhoto" method="post" enctype="multipart/form-data" name="form1">
 <label for="myFile">Upload your file</label>
      <input type="file" name="myFile" />

  
  <input type="submit" name="button" id="button" value="上传">    
</form>


</body>
</html>