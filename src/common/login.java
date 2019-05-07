package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class login {
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	public boolean test(String name,String password )  {
		boolean result=false;
		//String sql = "select name,pwd,rign from user where name=? and pwd=?";
		String sql = "select user_name,user_password,sign from user where user_name=? and user_password=?";
		Connection con = new login_jdbc().getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if (rs.next()) {//验证成功
				result=true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public String test1(String name)  {
		String result="";
		String sql = "select sign from user where user_name=?";
		//String sql = "select user_name,user_password from user where user_name=? and user_password=?";
		Connection con = new login_jdbc().getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result=rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public static void main(String[] args) {
		login cs = new login();
		String s=cs.test1("cs");
		System.out.println(s);
	}
}
