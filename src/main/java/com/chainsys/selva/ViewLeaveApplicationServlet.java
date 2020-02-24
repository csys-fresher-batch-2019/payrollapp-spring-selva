package com.chainsys.selva;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.payrollapp.model.HrModel;
import com.chainsys.payrollapp.service.PayrollService;


@WebServlet("/ViewLeaveApplicationServlet")
public class ViewLeaveApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PayrollService ps = new PayrollService();
		ArrayList<HrModel> list = new ArrayList<>();
		list = ps.viewLeaveApplication();
		request.setAttribute("LeaveDetails", list);
		RequestDispatcher rd = request.getRequestDispatcher("ViewLeaves.jsp");
		rd.forward(request,response);
	}
}
