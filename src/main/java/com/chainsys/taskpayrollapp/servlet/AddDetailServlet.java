package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.exceptions.ServiceException;
import com.chainsys.taskpayrollapp.model.AdminModel;
import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/AddDetailServlet")

public class AddDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	PayrollService service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int rows = 0;
		AdminModel admin = new AdminModel();
		String name = request.getParameter("Name");
		String desg = request.getParameter("Designation");
		System.out.println(desg);
		String pan = request.getParameter("pan");
		String food = request.getParameter("food");
		String cab = request.getParameter("cab");
		String mail = request.getParameter("Email");
		admin.setEmpName(name);
		admin.setDesignation(desg);
		admin.setPan(pan);
		admin.setFoodFacility(food);
		admin.setCabFacility(cab);
		admin.setEmail(mail);
		try {
			rows = service.addEmployeeDetails(admin);
			if (rows > 0) {
				int result = rows;
				response.sendRedirect("Admin.jsp?result=" + result);
			} else {
				String result = "Add Employee Failed";
				response.sendRedirect("Admin.jsp?result=" + result);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
