package analysis;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBConfig;


public class snort_ip {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws UnsupportedEncodingException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, UnsupportedEncodingException, SQLException {
		// TODO Auto-generated method stub
		snort_ip cs =new snort_ip();
		System.out.print(cs.ipToLong("10.185.207.10")); 
	}
    
		// ��ʮ����������ʽת����127.0.0.1��ʽ��ip��ַ
		 public static String longToIP(long longIp) {
		        StringBuffer sb = new StringBuffer("");
		        // ֱ������24λ
		        sb.append(String.valueOf((longIp >>> 24)));
		        sb.append(".");
		        // ����8λ��0��Ȼ������16λ
		        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		        sb.append(".");
		        // ����16λ��0��Ȼ������8λ
		        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		        sb.append(".");
		        // ����24λ��0
		        sb.append(String.valueOf((longIp & 0x000000FF)));
		        return sb.toString();
		    }
		 
		 public static long ipToLong(String ip) {
		        String[] arr = ip.split("\\.");
		        long result = 0;
		        for (int i = 0; i <= 3; i++) {
		            long num = Long.parseLong(arr[i]);
		            result |= num << ((3-i) << 3);
		        }
		        return result;
		    }

		 public List<String> snort_user() throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
				String sql1 = "select a.timestamp,c.sig_class_name,b.sig_name,b.sig_priority,d.ip_src,d.ip_dst from event as a ,signature as b,sig_class as c,iphdr as d where a.signature=b.sig_id and b.sig_class_id=c.sig_class_id and a.cid=d.cid and a.sid=d.sid order by a.timestamp desc";
				// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
				// ������������Ҫָ��useUnicode��characterEncoding
				// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
				// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
				DBConfig cs=new DBConfig();//ʹ�ú����������ݿ�
				String url =cs.getUrl2();
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
					        list.add(longToIP(Long.parseLong(rs.getString(5))));
					        list.add(longToIP(Long.parseLong(rs.getString(6))));
						}
				conn.close();
				return list;
			}
		 
		 public List<String> snort_select_user(String ip) throws SQLException,ClassNotFoundException, UnsupportedEncodingException {
			    long num=ipToLong(ip);
				String sql1 = "select a.timestamp,c.sig_class_name,b.sig_name,b.sig_priority,d.ip_src,d.ip_dst from event as a ,signature as b,sig_class as c,iphdr as d where a.signature=b.sig_id and b.sig_class_id=c.sig_class_id and a.cid=d.cid and a.sid=d.sid and d.ip_src="+num+" order by a.timestamp desc";
				// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
				// ������������Ҫָ��useUnicode��characterEncoding
				// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
				// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
				DBConfig cs=new DBConfig();//ʹ�ú����������ݿ�
				String url =cs.getUrl2();
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
					        list.add(longToIP(Long.parseLong(rs.getString(5))));
					        list.add(longToIP(Long.parseLong(rs.getString(6))));
						}
				conn.close();
				return list;
			}
}