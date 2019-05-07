package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBConfig;

public class log_file {
	public static void main(String[] arg) throws ClassNotFoundException, SQLException{
		log_file cs =new log_file();
		List<String> list =cs.logfile();
		System.out.println(list);
}

	public List<String> logfile() throws SQLException,ClassNotFoundException {
		String sql1 = "select substring(date,1,7),sum(main_log) from main group by substring(date,1,7)";
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
					long l=Long.parseLong(rs.getString(2));
					long x=l/(1024*1024);
					String values = String.valueOf(x);
					list.add(values+" M");
				}
		conn.close();
		return list;
}
}