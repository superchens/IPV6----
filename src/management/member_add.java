package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.DBConfig;

public class member_add {
	
	public static void main(String[] arg) throws ClassNotFoundException, SQLException{
		member_add cs =new member_add();
		System.out.println(cs.add_user("cc","123456","0"));
	}

	public String  add_user(String name,String pwd,String user) throws SQLException,ClassNotFoundException {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		String time=sdf.format(new Date(d.getTime()));//��ȡ���µ�ʱ��
		//
		String sql = "insert into user(user_name,user_password,sign,register_time) values("+"'"+name+"',"+"'"+pwd+"',"+"'"+user+"',"+"'"+time+"'"+")";
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
		stmt.execute(sql);
		conn.close();
	    String result="ע��ɹ�";
	    return result;
	}
	
}