package statistics;

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

public class web_class_month {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		web_class_month cs =new web_class_month();
		List<String> list1=cs.month_class_user("��ҵ��վ");
		List<String> list2=cs.month_web_user();
		System.out.println(list1);
		System.out.println(list2);
	}
	
	/*
	 * ������վ���Ͳ�ѯ��ǰ10����վ��һ��ʱ���ʹ�����
	 */
	public List<String> select_class_user(String time,String type) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		//sql����ѯʱ��ε�ǰʮIP
		String sql1 = "select b.webhost,sum(b.duration) value from user_web b ,(select name from application_host where class like "+"'"+type+"%') a where a.name = b.webhost  and b.date like "+"'"+time+"%' group by b.webhost order by value desc limit 10 ";
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
	 * ������վ���Ͳ�ѯ��ǰ10����վ�ڱ��µ�ʹ�����
	 */
	public List<String> month_class_user(String type) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
		String time=sdf.format(new Date(d.getTime()));//��ȡ���µ�ʱ��
		//sql����ѯʱ��ε�ǰʮIP
		type = new String(type.getBytes("utf-8"), "iso8859-1");
		String sql1 = "select b.webhost,COUNT(DISTINCT user) value from user_web b ,(select name from application_host where class like "+"'"+type+"%') a where a.name = b.webhost  and b.date like "+"'"+time+"%' group by b.webhost order by value desc limit 10 ";
		System.out.println(sql1);
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
	 * ������վ���Ͳ�ѯ��ǰ10����վ�ڱ��µ�ʹ�����
	 */
	public List<String> month_web_user() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
		String current=sdf.format(new Date(d.getTime()));//��ȡ���µ�ʱ��
		//sql����ѯʱ��ε�ǰʮIP
		String sql1 = "select webhost,COUNT(DISTINCT user) value   from user_web where date like"+"'" +current+"%' group by webhost ORDER BY value desc limit 10 ";
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