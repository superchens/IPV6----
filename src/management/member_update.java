package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import common.DBConfig;

public class member_update {
	
	public static void main(String[] arg) throws ClassNotFoundException, SQLException{
		member_update cs =new member_update();
		String result=cs.mod_user("rr","kk","1258","1");
		System.out.println(result);
	}

	public String  mod_user(String user,String name,String pwd,String admin) throws SQLException,ClassNotFoundException {
		String sql = "update user set user_name='"+name+"',user_password='"+pwd+"',sign='"+admin+"',register_time=now() where user_name='"+user+"'";
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
		stmt.executeUpdate(sql);
		conn.close();
	    String result="�޸ĳɹ�";
	    return result;
	}
}