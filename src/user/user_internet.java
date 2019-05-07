package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


import common.DBConfig;

public class user_internet {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
			user_internet cs=new user_internet();
			String ss=cs.current_new_user();
			String cc=cs.yesterday_new_user();
			Long qq=cs.current_online_user();
			Long ww=cs.yesterday_online_user();
			String zz=cs.current_user();
			String aa=cs.yesterday_user();
		    Long  dd=cs.current_user_time();
			Long ff=cs.yesterday_user_time();
			String ee=cs.current_user_traff();
			String rr=cs.yesterday_user_traff();
			
			System.out.println(ss);
			System.out.println(cc);
			System.out.println(qq);
			System.out.println(ww);
			System.out.println(zz);
			System.out.println(aa);
			System.out.println(dd);
			System.out.println(ff);
			System.out.println(ee);
			System.out.println(rr);
	}
	
	/*
	 * �����������û������������û�
	 */
	public String current_new_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String current=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
        String time=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//��ȡǰһ���ʱ��
		String sql1 = "select (select count(DISTINCT user) from user_traff where date between "+"'"+time+"' and "+"'"+current+"') - (select count(DISTINCT user) from user_traff where date like"+"'"+time+"%') as newuser";
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
		String newuser="";
		while (rs.next()) {
		 newuser=rs.getString(1);
				}
		conn.close();
		return newuser;
	}
	
	/*
	 * �����������û����ǰ������û�
	 */
	public String yesterday_new_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time1=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//��ȡ�����ʱ��
        String time2=sdf.format(new Date(d.getTime()- 2 * 24 * 60 * 60 * 1000));//��ȡǰһ���ʱ��
		String sql1 = "select (select count(DISTINCT user) from user_traff where date between "+"'"+time2+"' and "+"'"+time1+"') - (select count(DISTINCT user) from user_traff where date like"+"'"+time2+"%') as newuser";
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
	    String newuser ="";//�����е����ݶ�����һ��������  
		while (rs.next()) {
					newuser=rs.getString(1);
				}
		conn.close();
		return newuser;
	}
	
	/*
	 * ���������������û�
	 */
	public Long current_online_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String current=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
		String sql1 = "select count(DISTINCT user) from user_traff where date like "+"'"+current+"%"+"'";
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
		long user=0;
		while (rs.next()) {
		 user=Long.parseLong(rs.getString(1));
		}
		conn.close();
		return user;
	}
	
	
	/*
	 * ���������������û�
	 */
	public Long yesterday_online_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//��ȡ�����ʱ��
		String sql1 = "select count(DISTINCT user) from user_traff where date like "+"'"+time+"%"+"'";
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
		long user=0;
		while (rs.next()) {
		  user=Long.parseLong(rs.getString(1));
		}
		conn.close();
		return user;
	}
	/*
	 * ������������������û���������û�
	 */
	public String current_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String current=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
        String time=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//��ȡǰһ���ʱ��
		String sql1 = "select (select count(DISTINCT user) from user_traff where date between "+"'"+time+"' and "+"'"+current+"') - (select count(DISTINCT user) from user_traff where date like"+"'"+current+"%') as olduser";
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
		String olduser="";
		while (rs.next()) {
		 olduser=rs.getString(1);
				}
		conn.close();
		return olduser;
	}
	
	/*
	 * ǰ������������û�������û�
	 */
	public String yesterday_user() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time1=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//��ȡ�����ʱ��
        String time2=sdf.format(new Date(d.getTime()- 2 * 24 * 60 * 60 * 1000));//��ȡǰһ���ʱ��
		String sql1 = "select (select count(DISTINCT user) from user_traff where date between "+"'"+time2+"' and "+"'"+time1+"') - (select count(DISTINCT user) from user_traff where date like"+"'"+time1+"%') as olduser";
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
	    String olduser ="";//�����е����ݶ�����һ��������  
		while (rs.next()) {
			olduser=rs.getString(1);
				}
		conn.close();
		return olduser;
	}
	
	/*
	 * ���㵱���û�������ƽ��ʱ��
	 */
	public Long current_user_time() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
		String sql1 = "select sum(UNIX_TIMESTAMP(str_to_date(endTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+")) - UNIX_TIMESTAMP(str_to_date(beginTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+"))) sum from user_traff where date like "+"'"+time+"%'";
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
	    long usetime =0;//�����е����ݶ�����һ��������  
	    long online=current_online_user();
	    if(online!=0){
		while (rs.next()) {
			long l=Long.parseLong(rs.getString(1));
			usetime=l/(online*60);
		 }
	    }
		conn.close();
		return usetime;
	}
	
	/*
	 * ���������û�������ƽ��ʱ��
	 */
	public Long yesterday_user_time() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//��ȡ�����ʱ��
		String sql1 ="select sum(UNIX_TIMESTAMP(str_to_date(endTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+")) - UNIX_TIMESTAMP(str_to_date(beginTime,"+"'"+"%Y-%m-%d %H:%i:%s"+"'"+"))) sum from user_traff where date like "+"'"+time+"%'";
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
	    long usetime =0;//�����е����ݶ�����һ��������  
	    long online=yesterday_online_user();
	    if(online!=0){
		while (rs.next()) {
			long l=Long.parseLong(rs.getString(1));
			usetime=l/(online*60);
		}
	    }
		conn.close();
		return usetime;
	}
	
	/*
	 * ���㵱���ƽ������
	 */
	public String current_user_traff() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()));//��ȡ�����ʱ��
		String sql1 = "select sum(outgoingTraff+incomingTraff) from user_traff where date like"+"'"+ time+"%'";
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
	    String usetraff ="";//�����е����ݶ�����һ��������  
	    long online=current_online_user();
	    if(online!=0){
		while (rs.next()) {
			long l=Long.parseLong(rs.getString(1));
			long x=l/(online*1024);
			usetraff = String.valueOf(x);
				}
	    }
		conn.close();
		return usetraff;
	}
	
	/*
	 * ���������û�������ƽ������
	 */
	public String yesterday_user_traff() throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String time=sdf.format(new Date(d.getTime()- 24 * 60 * 60 * 1000));//��ȡ�����ʱ��
		String sql1 = "select sum(outgoingTraff+incomingTraff) from user_traff where date like"+"'"+ time+"%'";
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
	    String usetraff ="";//�����е����ݶ�����һ��������  
	    long online=yesterday_online_user();
	    if(online!=0){
		while (rs.next()) {
			long l=Long.parseLong(rs.getString(1));
			long x=l/(online*1024);
			usetraff = String.valueOf(x);
		}
	    }
		conn.close();
		return usetraff;
	}
}