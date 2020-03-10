package com.chainsys.taskpayrollapp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainsys.taskpayrollapp.dao.daoimplements.AdminDAOImpl;
import com.chainsys.taskpayrollapp.dao.daoimplements.AccountantDAOImpl;
import com.chainsys.taskpayrollapp.dao.daoimplements.HrDAOImpl;
import com.chainsys.taskpayrollapp.dao.daoimplements.LogMonitor;
import com.chainsys.taskpayrollapp.dao.daoimplements.LeaveApplication;
import com.chainsys.taskpayrollapp.dao.*;
import com.chainsys.taskpayrollapp.exceptions.DBExceptions;
import com.chainsys.taskpayrollapp.model.AdminModel;
import com.chainsys.taskpayrollapp.model.HrModel;
import com.chainsys.taskpayrollapp.model.LeaveApplicationModel;
import com.chainsys.taskpayrollapp.model.LeaveApplicationModel.LeaveStatus;
import com.chainsys.taskpayrollapp.util.ErrorMessages;
import com.chainsys.taskpayrollapp.validation.UserDetailsValidation;

@Service 
public class PayrollService {
	@Autowired
	private AdminDAOImpl ado;
	private AccountantDAO aco = new AccountantDAOImpl(); 
	@Autowired
	private HrDAOImpl hdo;
	private LogMonitor lm = new LogMonitor();
	@Autowired
	private LeaveApplication leave;
	private UserDetailsValidation user = new UserDetailsValidation();;
	
	//Admin Services
	public int addEmployeeDetails(AdminModel a) throws Exception
	{
		int rows = 0;
		boolean result = true;
		try {
			result = user.emailValidation(a.getEmail());
			
			result = user.panValidation(a.getPan());
		} catch (DBExceptions e) {
			throw new DBExceptions(ErrorMessages.Error); 
		}
		if(result)
		{
			rows = ado.addUsers(a);
			return rows;
		}
		else
		{
			return 0;
		}
	}
	public int deleteEmployee(int id) throws DBExceptions, SQLException
	{
		int rows = 0;
		boolean result = false;
		try {
			result = user.idValidation(id);
			System.out.println(result);
		} catch (DBExceptions e) {
			throw new DBExceptions(ErrorMessages.Error);
		}
		if(result)
		{
			rows = ado.removeUsers(id);
			return rows;
		}
		else
		{
			return 0;
		}
	}
	public int calculateLOP() throws Exception
	{
		int rows = ado.calculateLOP();
		return rows;
	}
	public int unlockUserAccount(int id) throws DBExceptions, SQLException
	{
		int rows = 0;
		boolean result = false;
		try {
			result = user.idValidation(id);
		} catch (DBExceptions e) {
			throw new DBExceptions(ErrorMessages.Error);
		}
		if(result)
		{
			rows = ado.resetPassword(id);
		}
		else
		{
			return 0;
		}
		return rows;
	}
	public ArrayList<AdminModel> display() throws Exception
	{
		ArrayList<AdminModel> list = new ArrayList<>();
		list = ado.viewDetails();
		return list;
	}
	
	
	//Accountant Services
	
	public int calculatePF() throws Exception
	{
		int rows = aco.calculatePF();
		return rows;
	}
	public int calculateIncrement() throws Exception
	{
		int rows = aco.calculateIncrement();
		return rows;
	}
	public int  markAttandence() throws Exception
	{
		int rows = aco.markAttendance();
		return rows;
	}
	public int calculateSalary() throws Exception
	{
		int rows = aco.calculatesalary();
		return rows;
	}
	public int generatePaySlip() throws Exception
	{
		int rows = aco.GeneratePaySlip();
		return rows;
	}

	// HR Services
	
	public int addGrade(int id,int grade) throws Exception
	{
		int rows = hdo.addGrade(id, grade);
		return rows;
	}
	public int addBasepay(int id,int basepay) throws Exception
	{
		int rows = hdo.addBasepay(id,basepay);
		return rows;
	}
	public int addCredit(int id,int allowance) throws Exception
	{
		int rows = hdo.addCredit(allowance,id);
		return rows;
	}
	public ArrayList<HrModel> viewLeaveApplication() throws Exception 
	{
		ArrayList<HrModel> list = new ArrayList<HrModel>(); 
		list = hdo.viewLeaveApplication();
		return list;
	}
	
	//Swipe
	
	public int swipe(int id)
	{
		int rows = 0;
		try {
			rows = lm.swipe(id);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return rows;
	}

	//Leaveform
	
	public int applyLeave(int id,LeaveApplicationModel leavemodel)
	{
		int rows = leave.applyLeave(id, leavemodel);
		return rows;
	}
	
	//Leave
	
	public int acceptLeave(int id) throws DBExceptions
	{
		int rows = 0;
		boolean result = false;
		String status = "";
		status = LeaveStatus.APPROVED.toString();
		rows = leave.leaveStatusUpdate(id,1);
		rows = leave.leaveCountUpdate(id);
		result = leave.sendMail(id, status);
		if(result)
		{
			return rows;
		}
		else
		{
			return 0;
		}
			
	}
	public int RejectLeave(int id)
	{
		int rows = 0;
		rows = LeaveApplication.Status(id,2);
		return rows;
	}
}
