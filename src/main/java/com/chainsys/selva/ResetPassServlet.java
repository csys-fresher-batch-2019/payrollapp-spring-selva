package com.chainsys.selva;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.payrollapp.service.PayrollService;
@WebServlet("/ResetPassServlet")

public class ResetPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int id = Integer.parseInt((String)request.getParameter("id"));
		PayrollService ps = new PayrollService();
		int rows = ps.unlockUserAccount(id);
		if(rows==1)
		{
			String result = "Updated Successfully";
			response.sendRedirect("Admin.jsp?result="+result);
		}
		else
		{
			String result = "Updates Failed";
			response.sendRedirect("Admin.jsp?result="+result);
		}
		
	}


}
