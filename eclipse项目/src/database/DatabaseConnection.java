package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 闁剧偓甯撮弫鐗堝祦鎼达拷
 * @author 闂冩寧娼�
 */
public class DatabaseConnection {
	static Connection conn=null;
	public static Connection getConnection()
	{
		try {	    	 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		   	String url="jdbc:mysql://localhost:3306/management?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			conn=DriverManager.getConnection(url, "root", "WAILI520guzal");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}