package com.chainsys.taskpayrollapp.model;

import lombok.Data;

@Data
public class PaySlipModel {
	private int empId;
	private String empName;
	private int basePay;
	private int performanceGrade;
	private int salaryIncrement;
	private int allowance;
	private int leavesTaken;
	private int lossOfPay;
	private int foodDeduction;
	private int cabDeduction;
	private int providentFund;
	private int salaryToBeCredited;

}