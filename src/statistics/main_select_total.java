package statistics;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.DBConfig;

public class main_select_total {
	
	public static void main(String[] args) throws Exception {
		main_select_total cs = new main_select_total();
		List<String> list=cs.select_current();
		List<String> test = cs.select("2018-03-01","2018-03-05");
		System.out.println(test);
		System.out.println(list);
	}
	
	public List<String> select_current() throws SQLException,ClassNotFoundException {
		/*
		 * 函数selectdata()：
		 *  查询所有的信息
		 */  
		String sql1 = "select date_format(date,'%H:%i'),main_conn,resp_traff,orig_traff from main ORDER BY date desc limit 24";
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
				long l=Long.parseLong(rs.getString(2));
				long x=l/100;
				String values = String.valueOf(x);
				list.add(values);
				long zz=Long.parseLong(rs.getString(3));
				long y=zz/(1024*1024);
				String resp_traff = String.valueOf(y);
				list.add(resp_traff);
				long cc=Long.parseLong(rs.getString(4));
				long k=cc/(1024*1024);
				String orig_traff = String.valueOf(k);
				list.add(orig_traff);
				}
		conn.close();
		Collections.reverse(list);
		return list;
	}
	
	
	public List<String> select(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		/*
		 * 函数select()：
		 *  设置两个时间段的参数，查询这段时间的流量
		 */  
		String sql1 = "select date_format(date,'%m-%d %H:%i'),main_conn,resp_traff,orig_traff from main where date between"+"'"+ firsttime+"'"+" and "+"'"+lasttime+"'";
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
					long l=Long.parseLong(rs.getString(2));
					long x=l/100;
					String values = String.valueOf(x);
					list.add(values);
					long zz=Long.parseLong(rs.getString(3));
					long y=zz/(1024*1024);
					String resp_traff = String.valueOf(y);
					list.add(resp_traff);
					long cc=Long.parseLong(rs.getString(4));
					long k=cc/(1024*1024);
					String orig_traff = String.valueOf(k);
					list.add(orig_traff);
				}
		conn.close();
		return list;
	}
}
