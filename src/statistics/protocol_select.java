package statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import common.DBConfig;
public class protocol_select {

	public long values_total;
	public long values_portion;
	
	public static void main(String[] args) throws Exception {
		protocol_select cs = new protocol_select();
		List<String> test = cs.protocol_name("2017-04-27","2017-04-28");
		long ss=cs.Total("2017-04-27","2017-04-28");
		List<String> cc = cs.pro_values("2017-04-27","2017-04-28");
		long aa=cs.Portion("2017-04-27","2017-04-28");
		List<String> zz=cs.sum("2017-04-27","2017-04-28");
		List<String> ll=cs.select_date("2017-04-27","2017-04-28","http");
		System.out.println(test);
		System.out.println(ss);
		System.out.println(cc);
		System.out.println(aa);
		System.out.println(zz);
		System.out.println(ll);
	}
	
	public List<String> sum(String firsttime,String lasttime) throws ClassNotFoundException, SQLException{
		//计算else的值
		protocol_select cs = new protocol_select();
		List<String> c=cs.pro_values(firsttime,lasttime);
		long pro_value=cs.Total(firsttime,lasttime)-cs.Portion( firsttime,lasttime);
		String value_string=String.valueOf(pro_value);
		c.add(value_string);
		return c;
	}

	public List<String> protocol_name(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		//读取protocol表中的协议和最大流量的10条
		String sql1 = "select protocol,sum(incomingTraff+outgoingTraff) as value from protocol where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+"group by protocol order by value desc limit 10";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			String pro = rs.getString(1);
			pro=pro.replaceAll(",","-");//将字符串里面的，转变成-
			list.add(pro);
		}
		conn.close();
		list.add("else");
		return list;
	}
	public List<String> pro_values(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		//读取protocol表中的最大10条的流量
		String sql1 = "select protocol,sum(incomingTraff+outgoingTraff) as value from protocol where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+"group by protocol order by value desc limit 10";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			String value = rs.getString(2);
			list.add(value);
			
		}
		conn.close();
		return list;
	}
	public long Total(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		String sql2="select sum(incomingTraff+outgoingTraff)  from protocol where date between "+"'"+firsttime+"%'"+"and"+"'"+lasttime+"%'";//读取数据库里面的总数据
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet total=stmt.executeQuery(sql2);// executeQuery会返回结果的集合，否则返回空值
		while (total.next()) {
			values_total=total.getLong(1);
		}
		conn.close();
		return values_total;
	}
	public long Portion(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		String sql3="select sum(val) from (select sum(incomingTraff+outgoingTraff) as val from protocol where date between"+"'"+firsttime+"%'"+" and "+"'"+lasttime+"%'"+"group by protocol order by val desc limit 10) as A";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet total=stmt.executeQuery(sql3);// executeQuery会返回结果的集合，否则返回空值
		while (total.next()) {
			values_portion=total.getLong(1);
		}
		conn.close();
		return values_portion;
	}
	
	//统计时间段的一个协议的走势
	public List<String> select_date(String firsttime,String lasttime,String pro) throws SQLException,ClassNotFoundException {
		//读取protocol表中的协议和最大流量的10条
		String sql1 = "select date  from protocol  where  date between "+"'"+firsttime+"%'"+" and"+"'"+ lasttime+"%'"+" and protocol like"+"'"+pro+"'" ;
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			String date = rs.getString(1);
			list.add(date);
		}
		conn.close();
		return list;
	}
	
	//下行流量
	public List<String> select_incoming(String firsttime,String lasttime,String pro) throws SQLException,ClassNotFoundException {
		//读取protocol表中的最大10条的流量
		String sql1 = "select incomingTraff  from protocol  where  date between "+"'"+firsttime+"%'"+" and"+"'"+ lasttime+"'"+" and protocol like"+"'"+pro+"'";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			String value = rs.getString(1);
			long l=Long.parseLong(value);
			long x=l/1024;
			String values = String.valueOf(x);
			list.add(values);
		}
		conn.close();
		return list;
	}
	
	//上行流量
	public List<String> select_outgoing(String firsttime,String lasttime,String pro) throws SQLException,ClassNotFoundException {
		//读取protocol表中的最大10条的流量
		String sql1 = "select outgoingTraff  from protocol  where  date between "+"'"+firsttime+"%'"+" and"+"'"+ lasttime+"'"+" and protocol like"+"'"+pro+"'";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			String value = rs.getString(1);
			long l=Long.parseLong(value);
			long x=l/1024;
			String values = String.valueOf(x);
			list.add(values);
		}
		conn.close();
		return list;
	}
}
