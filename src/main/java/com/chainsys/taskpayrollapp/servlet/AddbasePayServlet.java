package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/AddbasePayServlet")
public class AddbasePayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String id = request.getParameter("id");
		String pay = request.getParameter("pay");
		int eid = Integer.parseInt(id);
		int epay = Integer.parseInt(pay);
		PayrollService ps = new PayrollService();
		try {
			int rows = ps.addBasepay(eid, epay);
			if(rows==1)
			{
				String result = "Updated Successfully";
				response.sendRedirect("hr.jsp?result="+result);
			}
			else
			{
				String result = "Updates Failed";
				response.sendRedirect("hr.jsp?result="+result);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
