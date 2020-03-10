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

import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.exceptions.DBException;
import com.chainsys.taskpayrollapp.model.HrModel;
import com.chainsys.taskpayrollapp.service.PayrollService;

@WebServlet("/ViewLeaveApplicationServlet")
public class ViewLeaveApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	PayrollService ps;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<HrModel> list = new ArrayList<>();
		try {
			list = ps.viewLeaveApplication();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("LeaveDetails", list);
		RequestDispatcher rd = request.getRequestDispatcher("ViewLeaves.jsp");
		rd.forward(request, response);
	}
}
