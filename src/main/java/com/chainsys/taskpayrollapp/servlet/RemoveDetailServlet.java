package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.service.PayrollService;
@WebServlet("/RemoveDetailServlet")

public class RemoveDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	PayrollService ps;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int id = Integer.parseInt((String)request.getParameter("id"));
		int rows=0;
		try {
			rows = ps.deleteEmployee(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rows > 0)
		{
			String result = "Removed Successfully";
			response.sendRedirect("Admin.jsp?result="+result);
		}
		else
		{
			String result = "Remove Failed ! ";
			response.sendRedirect("Admin.jsp?result="+result);
		}
	}
}
