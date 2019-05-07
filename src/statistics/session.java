package statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import common.DBConfig;
public class session {
	
	public static void main(String[] args) throws Exception {
		session cs =new session();
		List<String> aa=cs.session_IP();
		List<String> bb=cs.session_orig();
		List<String> cc=cs.session_resp();
		List<String> zz=cs.session_values();
		System.out.println(aa);
		System.out.println(bb);
		System.out.println(cc);
		System.out.println(zz);
	}
	
	
	//��ȡ���ݿ������IP,Դip��Ŀ��IP,����������
	public List<String> session_sum() throws SQLException,ClassNotFoundException {
		/*
		 * ����Main_conn()��
		 *  �������ݿ⣬������ȡmain_conn�ֶε�value1,value2��ֵ,����һ��list1�С�
		 */
		String sql1 = "select CONCAT(origIP,'~',respIP),origBytes,respBytes,(origBytes+respBytes) from sessionTop ";
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
	//��ȡ�Ự��ip����
	public List<String> session_IP()  throws SQLException,ClassNotFoundException {
		   List<String> session=session_sum();
		   List<String> ip =new ArrayList<String>();
		   for(int i=0;i<session.size();i=i+4){
			   ip.add(session.get(i));
		   }
		return ip;	
	}
	//��ȡ�Ự��origin������
	public List<String> session_orig()  throws SQLException,ClassNotFoundException {
		   List<String> session=session_sum();
		   List<String> orig_value =new ArrayList<String>();
		   for(int i=1;i<session.size();i=i+4){
			   long l=Long.parseLong(session.get(i));
				long x=l/1024;
				String values = String.valueOf(x);
			   orig_value.add(values);
		   }
		return orig_value;	
	}
	//��ȡ�Ự��resp������
	public List<String> session_resp()  throws SQLException,ClassNotFoundException {
		   List<String> session=session_sum();
		   List<String> resp_value =new ArrayList<String>();
		   for(int i=2;i<session.size();i=i+4){
			   long l=Long.parseLong(session.get(i));
				long x=l/1024;
				String values = String.valueOf(x);
			   resp_value.add(values);
		   }
		return resp_value;	
	}
	//��ȡ�Ựͳ�Ƶ�������
	public List<String> session_values()  throws SQLException,ClassNotFoundException {
		   List<String> session=session_sum();
		   List<String> value =new ArrayList<String>();
		   for(int i=3;i<session.size();i=i+4){
			   long l=Long.parseLong(session.get(i));
				long x=l/1024;
				String values = String.valueOf(x);
			   value.add(values);
		   }
		return value;	
	}
}