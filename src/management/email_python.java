package management;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class email_python {
private String results;
	
	public String getResults() {
		return results;
	}

	public static void main(String[] args) {
		email_python cs =new email_python();
		System.out.println(cs.exec("544740618@qq.com","wkjlwgstehatbchf","409843583@qq.com;790114743@qq.com","实验测试","all in"));
	}


	/**
	 * 
	 * @return 
	 * @Description: 执行python程序
	 * 
	 * @return void
	 * 
	 */
	public String exec(String user,String pwd,String username,String subject,String content) {
		try {
			//String path="python F:\\MYeclipse\\HubuTraff\\WebRoot\\python\\push_service.py "+user+" "+pwd+" "+username+" "+subject+" "+content;
			//String path="python /usr/local/tomcat/webapps/hubu/python/push_service.py "+user+" "+pwd+" "+username+" "+subject+" "+content;
			String path="python /usr/local/tomcat/webapps/BigData/python/push_service.py "+user+" "+pwd+" "+username+" "+subject+" "+content;
			Process pr=Runtime.getRuntime().exec(path);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					pr.getInputStream()));
			results=in.readLine();
			in.close();
			pr.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}
