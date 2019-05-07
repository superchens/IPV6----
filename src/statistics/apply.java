package statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import common.DBConfig;

public class apply {
	
	
	public static void main(String[] args) throws Exception {
		apply cs = new apply();
		List<String> conn=cs.apply_total();
		System.out.println(conn);
	}

	
	public List<String> apply_total() throws SQLException,ClassNotFoundException {
		/*
		 * ����Main_conn()��
		 *  �������ݿ⣬������ȡmain_conn�ֶε�value1,value2��ֵ,����һ��list1�С�
		 */
		String sql1 = "select protocol,incomingTraff,outgoingTraff  from protocol_rt ORDER BY incomingTraff ";
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
		}
		conn.close();
		return list1;
	}
	

}