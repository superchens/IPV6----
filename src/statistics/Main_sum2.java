package statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBConfig;

public class Main_sum2 {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
				Main_sum2 cs = new Main_sum2();
				System.out.println(cs.Mainsum2());
	}
	
	public List<String> Mainsum2() throws SQLException,ClassNotFoundException {
		/*
		 * 函数Main_conn()：
		 *  访问数据库，从中提取main_conn字段的value1,value2的值,放在一个list1中。
		 */
		String sql1 = "select date_format(date,'20%y-%m-%d'),sum(main_log),sum(main_conn),sum(main_traff) from main GROUP BY date_format(date,'%y-%m-%d') ORDER BY date_format(date,'%y-%m-%d') desc limit 5";
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
		List<String> list1 =new ArrayList<String>();
		while (rs.next()) {
			list1.add(rs.getString(1));
			list1.add(rs.getString(2));
			list1.add(rs.getString(3));
			list1.add(rs.getString(4));
		}
		conn.close();
		return list1;
	}
}
