package statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import common.DBConfig;
public class session {
	
	public static void main(String[] args) throws Exception {
		session cs =new session();
		List<String> aa=cs.session_IP();
		List<String> bb=cs.session_orig();
		List<String> cc=cs.session_resp();
		List<String> zz=cs.session_values();
		System.out.println(aa);
		System.out.println(bb);
		System.out.println(cc);
		System.out.println(zz);
	}
	
	
	//读取数据库里面的IP,源ip，目标IP,及进出流量
	public List<String> session_sum() throws SQLException,ClassNotFoundException {
		/*
		 * 函数Main_conn()：
		 *  访问数据库，从中提取main_conn字段的value1,value2的值,放在一个list1中。
		 */
		String sql1 = "select CONCAT(origIP,'~',respIP),origBytes,respBytes,(origBytes+respBytes) from sessionTop ";
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
	//获取会话的ip集合
	public List<String> session_IP()  throws SQLException,ClassNotFoundException {
		   List<String> session=session_sum();
		   List<String> ip =new ArrayList<String>();
		   for(int i=0;i<session.size();i=i+4){
			   ip.add(session.get(i));
		   }
		return ip;	
	}
	//获取会话的origin的流量
	public List<String> session_orig()  throws SQLException,ClassNotFoundException {
		   List<String> session=session_sum();
		   List<String> orig_value =new ArrayList<String>();
		   for(int i=1;i<session.size();i=i+4){
			   long l=Long.parseLong(session.get(i));
				long x=l/1024;
				String values = String.valueOf(x);
			   orig_value.add(values);
		   }
		return orig_value;	
	}
	//获取会话的resp的流量
	public List<String> session_resp()  throws SQLException,ClassNotFoundException {
		   List<String> session=session_sum();
		   List<String> resp_value =new ArrayList<String>();
		   for(int i=2;i<session.size();i=i+4){
			   long l=Long.parseLong(session.get(i));
				long x=l/1024;
				String values = String.valueOf(x);
			   resp_value.add(values);
		   }
		return resp_value;	
	}
	//获取会话统计的总流量
	public List<String> session_values()  throws SQLException,ClassNotFoundException {
		   List<String> session=session_sum();
		   List<String> value =new ArrayList<String>();
		   for(int i=3;i<session.size();i=i+4){
			   long l=Long.parseLong(session.get(i));
				long x=l/1024;
				String values = String.valueOf(x);
			   value.add(values);
		   }
		return value;	
	}
}
