package user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



public class user_similar_web {

private String results;
	
	public String getResults() {
		return results;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		user_similar_web cs =new user_similar_web();
		String result=cs.exec("10.185.131.9");
		System.out.println(cs.exec("10.185.131.9"));
		String state = result.substring(result.indexOf("[")+1,result.indexOf("]"));
        String[] list1=state.split(",");
        List<String> list= new ArrayList<String>();
        for (String str : list1){
             str=str.replaceAll("'","");
             String str1="";
             String str2="";
             int i=0;
            while(i<str.length()){
            	 if(str.charAt(i)=='\\'){ 
                  str1=str.substring(i+2,i+4)+str.substring(i+6,i+8)+str.substring(i+10,i+12);
                  str2=str2+cs.hexToStringGBK(str1);
                  i=i+12;
             }
            else{
            	str1=str.substring(i,i+1);
            	str2+=str1;
            	i++;
            }
        }
			  list.add(str2);
		} 
		System.out.println(list);
		//System.out.println(StringEscapeUtils.unescapeJava("'\\xe5\\xb0\\x8f\\xe7\\xb1\\xb3\\xe7\\xa4\\xbe\\xe5\\x8c\\xba'"));
		//System.out.println(cs.hexToStringGBK("xe5xb0x8fxe7xb1xb3xe7xa4xbexe5x8cxba".replaceAll("x", "")));
	
	}


	/**
	 * 
	 * @return 
	 * @Description: Ö´ÐÐpython³ÌÐò
	 * 
	 * @return void
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	public String exec(String ip) throws UnsupportedEncodingException {
		try {
			String path="python  C:\\apache-tomcat-8.0.23\\webapps\\IDS\\python\\recommend_system_user_web.py "+ip;
		//	String path="python /usr/tomcat/apache-tomcat-8.5.28/webapps/BigData/python/recommend_system_user_web.py "+ip;
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
		//results=new String(results.getBytes("iso8859-1"), "utf-8");
		return results;
	}
	
	public  String hexToStringGBK(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
		try {
		baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
		} catch (Exception e) {
		e.printStackTrace();
		return "";
		}
		}
		try {
		s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
		e1.printStackTrace();
		return "";
		}
		return s;
		}
}
