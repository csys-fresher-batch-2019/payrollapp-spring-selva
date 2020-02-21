package com.chainsys.selva;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.payrollapp.model.AdminModel;
import com.chainsys.payrollapp.service.PayrollService;

@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ArrayList<AdminModel> list = new ArrayList<>();
		PayrollService ps = new PayrollService();
		try {
			list = ps.display();
			request.setAttribute("EmployeeDetails", list);
			RequestDispatcher rd = request.getRequestDispatcher("ViewDetails.jsp");
			rd.forward(request,response);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	
}
