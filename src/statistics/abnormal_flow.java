package statistics;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.DBConfig;

public class abnormal_flow {
	
	public static void main(String[] args) throws ClassNotFoundException, UnsupportedEncodingException, SQLException{
		abnormal_flow cs=new abnormal_flow();
		List<String> list=cs.abnormal();
		System.out.println(list);
	}
	
	public List<String> abnormal() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select date_format(time,'20%y-%m-%d'),sum(conn),sum(highflow),sum(lowflow),sum(flow) from abnormal_flow GROUP BY date_format(time,'%y-%m-%d') ORDER BY date_format(time,'%y-%m-%d') desc limit 5";
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
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
					list.add(rs.getString(2));
					list.add(String.valueOf(Long.parseLong(rs.getString(3))/8192));
					list.add(String.valueOf(Long.parseLong(rs.getString(4))/8192));
					list.add(String.valueOf(Long.parseLong(rs.getString(5))/8192));
				}
		conn.close();
		return list;
	}
	
	public List<String> abnormal_select(String start,String end) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select date_format(time,'20%y-%m-%d'),sum(conn),sum(highflow),sum(lowflow),sum(flow) from abnormal_flow where time between '"+start+"' and '"+end+"' GROUP BY date_format(time,'%y-%m-%d') ORDER BY date_format(time,'%y-%m-%d') desc ";
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
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
					list.add(rs.getString(2));
					list.add(String.valueOf(Long.parseLong(rs.getString(3))/8192));
					list.add(String.valueOf(Long.parseLong(rs.getString(4))/8192));
					list.add(String.valueOf(Long.parseLong(rs.getString(5))/8192));
				}
		conn.close();
		return list;
	}
	
	public List<String> abnormal_high() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select time,highflow from abnormal_flow  ORDER BY time desc limit 24";
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
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
			        list.add(String.valueOf(Long.parseLong(rs.getString(2))/8192));
				}
		conn.close();
		return list;
	}
	
	public List<String> abnormal_high(String start,String end) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select time,highflow from abnormal_flow where time between '"+start+"' and '"+end+"' ";
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
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
			        list.add(String.valueOf(Long.parseLong(rs.getString(2))/8192));
				}
		conn.close();
		return list;
	}
	
	public List<String> abnormal_low() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select time,lowflow from abnormal_flow  ORDER BY time desc limit 24";
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
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
			        list.add(String.valueOf(Long.parseLong(rs.getString(2))/8192));
				}
		conn.close();
		return list;
	}
	
	public List<String> abnormal_low(String start,String end) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select time,lowflow from abnormal_flow where time between '"+start+"' and '"+end+"' ";
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
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
			        list.add(String.valueOf(Long.parseLong(rs.getString(2))/8192));
				}
		conn.close();
		return list;
	}
	
	public List<String> abnormal_conn() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select time,conn from abnormal_flow  ORDER BY time desc limit 24";
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
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
					list.add(rs.getString(2));
				}
		conn.close();
		return list;
	}
	
	public List<String> abnormal_conn(String start,String end) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select time,conn from abnormal_flow where time between '"+start+"' and '"+end+"' ";
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
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();//将所有的数据都存入一个数组中  
		while (rs.next()) {
			        list.add(rs.getString(1));
					list.add(rs.getString(2));
				}
		conn.close();
		return list;
	}
}
