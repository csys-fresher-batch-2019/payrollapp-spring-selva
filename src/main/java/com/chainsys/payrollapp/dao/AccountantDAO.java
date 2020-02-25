package com.chainsys.payrollapp.dao;


public interface AccountantDAO 
{
	int calculatePF()throws Exception;//To calculate the Provident Fund to an employee
	
	int calculateIncrement()throws Exception;//To calculate Salary increment
	
	int calculatesalary()throws Exception;//To calculate salary for an employee
	
	int markAttendance()throws Exception;//To note the Login and Logout time
	
	int GeneratePaySlip() throws Exception;//To Generate Payslip PDF
}
