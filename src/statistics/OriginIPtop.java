package statistics;

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
public class OriginIPtop {
	public String value= "";
	public long values_total;
	public long values_portion;
	public static void main(String[] args) throws Exception {
		OriginIPtop cs = new OriginIPtop();
//		List<String> cc=cs.Origin_Values();
//		List<String> s=cs.Origin_IP();
//		long cc=cs.Total();
//		long s=cs.Portion();
		List<String> c=cs.sum();
//		List<String> s=cs.Origin_IP();
//		System.out.println(s);
		System.out.println(c);
		
	}
	public List<String> sum() throws ClassNotFoundException, SQLException{
		OriginIPtop cs = new OriginIPtop();
		List<String> c=cs.Origin_Values();
		long ip_value=cs.Total()-cs.Portion();
		String value_string=String.valueOf(ip_value);
		c.add(value_string);
		return c;
	}

	public List<String> Origin_IP() throws SQLException,ClassNotFoundException {
		String sql1 = "select origIP,sum(value) value from origIp group by origIP order by value desc limit 10";//��ȡ���ݿ���������10������
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
			value = rs.getString(1);
			list.add(value);
			
		}
		conn.close();
		list.add("else");
		return list;
	}
	public List<String> Origin_Values() throws SQLException,ClassNotFoundException {
		String sql1 = "select origIP,sum(value) value from origIp group by origIP order by value desc limit 10";//��ȡ���ݿ���������10������
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
			value = rs.getString(2);
			list.add(value);
			
		}
		conn.close();
		return list;
	}
	public long Total() throws SQLException,ClassNotFoundException {
		String sql2="SELECT sum(`value`) as origin_total FROM origIp";//��ȡ���ݿ������������
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
		String sql3="select sum(value) as origin_portion from (select sum(value) value from origIp group by origIP order by value desc limit 10) as A";
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
	
	public List<String> orig() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String current=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
		//sql����ѯʱ��ε�ǰʮIP
		String sql1 = "select origIP,sum(value) value from origIp where date like '"+current+"%' GROUP BY origIP  order by value desc limit 10";
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
					list.add(rs.getString(2));
				}
		conn.close();
		return list;
	}


	public List<String> orig_five() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String current=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
		String time=sdf.format(new Date(d.getTime()-5 * 24 * 60 * 60 * 1000));
		//sql����ѯʱ��ε�ǰʮIP
		String sql1 = "select origIP,sum(value) value from origIp where date between '"+time+"' and '"+current+" ' group by origIP  order by value desc limit 10";
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
					list.add(rs.getString(2));
				}
		conn.close();
		return list;
	}
	
}