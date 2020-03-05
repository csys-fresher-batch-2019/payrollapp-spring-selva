package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.taskpayrollapp.dao.daoimplements.Login;
import com.chainsys.taskpayrollapp.exceptions.DBExceptions;
@WebServlet("/UpdatePasswordServlet")

public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String pwd = request.getParameter("npassword");
		String psd = request.getParameter("cpassword");
		HttpSession session = request.getSession();
		int EmpId = (int)session.getAttribute("value");
		int rows=0;
		try {
			rows = Login.updatePassword(pwd,psd, EmpId);
		} catch (DBExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rows==0)
			response.sendRedirect("activeLogin.jsp?result=Updation Failure");
		else
			response.sendRedirect("login.jsp");
	}
}
