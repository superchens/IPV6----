<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="statistics.file" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
</head>
<body>
<% 
response.setContentType("text/xml; charset=UTF-8");  
    response.setHeader("Cache-Control","no-cache"); 
    file cs=new file();
    List<String> list=cs.day_file();
    List<String> list1 =new ArrayList<String>();
    List<String> list2 =new ArrayList<String>();
    for(int i=0;i<list.size();i=i+2){
    	list1.add(list.get(i));
    	list2.add(list.get(i+1));
    }
    out.println("<file>");
    out.println(list1);
    out.println("</file>");
    out.println("<file_values>");
  	out.println(list2);
  	out.println("</file_values>");
 
 
 	List<String> list3=cs.day_hashfile();
    List<String> list4 =new ArrayList<String>();
    List<String> list5 =new ArrayList<String>();
    for(int i=0;i<list3.size();i=i+2){
    	list4.add(list3.get(i));
    	list5.add(list3.get(i+1));
    }
    out.println("<file_name>");
    out.println(list4);
    out.println("</file_name>");
    out.println("<hash_values>");
  	out.println(list5);
  	out.println("</hash_values>");
 
%>  
</body>
</html>
