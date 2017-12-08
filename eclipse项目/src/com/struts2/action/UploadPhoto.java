package com.struts2.action;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import database.DatabaseConnection;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import org.apache.commons.net.ftp.FTPReply;

public class UploadPhoto extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	private static final long serialVersionUID = 4433079815958580790L;
	private File myFile;
	   private String myFileContentType;
	   private String myFileFileName;
	   private String destPath;
	   
	   Map<String, Object> session;
	   public UploadPhoto() {
		   ActionContext context = ActionContext.getContext();
		   session = context.getSession();
	   }
	   
	   //教师上传头像
	   public String execute()
	   
	   {
		   
		   String hostName = "ftp.sinas3.com"; 
		    int serverPort = 10021; 
		    String userName = "1y0045wl42"; 
		    String password = "y4mim02k2l1zxzk0xlyjk3k5yl30023ll0j4k5lm"; 
		    
		    FTPClient ftpClient = new FTPClient(); 
		    String file_ftppath="tongtong/student-img/";
		    
		    int reply;

			
			
			
			
		   String teaaccount=session.get("TeacherAccount")+"";
		   if(teaaccount.equals("null")==false)
		   {
			   destPath ="C:\\Users\\阿杜\\Desktop\\软件工程\\项目\\xaingmu\\WebContent\\images\\teacher";
			   try{
			     	String filetype=myFileFileName.substring(myFileFileName.lastIndexOf("."),myFileFileName.length());
			     	String url;//request.getRequestURL().toString()+"";
			     
			     	System.out.println(destPath);
			     	File destFile  = new File(destPath,teaaccount+filetype);
			    	FileUtils.copyFile(myFile, destFile);
			    	
			    	
			    	
			    	try {
						ftpClient.connect(hostName, serverPort);
						ftpClient.login(userName, password);
						System.out.println(ftpClient.isConnected());
						ftpClient.changeWorkingDirectory(file_ftppath);
						
						reply=ftpClient.getReplyCode();
						if(!FTPReply.isPositiveCompletion(reply)) {
							System.out.println("连接失败");
						}
					//	File file=new File("D:\\11.jpg");
						
						FileInputStream inputStream=new FileInputStream(destFile);
						
						ftpClient.setControlEncoding("UTF-8");
						ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
						ftpClient.enterLocalPassiveMode();
						ftpClient.changeWorkingDirectory(file_ftppath);
						ftpClient.storeFile("11.jpg",inputStream);
						ftpClient.logout();
						inputStream.close();
						
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("连接失败");
					}
					
			    	
			    	
			    	
			    	
			    	
			    	Connection conn;
			    	Statement stmt2;
					ResultSet rs2;
					Connection conn1;
			 		try {
						conn=DatabaseConnection.getConnection();
						String sql="update teacher set Picture=? where TeacherAccount=?";
						PreparedStatement ps= conn.prepareStatement(sql);
						ps.setString(1, destPath+teaaccount+filetype);
						ps.setString(2, teaaccount);
						ps.executeUpdate();
						
						
						conn1=DatabaseConnection.getConnection();
						stmt2 = conn1.createStatement();
						String sql2 = "select *from teacher where TeacherAccount='" + teaaccount + "' ";
						rs2 = stmt2.executeQuery(sql2);
						if (rs2.next()) 
						{
							Teacher teacher = new Teacher();
							teacher.setTeacherAccount(rs2.getString("TeacherAccount"));
							teacher.setPhone(rs2.getString("Phone"));
							teacher.setPicture(rs2.getString("Picture"));
							teacher.setCollege(rs2.getString("College"));
							teacher.setTeacherName(rs2.getString("TeacherName"));
							teacher.setFSRA(rs2.getString("FSRA"));
							teacher.setMoney(rs2.getString("Money"));
							teacher.setPosition(rs2.getString("Position"));
							teacher.setPhone(rs2.getString("Phone"));
							session.put("teacher", teacher);
						}
						}catch (Exception e) {}	
						
		      }catch(Exception e){
		         e.printStackTrace();
		         return "error";
		      }
			   return "sucess";
		   }
		   return "error";
	   }
	   
	   
	   public String StudentAdminAction()
	   {
		   String stdaccount=session.get("StudentAccount")+"";
		   if(stdaccount.equals("null")==false)
		   {
			   //本地url:
			   destPath ="C:\\Users\\阿杜\\Desktop\\软件工程\\项目\\xaingmu\\WebContent\\images\\student";
			   
			   //sae url  
			  // destPath="http://tongtong-tongtong.stor.sinaapp.com/";
			   
			   try{
				   	
			     	String filetype=myFileFileName.substring(myFileFileName.lastIndexOf("."),myFileFileName.length());
			     	// File destFile  = new File(destPath, myFileFileName);
			     	File destFile  = new File(destPath,stdaccount+filetype);
			    	FileUtils.copyFile(myFile, destFile);
			 
			    	Connection conn;
			 		try {
						conn=DatabaseConnection.getConnection();
						String sql="update student set Picture=? where StudentAccount=?";
						PreparedStatement ps= conn.prepareStatement(sql);
						ps.setString(1, "images/student/"+stdaccount+filetype);
						ps.setString(2, stdaccount);
						ps.executeUpdate();
						
						//更信息
						Statement stmt2;
						ResultSet rs;
						stmt2 = conn.createStatement();
						String sql2 = "select *from student where StudentAccount='" + stdaccount + "' ";
						rs = stmt2.executeQuery(sql2);
						if (rs.next())
						{
							Student student = new Student();
							student.setStudentAccount(rs.getString("StudentAccount"));
							student.setStudentName(rs.getString("StudentName"));
							student.setMajor(rs.getString("Major"));
							student.setCollege(rs.getString("College"));
							student.setPhone(rs.getString("Phone"));
							student.setPicture(rs.getString("Picture"));
				
							session.put("student", student);
						}
					} catch (Exception e) {}	
		      }catch(Exception e){
		         e.printStackTrace();
		         return "error";
		      }
			   return "sucess";
		   }
		   return "error";
	   }
	   
	   
	   
	   public File getMyFile() {
	      return myFile;
	   }
	   public void setMyFile(File myFile) {
	      this.myFile = myFile;
	   }
	   public String getMyFileContentType() {
	      return myFileContentType;
	   }
	   public void setMyFileContentType(String myFileContentType) {
	      this.myFileContentType = myFileContentType;
	   }
	   public String getMyFileFileName() {
	      return myFileFileName;
	   }
	   public void setMyFileFileName(String myFileFileName) {
	      this.myFileFileName = myFileFileName;
	   }
}
