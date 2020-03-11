package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/AddAllowanceServlet")
public class AddAllowanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	PayrollService ps;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String id = request.getParameter("id");
		String allo = request.getParameter("allowance");
		int eid = Integer.parseInt(id);
		int allowance = Integer.parseInt(allo);
		int rows = ps.addCredit(eid, allowance);
		if (rows == 1) {
			String result = "Updated Successfully";
			response.sendRedirect("hr.jsp?result=" + result);
		} else {
			String result = "Updates Failed";
			response.sendRedirect("hr.jsp?result=" + result);
		}
	}
}
