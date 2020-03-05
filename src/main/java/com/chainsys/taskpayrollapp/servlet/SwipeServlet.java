package com.chainsys.taskpayrollapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.service.PayrollService;
@WebServlet("/SwipeServlet")


public class SwipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	PayrollService ps;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		int EmpId = (int)session.getAttribute("value");
		System.out.println(EmpId);
		int rows = ps.swipe(EmpId);
		if(rows==1)
		{
			String result = "HI "+EmpId;
			response.sendRedirect("ceo.jsp?result="+result);
		}
		else
		{
			String result = "Failed";
			response.sendRedirect("ceo.jsp?result="+result);
		}
	}

	
}
