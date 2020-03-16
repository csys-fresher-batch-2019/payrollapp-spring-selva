package com.chainsys.taskpayrollapp.model;

import lombok.Data;

@Data
public class HrModel {

	private int empId;
	private int allowance;
	private int basePay;
	private int performanceGrade;
	private String fromDate;
	private String reasonForLeave;
	private String toDate;
}