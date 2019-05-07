<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="statistics.resp_ip_hadoop" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ch">
<head>
    
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/jPages.css" type="text/css" rel="stylesheet"></link>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jPages.js"></script>
    
     <script type="text/javascript" src="sorttable.js"></script>
    
	<style type="text/css">  
	.sort_able{cursor:pointer;padding:0 28px;background:url(small.gif) no-repeat right center;}
	.sort_asc{background:url(asc.png) no-repeat right center;color:#ffffff;cursor:pointer;padding:0 28px;}
	.sort_desc{background:url(desc.png) no-repeat right center;color:#ffffff;cursor:pointer;padding:0 28px;}
	</style>
</head>
<body>

<div style="width:100%;height:400px" align="center">

<table width="90%" height="350px" border="1" align="center" style="font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;">
	<thead>
	<tr>
		<th scope="col" colspan="9" height="50px" style="border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;text-align:center;font-size:20px;">当天目标IP Top100</th>
		<tr>
			<th width="50px" style="border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;text-align:center;">目标IP</th>
			<th width="50px" style="border-width: 1px;padding: 8px;border-style: solid;border-color: #666666;background-color: #dedede;text-align:center;">连接数(单位:条)</th>
		</tr>
	</thead>
	
	<tbody id="movies">

<%    
       resp_ip_hadoop cs = new resp_ip_hadoop();
   		 List<String> list =cs.hadoop_IP();
         	for(int i=0;i<list.size();i=i+2){
     %>
        <tr>
             <td width="50px" style="border-width: 1px;padding: 6px;border-style: solid;border-color: #666666;background-color: #ffffff;text-align:center;"><%=list.get(i)%></td>
             <td width="50px" style="border-width: 1px;padding: 6px;border-style: solid;border-color: #666666;background-color: #ffffff;text-align:center;"><%=list.get(i+1)%></td>
     	</tr>
     	 <%
          }
       %>
</tbody>
</table>
</div>
<div class="holder" align="center">
    <a class="jp-previous jp-disabled">← previous</a>
    <a class="jp-current">1</a>
    <span class="jp-hidden">...</span>
    <a>2</a>
    <a>3</a>
    <a>4</a>
    <a>5</a>
    <a class="jp-hidden">6</a>
    <a class="jp-hidden">7</a>
    <a class="jp-hidden">8</a>
    <a class="jp-hidden">9</a>
    <span>...</span>
    <a>10</a>
    <a class="jp-next">next →</a>
</div>
</body>
<script type="text/javascript">
	$(function(){
  $("div.holder").jPages({
    containerID : "movies"
  });
});
	</script>
</body>
</html>