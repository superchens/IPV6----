package analysis;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBConfig;

public class DNN {
	
	public static void main(String[] args) throws Exception {
		lstm cs = new lstm();
		List<String> c=cs.test();
		System.out.println(c);
		
	}

	public List<String> test() throws SQLException, ClassNotFoundException {
		String sql = "select * from user1";
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
			list.add(rs.getString(2));
			list.add(rs.getString(3));
			list.add(rs.getString(4));
			list.add(rs.getString(5));
			list.add(rs.getString(6));
			list.add(rs.getString(7));
			list.add(rs.getString(8));
			list.add(rs.getString(9));
			list.add(rs.getString(10));
			list.add(rs.getString(12));
			list.add(rs.getString(13));
			list.add(rs.getString(14));
		}
		conn.close();
		return list;

	}
	 
	public List<String> DNN_test() throws SQLException, ClassNotFoundException {
		String sql = "select * from zdy";
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
			list.add(rs.getString(2));
			list.add(rs.getString(3));
			list.add(rs.getString(4));
		}
		conn.close();
		return list;

	}
	
}