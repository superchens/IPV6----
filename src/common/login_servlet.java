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
	        //��ȡ�û���������
	        String username = request.getParameter("username");
	        
	        String password = request.getParameter("password");
	        //��ȡUserDaoʵ��
	        login cs=new login();

	        boolean flag = cs.test(username,password);
	        String result =cs.test1(username);
	        // �ж�user�Ƿ�Ϊ��
	        if(flag){
	        //  getRequestDispatcher()������ת��
	        	HttpSession session = request.getSession();
	        	//�����������ȡ������ֵ,���������ݿ�ȡ������ֵ ���������������
	        	//Integer rign = 1;
	        	//������ֱ������session����
	        	session.setAttribute("sign", result);
				session.setAttribute("username", username);
			//	  session.setMaxInactiveInterval(1800);
	            request.getRequestDispatcher("index.html").forward(request, response);
	        }else{
	        // ��¼ʧ��
	        	response.sendRedirect(request.getContextPath()+"/login.jsp?error=yes");
	       //     request.getRequestDispatcher("login.jsp").forward(request, response);
	                }
	        }

	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }

	}
