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

public class file_class {
	
	public List<String> day_file() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String time=sdf.format(new Date(d.getTime()));//获取当天的时间
		String sql1 = "select file_type,sum(count1) value  from file_type where date like "+"'"+time+"%'"+" group by file_type  order by value desc limit 10";
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
	
	public List<String> select_time(String firsttime,String lasttime) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		//sql语句查询时间段的前十IP
		String sql1 = "select file_type,sum(count1) value  from file_type where date between"+"'"+firsttime+"'"+"and"+"'"+ lasttime+"'"+" group by file_type  order by value desc limit 10";
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
	
	public List<String> day_fileclass() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String time=sdf.format(new Date(d.getTime()));//获取当天的时间
		String sql1 = "select substring_index(file_type,'/',1) file,sum(count1) value  from file_type where date like '"+time+"%' group by file  order by value desc limit 10";
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
	
	public List<String> select_classtime(String firsttime,String lasttime) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		//sql语句查询时间段的前十IP
		String sql1 = "select substring_index(file_type,'/',1) file,sum(count1) value  from file_type where date between"+"'"+firsttime+"'"+"and"+"'"+ lasttime+"'"+" group by file  order by value desc limit 10";
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
