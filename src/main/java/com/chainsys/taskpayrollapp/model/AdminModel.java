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

	public String getPan() {
		return panNumber;
	}

	public void setPan(String pan) {
		this.panNumber = pan;
	}

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
		this.empName = empName.trim();
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation.trim();
	}

	public String getFoodFacility() {
		return foodSubscription;
	}

	public void setFoodFacility(String foodFacility) {
		this.foodSubscription = foodFacility.trim();
	}

	public String getCabFacility() {
		return cabSubscription;
	}

	public void setCabFacility(String cabFacility) {
		this.cabSubscription = cabFacility.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}
}
