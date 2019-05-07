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

import user.user_device;

import common.DBConfig;

public class device_class {
	

	public static void main(String[] arg) throws ClassNotFoundException, UnsupportedEncodingException, SQLException{
		device_class cs =new device_class();
		List<String> list =cs.look_device();
		System.out.println(list);
	}
	
	//统计全部的设备，在本月的使用人数最多的
	public List<String> look_device() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
        String time=sdf.format(new Date(d.getTime()));//获取当天的时间
		String sql1 = "select device_type,COUNT(device_type) value from my_device_test where date like "+"'"+time+"%'"+" group by device_type order by value desc limit 10";
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
					list.add(new String(rs.getString(1).getBytes("iso8859-1"), "utf-8"));
					list.add(rs.getString(2));
				}
		conn.close();
		return list;
	}
	
	//查询操作系统的，使用人数最多的设备
	public List<String> select_device(String type) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
        String time=sdf.format(new Date(d.getTime()));//获取当天的时间
		String sql1 = "select device_type,COUNT(device_type) value from my_device_test where date like '"+time+"%'and device_class like '"+type+"%' group by device_type order by value desc limit 10";
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
					list.add(new String(rs.getString(1).getBytes("iso8859-1"), "utf-8"));
					list.add(rs.getString(2));
				}
		conn.close();
		return list;
	}
}
