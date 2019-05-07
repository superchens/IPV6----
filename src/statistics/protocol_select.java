package statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import common.DBConfig;
public class protocol_select {

	public long values_total;
	public long values_portion;
	
	public static void main(String[] args) throws Exception {
		protocol_select cs = new protocol_select();
		List<String> test = cs.protocol_name("2017-04-27","2017-04-28");
		long ss=cs.Total("2017-04-27","2017-04-28");
		List<String> cc = cs.pro_values("2017-04-27","2017-04-28");
		long aa=cs.Portion("2017-04-27","2017-04-28");
		List<String> zz=cs.sum("2017-04-27","2017-04-28");
		List<String> ll=cs.select_date("2017-04-27","2017-04-28","http");
		System.out.println(test);
		System.out.println(ss);
		System.out.println(cc);
		System.out.println(aa);
		System.out.println(zz);
		System.out.println(ll);
	}
	
	public List<String> sum(String firsttime,String lasttime) throws ClassNotFoundException, SQLException{
		//����else��ֵ
		protocol_select cs = new protocol_select();
		List<String> c=cs.pro_values(firsttime,lasttime);
		long pro_value=cs.Total(firsttime,lasttime)-cs.Portion( firsttime,lasttime);
		String value_string=String.valueOf(pro_value);
		c.add(value_string);
		return c;
	}

	public List<String> protocol_name(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		//��ȡprotocol���е�Э������������10��
		String sql1 = "select protocol,sum(incomingTraff+outgoingTraff) as value from protocol where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+"group by protocol order by value desc limit 10";
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
	public List<String> pro_values(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		//��ȡprotocol���е����10��������
		String sql1 = "select protocol,sum(incomingTraff+outgoingTraff) as value from protocol where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+"group by protocol order by value desc limit 10";
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
	public long Total(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		String sql2="select sum(incomingTraff+outgoingTraff)  from protocol where date between "+"'"+firsttime+"%'"+"and"+"'"+lasttime+"%'";//��ȡ���ݿ������������
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
	public long Portion(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		String sql3="select sum(val) from (select sum(incomingTraff+outgoingTraff) as val from protocol where date between"+"'"+firsttime+"%'"+" and "+"'"+lasttime+"%'"+"group by protocol order by val desc limit 10) as A";
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
	
	//ͳ��ʱ��ε�һ��Э�������
	public List<String> select_date(String firsttime,String lasttime,String pro) throws SQLException,ClassNotFoundException {
		//��ȡprotocol���е�Э������������10��
		String sql1 = "select date  from protocol  where  date between "+"'"+firsttime+"%'"+" and"+"'"+ lasttime+"%'"+" and protocol like"+"'"+pro+"'" ;
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
			String date = rs.getString(1);
			list.add(date);
		}
		conn.close();
		return list;
	}
	
	//��������
	public List<String> select_incoming(String firsttime,String lasttime,String pro) throws SQLException,ClassNotFoundException {
		//��ȡprotocol���е����10��������
		String sql1 = "select incomingTraff  from protocol  where  date between "+"'"+firsttime+"%'"+" and"+"'"+ lasttime+"'"+" and protocol like"+"'"+pro+"'";
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
			String value = rs.getString(1);
			long l=Long.parseLong(value);
			long x=l/1024;
			String values = String.valueOf(x);
			list.add(values);
		}
		conn.close();
		return list;
	}
	
	//��������
	public List<String> select_outgoing(String firsttime,String lasttime,String pro) throws SQLException,ClassNotFoundException {
		//��ȡprotocol���е����10��������
		String sql1 = "select outgoingTraff  from protocol  where  date between "+"'"+firsttime+"%'"+" and"+"'"+ lasttime+"'"+" and protocol like"+"'"+pro+"'";
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
			String value = rs.getString(1);
			long l=Long.parseLong(value);
			long x=l/1024;
			String values = String.valueOf(x);
			list.add(values);
		}
		conn.close();
		return list;
	}
}