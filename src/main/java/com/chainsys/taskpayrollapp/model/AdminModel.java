package com.chainsys.taskpayrollapp.model;

public class AdminModel {
	public enum FoodandCab {
		Y, N
	}

	private int empId;
	private String empName;
	private String designation;
	private String foodSubscription;
	private String cabSubscription;
	private String email;
	private String panNumber;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getFoodSubscription() {
		return foodSubscription;
	}
	public void setFoodSubscription(String foodSubscription) {
		this.foodSubscription = foodSubscription;
	}
	public String getCabSubscription() {
		return cabSubscription;
	}
	public void setCabSubscription(String cabSubscription) {
		this.cabSubscription = cabSubscription;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	}
