package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import common.DBConfig;

public class internet_number {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		internet_number cs =new internet_number();
		List<String> cc=cs.number();
		System.out.println(cc);
		List<String> ss=cs.select_number("2017-05-06","2017-05-08");
		System.out.println(ss);
	}

	public List<String> currentdate(){
		/*
		 * ��ȡ��ǰ���ڵ�ǰ����֮����������ڵ��ַ�����
		 */
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        List<String> datetime=new ArrayList<String>();
        for (int i=0;i<5;i++) {  
        	 datetime.add(sdf.format(new Date(d.getTime() - i * 24 * 60 * 60 * 1000)));
        }  
        Collections.reverse(datetime);
        return datetime;
	}
	
	public List<String> number() throws SQLException,ClassNotFoundException {
		/*
		 * ����number()��
		 *  �������ݿ⣬������ȡ������û���,����һ��list2�С�
		 */
		List<String> list1=currentdate();
		List<String> list2 =new ArrayList<String>();//�����е����ݶ�����һ��������
		for(int i=0;i<list1.size();i++){       
				String sql1 = "select count(DISTINCT user) from user_traff where date like "+"'"+list1.get(i)+"%"+"'";
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
				
				while (rs.next()) {
					list2.add(rs.getString(1));
				}
				conn.close();
		}
		//Collections.reverse(list2);
		return list2;
	}
	
	public List<String> select_number(String start,String end) throws SQLException,ClassNotFoundException {
		/*
		 * ����select_number()��
		 *  �������ݿ⣬��ѯʱ���ÿ����û���,����һ��list2�С�
		 */
		List<String> list2 =new ArrayList<String>();//�����е����ݶ�����һ��������    
				String sql1 = "select date,count(DISTINCT user) from user_traff where date between"+"'"+start+"'"+"and"+"'"+end+"'"+"group by date";
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
				while (rs.next()) {
					list2.add(rs.getString(1));
					list2.add(rs.getString(2));
				}
				conn.close();
		
		return list2;
	}
	
}