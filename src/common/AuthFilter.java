package common;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * �������������������鿴�û��Ƿ��½����δ��¼��ֹ����ҳ��
 *  
 * @author ������ʮ
 *
 */
public class AuthFilter implements Filter {

	 public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,   
			   FilterChain filterChain) throws IOException, ServletException {//1,doFilter�����ĵ�һ������ΪServletRequest���󡣴˶�����������ṩ�˶Խ������Ϣ�������������ݡ�cookie��HTTP����ͷ������ȫ���ʡ��ڶ�������ΪServletResponse��ͨ���ڼ򵥵Ĺ������к��Դ˲��������һ������ΪFilterChain���˲�����������servlet��JSPҳ��   
			  
			  HttpServletRequest request = (HttpServletRequest)servletRequest;//�������HTTP���󣬲�����Ҫ��������getHeader��getCookies����ServletRequest���޷��õ��ķ�������Ҫ�Ѵ�request�������HttpServletRequest   
			  HttpServletResponse response = (HttpServletResponse)servletResponse; 
			  HttpSession session = request.getSession(); 
			  
			  String currentURL = request.getRequestURI();//ȡ�ø�Ŀ¼����Ӧ�ľ���·��:   
			  String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length());  //��ȡ����ǰ�ļ������ڱȽ�   
			  
			     
			 if (!"/login.jsp".equals(targetURL)) {//�жϵ�ǰҳ�Ƿ����ض����Ժ�ĵ�¼ҳ��ҳ�棬����ǾͲ���session���жϣ���ֹ������ѭ��   
			 if (session.getAttribute("username") == null) {//*�û���¼�Ժ����ֶ�����session   
			    //System.out.println("request.getContextPath()=" + request.getContextPath());   
				 filterChain.doFilter(request, response);
				 response.setContentType("text/html;charset=UTF-8");
			     response.getWriter().println("<script language=\"javascript\">alert(\"����û�е�¼�����ȵ�¼!\");if(window.opener==null){window.top.location.href=\"/IDS/login.jsp\";}else{window.opener.top.location.href=\"/IDS/login.jsp\";window.close();}</script>");
			    return;   
			   }
			 }
			  //����filter����������ִ��   
			 filterChain.doFilter(request, response);//.����FilterChain�����doFilter������Filter�ӿڵ�doFilter����ȡһ��FilterChain������Ϊ����һ���������ڵ��ô˶����doFilter����ʱ��������һ����صĹ����������û����һ����������servlet��JSPҳ���������servlet��JSPҳ�汻���  
			 
		}   
  
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}