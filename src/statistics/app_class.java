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
public class app_class {

	
		public static void main(String[] args) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
			// TODO Auto-generated method stub
			app_class cs =new app_class();
			List<String> list1=cs.month_class("�����Ķ�");
			System.out.println(list1);
		}
		
		/*
		 * ������վ���Ͳ�ѯ��ǰ10����վ��һ��ʱ���ʹ�����
		 */
		public List<String> select_class(String time,String type) throws SQLException,ClassNotFoundException {
			//sql����ѯʱ��ε�ǰʮIP
			String sql1 = "select b.app_name,sum(b.duration) value from my_app b ,(select app from app_class where class like "+"'"+type+"%') a where a.app = b.app_name  and b.date like "+"'"+time+"%' group by b.app_name order by value desc limit 10 ";
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
		
		/*
		 * ������վ���Ͳ�ѯ��ǰ10����վ�ڱ��µ�ʹ�����
		 */
		public List<String> month_class(String type) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
			String time=sdf.format(new Date(d.getTime()));//��ȡ���µ�ʱ��
			//sql����ѯʱ��ε�ǰʮIP
			type = new String(type.getBytes("utf-8"), "iso8859-1");
			String sql1 = "select b.app_name,sum(b.duration) value from my_app b ,(select app from app_class where class like "+"'"+type+"%') a where a.app = b.app_name  and b.date like "+"'"+time+"%' group by b.app_name order by value desc limit 10 ";
			// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
			// ������������Ҫָ��useUnicode��characterEncoding
			// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
			// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
			DBConfig cs=new DBConfig();//ʹ�ú����������ݿ�
			String url =cs.getUrl();
			System.out.println(sql1);
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
		/*
		 * ������վ���Ͳ�ѯ��ǰ10����վ�ڱ��µ�ʹ�����
		 */
		public List<String> month_app() throws SQLException,ClassNotFoundException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
			String current=sdf.format(new Date(d.getTime()));//��ȡ���µ�ʱ��
			//sql����ѯʱ��ε�ǰʮIP
			String sql1 = "select app_name,sum(duration) value  from my_app where date like"+"'" +current+"%' group by app_name ORDER BY value desc limit 10 ";
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
		
		public List<String> month_app_member() throws SQLException,ClassNotFoundException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
	        String time=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
			String sql1 = "select app_name,COUNT(DISTINCT user) value from my_app where date like "+"'"+time+"%'"+" group by app_name ORDER BY value desc limit 10";
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
		public List<String> month_app_user(String type) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
			String time=sdf.format(new Date(d.getTime()));//��ȡ���µ�ʱ��
			//sql����ѯʱ��ε�ǰʮIP
			type = new String(type.getBytes("utf-8"), "iso8859-1");
			String sql1 = "select b.app_name,COUNT(DISTINCT user) value from my_app b ,(select app from app_class where class like "+"'"+type+"%') a where a.app = b.app_name  and b.date like "+"'"+time+"%' group by b.app_name order by value desc limit 10 ";
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
		
		public List<String> day_app_member() throws SQLException,ClassNotFoundException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String time=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
			String sql1 = "select app_name,COUNT(DISTINCT user) value from my_app where date like "+"'"+time+"%'"+" group by app_name ORDER BY value desc limit 10";
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
		
		public List<String> day_app_user(String type) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			String time=sdf.format(new Date(d.getTime()));//��ȡ���µ�ʱ��
			//sql����ѯʱ��ε�ǰʮIP
			type = new String(type.getBytes("utf-8"), "iso8859-1");
			String sql1 = "select b.app_name,COUNT(DISTINCT user) value from my_app b ,(select app from app_class where class like "+"'"+type+"%') a where a.app = b.app_name  and b.date like "+"'"+time+"%' group by b.app_name order by value desc limit 10 ";
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

