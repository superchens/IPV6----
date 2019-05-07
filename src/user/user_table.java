package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import common.DBConfig;

public class user_table {
	
	public static void main(String[] args) throws Exception {
		user_table cs = new user_table();
		//List<String> s=cs.test();
		List<String> c=cs.user_condition("2017-05-07","2017-05-09","192.168.1.58");
		System.out.println(c);
		
	}

	public List<String> test() throws SQLException, ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String current=sdf.format(new Date(d.getTime()));//获取当天的时间
		String sql = "select user,beginTime,endTime,outgoingTraff,incomingTraff from user_traff where date like '"+current+"%' ORDER BY endTime desc limit 100";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		//String url = "jdbc:mysql://192.168.1.62:3306/hubutraff?"
		//		+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接

		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		
		List<String> list =new ArrayList<String>();
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
	
	public List<String> user_condition(String begintime,String endtime,String user) throws SQLException, ClassNotFoundException {
		String sql = "select outgoingTraff,incomingTraff,CONCAT(right(beginTime,14),"+"'"+"~"+"'"+",right(endTime,14)) time,(UNIX_TIMESTAMP(str_to_date(endTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+")) - UNIX_TIMESTAMP(str_to_date(beginTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+"))) sum from user_traff where date between "+"'"+begintime+"'"+"and '"+endtime+"' and user like "+"'"+user+"'";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			long l=Long.parseLong(rs.getString(1));
			long x=l/(1024*1024);
			String values = String.valueOf(x);
			list.add(values);
			long z=Long.parseLong(rs.getString(2));
			long y=z/(1024*1024);
			String value = String.valueOf(y);
			list.add(value);
			list.add(rs.getString(3));
			list.add(rs.getString(4));
		}
		conn.close();
		return list;

	}
	 
	 
}