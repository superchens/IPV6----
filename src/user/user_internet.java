package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


import common.DBConfig;

public class user_internet {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
			user_internet cs=new user_internet();
			String ss=cs.current_new_user();
			String cc=cs.yesterday_new_user();
			Long qq=cs.current_online_user();
			Long ww=cs.yesterday_online_user();
			String zz=cs.current_user();
			String aa=cs.yesterday_user();
		    Long  dd=cs.current_user_time();
			Long ff=cs.yesterday_user_time();
			String ee=cs.current_user_traff();
			String rr=cs.yesterday_user_traff();
			
			System.out.println(ss);
			System.out.println(cc);
			System.out.println(qq);
			System.out.println(ww);
			System.out.println(zz);
			System.out.println(aa);
			System.out.println(dd);
			System.out.println(ff);
			System.out.println(ee);
			System.out.println(rr);
	}
	
	/*
	 * 计算出今天的用户相对昨天的新用户
	 */
	public String current_new_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String current=sdf.format(new Date(d.getTime()));//获取当天的时间
        String time=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//获取前一天的时间
		String sql1 = "select (select count(DISTINCT user) from user_traff where date between "+"'"+time+"' and "+"'"+current+"') - (select count(DISTINCT user) from user_traff where date like"+"'"+time+"%') as newuser";
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
		String newuser="";
		while (rs.next()) {
		 newuser=rs.getString(1);
				}
		conn.close();
		return newuser;
	}
	
	/*
	 * 计算出昨天的用户相对前天的新用户
	 */
	public String yesterday_new_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time1=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//获取当天的时间
        String time2=sdf.format(new Date(d.getTime()- 2 * 24 * 60 * 60 * 1000));//获取前一天的时间
		String sql1 = "select (select count(DISTINCT user) from user_traff where date between "+"'"+time2+"' and "+"'"+time1+"') - (select count(DISTINCT user) from user_traff where date like"+"'"+time2+"%') as newuser";
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
	    String newuser ="";//将所有的数据都存入一个数组中  
		while (rs.next()) {
					newuser=rs.getString(1);
				}
		conn.close();
		return newuser;
	}
	
	/*
	 * 计算出今天的在线用户
	 */
	public Long current_online_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String current=sdf.format(new Date(d.getTime()));//获取当天的时间
		String sql1 = "select count(DISTINCT user) from user_traff where date like "+"'"+current+"%"+"'";
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
		long user=0;
		while (rs.next()) {
		 user=Long.parseLong(rs.getString(1));
		}
		conn.close();
		return user;
	}
	
	
	/*
	 * 计算出昨天的在线用户
	 */
	public Long yesterday_online_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//获取当天的时间
		String sql1 = "select count(DISTINCT user) from user_traff where date like "+"'"+time+"%"+"'";
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
		long user=0;
		while (rs.next()) {
		  user=Long.parseLong(rs.getString(1));
		}
		conn.close();
		return user;
	}
	/*
	 * 计算昨天上网，今天没有上网的用户
	 */
	public String current_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String current=sdf.format(new Date(d.getTime()));//获取当天的时间
        String time=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//获取前一天的时间
		String sql1 = "select (select count(DISTINCT user) from user_traff where date between "+"'"+time+"' and "+"'"+current+"') - (select count(DISTINCT user) from user_traff where date like"+"'"+current+"%') as olduser";
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
		String olduser="";
		while (rs.next()) {
		 olduser=rs.getString(1);
				}
		conn.close();
		return olduser;
	}
	
	/*
	 * 前天上网，昨天没上网的用户
	 */
	public String yesterday_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time1=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//获取当天的时间
        String time2=sdf.format(new Date(d.getTime()- 2 * 24 * 60 * 60 * 1000));//获取前一天的时间
		String sql1 = "select (select count(DISTINCT user) from user_traff where date between "+"'"+time2+"' and "+"'"+time1+"') - (select count(DISTINCT user) from user_traff where date like"+"'"+time1+"%') as olduser";
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
	    String olduser ="";//将所有的数据都存入一个数组中  
		while (rs.next()) {
			olduser=rs.getString(1);
				}
		conn.close();
		return olduser;
	}
	
	/*
	 * 计算当天用户上网的平均时间
	 */
	public Long current_user_time() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()));//获取当天的时间
		String sql1 = "select sum(UNIX_TIMESTAMP(str_to_date(endTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+")) - UNIX_TIMESTAMP(str_to_date(beginTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+"))) sum from user_traff where date like "+"'"+time+"%'";
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
	    long usetime =0;//将所有的数据都存入一个数组中  
	    long online=current_online_user();
	    if(online!=0){
		while (rs.next()) {
			long l=Long.parseLong(rs.getString(1));
			usetime=l/(online*60);
		 }
	    }
		conn.close();
		return usetime;
	}
	
	/*
	 * 计算昨天用户上网的平均时间
	 */
	public Long yesterday_user_time() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//获取昨天的时间
		String sql1 ="select sum(UNIX_TIMESTAMP(str_to_date(endTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+")) - UNIX_TIMESTAMP(str_to_date(beginTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+"))) sum from user_traff where date like "+"'"+time+"%'";
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
	    long usetime =0;//将所有的数据都存入一个数组中  
	    long online=yesterday_online_user();
	    if(online!=0){
		while (rs.next()) {
			long l=Long.parseLong(rs.getString(1));
			usetime=l/(online*60);
		}
	    }
		conn.close();
		return usetime;
	}
	
	/*
	 * 计算当天的平均流量
	 */
	public String current_user_traff() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()));//获取当天的时间
		String sql1 = "select sum(outgoingTraff+incomingTraff) from user_traff where date like"+"'"+ time+"%'";
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
	    String usetraff ="";//将所有的数据都存入一个数组中  
	    long online=current_online_user();
	    if(online!=0){
		while (rs.next()) {
			long l=Long.parseLong(rs.getString(1));
			long x=l/(online*1024);
			usetraff = String.valueOf(x);
				}
	    }
		conn.close();
		return usetraff;
	}
	
	/*
	 * 计算昨天用户上网的平均流量
	 */
	public String yesterday_user_traff() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//获取昨天的时间
		String sql1 = "select sum(outgoingTraff+incomingTraff) from user_traff where date like"+"'"+ time+"%'";
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
	    String usetraff ="";//将所有的数据都存入一个数组中  
	    long online=yesterday_online_user();
	    if(online!=0){
		while (rs.next()) {
			long l=Long.parseLong(rs.getString(1));
			long x=l/(online*1024);
			usetraff = String.valueOf(x);
		}
	    }
		conn.close();
		return usetraff;
	}
}
