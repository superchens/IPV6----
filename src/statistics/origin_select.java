package statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import common.DBConfig;
public class origin_select {
	
	public long values_total;
	public long values_portion;
	
		public static void main(String[] args) throws Exception {
			origin_select cs = new origin_select();
			List<String> test = cs.selectIP("2017-04-26","2017-04-27");
			long ss=cs.Total("2017-04-26","2017-04-27");
			List<String> cc = cs.selectvalue("2017-04-26","2017-04-27");
			long aa=cs.Portion("2017-04-26","2017-04-27");
			List<String> zz=cs.sum("2017-04-26","2017-04-27");
			System.out.println(test);
			System.out.println(ss);
			System.out.println(cc);
			System.out.println(aa);
			System.out.println(zz);
		}
		
		/*
		 * 函数sum()：
		 *  计算else的值
		 */ 
		public List<String> sum(String firsttime,String lasttime) throws ClassNotFoundException, SQLException{
			origin_select cs = new origin_select();
			List<String> c=cs.selectvalue(firsttime,lasttime);
			long ip_value=cs.Total(firsttime,lasttime)-cs.Portion(firsttime,lasttime);
			String value_string=String.valueOf(ip_value);
			c.add(value_string);
			return c;
		}
		
		/*
		 * 函数selectIP()：
		 *  获取查询时间的IP集合
		 */  
		public List<String> selectIP(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
			//sql语句查询时间段的前十IP
			String sql1 = "select origIP,sum(value) value from origIp where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+" group by origIP  order by value desc limit 10";
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
					}
			conn.close();
			list.add("else");
			return list;
		}
		
		public List<String> selectvalue(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
			//sql语句查询时间段的前十IP的值
			String sql1 = "select origIP,sum(value) value from origIp where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+" group by origIP  order by value desc limit 10";
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
						list.add(rs.getString(2));
					}
			conn.close();
			return list;
		}
		public long Total(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
			//读取时间段的总流量值
			String sql2="select sum(value)  from origIp where date between "+"'"+firsttime+"%'"+"and"+"'"+lasttime+"%'";
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
			//读取时间段的前十的流量
			String sql3="select sum(value) from (select sum(value) value from origIp where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+" group by origIP  order by value desc limit 10 ) as A";
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
		
		public List<String> orig_select(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
			//sql语句查询时间段的前十IP
			String sql1 = "select origIP,sum(value) value from origIp where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+" group by origIP  order by value desc limit 10";
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


