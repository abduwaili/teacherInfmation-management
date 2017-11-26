package com.struts2.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import database.DatabaseConnection;
import net.sf.json.JSONObject;

public class Loginin extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	
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
		Statement stmt2;	
		ResultSet rs;	
		ResultSet rs2;
		Map<String, Object>session;
		Map<String, Object>ret = new HashMap<String, Object>();
		String Account = request.getParameter("Account");
		String IsTeacher = request.getParameter("IsTeacher");
		String Password = request.getParameter("Password");
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
		
		
		
		//教师登陆
		if("1".equals(IsTeacher)) 
		{	
			
			try {
				 conn=DatabaseConnection.getConnection();	
				 stmt=conn.createStatement();
				 String sql="select *from teacher where TeacherAccount='"+Account+"'";
				 rs=stmt.executeQuery(sql);
				
				if(rs.next())
				 {
					 stmt2=conn.createStatement();
					 String sql2="select *from teacher where Password='"+Password+"' ";
					 rs2=stmt2.executeQuery(sql2);
					 if(rs2.next())
					 {  
						 Teacher teacher=new Teacher();
						 teacher.setTeacherAccount(rs.getString("TeacherAccount"));
						 teacher.setPhone(rs.getString("Phone"));
						 teacher.setPicture(rs.getString("Picture"));
						 teacher.setCollege(rs.getString("College"));
						 teacher.setTeacherName(rs.getString("TeacherName"));
						 teacher.setFSRA(rs.getString("FSRA"));
						 teacher.setMoney(rs.getString("Money"));
						 teacher.setPosition(rs.getString("Position"));
						 teacher.setPhone(rs.getString("Phone"));
						 session.put("teacher", teacher);
						 session.put("isLogin", "true");   
						 session.put("isTeacher", "true");
						 ret.put("ret", "Success");
						 session.put("TeacherAccount", Account);
						 
						 
						 
						 //提取老师的日程信息
						 Connection conn_0;	
						 Statement stmt_0;			
						 ResultSet rs_0;	
						 conn_0=DatabaseConnection.getConnection();	
						 stmt_0=conn_0.createStatement();
						 String sql_0="select *from schedule where TeacherAccount='"+Account+"'";
						 rs_0=stmt_0.executeQuery(sql_0);
						 
						 while(rs_0.next())
						 {
								 session.put("MonAm", rs_0.getString("MonAm"));							 
								 session.put("MonPm", rs_0.getString("MonPm"));
								 session.put("TueAm", rs_0.getString("TueAm"));					
								 session.put("TuePm", rs_0.getString("TuePm"));
								 session.put("WedAm", rs_0.getString("WedAm"));						
								 session.put("WedPm", rs_0.getString("WedPm"));						
								 session.put("ThuAm", rs_0.getString("ThuAm"));
								 session.put("ThuPm", rs_0.getString("ThuPm"));
								 session.put("FriAm", rs_0.getString("FriAm"));
								 session.put("FriPm", rs_0.getString("FriPm"));
						 }		
						 
						 //教师的预约信息
						 Connection conn_1;	
						 Statement stmt_1;			
						 ResultSet rs_1;
						 conn_1=DatabaseConnection.getConnection();	
						 stmt_1=conn_1.createStatement();
						 String sql_1="select *from arrange where TeacherAccount='"+Account+"'";
						 rs_1=stmt_1.executeQuery(sql_1);
						 ArrayList<Arrange> L = new ArrayList<Arrange>();
						 while(rs_1.next())
						 {
							 if(rs_1.getString("SuccessFail").equals("false"))
							 {
								 Arrange temp = new Arrange();
								 temp.setTeacherAccount(rs_1.getString("TeacherAccount"));
								 temp.setStudentAccount(rs_1.getString("StudentAccount"));
								 temp.setArrangeTime(rs_1.getString("ArrangeTime"));
								 temp.setArrangeReason(rs_1.getString("ArrangeReason"));
								 temp.setSuccessFail(rs_1.getString("SuccessFail"));
								 temp.setStudentName(rs_1.getString("StudentName"));
								 temp.setStudentMajor(rs_1.getString("StudentMajor"));
								 temp.setStudentPhone(rs_1.getString("StudentPhone"));
								 temp.setArrangeId(rs_1.getInt("ArrangeId"));
								 L.add(temp);
							 }
							
						 }
						 session.put("teacherOrder", L);
						 
						 
					 }
					 else {
						 ret.put("ret", "ErrorPassword");
					 }
					 
				 }else{
					 ret.put("ret", "NotFound");
				 }	 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}	
		//学生登录
		else if("0".equals(IsTeacher))
		{
			
			try {
				 conn=DatabaseConnection.getConnection();	
				 stmt=conn.createStatement();
				 String sql="select *from student where StudentAccount='"+Account+"'";
				 rs=stmt.executeQuery(sql);
				if(rs.next())
				 {
					stmt2=conn.createStatement();
					String sql2="select *from student where Password='"+Password+"' ";
					 rs2=stmt2.executeQuery(sql2);
					if(rs2.next())
					{
						
						 session.put("isLogin", "true");
						 session.put("isTeacher", "false"); 
						 ret.put("ret", "Success");
						 
						 Student student=new Student();
						 student.setStudentAccount(rs.getString("StudentAccount"));
						 student.setStudentName(rs.getString("StudentName"));
						 student.setMajor(rs.getString("Major"));
						 student.setCollege(rs.getString("College"));
						 student.setPhone(rs.getString("Phone"));
						 student.setPicture(rs.getString("Picture"));
						 session.put("student", student);
						 
						 
						 
						//学生预约信息
						 Connection conn_1;	
						 Statement stmt_1;			
						 ResultSet rs_1;
						 conn_1=DatabaseConnection.getConnection();	
						 stmt_1=conn_1.createStatement();
						 String sql_1="select *from arrange where StudentAccount='"+Account+"'";
						 rs_1=stmt_1.executeQuery(sql_1);
						 ArrayList<Arrange> L = new ArrayList<Arrange>();
						 while(rs_1.next())
						 {
							 if(rs_1.getString("commitornot").equals("false"))
							 {
								 continue;
							 }
							 Arrange temp = new Arrange();
							 temp.setTeacherAccount(rs_1.getString("TeacherAccount"));
							 temp.setTeacherName(rs_1.getString("TeacherName"));
							 temp.setStudentAccount(rs_1.getString("StudentAccount"));
							 temp.setArrangeTime(rs_1.getString("ArrangeTime"));
							 temp.setArrangeReason(rs_1.getString("ArrangeReason"));
							 temp.setSuccessFail(rs_1.getString("SuccessFail"));
							 temp.setArrangeId(rs_1.getInt("ArrangeId"));
							 L.add(temp);
						 }
						 session.put("studentOrder", L);
						
					}else {
						 ret.put("ret", "ErrorPassword");
					}
					 
				 }else{
					 ret.put("ret", "NotFound");  
					 
				 }	 
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		JSONObject jsonObject = JSONObject.fromObject(ret);
		result = jsonObject.toString();
		return SUCCESS;
	}

}
