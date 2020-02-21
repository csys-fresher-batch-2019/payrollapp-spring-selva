package com.chainsys.payrollapp.dao;

import java.util.ArrayList;

public interface AccountantDAO 
{
	int calculatePF()throws Exception;
	int calculateIncrement()throws Exception;
	int calculatesalary()throws Exception;
	int markAttendance()throws Exception;
	int GeneratePaySlip() throws Exception;
	int sendPayslip();
}
