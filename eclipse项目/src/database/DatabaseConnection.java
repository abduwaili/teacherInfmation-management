package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 閾炬帴鏁版嵁搴�
 * @author 闃挎潨
 */
public class DatabaseConnection {
	static Connection conn=null;
	public static Connection getConnection()
	{
		try {
		/*Class.forName("com.mysql.jdbc.Driver").newInstance();
	   	String url="jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_tongtong?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		conn=DriverManager.getConnection(url, "1y0045wl42", "y4mim02k2l1zxzk0xlyjk3k5yl30023ll0j4k5lm");*/
		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		   	String url="jdbc:mysql://localhost:3306/management?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			conn=DriverManager.getConnection(url, "root", "WAILI520guzal");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}

