package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.exceptions.ServiceException;
import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/AcceptLeaveServlet")
public class AcceptLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	PayrollService service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int rows = 0;
		String id = request.getParameter("Accept");
		int eid = Integer.parseInt(id);
		try {
			rows = service.acceptLeave(eid);
			if (rows > 0) {
				String result = "Updated";
				response.sendRedirect("hr.jsp?result=" + result);
			} else {
				String result = "Failed";
				response.sendRedirect("hr.jsp?result=" + result);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
