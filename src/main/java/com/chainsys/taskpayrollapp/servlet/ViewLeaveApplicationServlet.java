package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
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
import com.chainsys.taskpayrollapp.model.HrModel;
import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/ViewLeaveApplicationServlet")
public class ViewLeaveApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ViewLeaveApplicationServlet.class);
	@Autowired
	PayrollService ps;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<HrModel> list = ps.viewLeaveApplication();
			request.setAttribute("LeaveDetails", list);
			RequestDispatcher rd = request.getRequestDispatcher("ViewLeaves.jsp");
			rd.forward(request, response);
		} catch (IOException | ServiceException | NumberFormatException e) {
			logger.error("Error in Calculating increment", e);
			response.sendRedirect("Error.jsp?error=" + e);
		}
	}
}
