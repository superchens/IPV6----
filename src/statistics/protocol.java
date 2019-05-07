package statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import common.DBConfig;
public class protocol {
	
	public static void main(String[] args) throws Exception {
		protocol cs = new protocol();
		List<String> c=cs.protocol_name();
		List<String> s=cs.pro_values();
		long a=cs.Total();
		long z=cs.Portion();
		List<String> x=cs.sum();
		System.out.println(c);
		System.out.println(s);
		System.out.println(a);
		System.out.println(z);
		System.out.println(x);
	}
	
	public long values_total;
	public long values_portion;
	
	
	public List<String> sum() throws ClassNotFoundException, SQLException{
		//����else��ֵ
		protocol cs = new protocol();
		List<String> c=cs.pro_values();
		long pro_value=cs.Total()-cs.Portion();
		String value_string=String.valueOf(pro_value);
		c.add(value_string);
		return c;
	}

	public List<String> protocol_name() throws SQLException,ClassNotFoundException {
		//��ȡprotocol���е�Э������������10��
		String sql1 = "select protocol,sum(incomingTraff+outgoingTraff) as value from protocol group by protocol order by value desc limit 10";
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
			String pro = rs.getString(1);
			pro=pro.replaceAll(",","-");//���ַ�������ģ�ת���-
			list.add(pro);
		}
		conn.close();
		list.add("else");
		return list;
	}
	public List<String> pro_values() throws SQLException,ClassNotFoundException {
		//��ȡprotocol���е����10��������
		String sql1 = "select protocol,sum(incomingTraff+outgoingTraff) as value from protocol group by protocol order by value desc limit 10";
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
			String value = rs.getString(2);
			list.add(value);
			
		}
		conn.close();
		return list;
	}
	public long Total() throws SQLException,ClassNotFoundException {
		String sql2="select sum(incomingTraff+outgoingTraff)  from protocol";//��ȡ���ݿ������������
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
		ResultSet total=stmt.executeQuery(sql2);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (total.next()) {
			values_total=total.getLong(1);
		}
		conn.close();
		return values_total;
	}
	public long Portion() throws SQLException,ClassNotFoundException {
		String sql3="select sum(val)  from (select sum(incomingTraff+outgoingTraff) as val from protocol group by protocol order by val desc limit 10) as A";
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
		ResultSet total=stmt.executeQuery(sql3);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (total.next()) {
			values_portion=total.getLong(1);
		}
		conn.close();
		return values_portion;
	}
	
}