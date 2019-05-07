package statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import common.DBConfig;
public class origin_select {
	
	public long values_total;
	public long values_portion;
	
		public static void main(String[] args) throws Exception {
			origin_select cs = new origin_select();
			List<String> test = cs.selectIP("2017-04-26","2017-04-27");
			long ss=cs.Total("2017-04-26","2017-04-27");
			List<String> cc = cs.selectvalue("2017-04-26","2017-04-27");
			long aa=cs.Portion("2017-04-26","2017-04-27");
			List<String> zz=cs.sum("2017-04-26","2017-04-27");
			System.out.println(test);
			System.out.println(ss);
			System.out.println(cc);
			System.out.println(aa);
			System.out.println(zz);
		}
		
		/*
		 * ����sum()��
		 *  ����else��ֵ
		 */ 
		public List<String> sum(String firsttime,String lasttime) throws ClassNotFoundException, SQLException{
			origin_select cs = new origin_select();
			List<String> c=cs.selectvalue(firsttime,lasttime);
			long ip_value=cs.Total(firsttime,lasttime)-cs.Portion(firsttime,lasttime);
			String value_string=String.valueOf(ip_value);
			c.add(value_string);
			return c;
		}
		
		/*
		 * ����selectIP()��
		 *  ��ȡ��ѯʱ���IP����
		 */  
		public List<String> selectIP(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
			//sql����ѯʱ��ε�ǰʮIP
			String sql1 = "select origIP,sum(value) value from origIp where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+" group by origIP  order by value desc limit 10";
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
					}
			conn.close();
			list.add("else");
			return list;
		}
		
		public List<String> selectvalue(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
			//sql����ѯʱ��ε�ǰʮIP��ֵ
			String sql1 = "select origIP,sum(value) value from origIp where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+" group by origIP  order by value desc limit 10";
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
						list.add(rs.getString(2));
					}
			conn.close();
			return list;
		}
		public long Total(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
			//��ȡʱ��ε�������ֵ
			String sql2="select sum(value)  from origIp where date between "+"'"+firsttime+"%'"+"and"+"'"+lasttime+"%'";
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
			//��ȡʱ��ε�ǰʮ������
			String sql3="select sum(value) from (select sum(value) value from origIp where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+" group by origIP  order by value desc limit 10 ) as A";
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
		
		public List<String> orig_select(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
			//sql����ѯʱ��ε�ǰʮIP
			String sql1 = "select origIP,sum(value) value from origIp where date between"+"'"+firsttime+"%'"+"and"+"'"+ lasttime+"%'"+" group by origIP  order by value desc limit 10";
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

