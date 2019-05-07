package management;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;



public class sys_file {
    public static void main(String[] args) {
        sys_file cs =new sys_file();
        List<String> list =cs.doubleFormat(0);
        System.out.println(list);
       
    }
    public  List<String> doubleFormat(double d){
        DecimalFormat df = new DecimalFormat("0.##");
        List<String> list =new ArrayList<String>();
        File[] roots = File.listRoots();
        double constm = 1024 * 1024 * 1024 ;
      for (File file : roots) {
            list.add(file.getPath());
            list.add(df.format((file.getTotalSpace()-file.getUsableSpace()) /constm)+" G");
            list.add(df.format(file.getFreeSpace()/constm)+" G");
            list.add(df.format(file.getTotalSpace()/constm)+" G");
    }
        
        sys_file cs=new sys_file();
        list.add(cs.test());
        String log=cs.test();
        log = log.substring(0,log.length()-2);
        Double l=Double.parseDouble(log);
        String use=list.get(3);
        use = use.substring(0,log.length()-2);
        Double y=Double.parseDouble(use);
        Double x=(l/y)*100;
		String values = String.valueOf(df.format(x)+" %");
		list.add(values);
       return list;
}
    
    public String s ="";
    public long getFileSizes(File f) throws Exception{
	       long s=0;
	       if (f.exists()) {
	           FileInputStream fis = null;
	           fis = new FileInputStream(f);
	          s= fis.available();
	       } else {
	           f.createNewFile();
	           System.out.println("file not exist");
	       }
	       return s;
	    }

	public long getFileSize(File f)throws Exception
 {
    long size = 0;
    File flist[] = f.listFiles();
    for (int i = 0; i < flist.length; i++)
    {
        if (flist[i].isDirectory())
        {
            size = size + getFileSize(flist[i]);
        } else
        {
            size = size + flist[i].length();
        }
    }
    return size;
 }

 public String FormetFileSize(long fileS) {
    DecimalFormat df = new DecimalFormat("#.00");
    String fileSizeString = "";
  
  if (fileS < 1024) {
        fileSizeString = df.format((double) fileS)+" B";
    } else if (fileS < 1048576) {
        fileSizeString = df.format((double) fileS / 1024)+" K";
    } else if (fileS < 1073741824) {
        fileSizeString = df.format((double) fileS / 1048576)+" M";
    } else {
        fileSizeString = df.format((double) fileS / 1073741824)+" G";
    }
    return fileSizeString;
 }

 public String test(){
 	sys_file g = new sys_file();
     while(true){
	       try
	       {
	           long l = 0;
	           String path = "/usr/local/bro/logs/";
	           File ff = new File(path);
	           if (ff.isDirectory()) { 
	               l = g.getFileSize(ff);
	               s= g.FormetFileSize(l); 
	               return s;
	           }
	       } catch (Exception e)
	       {
	           e.printStackTrace();
	       }
	      
     }
   }
 
 
}