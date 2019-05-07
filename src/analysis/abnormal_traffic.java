package analysis;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.DBConfig;

public class abnormal_traffic {

	public static void main(String[] arg) throws ClassNotFoundException, SQLException, UnsupportedEncodingException{
		abnormal_traffic cs =new abnormal_traffic();
		//List<String> list =cs.abnormal_select_ip("2017-06-22","2017-06-24","10.185.129.183");
		List<String> list =cs.abnormal();
		System.out.println(list);
	}
	
	public List<String> abnormal() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select date,anomalyType,count(DISTINCT ip) from anomaly_ip   GROUP BY date_format(date,'%y-%m-%d'),anomalyType ORDER BY date_format(date,'%y-%m-%d') desc limit 40";
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
					list.add(new String(rs.getString(2)));//.getBytes("iso8859-1"), "utf-8"));
					list.add(rs.getString(3));
				}
		conn.close();
		Collections.reverse(list);
		return list;
	}
	
	
	public List<String> abnormal_select(String start,String end) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select date,anomalyType,count(DISTINCT ip) from anomaly_ip where date between '"+start+"' and '"+end+"'  GROUP BY date_format(date,'%y-%m-%d'),anomalyType";
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
					list.add(new String(rs.getString(2)));//.getBytes("iso8859-1"), "utf-8"));
					list.add(rs.getString(3));
				}
		conn.close();
		return list;
	}
	
	public List<String> abnormal_select_ip(String start,String end,String ip) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
		String sql1 = "select anomaly_ip.date,anomalyType,origIp.`value` FROM origip,anomaly_ip where origip.date=anomaly_ip.date and origip.origIP=anomaly_ip.ip and anomaly_ip.date between '"+start+"' and '"+end+"' and ip like '"+ip+"%' GROUP BY date_format(anomaly_ip.date,'%y-%m-%d'),anomalyType";
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
					list.add(new String(rs.getString(2)));//.getBytes("iso8859-1"), "utf-8"));
					list.add(rs.getString(3));
				}
		conn.close();
		return list;
	}

}