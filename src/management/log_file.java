package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBConfig;

public class log_file {
	public static void main(String[] arg) throws ClassNotFoundException, SQLException{
		log_file cs =new log_file();
		List<String> list =cs.logfile();
		System.out.println(list);
}

	public List<String> logfile() throws SQLException,ClassNotFoundException {
		String sql1 = "select substring(date,1,7),sum(main_log) from main group by substring(date,1,7)";
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
					long x=l/(1024*1024);
					String values = String.valueOf(x);
					list.add(values+" M");
				}
		conn.close();
		return list;
}
}