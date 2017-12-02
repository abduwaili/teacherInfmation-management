<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理员登陆</title>
	<link rel="stylesheet" type="text/css" href="css/style1.css">
	<style type="text/css">
		body{

		}
	</style>
</head>
<body>
	<div class="login">
		<form action="AdminLogin" method="post" accept-charset="utf-8">
			<table border="0" align="center"   cellpadding="10px">
				<tr>
					<td width="80px">管理员账号</td>
					<td width="300px"><input type="text" name="AdminName" id="AdminName" class="form1"></td>
				</tr>
				<tr>
					<td>账号 密码</td>
					<td><input type="password" name="AdminPassword" id="AdminPassword" class="form1"></td>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" name="summit1" value="登陆" class="submit1"></th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>