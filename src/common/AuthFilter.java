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
 * 过滤器（拦截器），查看用户是否登陆过，未登录禁止访问页面
 *  
 * @author 朝九晚十
 *
 */
public class AuthFilter implements Filter {

	 public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,   
			   FilterChain filterChain) throws IOException, ServletException {//1,doFilter方法的第一个参数为ServletRequest对象。此对象给过滤器提供了对进入的信息（包括表单数据、cookie和HTTP请求头）的完全访问。第二个参数为ServletResponse，通常在简单的过滤器中忽略此参数。最后一个参数为FilterChain，此参数用来调用servlet或JSP页。   
			  
			  HttpServletRequest request = (HttpServletRequest)servletRequest;//如果处理HTTP请求，并且需要访问诸如getHeader或getCookies等在ServletRequest中无法得到的方法，就要把此request对象构造成HttpServletRequest   
			  HttpServletResponse response = (HttpServletResponse)servletResponse; 
			  HttpSession session = request.getSession(); 
			  
			  String currentURL = request.getRequestURI();//取得根目录所对应的绝对路径:   
			  String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length());  //截取到当前文件名用于比较   
			  
			     
			 if (!"/login.jsp".equals(targetURL)) {//判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环   
			 if (session.getAttribute("username") == null) {//*用户登录以后需手动添加session   
			    //System.out.println("request.getContextPath()=" + request.getContextPath());   
				 filterChain.doFilter(request, response);
				 response.setContentType("text/html;charset=UTF-8");
			     response.getWriter().println("<script language=\"javascript\">alert(\"您还没有登录，请先登录!\");if(window.opener==null){window.top.location.href=\"/IDS/login.jsp\";}else{window.opener.top.location.href=\"/IDS/login.jsp\";window.close();}</script>");
			    return;   
			   }
			 }
			  //加入filter链继续向下执行   
			 filterChain.doFilter(request, response);//.调用FilterChain对象的doFilter方法。Filter接口的doFilter方法取一个FilterChain对象作为它的一个参数。在调用此对象的doFilter方法时，激活下一个相关的过滤器。如果没有另一个过滤器与servlet或JSP页面关联，则servlet或JSP页面被激活。  
			 
		}   
  
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}