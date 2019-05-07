package statistics;

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
public class main_select {
	
	
	public static void main(String[] args) throws Exception {
		main_select cs = new main_select();
		List<String> list1=cs.selectdata();
		List<String> test = cs.selectdate("2017-04-12","2017-04-14");
		System.out.println(list1);
		System.out.println(test);
	}
	
	public List<String> selectdata() throws SQLException,ClassNotFoundException {
		/*
		 * 函数selectdata()：
		 *  查询所有的信息
		 */  
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String time1=sdf.format(new Date(d.getTime()+24 * 60 * 60 * 1000));
        String time2=sdf.format(new Date(d.getTime()-20 * 24 * 60 * 60 * 1000));
		String sql1 = "select date_format(date,'20%y-%m-%d'),sum(main_log),sum(main_conn),sum(main_traff),sum(resp_traff),sum(orig_traff) from main where date between"+"'"+time2+"%"+"'"+"and"+"'"+time1+"%"+"'"+"group by date_format(date,'%y-%m-%d')";
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
					list.add(rs.getString(6));
				}
		conn.close();
		return list;
	}
	public List<String> selectdate(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		/*
		 * 函数selectdate()：
		 *  设置两个时间段的参数，查询这段时间的流量
		 */  
		String sql1 = "select date_format(date,'20%y-%m-%d'),sum(main_log),sum(main_conn),sum(main_traff) from main where date between"+"'"+firsttime+"%"+"'"+"and"+"'"+lasttime+"%"+"'"+"group by date_format(date,'%y-%m-%d')";
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
				}
		conn.close();
		return list;
	}
	
}
