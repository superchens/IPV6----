package management;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.ParseException;
import java.util.Date;

public class bro_logfile {


	    /**
	     * 
	     * @author zdz8207
	     */
	   

	    public List<String> getFileName() {
	        String path = "/usr/local/bro/logs"; // 路径
	        File f = new File(path);
	        List<String> list =new ArrayList<String>();
	        if (!f.exists()) {
	            list.add(path + " not exists");
	            
	        }

	        File fa[] = f.listFiles();
	        for (int i = 0; i < fa.length; i++) {
	            File fs = fa[i];
	            if (fs.isDirectory()) {
	                list.add(fs.getName() + " [文件夹]");
	            } else {
	                list.add(fs.getName());
	            }
	        }
	        return list;
	    }
	    
	   
	    
	    public String  remove(String start, String end) throws ParseException, InterruptedException, IOException{
		     try {  
		    	 Calendar c = Calendar.getInstance();
		    	 DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		
		    	 //开始时间必须小于结束时间
		
		    	 Date beginDate =dateFormat1.parse(start);
		    	 Date endDate =  dateFormat1.parse(end);
		    	 Date date = beginDate;
		    	 while (date.getTime() <= endDate.getTime()) {
		    	 String date1=dateFormat1.format(date);
		    	 c.setTime(date);
		    	 c.add(Calendar.DATE, 1); // 日期加1天
		    	 date = c.getTime();
		    	 String cmd="rm -rf "+"/usr/local/bro/logs/"+date1;
		    	 System.out.println(cmd);
		         Runtime.getRuntime().exec(cmd); 
		       }
		    
		     } catch (IOException e) {  
		         e.printStackTrace();  
		     }  
		
		     String result="删除成功！";
		 	    return result;
		 }


	    
	    public static void main(String[] args) throws ParseException, InterruptedException, IOException {
	    	//remove("2017-04-02","2017-04-05");
	    	bro_logfile cs =new bro_logfile();
	    	String list=cs.remove("2017-06-04","2017-06-06");
	    	
	    	System.out.println(list);
	    }
	  }
	

