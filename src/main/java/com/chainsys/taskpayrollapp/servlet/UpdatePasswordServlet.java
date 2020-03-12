package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.dao.daoimplements.LoginDAOImpl;
import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/UpdatePasswordServlet")

public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	LoginDAOImpl loginObject;
	@Autowired
	PayrollService service;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pwd = request.getParameter("npassword");
		String psd = request.getParameter("cpassword");
		HttpSession session = request.getSession();
		int empId = (int) session.getAttribute("value");
		int rows = 0;

		rows = loginObject.updatePassword(pwd, psd, empId);
		if (rows == 0)
			response.sendRedirect("activeLogin.jsp?result=Updation Failure");
		else
			response.sendRedirect("login.jsp");
	}
}
