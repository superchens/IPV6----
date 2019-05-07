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


public class snort_ip {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws UnsupportedEncodingException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, UnsupportedEncodingException, SQLException {
		// TODO Auto-generated method stub
		snort_ip cs =new snort_ip();
		System.out.print(cs.ipToLong("10.185.207.10")); 
	}
    
		// 将十进制整数形式转换成127.0.0.1形式的ip地址
		 public static String longToIP(long longIp) {
		        StringBuffer sb = new StringBuffer("");
		        // 直接右移24位
		        sb.append(String.valueOf((longIp >>> 24)));
		        sb.append(".");
		        // 将高8位置0，然后右移16位
		        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		        sb.append(".");
		        // 将高16位置0，然后右移8位
		        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		        sb.append(".");
		        // 将高24位置0
		        sb.append(String.valueOf((longIp & 0x000000FF)));
		        return sb.toString();
		    }
		 
		 public static long ipToLong(String ip) {
		        String[] arr = ip.split("\\.");
		        long result = 0;
		        for (int i = 0; i <= 3; i++) {
		            long num = Long.parseLong(arr[i]);
		            result |= num << ((3-i) << 3);
		        }
		        return result;
		    }

		 public List<String> snort_user() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
				String sql1 = "select a.timestamp,c.sig_class_name,b.sig_name,b.sig_priority,d.ip_src,d.ip_dst from event as a ,signature as b,sig_class as c,iphdr as d where a.signature=b.sig_id and b.sig_class_id=c.sig_class_id and a.cid=d.cid and a.sid=d.sid order by a.timestamp desc";
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
					        list.add(rs.getString(3));
					        list.add(rs.getString(4));
					        list.add(longToIP(Long.parseLong(rs.getString(5))));
					        list.add(longToIP(Long.parseLong(rs.getString(6))));
						}
				conn.close();
				return list;
			}
		 
		 public List<String> snort_select_user(String ip) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
			    long num=ipToLong(ip);
				String sql1 = "select a.timestamp,c.sig_class_name,b.sig_name,b.sig_priority,d.ip_src,d.ip_dst from event as a ,signature as b,sig_class as c,iphdr as d where a.signature=b.sig_id and b.sig_class_id=c.sig_class_id and a.cid=d.cid and a.sid=d.sid and d.ip_src="+num+" order by a.timestamp desc";
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
					        list.add(rs.getString(3));
					        list.add(rs.getString(4));
					        list.add(longToIP(Long.parseLong(rs.getString(5))));
					        list.add(longToIP(Long.parseLong(rs.getString(6))));
						}
				conn.close();
				return list;
			}
}
