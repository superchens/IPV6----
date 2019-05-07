package statistics;

import java.io.UnsupportedEncodingException;
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

public class file {
	
	public static void main(String[] arg) throws ClassNotFoundException, UnsupportedEncodingException, SQLException{
		file cs =new file();
		List<String> list =cs.time_file("2017-06-24", "2017-06-25");
		System.out.println(list);
	}

	public List<String> day_file() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()));//获取当月的时间
		String sql1 = "select concat(file_id,'-',class2)file,sum(count1) value  from file_download_analysis where date like '"+time+"%' group by file order by value desc limit 10";
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
	
	public List<String> time_file(String first,String end) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select concat(file_id,'-',class2)file,sum(count1) value  from file_download_analysis where date between '"+first+"' and '"+end+"' group by file order by value desc limit 10";
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
	
	public List<String> day_hashfile() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()));//获取当月的时间
		String sql1 = "select  concat(file_id,'-',class2),sum(count1) value  from file_download_analysis where date like '"+time+"%' group by hash order by value desc limit 10";
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
	
	public List<String> time_hashfile(String first,String end) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select  concat(file_id,'-',class2),sum(count1) value  from file_download_analysis where date between '"+first+"' and '"+end+"' group by hash order by value desc limit 10";
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
	
	public List<String> file_select_user(String first,String end,String ip) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select ori_ip,res_ip,class1,concat(file_id,'-',class2)file,sum(count1) value  from file_download_analysis where date between '"+first+"' and '"+end+"' and ori_ip like '"+ip+"' or res_ip like '"+ip+"'  group by file ";
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
					list.add(rs.getString(3));
					list.add(rs.getString(4));
					list.add(rs.getString(5));
				}
		conn.close();
		return list;
	}
}
