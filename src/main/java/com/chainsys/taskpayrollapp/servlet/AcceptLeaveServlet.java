package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.exception.ServiceException;
import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/AcceptLeaveServlet")
public class AcceptLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AcceptLeaveServlet.class);
	@Autowired
	PayrollService service;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		} catch (IOException | ServiceException | NumberFormatException e) {
			logger.error("Error in Accept Leave Servlet", e);
			response.sendRedirect("Error.jsp?Error=" + e);
		}
	}
}
