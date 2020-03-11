/*package com.chainsys.taskpayrollapp.servlet;
UNUSED
import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.taskpayrollapp.util.SelectDataUtil;

@WebServlet("/SelectionServlet")

public class SelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    //PrintWriter out = response.getWriter();
    //String desgination = request.getParameter("desg");
    SelectDataUtil selectid = new SelectDataUtil();
    ArrayList<Integer> idlist = new ArrayList<>();
    try {
		idlist = selectid.selectUserId();
		HttpSession sess = request.getSession();
		sess.setAttribute("Allid", idlist);
		RequestDispatcher rdis1 = request.getRequestDispatcher("hr.jsp");
		rdis1.forward(request,response);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	//out.println(desgination);	
	}
}
*/