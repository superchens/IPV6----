package user;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBConfig;

public class video_sta {
	
	public static void main(String[] arg) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		video_sta cs =new video_sta();
		List<String> result=cs.time_class("0","4");
		System.out.println(result);
	}

	public List<String>  M_table() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql = "select  COUNT(DISTINCT CARD_ID),COUNT(DISTINCT SERVICE_ID),COUNT(DISTINCT CHANNEL_NUM),(SELECT COUNT(CHANNEL_INFO_STATUS) from tb_get_status_2015_11_09 where  CREATEDATE BETWEEN '2015-11-09 04:00:00' and '2015-11-09 09:00:00' and CHANNEL_INFO_STATUS=1),SUM(DATE)/(COUNT(DISTINCT CARD_ID)*3600*1000*1000) from tb_get_status_2015_11_09  where CREATEDATE BETWEEN '2015-11-09 04:00:00' and '2015-11-09 09:00:00'";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();//使用函数调用数据库
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
					list.add(rs.getString(2));
					list.add(rs.getString(3));
					list.add(rs.getString(4));
					list.add(rs.getString(5));
				}
		conn.close();
	    return list;
	}
	
	public List<String>  A_table() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql = "select  COUNT(DISTINCT CARD_ID),COUNT(DISTINCT SERVICE_ID),COUNT(DISTINCT CHANNEL_NUM),(SELECT COUNT(CHANNEL_INFO_STATUS) from tb_get_status_2015_11_09 where  CREATEDATE BETWEEN '2015-11-09 11:00:00' and '2015-11-09 13:00:00' and CHANNEL_INFO_STATUS=1),SUM(DATE)/(COUNT(DISTINCT CARD_ID)*3600*1000*1000) from tb_get_status_2015_11_09  where CREATEDATE BETWEEN '2015-11-09 11:00:00' and '2015-11-09 13:00:00'";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();//使用函数调用数据库
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
					list.add(rs.getString(2));
					list.add(rs.getString(3));
					list.add(rs.getString(4));
					list.add(rs.getString(5));
				}
		conn.close();
	    return list;
	}
	
	public List<String>  E_table() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql = "select  COUNT(DISTINCT CARD_ID),COUNT(DISTINCT SERVICE_ID),COUNT(DISTINCT CHANNEL_NUM),(SELECT COUNT(CHANNEL_INFO_STATUS) from tb_get_status_2015_11_09 where  CREATEDATE BETWEEN '2015-11-09 18:00:00' and '2015-11-09 21:00:00' and CHANNEL_INFO_STATUS=1),SUM(DATE)/(COUNT(DISTINCT CARD_ID)*3600*1000*1000) from tb_get_status_2015_11_09  where CREATEDATE BETWEEN '2015-11-09 18:00:00' and '2015-11-09 21:00:00'";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();//使用函数调用数据库
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
					list.add(rs.getString(2));
					list.add(rs.getString(3));
					list.add(rs.getString(4));
					list.add(rs.getString(5));
				}
		conn.close();
	    return list;
	}
	
	public List<String>  time_class(String start,String end) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql = "select CHANNEL_NAME,COUNT(CHANNEL_NAME) val  from tb_get_status_2015_11_09  where  hour(CREATEDATE) >="+ start +" and hour(CREATEDATE)<="+ end +" group by CHANNEL_NAME  order by val desc limit 10";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();//使用函数调用数据库
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
					list.add(rs.getString(2));
				}
		conn.close();
	    return list;
	}
	
	public List<String>  time_class_total() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql = "select CHANNEL_NAME,COUNT(CHANNEL_NAME) val  from tb_get_status_2015_11_09 group by CHANNEL_NAME  order by val desc limit 10";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();//使用函数调用数据库
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
					list.add(rs.getString(2));
				}
		conn.close();
	    return list;
	}
	
}
