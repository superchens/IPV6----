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
public class main_select {
	
	
	public static void main(String[] args) throws Exception {
		main_select cs = new main_select();
		List<String> list1=cs.selectdata();
		List<String> test = cs.selectdate("2017-04-12","2017-04-14");
		System.out.println(list1);
		System.out.println(test);
	}
	
	public List<String> selectdata() throws SQLException,ClassNotFoundException {
		/*
		 * ����selectdata()��
		 *  ��ѯ���е���Ϣ
		 */  
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String time1=sdf.format(new Date(d.getTime()+24 * 60 * 60 * 1000));
        String time2=sdf.format(new Date(d.getTime()-20 * 24 * 60 * 60 * 1000));
		String sql1 = "select date_format(date,'20%y-%m-%d'),sum(main_log),sum(main_conn),sum(main_traff),sum(resp_traff),sum(orig_traff) from main where date between"+"'"+time2+"%"+"'"+"and"+"'"+time1+"%"+"'"+"group by date_format(date,'%y-%m-%d')";
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
					list.add(rs.getString(3));
					list.add(rs.getString(4));
					list.add(rs.getString(5));
					list.add(rs.getString(6));
				}
		conn.close();
		return list;
	}
	public List<String> selectdate(String firsttime,String lasttime) throws SQLException,ClassNotFoundException {
		/*
		 * ����selectdate()��
		 *  ��������ʱ��εĲ�������ѯ���ʱ�������
		 */  
		String sql1 = "select date_format(date,'20%y-%m-%d'),sum(main_log),sum(main_conn),sum(main_traff) from main where date between"+"'"+firsttime+"%"+"'"+"and"+"'"+lasttime+"%"+"'"+"group by date_format(date,'%y-%m-%d')";
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
					list.add(rs.getString(3));
					list.add(rs.getString(4));
				}
		conn.close();
		return list;
	}
	
}