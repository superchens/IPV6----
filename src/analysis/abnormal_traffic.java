package analysis;

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

public class abnormal_traffic {

	public static void main(String[] arg) throws ClassNotFoundException, SQLException, UnsupportedEncodingException{
		abnormal_traffic cs =new abnormal_traffic();
		//List<String> list =cs.abnormal_select_ip("2017-06-22","2017-06-24","10.185.129.183");
		List<String> list =cs.abnormal();
		System.out.println(list);
	}
	
	public List<String> abnormal() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select date,anomalyType,count(DISTINCT ip) from anomaly_ip   GROUP BY date_format(date,'%y-%m-%d'),anomalyType ORDER BY date_format(date,'%y-%m-%d') desc limit 40";
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
					list.add(new String(rs.getString(2)));//.getBytes("iso8859-1"), "utf-8"));
					list.add(rs.getString(3));
				}
		conn.close();
		Collections.reverse(list);
		return list;
	}
	
	
	public List<String> abnormal_select(String start,String end) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select date,anomalyType,count(DISTINCT ip) from anomaly_ip where date between '"+start+"' and '"+end+"'  GROUP BY date_format(date,'%y-%m-%d'),anomalyType";
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
					list.add(new String(rs.getString(2)));//.getBytes("iso8859-1"), "utf-8"));
					list.add(rs.getString(3));
				}
		conn.close();
		return list;
	}
	
	public List<String> abnormal_select_ip(String start,String end,String ip) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select anomaly_ip.date,anomalyType,origIp.`value` FROM origip,anomaly_ip where origip.date=anomaly_ip.date and origip.origIP=anomaly_ip.ip and anomaly_ip.date between '"+start+"' and '"+end+"' and ip like '"+ip+"%' GROUP BY date_format(anomaly_ip.date,'%y-%m-%d'),anomalyType";
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
					list.add(new String(rs.getString(2)));//.getBytes("iso8859-1"), "utf-8"));
					list.add(rs.getString(3));
				}
		conn.close();
		return list;
	}

}
