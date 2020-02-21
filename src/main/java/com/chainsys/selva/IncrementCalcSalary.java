package com.chainsys.selva;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.payrollapp.service.PayrollService;

@WebServlet("/IncrementCalcSalary")

public class IncrementCalcSalary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int rows = 0;
		PayrollService ps = new PayrollService();
		try
		{
			rows = ps.calculateIncrement();
			if(rows > 0)
			{
				String result = "Calculated";
				response.sendRedirect("Accountant.jsp?result="+result);
			}
			else
			{
				String result = "Failed";
				response.sendRedirect("Accountant.jsp?result="+result);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
