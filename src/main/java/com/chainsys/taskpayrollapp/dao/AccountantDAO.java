package com.chainsys.taskpayrollapp.dao;

import org.springframework.stereotype.Service;

import com.chainsys.taskpayrollapp.exceptions.DBException;

@Service
public interface AccountantDAO {
	int calculatePF() throws DBException;// To calculate the Provident Fund to an employee

	int calculateIncrement() throws DBException;// To calculate Salary increment

	int calculatesalary() throws DBException;// To calculate salary for an employee

	int markAttendance() throws DBException;// To note the Login and Logout time

	int GeneratePaySlip() throws DBException;// To Generate Payslip PDF
}
