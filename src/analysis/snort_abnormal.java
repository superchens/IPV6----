package analysis;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBConfig;

public class snort_abnormal {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws UnsupportedEncodingException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, UnsupportedEncodingException, SQLException {
		// TODO Auto-generated method stub
		snort_abnormal cs =new snort_abnormal();
		System.out.println(cs.snort_count());
	}
	
	public List<String> snort_count() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select date_format(timestamp,'%Y-%m-%d'),count(cid)  from event group by date_format(timestamp,'%Y-%m-%d') desc limit 15";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();//使用函数调用数据库
		String url =cs.getUrl2();
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
	
	public List<String> snort_type() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select c.sig_class_name,count(a.signature) from event as a ,signature as b,sig_class as c where a.signature=b.sig_id and b.sig_class_id=c.sig_class_id GROUP BY c.sig_class_name";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();//使用函数调用数据库
		String url =cs.getUrl2();
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
	
	public List<String> snort_abnor() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select b.sig_name,count(a.signature)   from event as a ,signature as b where a.signature=b.sig_id  GROUP BY b.sig_name";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();//使用函数调用数据库
		String url =cs.getUrl2();
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
