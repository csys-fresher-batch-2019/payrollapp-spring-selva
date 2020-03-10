package com.chainsys.taskpayrollapp.model;

public class HrModel {

	private int empId;
	private int allowance;
	private int basePay;
	private int performanceGrade;
	private String fromDate;
	private String reasonForLeave;
	private String toDate;

	public void setId(int id) {
		this.empId = id;
	}

	public int getID() {
		return empId;
	}

	public void setAllowance(int allowance) {
		this.allowance = allowance;
	}

	public int getAllowance() {
		return allowance;
	}

	public void setBasePay(int basePay) {
		this.basePay = basePay;
	}

	public int getBasePay() {
		return basePay;
	}

	public void setPerformanceGrade(int grade) {
		this.performanceGrade = grade;
	}

	public int getPerformanceGrade() {
		return performanceGrade;
	}

	public void setFromDate(String fDate) {
		this.fromDate = fDate;
	}

	public String getFromDate() {
		return fromDate.substring(0, 10);
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getToDate() {
		return toDate.substring(0, 10);
	}

	public void setReason(String reason) {
		this.reasonForLeave = reason;
	}

	public String getReason() {
		return reasonForLeave;
	}

}