package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/AddgradeServlet")
public class AddgradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	PayrollService ps;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("id");
		String grade = request.getParameter("grade");
		int eid = Integer.parseInt(id);
		int egrade = Integer.parseInt(grade);
		int rows = ps.addGrade(eid, egrade);
		if (rows == 1) {
			String result = "Updated Successfully";
			response.sendRedirect("hr.jsp?result=" + result);
		} else {
			String result = "Updates Failed";
			response.sendRedirect("hr.jsp?result=" + result);
		}
	}
}
