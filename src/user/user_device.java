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

public class user_device {

	public static void main(String[] arg) throws ClassNotFoundException, UnsupportedEncodingException, SQLException{
		user_device cs =new user_device();
		List<String> list =cs.look_device();
		System.out.println(list);
	}
	/*
	 * �����û������ǰʮ��device
	 */
	public List<String> select_device(String user) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
		String time=sdf.format(new Date(d.getTime()));//��ȡ���µ�ʱ��
		//sql����ѯʱ��ε�ǰʮIP
		String sql1 = "select device_type,sum(duration) value from my_device_test where user like "+"'"+user+"%'"+"and date like "+"'"+time+"%' group by device_type  order by value desc limit 10";
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
	 * һ���û�ʹ���豸���
	 */
	public List<String> select_time_device(String firsttime,String lasttime,String user) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		//sql����ѯʱ��ε�ǰʮIP
		String sql1 = "select device_type,sum(duration) value from my_device_test  where  date between"+"'"+firsttime+"' and '"+ lasttime+"'and user like "+"'"+user+"%' group by device_type  order by value desc limit 10" ;
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
	
	public List<String> look_device() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String time=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
		String sql1 = "select device_type,COUNT(device_type) value from my_device_test where date like "+"'"+time+"%'"+" group by device_type order by value desc limit 10";
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