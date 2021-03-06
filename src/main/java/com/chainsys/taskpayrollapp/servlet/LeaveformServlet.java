package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.model.LeaveApplicationModel;
import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/LeaveformServlet")
public class LeaveformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	PayrollService ps;

	@Override
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LeaveApplicationModel leave = new LeaveApplicationModel();
		String fromDate = request.getParameter("fromdate");
		String toDate = request.getParameter("todate");
		String reason = request.getParameter("Reason");
		leave.setFromDate(fromDate);
		leave.setToDate(toDate);
		leave.setReasonForLeave(reason);
		int rows = 0;
		HttpSession session = request.getSession();
		int empId = (int) session.getAttribute("value");
		rows = ps.applyLeave(empId, leave);
		if (rows > 0) {
			String result = "Leave Applied";
			response.sendRedirect("ceo.jsp?result=" + result);
		} else {
			String result = "Application Failed";
			response.sendRedirect("ceo.jsp?result=" + result);
		}
	}

}
