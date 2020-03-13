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

@WebServlet("/AddAllowanceServlet")
public class AddAllowanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AddAllowanceServlet.class);
	@Autowired
	PayrollService ps;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String allo = request.getParameter("allowance");
		int eid = Integer.parseInt(id);
		int allowance = Integer.parseInt(allo);
		try {
			int rows = ps.addCredit(eid, allowance);
			if (rows == 1) {
				String result = "Updated Successfully";
				response.sendRedirect("hr.jsp?result="+result);
			} else {
				String result = "Updates Failed";
				response.sendRedirect("hr.jsp?result="+result);
			}
		} catch (IOException | ServiceException | NumberFormatException e) {
			logger.error("Error in Accept Leave Servlet", e);
			response.sendRedirect("Error.jsp?Error=" + e);

		}
	}
}
