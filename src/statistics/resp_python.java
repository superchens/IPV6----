package statistics;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class resp_python {

	private String results;
	
	public String getResults() {
		return results;
	}

	public static void main(String[] args) {
		resp_python cs =new resp_python();
		System.out.println(cs.exec("202.114.144.53"));
	}


	/**
	 * 
	 * @return 
	 * @Description: Ö´ÐÐpython³ÌÐò
	 * 
	 * @return void
	 * 
	 */
	public String exec(String filename) {
		try {
			String path="python F:\\MYeclipse\\HubuTraff\\WebRoot\\python\\getDNS.py "+filename;
			//String path="python /usr/local/tomcat/webapps/BigData/python/getDNS.py "+filename;
			Process pr = Runtime.getRuntime().exec(path);
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
