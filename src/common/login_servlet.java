package common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login_servlet extends HttpServlet {

		public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setCharacterEncoding("utf-8");
	        //获取用户名和密码
	        String username = request.getParameter("username");
	        
	        String password = request.getParameter("password");
	        //获取UserDao实例
	        login cs=new login();

	        boolean flag = cs.test(username,password);
	        String result =cs.test1(username);
	        // 判断user是否为空
	        if(flag){
	        //  getRequestDispatcher()是请求转发
	        	HttpSession session = request.getSession();
	        	//比如这个是你取出来的值,你把你从数据库取出来的值 给这个变量就行了
	        	//Integer rign = 1;
	        	//或者是直接塞到session里面
	        	session.setAttribute("sign", result);
				session.setAttribute("username", username);
			//	  session.setMaxInactiveInterval(1800);
	            request.getRequestDispatcher("index.html").forward(request, response);
	        }else{
	        // 登录失败
	        	response.sendRedirect(request.getContextPath()+"/login.jsp?error=yes");
	       //     request.getRequestDispatcher("login.jsp").forward(request, response);
	                }
	        }

	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }

	}

