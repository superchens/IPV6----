<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../../js/echarts.min.js"></script>
<title>Insert title here</title>
<script language="javascript"> 
    var XMLHttpReq;  
        //创建XMLHttpRequest对象         
        function createXMLHttpRequest() {  
            if(window.XMLHttpRequest) { //Mozilla 浏览器  
                XMLHttpReq = new XMLHttpRequest();  
            }  
            else if (window.ActiveXObject) { // IE浏览器  
                try {  
                    XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");  
                } catch (e) {  
                    try {  
                        XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");  
                    } catch (e) {}  
                }  
            }  
        }  
        //发送请求函数  
        function sendRequest() {  
            createXMLHttpRequest();  
            var url = "ipv6_web_ajax.jsp";  
            XMLHttpReq.open("GET", url, true);  
            XMLHttpReq.onreadystatechange = processResponse;//指定响应函数  
            XMLHttpReq.send(null);  // 发送请求  
        }  
        // 处理返回信息函数  
        function processResponse() {  
            if (XMLHttpReq.readyState == 4) { // 判断对象状态  
                if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息  
                    DisplayHot();  
                } else { //页面不正常  
                    window.alert("您所请求的页面有异常。");  
                }  
            }  
        }  
        function DisplayHot() {  
            var conn1 = XMLHttpReq.responseXML.getElementsByTagName("web_time");  
            var conn2=conn1[0].childNodes[0].nodeValue.replace("]","");
          		conn2=conn2.replace("[","");
          		conn2=conn2.substring(1,conn2.length);
          	var time=conn2.split(",");
          	
          	var traff1 = XMLHttpReq.responseXML.getElementsByTagName("web_duration");  
            var traff2=traff1[0].childNodes[0].nodeValue.replace("]","");
          		traff2=traff2.replace("[","");
          	var result=traff2.split(",");
          	
          	var myChart = echarts.init(document.getElementById('main'));
	        var option = {
		        		title: {
				        text: '(IPv6)总的网页访问时长(按天统计)'
				   	 },
					    tooltip : {
					        trigger: 'axis',
					    },
					    legend: {
					        data:['浏览时长(/小时)']
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'category',
					            boundaryGap : false,
					            data : ['2017-09-06','2017-09-07','2017-09-08','2017-09-09','2017-09-10','2017-09-11','2017-09-12','2017-09-13','2017-09-14','2017-09-15','2017-09-16','2017-09-17','2017-09-18','2017-10-10','2017-10-11']
					        }
					    ],
					    yAxis : [
					        {
					        	
					            type : 'value',
					        }
					    ],
					    series : [
					        {
					            name:'浏览时长(/小时)',
					            type:'line',
					            stack: '总量',
					            itemStyle: {normal: {areaStyle:{
					            								type: 'default',
					            								color:'#00E3E3',
					            								}
					            							}},
					            data:[289,267,284,279,291,289,269,286,274,295,277,299,266,172,239],
					        },
					    ]
					};
					myChart.setOption(option);
        }
    </script>  
      
</head>
<body onload =sendRequest()>  
   <div id="main" style="width: 100%;height:400px"></div>
    </body>  
</html>