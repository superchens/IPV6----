package user;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBConfig;

public class user_lose {
	
	public static void main(String[] args) throws Exception {
		user_lose cs = new user_lose();
		List<String> c=cs.test_data();
		System.out.println(c);
		
	}

	public List<String> test() throws SQLException, ClassNotFoundException {
		String sql = "select user from user_lose";
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
			list.add(rs.getString(1));
		}
		conn.close();
		return list;

	}
	 
	
	public List<String> test_data() throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
		String sql = "select * from test_lose";
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
			list.add(new String(rs.getString(1).getBytes("iso8859-1"), "utf-8"));
			list.add(new String(rs.getString(2).getBytes("iso8859-1"), "utf-8"));
			list.add(new String(rs.getString(3).getBytes("iso8859-1"), "utf-8"));
			list.add(new String(rs.getString(4).getBytes("iso8859-1"), "utf-8"));
			list.add(new String(rs.getString(5).getBytes("iso8859-1"), "utf-8"));
			list.add(new String(rs.getString(6).getBytes("iso8859-1"), "utf-8"));
			list.add(new String(rs.getString(7).getBytes("iso8859-1"), "utf-8"));
			list.add(new String(rs.getString(8).getBytes("iso8859-1"), "utf-8"));
			list.add(new String(rs.getString(9).getBytes("iso8859-1"), "utf-8"));
			list.add(new String(rs.getString(10).getBytes("iso8859-1"), "utf-8"));
		}
		conn.close();
		return list;

	}
}