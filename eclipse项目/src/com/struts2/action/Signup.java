package com.struts2.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.mysql.jdbc.PreparedStatement;
import com.opensymphony.xwork2.ActionSupport;

import database.DatabaseConnection;
import net.sf.json.JSONObject;

/**
 * 注册类
 *
 */
public class Signup extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	
	/*IsTeacher--0-是学生；IsTeacher--1-是教师*/
	private String IsTeacher;
	private String Account;//用户名或账号
	private String Name;//姓名
	private String Password;//密码
	private String College;//学院
	private String Phone;//电话
	
	private String Major;//学生专业
	private String Position;//教师职位
	
	
	
	private HttpServletRequest request;
	private String result;
	
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	
	public String execute() throws Exception {
		Connection conn;	
		Statement stmt;	
		ResultSet rs;		
		
		Map<String, Object>ret = new HashMap<String, Object>();
		IsTeacher=request.getParameter("IsTeacher");
		
		//教师注册
		if("1".equals(IsTeacher))
		{		
			Account=request.getParameter("Account");
			Name=request.getParameter("realName");
			Password=request.getParameter("Password");
			College=request.getParameter("college");
			Position=request.getParameter("majorOrPosition");
			Phone=request.getParameter("phone");
			
			try {
				 conn=DatabaseConnection.getConnection();	
				 stmt=conn.createStatement();
				 String sql="select *from teacher where TeacherAccount='"+Account+"'";
				 rs=stmt.executeQuery(sql);
				if(rs.next())
				 {
					 ret.put("ret", "Exist");//账号已经存在
					 
				 }else{
					 conn=DatabaseConnection.getConnection();
					 String sql1="insert into teacher(TeacherAccount,Password,TeacherName,Position,College,Phone)values(?,?,?,?,?,?)";
					 PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql1);
					 ps.setString(1, Account);
					 ps.setString(2, Password);
					 ps.setString(3, Name);
					 ps.setString(4, Position);
					 ps.setString(5, College);
					 ps.setString(6, Phone);
					 ps.executeUpdate();
					 conn.close();
					 ps.close();
					 ret.put("ret", "Success");//注册成功
				 }	 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}	
		
		//学生注册
		else if("0".equals(IsTeacher))
		{
			Account=request.getParameter("Account");
			Name=request.getParameter("realName");
			Password=request.getParameter("Password");
			College=request.getParameter("college");
			Major=request.getParameter("majorOrPosition");
			Phone=request.getParameter("phone");
			
			try {
				 conn=DatabaseConnection.getConnection();	
				 stmt=conn.createStatement();
				 String sql="select *from student where StudentAccount='"+Account+"'";
				 rs=stmt.executeQuery(sql);
				if(rs.next())
				 {
					 ret.put("ret", "Exist");//账号已经存在
					 
				 }else{
					 conn=DatabaseConnection.getConnection();
					 String sql1="insert into student(StudentAccount,Password,StudentName,Major,College,Phone)values(?,?,?,?,?,?)";
					 PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql1);
					 ps.setString(1, Account);
					 ps.setString(2, Password);
					 ps.setString(3, Name);
					 ps.setString(4, Major);
					 ps.setString(5, College);
					 ps.setString(6, Phone);
					 ps.executeUpdate();
					 conn.close();
					 ps.close();
					 ret.put("ret", "Success");//注册成功
				 }	 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return "success";
	}
	
	
}
