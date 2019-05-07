package analysis;


import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.DBConfig;

public class snort_content {

	public static String hexStringToString(String s) {
	    if (s == null || s.equals("")) {
	        return null;
	    }
	    s = s.replace(" ", "");
	    byte[] baKeyword = new byte[s.length() / 2];
	    for (int i = 0; i < baKeyword.length; i++) {
	        try {
	            baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    try {
	        s = new String(baKeyword, "UTF-8");
	        new String();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	    return s;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, UnsupportedEncodingException, SQLException{
		snort_content cs = new snort_content();
		System.out.println(cs.snort_ana("192.168.1.139"));
	}
	
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
	 
	 public List<String> snort_analysis() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
			String sql1 = "select a.timestamp,b.ip_src,b.ip_dst,c.sig_name,d.data_payload from event as a,iphdr as b,signature as c,data as d where  a.signature=c.sig_id and b.cid=a.cid and a.cid=d.cid  limit 100";
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
				        list.add(longToIP(Long.parseLong(rs.getString(2))));
				        list.add(longToIP(Long.parseLong(rs.getString(3))));
				        list.add(rs.getString(4));
				        list.add(rs.getString(5));
					}
			conn.close();
			return list;
		}
	 
	 public List<String> snort_ana(String ip) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		 	long num=ipToLong(ip);
			String sql1 = "select a.timestamp,b.ip_src,b.ip_dst,c.sig_name,d.data_payload from event as a,iphdr as b,signature as c,data as d where b.ip_src="+num+" and a.signature=c.sig_id and b.cid=a.cid and a.cid=d.cid ";
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
				        list.add(longToIP(Long.parseLong(rs.getString(2))));
				        list.add(longToIP(Long.parseLong(rs.getString(3))));
				        list.add(rs.getString(4));
				        list.add(hexStringToString(rs.getString(5)));
					}
			conn.close();
			return list;
		}
}
