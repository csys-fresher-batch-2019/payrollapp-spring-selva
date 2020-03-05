package com.chainsys.taskpayrollapp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainsys.taskpayrollapp.dao.daoimplements.AdminOperations;
import com.chainsys.taskpayrollapp.dao.daoimplements.AccountantOperations;
import com.chainsys.taskpayrollapp.dao.daoimplements.HrOperations;
import com.chainsys.taskpayrollapp.dao.daoimplements.LogMonitor;
import com.chainsys.taskpayrollapp.dao.daoimplements.LeaveApplication;
import com.chainsys.taskpayrollapp.dao.*;
import com.chainsys.taskpayrollapp.exceptions.DBExceptions;
import com.chainsys.taskpayrollapp.model.AdminModel;
import com.chainsys.taskpayrollapp.model.HrModel;
import com.chainsys.taskpayrollapp.model.LeaveApplicationModel;
import com.chainsys.taskpayrollapp.util.ErrorMessages;
import com.chainsys.taskpayrollapp.validation.UserDetailsValidation;

@Service 
public class PayrollService {
	@Autowired
	private AdminOperations ado;
	private AccountantDAO aco = new AccountantOperations(); 
	@Autowired
	private HrOperations hdo;
	private LogMonitor lm = new LogMonitor();
	private LeaveApplication leave = new LeaveApplication();
	private UserDetailsValidation user = new UserDetailsValidation();;
	
	//Admin Services
	public int addEmployeeDetails(AdminModel a) throws DBExceptions, SQLException
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
	public ArrayList<HrModel> viewLeaveApplication() throws DBExceptions 
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
	
	public int acceptLeave(int id)
	{
		int rows = 0;
		rows = LeaveApplication.Status(id,1);
		return rows;
	}
	public int RejectLeave(int id)
	{
		int rows = 0;
		rows = LeaveApplication.Status(id,2);
		return rows;
	}
}
