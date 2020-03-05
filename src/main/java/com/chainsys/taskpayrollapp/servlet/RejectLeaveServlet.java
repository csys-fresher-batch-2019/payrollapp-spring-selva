package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/RejectLeaveServlet")
public class RejectLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int rows = 0;
		String id = request.getParameter("Reject");
		int eid = Integer.parseInt(id);
		PayrollService ps = new PayrollService();
		rows = ps.RejectLeave(eid);
		if(rows > 0)
		{
			String result = "Updated";
			response.sendRedirect("hr.jsp?result="+result);
		}
		else
		{
			String result = "Failed";
			response.sendRedirect("hr.jsp?result="+result);
		}
	}
}
