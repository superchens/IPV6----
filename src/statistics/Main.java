package statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import common.DBConfig;

public class Main {
	
	
	public static void main(String[] args) throws Exception {
		Main cs = new Main();
		List<String> conn=cs.Main_conn();
		List<String> traff=cs.Main_traff();
		List<String> output=cs.Main_output();
		List<String> input=cs.Main_input();
		System.out.println(conn);
		System.out.println(traff);
		System.out.println(output);
		System.out.println(input);
	}

	
	public List<String> Main_conn() throws SQLException,ClassNotFoundException {
		/*
		 * ����Main_conn()��
		 *  �������ݿ⣬������ȡmain_conn�ֶε�value1,value2��ֵ,����һ��list1�С�
		 */
		String sql1 = "select valuePre,valuePost  from temp_all where name='main_conn' ";
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		DBConfig cs=new DBConfig();//ʹ�ú����������ݿ�
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����
		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		List<String> list1 =new ArrayList<String>();
		while (rs.next()) {
			list1.add(rs.getString(1));
			list1.add(rs.getString(2));
		}
		conn.close();
		return list1;
	}
	
	public List<String> Main_traff() throws SQLException,ClassNotFoundException {
		/*
		 * ����Main_conn()��
		 *  �������ݿ⣬������ȡmain_traff�ֶε�value1,value2��ֵ,����һ��list2�С�
		 */
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		String sql2 = "select valuePre,valuePost  from temp_all where name='main_traff'";
		DBConfig cs=new DBConfig();//ʹ�ú����������ݿ�
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����
		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql2);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		List<String> list2 =new ArrayList<String>();
		while (rs.next()) {
			list2.add(rs.getString(1));
			list2.add(rs.getString(2));
		}
		conn.close();
		return list2;
	}
	
	
	public List<String> Main_output() throws SQLException,ClassNotFoundException {
		/*
		 * ����Main_conn()��
		 *  �������ݿ⣬������ȡmain_log�ֶε�value1,value2��ֵ,����һ��list2�С�
		 */
		String sql3 = "select valuePre,valuePost  from temp_all where name='orig_traff'";
		DBConfig cs=new DBConfig();//ʹ�ú����������ݿ�
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����
		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql3);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		List<String> list3 =new ArrayList<String>();
		while (rs.next()) {
			list3.add(rs.getString(1));
			list3.add(rs.getString(2));
		}
		conn.close();
		return list3;
	}
	
	public List<String> Main_input() throws SQLException,ClassNotFoundException {
		/*
		 * ����Main_conn()��
		 *  �������ݿ⣬������ȡmain_log�ֶε�value1,value2��ֵ,����һ��list2�С�
		 */
		String sql3 = "select valuePre,valuePost  from temp_all where name='resp_traff'";
		DBConfig cs=new DBConfig();//ʹ�ú����������ݿ�
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����
		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql3);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		List<String> list4 =new ArrayList<String>();
		while (rs.next()) {
			list4.add(rs.getString(1));
			list4.add(rs.getString(2));
		}
		conn.close();
		return list4;
	}
	
}