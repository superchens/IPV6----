package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import common.DBConfig;

public class internet_number {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		internet_number cs =new internet_number();
		List<String> cc=cs.number();
		System.out.println(cc);
		List<String> ss=cs.select_number("2017-05-06","2017-05-08");
		System.out.println(ss);
	}

	public List<String> currentdate(){
		/*
		 * 获取当前日期到前五天之间的五天日期的字符串。
		 */
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        List<String> datetime=new ArrayList<String>();
        for (int i=0;i<5;i++) {  
        	 datetime.add(sdf.format(new Date(d.getTime() - i * 24 * 60 * 60 * 1000)));
        }  
        Collections.reverse(datetime);
        return datetime;
	}
	
	public List<String> number() throws SQLException,ClassNotFoundException {
		/*
		 * 函数number()：
		 *  访问数据库，从中提取五天的用户数,放在一个list2中。
		 */
		List<String> list1=currentdate();
		List<String> list2 =new ArrayList<String>();//将所有的数据都存入一个数组中
		for(int i=0;i<list1.size();i++){       
				String sql1 = "select count(DISTINCT user) from user_traff where date like "+"'"+list1.get(i)+"%"+"'";
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
				
				while (rs.next()) {
					list2.add(rs.getString(1));
				}
				conn.close();
		}
		//Collections.reverse(list2);
		return list2;
	}
	
	public List<String> select_number(String start,String end) throws SQLException,ClassNotFoundException {
		/*
		 * 函数select_number()：
		 *  访问数据库，查询时间的每天的用户数,放在一个list2中。
		 */
		List<String> list2 =new ArrayList<String>();//将所有的数据都存入一个数组中    
				String sql1 = "select date,count(DISTINCT user) from user_traff where date between"+"'"+start+"'"+"and"+"'"+end+"'"+"group by date";
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
				while (rs.next()) {
					list2.add(rs.getString(1));
					list2.add(rs.getString(2));
				}
				conn.close();
		
		return list2;
	}
	
}
