package user;

import java.io.UnsupportedEncodingException;
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

public class user_web {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
		user_web cs=new user_web();
		List<String> list=cs.select_web("10.185.167.61");
		List<String> list1=cs.look_web();
		System.out.println(list);
	}
	
	/*
	 * �����û������ǰʮ����ҳ
	 */
	public List<String> select_web(String user) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
		String time=sdf.format(new Date(d.getTime()));//��ȡ���µ�ʱ��
		//sql����ѯʱ��ε�ǰʮIP
		String sql1 = "select webhost,sum(duration) value from user_web where user like "+"'"+user+"%'"+"and date like "+"'"+time+"%' group by webhost  order by value desc limit 10";
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
					list.add(new String(rs.getString(1).getBytes("iso8859-1"), "utf-8"));
					list.add(rs.getString(2));
				}
		conn.close();
		return list;
	}
	
	/*
	 * �����û���ʱ��ε�web�Ĳ�ѯ
	 */
	public List<String> select_time_web(String firsttime,String lasttime,String user) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		//��ȡprotocol���е�Э������������10��
		String sql1 = "select webhost,sum(duration) value from user_web  where  date between"+"'"+firsttime+"' and '"+ lasttime+"'and user like "+"'"+user+"%'  group by webhost  order by value desc limit 10" ;
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
			list.add(new String(rs.getString(1).getBytes("iso8859-1"), "utf-8"));
			list.add(rs.getString(2));
		}
		conn.close();
		return list;
	}
	
	/*
	 * ��ѯ�����web��ҳ���ǰʮ
	 */
	public List<String> look_web() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String time=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
		String sql1 = "select webhost,COUNT(webhost) value from user_web where date like "+"'"+time+"%'"+" group by webhost  order by value desc limit 10";
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
					list.add(new String(rs.getString(1).getBytes("iso8859-1"), "utf-8"));
					list.add(rs.getString(2));
				}
		conn.close();
		return list;
	}
}