package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.taskpayrollapp.service.PayrollService;
@WebServlet("/PFcalcServlet")

public class PFcalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int row = 0;
		PayrollService ps = new PayrollService();
		try {
			row = ps.calculatePF();
			System.out.println(row);
			if(row > 0)
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
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
