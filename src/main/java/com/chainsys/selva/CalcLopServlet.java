package com.chainsys.selva;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.payrollapp.service.PayrollService;
@WebServlet("/CalcLopServlet")

public class CalcLopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PayrollService ps = new PayrollService();
		int rows = 0;
		try {
		 rows = ps.calculateLOP();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(rows>0)
		{
			String result = "Calculated";
			response.sendRedirect("Admin.jsp?result="+result);
		}
		else
		{
			String result = "Calculation Failure";
			response.sendRedirect("Admin.jsp?result="+result);
		}
	}

}
