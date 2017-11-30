package com.struts2.action;



import java.io.File;



import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;

public class UploadPhoto extends ActionSupport {

	private static final long serialVersionUID = 4433079815958580790L;
	private File myFile;
	   private String myFileContentType;
	   private String myFileFileName;
	   private String destPath;
	   

	   public String execute()
	   {
	     
	      destPath ="C:\\Users\\阿杜\\Desktop\\软件工程\\项目\\xaingmu\\WebContent\\images\\student";
	      
	      
	      try{
	     	 System.out.println("Src File name: " + myFile);
	     	 System.out.println("Dst File name: " + myFileFileName);
	     	    	 
	     	 File destFile  = new File(destPath, myFileFileName);
	    	 FileUtils.copyFile(myFile, destFile);
	  
	      }catch(Exception e){
	         e.printStackTrace();
	         return "error";
	      }

	      return "sucess";
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
