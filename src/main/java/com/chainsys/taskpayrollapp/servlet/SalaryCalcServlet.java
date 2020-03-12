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

@WebServlet("/SalaryCalcServlet")

public class SalaryCalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SalaryCalcServlet.class);
	@Autowired
	PayrollService ps;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rows = 0;
		try {
			rows = ps.calculateSalary();
			if (rows > 0) {
				String result = "Done";
				response.sendRedirect("Accountant.jsp?result=" + result);
			} else {
				String result = "Failed";
				response.sendRedirect("Accountant.jsp?result=" + result);
			}
		} catch (IOException | ServiceException | NumberFormatException e) {
			logger.error("Error in Calculating increment", e);
			response.sendRedirect("Error.jsp?error=" + e);
		}
	}
}
