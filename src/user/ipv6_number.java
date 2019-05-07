package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import common.DBConfig;

public class ipv6_number {

	
	public List<String> number() throws SQLException,ClassNotFoundException {
		String sql1 = "select date,count(DISTINCT user)  from user_traff_ipv6 where user like '%:%' GROUP BY date_format(date,'%y-%m-%d')  desc limit 15" ;
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
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString(1));
			list.add(rs.getString(2));
		}
		conn.close();
		Collections.reverse(list);
		return list;
	}
		
	public List<String> select_number(String start,String end) throws SQLException,ClassNotFoundException {
		/*
		 * ����select_number()��
		 *  �������ݿ⣬��ѯʱ���ÿ����û���,����һ��list2�С�
		 */
		List<String> list2 =new ArrayList<String>();//�����е����ݶ�����һ��������    
				String sql1 = "select date,count(DISTINCT user) from user_traff_ipv6 where date between"+"'"+start+"'"+"and"+"'"+end+"'"+" and user like '%:%' group by date";
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
				while (rs.next()) {
					list2.add(rs.getString(1));
					list2.add(rs.getString(2));
				}
				conn.close();
		
		return list2;
	}
	
	
}