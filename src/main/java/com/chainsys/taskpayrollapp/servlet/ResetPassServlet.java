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

@WebServlet("/ResetPassServlet")

public class ResetPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	PayrollService ps;
	private static final Logger logger = LoggerFactory.getLogger(ResetPassServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int rows = 0;
		try {
			rows = ps.unlockUserAccount(id);
			if (rows == 1) {
				String result = "Updated Successfully";
				response.sendRedirect("Admin.jsp?result=" + result);
			} else {
				String result = "Updates Failed";
				response.sendRedirect("Admin.jsp?result=" + result);
			}
		} catch (IOException | ServiceException | NumberFormatException e) {
			logger.error("Error in Accept Leave Servlet", e);
			response.sendRedirect("Error.jsp?Error=" + e);
		}
	}

}
