package com.struts2.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;



import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import database.DatabaseConnection;

public class UploadPhoto extends ActionSupport {

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
	   public String execute()
	   {
		   String stdaccount=session.get("StudentAccount")+"";
		   if(stdaccount.equals("null")==false)
		   {
			   destPath ="C:\\Users\\阿杜\\Desktop\\软件工程\\项目\\xaingmu\\WebContent\\images\\";
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
						ps.setString(1, "images/"+stdaccount+filetype);
						ps.setString(2, stdaccount);
						ps.executeUpdate();
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
