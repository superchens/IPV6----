package statistics;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.DBConfig;

public class main_select_total {
	
	public static void main(String[] args) throws Exception {
		main_select_total cs = new main_select_total();
		List<String> list=cs.select_current();
		List<String> test = cs.select("2018-03-01","2018-03-05");
		System.out.println(test);
		System.out.println(list);
	}
	
	public List<String> select_current() throws SQLException,ClassNotFoundException {
		/*
		 * ����selectdata()��
		 *  ��ѯ���е���Ϣ
		 */  
		String sql1 = "select date_format(date,'%H:%i'),main_conn,resp_traff,orig_traff from main ORDER BY date desc limit 24";
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
		List<String> list =new ArrayList<String>();//�����е����ݶ�����һ��������  
		while (rs.next()) {
				list.add(rs.getString(1));
				long l=Long.parseLong(rs.getString(2));
				long x=l/100;
				String values = String.valueOf(x);
				list.add(values);
				long zz=Long.parseLong(rs.getString(3));
				long y=zz/(1024*1024);
				String resp_traff = String.valueOf(y);
				list.add(resp_traff);
				long cc=Long.parseLong(rs.getString(4));
				long k=cc/(1024*1024);
				String orig_traff = String.valueOf(k);
				list.add(orig_traff);
				}
		conn.close();
		Collections.reverse(list);
		return list;
	}
	
	
	public List<String> select(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		/*
		 * ����select()��
		 *  ��������ʱ��εĲ�������ѯ���ʱ�������
		 */  
		String sql1 = "select date_format(date,'%m-%d %H:%i'),main_conn,resp_traff,orig_traff from main where date between"+"'"+ firsttime+"'"+" and "+"'"+lasttime+"'";
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
		List<String> list =new ArrayList<String>();//�����е����ݶ�����һ��������  
		while (rs.next()) {
					list.add(rs.getString(1));
					long l=Long.parseLong(rs.getString(2));
					long x=l/100;
					String values = String.valueOf(x);
					list.add(values);
					long zz=Long.parseLong(rs.getString(3));
					long y=zz/(1024*1024);
					String resp_traff = String.valueOf(y);
					list.add(resp_traff);
					long cc=Long.parseLong(rs.getString(4));
					long k=cc/(1024*1024);
					String orig_traff = String.valueOf(k);
					list.add(orig_traff);
				}
		conn.close();
		return list;
	}
}