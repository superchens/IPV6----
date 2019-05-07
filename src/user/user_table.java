package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import common.DBConfig;

public class user_table {
	
	public static void main(String[] args) throws Exception {
		user_table cs = new user_table();
		//List<String> s=cs.test();
		List<String> c=cs.user_condition("2017-05-07","2017-05-09","192.168.1.58");
		System.out.println(c);
		
	}

	public List<String> test() throws SQLException, ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String current=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
		String sql = "select user,beginTime,endTime,outgoingTraff,incomingTraff from user_traff where date like '"+current+"%' ORDER BY endTime desc limit 100";
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		//String url = "jdbc:mysql://192.168.1.62:3306/hubutraff?"
		//		+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����

		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString(1));
			list.add(rs.getString(2));
			list.add(rs.getString(3));
			list.add(rs.getString(4));
			list.add(rs.getString(5));
		}

		conn.close();

		return list;

	}
	
	public List<String> user_condition(String begintime,String endtime,String user) throws SQLException, ClassNotFoundException {
		String sql = "select outgoingTraff,incomingTraff,CONCAT(right(beginTime,14),"+"'"+"~"+"'"+",right(endTime,14)) time,(UNIX_TIMESTAMP(str_to_date(endTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+")) - UNIX_TIMESTAMP(str_to_date(beginTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+"))) sum from user_traff where date between "+"'"+begintime+"'"+"and '"+endtime+"' and user like "+"'"+user+"'";
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����
		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			long l=Long.parseLong(rs.getString(1));
			long x=l/(1024*1024);
			String values = String.valueOf(x);
			list.add(values);
			long z=Long.parseLong(rs.getString(2));
			long y=z/(1024*1024);
			String value = String.valueOf(y);
			list.add(value);
			list.add(rs.getString(3));
			list.add(rs.getString(4));
		}
		conn.close();
		return list;

	}
	 
	 
}