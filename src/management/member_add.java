package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.DBConfig;

public class member_add {
	
	public static void main(String[] arg) throws ClassNotFoundException, SQLException{
		member_add cs =new member_add();
		System.out.println(cs.add_user("cc","123456","0"));
	}

	public String  add_user(String name,String pwd,String user) throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		String time=sdf.format(new Date(d.getTime()));//获取当月的时间
		//
		String sql = "insert into user(user_name,user_password,sign,register_time) values("+"'"+name+"',"+"'"+pwd+"',"+"'"+user+"',"+"'"+time+"'"+")";
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
		stmt.execute(sql);
		conn.close();
	    String result="注册成功";
	    return result;
	}
	
}
