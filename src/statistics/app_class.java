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
public class app_class {

	
		public static void main(String[] args) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
			// TODO Auto-generated method stub
			app_class cs =new app_class();
			List<String> list1=cs.month_class("在线阅读");
			System.out.println(list1);
		}
		
		/*
		 * 输入网站类型查询出前10个网站在一段时间的使用情况
		 */
		public List<String> select_class(String time,String type) throws SQLException,ClassNotFoundException {
			//sql语句查询时间段的前十IP
			String sql1 = "select b.app_name,sum(b.duration) value from my_app b ,(select app from app_class where class like "+"'"+type+"%') a where a.app = b.app_name  and b.date like "+"'"+time+"%' group by b.app_name order by value desc limit 10 ";
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
		
		/*
		 * 输入网站类型查询出前10个网站在本月的使用情况
		 */
		public List<String> month_class(String type) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
			String time=sdf.format(new Date(d.getTime()));//获取当月的时间
			//sql语句查询时间段的前十IP
			type = new String(type.getBytes("utf-8"), "iso8859-1");
			String sql1 = "select b.app_name,sum(b.duration) value from my_app b ,(select app from app_class where class like "+"'"+type+"%') a where a.app = b.app_name  and b.date like "+"'"+time+"%' group by b.app_name order by value desc limit 10 ";
			// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
			// 避免中文乱码要指定useUnicode和characterEncoding
			// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
			// 下面语句之前就要先创建javademo数据库
			DBConfig cs=new DBConfig();//使用函数调用数据库
			String url =cs.getUrl();
			System.out.println(sql1);
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
		/*
		 * 输入网站类型查询出前10个网站在本月的使用情况
		 */
		public List<String> month_app() throws SQLException,ClassNotFoundException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
			String current=sdf.format(new Date(d.getTime()));//获取当月的时间
			//sql语句查询时间段的前十IP
			String sql1 = "select app_name,sum(duration) value  from my_app where date like"+"'" +current+"%' group by app_name ORDER BY value desc limit 10 ";
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
		
		public List<String> month_app_member() throws SQLException,ClassNotFoundException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
	        String time=sdf.format(new Date(d.getTime()));//获取当天的时间
			String sql1 = "select app_name,COUNT(DISTINCT user) value from my_app where date like "+"'"+time+"%'"+" group by app_name ORDER BY value desc limit 10";
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
		public List<String> month_app_user(String type) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
			String time=sdf.format(new Date(d.getTime()));//获取当月的时间
			//sql语句查询时间段的前十IP
			type = new String(type.getBytes("utf-8"), "iso8859-1");
			String sql1 = "select b.app_name,COUNT(DISTINCT user) value from my_app b ,(select app from app_class where class like "+"'"+type+"%') a where a.app = b.app_name  and b.date like "+"'"+time+"%' group by b.app_name order by value desc limit 10 ";
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
		
		public List<String> day_app_member() throws SQLException,ClassNotFoundException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String time=sdf.format(new Date(d.getTime()));//获取当天的时间
			String sql1 = "select app_name,COUNT(DISTINCT user) value from my_app where date like "+"'"+time+"%'"+" group by app_name ORDER BY value desc limit 10";
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
		
		public List<String> day_app_user(String type) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			String time=sdf.format(new Date(d.getTime()));//获取当月的时间
			//sql语句查询时间段的前十IP
			type = new String(type.getBytes("utf-8"), "iso8859-1");
			String sql1 = "select b.app_name,COUNT(DISTINCT user) value from my_app b ,(select app from app_class where class like "+"'"+type+"%') a where a.app = b.app_name  and b.date like "+"'"+time+"%' group by b.app_name order by value desc limit 10 ";
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


