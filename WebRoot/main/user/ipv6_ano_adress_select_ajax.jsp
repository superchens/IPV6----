<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.ipv6_abnormal" %>
<%@ page import="java.util.*" %>
<% 
String firsttime="";
String lasttime="";
firsttime=request.getParameter("start");
lasttime=request.getParameter("end");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
</head>
<body>
<% 
response.setContentType("text/xml; charset=UTF-8");  
    response.setHeader("Cache-Control","no-cache"); 
	ipv6_abnormal cs=new ipv6_abnormal();
    List<String> list=cs.ipv6_oringip(firsttime,lasttime);
    List<String> list1 =new ArrayList<String>();
    List<String> list2 =new ArrayList<String>();
    for(int i=0;i<list.size();i=i+2){
    	list1.add(list.get(i));
    	list2.add(list.get(i+1));
    }
    out.println("<system_name>");
    out.println(list1);
    out.println("</system_name>");
    out.println("<system_values>");
  	out.println(list2);
  	out.println("</system_values>");
  	
  	 List<String> list3=cs.ipv6_respip(firsttime,lasttime);
    List<String> list4 =new ArrayList<String>();
    List<String> list5 =new ArrayList<String>();
    for(int i=0;i<list3.size();i=i+2){
    	list4.add(list3.get(i));
    	list5.add(list3.get(i+1));
    }
  	out.println("<video>");
    out.println(list4);
    out.println("</video>");
    out.println("<number>");
  	out.println(list5);
  	out.println("</number>");
			  	
%>  

</body>
</html>
