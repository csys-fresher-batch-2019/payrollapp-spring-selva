package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.exception.ServiceException;
import com.chainsys.taskpayrollapp.model.AdminModel;
import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DisplayServlet.class);
	@Autowired
	PayrollService ps;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<AdminModel> list = new ArrayList<>();
		try {
			list = ps.display();
			request.setAttribute("EmployeeDetails", list);
			RequestDispatcher rd = request.getRequestDispatcher("ViewDetails.jsp");
			rd.forward(request, response);
		} catch (IOException | ServiceException | NumberFormatException e) {
			logger.error("Error in Calculating LOP", e);
			response.sendRedirect("Error.jsp?error=" + e);
		}
	}

}
