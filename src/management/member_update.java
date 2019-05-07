package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import common.DBConfig;

public class member_update {
	
	public static void main(String[] arg) throws ClassNotFoundException, SQLException{
		member_update cs =new member_update();
		String result=cs.mod_user("rr","kk","1258","1");
		System.out.println(result);
	}

	public String  mod_user(String user,String name,String pwd,String admin) throws SQLException,ClassNotFoundException {
		String sql = "update user set user_name='"+name+"',user_password='"+pwd+"',sign='"+admin+"',register_time=now() where user_name='"+user+"'";
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
		stmt.executeUpdate(sql);
		conn.close();
	    String result="修改成功";
	    return result;
	}
}
