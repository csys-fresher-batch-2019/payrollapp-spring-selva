package com.chainsys.selva;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.payrollapp.exceptions.DBExceptions;
import com.chainsys.payrollapp.service.PayrollService;
@WebServlet("/RemoveDetailServlet")

public class RemoveDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int id = Integer.parseInt((String)request.getParameter("id"));
		PayrollService ps = new PayrollService();
		int rows=0;
		try {
			rows = ps.deleteEmployee(id);
		} catch (DBExceptions e) {
			e.printStackTrace();
		}
		if(rows==1)
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
