package com.admin.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.struts2.action.Student;
import com.struts2.action.Teacher;

import database.DatabaseConnection;
import net.sf.json.JSONObject;

public class AdminAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -421258846336956137L;

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
	
	Map<String, Object>ret = new HashMap<String, Object>();
	Map<String, Object>session;
	
	public AdminAction() {  
		ActionContext context=ActionContext.getContext();
		session=context.getSession();
	}
	
	
	public String execute() throws Exception {
		
		return null;
	}
	

	
	
	//审核教师注册注册
	public String AuditTeacherSigin()
	{
		String Account=request.getParameter("TeacherAccount");
		Connection conn;
		try {
			conn=DatabaseConnection.getConnection();
			String sql="update teacher set IsSiginUp=? where TeacherAccount=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, "True");
			ps.setString(2, Account);
			ps.executeUpdate();
			ret.put("ret", "Success");
		} catch (Exception e) {
			ret.put("ret", "Fail");
		}	
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;	
	}
	
	//拒绝教师注册
	public String NoAuditTeacherSigin()
	{
		String Account=request.getParameter("TeacherAccount");
		Connection conn;
		try {
			 conn=DatabaseConnection.getConnection();	
		  	 String sql=" delete from teacher where TeacherAccount=?";
		  	 PreparedStatement pStatement=conn.prepareStatement(sql);
		  	 pStatement.setString(1, Account);
		  	 pStatement.executeUpdate();
			ret.put("ret", "Success");
		} catch (Exception e) {
			ret.put("ret", "Fail");
		}	
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;
		
	}
	
	//通过学生注册
		public String AuditStudentSigin()
		{
			String Account=request.getParameter("StudentAccount");
			Connection conn;
			try {
				conn=DatabaseConnection.getConnection();
				String sql="update student set IsSiginUp=? where StudentAccount=?";
				PreparedStatement ps= conn.prepareStatement(sql);
				ps.setString(1, "True");
				ps.setString(2, Account);
				ps.executeUpdate();
				ret.put("ret", "Success");
			} catch (Exception e) {
				ret.put("ret", "Fail");
			}	
			JSONObject json = JSONObject.fromObject(ret);
			result = json.toString();
			return SUCCESS;	
		}
		
		//拒绝学生注册
		public String NoAuditStudentSigin()
		{
			String Account=request.getParameter("StudentAccount");
			Connection conn;
			try {
				 conn=DatabaseConnection.getConnection();	
			  	 String sql=" delete from student where StudentAccount=?";
			  	 PreparedStatement pStatement=conn.prepareStatement(sql);
			  	 pStatement.setString(1, Account);
			  	 pStatement.executeUpdate();
				ret.put("ret", "Success");
			} catch (Exception e) {
				ret.put("ret", "Fail");
			}	
			JSONObject json = JSONObject.fromObject(ret);
			result = json.toString();
			return SUCCESS;
			
		}
	
	
	
	
	
	
	//显示所有的学生列表
	public String Students() {
		Connection conn;	
		Statement stmt;	
		ResultSet rs;
		
		try {
			conn=DatabaseConnection.getConnection();
			 stmt=conn.createStatement();
			 String sql="select *from studen";
			 rs=stmt.executeQuery(sql);
			 ArrayList<Student>list=new ArrayList<>();
			 while(rs.next()) {
				 Student student=new Student();
				 student.setStudentAccount(rs.getString("StudentAccount"));
				 student.setStudentName(rs.getString("StudentName"));
				 ret.put("StudentAccount", rs.getString("StudentAccount"));
				 ret.put("StudentName", rs.getString("StudentName"));
				 
				 list.add(student);
			 }
			 session.put("Students", list);
		}catch (Exception e) {
			
		}
		JSONObject json = JSONObject.fromObject(ret);
		//result = json.toString();
		return SUCCESS;
	}
	
	//显示所有的教师列表
	public String Teachers() {
		System.out.println("tttttttttttttttttttttttttttttttttttttttttttttttttt");
		Connection conn;	
		Statement stmt;	
		ResultSet rs;
		try {
			conn=DatabaseConnection.getConnection();
			 stmt=conn.createStatement();
			 String sql="select *from teacher";
			 rs=stmt.executeQuery(sql);
			 ArrayList<Teacher>list=new ArrayList<>();
			 while(rs.next()) {
				 Teacher teacher=new Teacher();
				 teacher.setTeacherAccount(rs.getString("TeacherAccount"));
				 teacher.setTeacherName(rs.getString("TeacherName"));
				 teacher.setCollege(rs.getString("College"));
				 teacher.setPosition(rs.getString("Position"));
				
				 list.add(teacher);
			 }
			 session.put("Teachers", list);
		}catch (Exception e) {
			System.out.println("教师列表查询失败");
		}
		JSONObject json = JSONObject.fromObject(ret);
		result = json.toString();
		return SUCCESS;
		
	}
	
	



}
