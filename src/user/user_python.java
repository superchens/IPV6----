package user;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class user_python {

private String results;
	
	public String getResults() {
		return results;
	}

	public static void main(String[] args) {
		user_python cs =new user_python();
		System.out.println(cs.exec("10.185.144.169"));
	}


	/**
	 * 
	 * @return 
	 * @Description: Ö´ÐÐpython³ÌÐò
	 * 
	 * @return void
	 * 
	 */
	public String exec(String ip) {
		try {
			String path="python C:\\apache-tomcat-8.0.23\\webapps\\IDS\\python\\recommend_system_user.py "+ip;
	//		String path="python F:\\MYeclipse\\BigData\\WebRoot\\python\\recommend_system_user.py "+ip;
	//		String path="python /usr/tomcat/apache-tomcat-8.5.28/webapps/BigData/python/recommend_system_user.py "+ip;
			Process pr=Runtime.getRuntime().exec(path);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					pr.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				results = results + line;
			}
			in.close();
			pr.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		if (results != "null") {
			results = results.substring(4);
		}
		
		return results;
	}
}
