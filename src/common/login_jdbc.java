package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class login_jdbc{
               //public ConnectionDemo02 conn;
	public static final String DBDRIVER="com.mysql.jdbc.Driver";
	public static final String DBURL="jdbc:mysql://127.0.0.1:3306/hubutraff?";//10.37.13.34:3306/hubutraff
	public static final String DBUSER="root";
	public static final String DBPASS="*Hubu1411";//*Hubu1411
	public  Connection getConn(){
		Connection conn=null;
		try{
			Class.forName(DBDRIVER);
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		try{
			conn=DriverManager.getConnection(DBURL,DBUSER,DBPASS);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return conn;
	}
}