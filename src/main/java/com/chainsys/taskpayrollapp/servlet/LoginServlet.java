package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.taskpayrollapp.dao.daoimplements.Login;
import com.chainsys.taskpayrollapp.exceptions.DBExceptions;


@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		int id = Integer.parseInt(userName);
		String password = request.getParameter("password");
		String desg;
		if(id==9999 && password.equals("ceo"))
		{
			desg = "CEO";
			HttpSession sess = request.getSession();
			sess.setAttribute("desg", desg);
			RequestDispatcher sd = request.getRequestDispatcher("ceo.jsp");
			sd.forward(request,response);
		}
		else {
			String res = "activate";
			String resu = "wrong password";
			String result = "Not a user";
			try {
				desg = Login.login(id, password);
				HttpSession sess = request.getSession();
				sess.setAttribute("desg", desg);
				HttpSession session = request.getSession();
				session.setAttribute("value", id);
				if(desg.contentEquals(result))
				{
					String display = "Not a user, Please contact admin!";
					response.sendRedirect("login.jsp?result="+display);
				}
				else if(desg.contentEquals(resu))
				{
					String display = "Invalid Username or Password!!";
					response.sendRedirect("login.jsp?result="+display);
				}
				else if(desg.contentEquals(res))
				{
					response.sendRedirect("activeLogin.jsp");
				}
				else
				{
					switch(desg) 
					{
						case "ADMIN":
							RequestDispatcher rdis1 = request.getRequestDispatcher("Admin.jsp");
							rdis1.forward(request,response);
							break;
						case "CONSULTANT":
							RequestDispatcher rdis2 = request.getRequestDispatcher("Consultant.jsp");
							rdis2.forward(request,response);
							break;
						case "DEVELOPER":
							RequestDispatcher rdis3 = request.getRequestDispatcher("Developer.jsp");
							rdis3.forward(request,response);
							break;
						case "ACCOUNTANT":
							RequestDispatcher rdis4 = request.getRequestDispatcher("Accountant.jsp");
							rdis4.forward(request,response);
							break;
						case "HR":
							RequestDispatcher rdis5 = request.getRequestDispatcher("hr.jsp");
							rdis5.forward(request,response);
							break;
						default :
							RequestDispatcher rdis6 = request.getRequestDispatcher("login.jsp");
							rdis6.forward(request,response);
							break;
					}
				}
			} catch (ClassNotFoundException | SQLException | DBExceptions e) {
				e.printStackTrace();
			}
		}
	}

	
}
