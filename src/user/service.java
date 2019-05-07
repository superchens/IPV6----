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

public class service {
	
	public static void main(String[] arg) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		service cs =new service();
		List<String> result=cs.video();
		System.out.println(result);
	}
	
	public List<String>  serv() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql = "select  a.service,COUNT(b.TS_ID) val from service as a ,tb_get_status_2015_11_09 as b where a.service_id=b.SERVICE_ID group by a.service order by val desc limit 10";
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
		ResultSet rs = stmt.executeQuery(sql);
		List<String> list =new ArrayList<String>();//�����е����ݶ�����һ��������  
		while (rs.next()) {
			        list.add(rs.getString(1));
					list.add(new String(rs.getString(2).getBytes("iso8859-1"), "utf-8"));
				}
		conn.close();
	    return list;
	}
	
	public List<String>  video() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql = "select CHANNEL_NAME,COUNT(CHANNEL_NAME) val from tb_get_status_2015_11_09 group by CHANNEL_NAME  order by val desc limit 10 ";
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
		ResultSet rs = stmt.executeQuery(sql);
		List<String> list =new ArrayList<String>();//�����е����ݶ�����һ��������  
		while (rs.next()) {
			        list.add(rs.getString(1));
					list.add(rs.getString(2));
				}
		conn.close();
	    return list;
	}
}