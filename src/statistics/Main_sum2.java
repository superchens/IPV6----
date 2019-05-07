package statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBConfig;

public class Main_sum2 {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
				Main_sum2 cs = new Main_sum2();
				System.out.println(cs.Mainsum2());
	}
	
	public List<String> Mainsum2() throws SQLException,ClassNotFoundException {
		/*
		 * ����Main_conn()��
		 *  �������ݿ⣬������ȡmain_conn�ֶε�value1,value2��ֵ,����һ��list1�С�
		 */
		String sql1 = "select date_format(date,'20%y-%m-%d'),sum(main_log),sum(main_conn),sum(main_traff) from main GROUP BY date_format(date,'%y-%m-%d') ORDER BY date_format(date,'%y-%m-%d') desc limit 5";
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
		List<String> list1 =new ArrayList<String>();
		while (rs.next()) {
			list1.add(rs.getString(1));
			list1.add(rs.getString(2));
			list1.add(rs.getString(3));
			list1.add(rs.getString(4));
		}
		conn.close();
		return list1;
	}
}