package com.chainsys.payrollapp.model;

public class AdminModel {
	public enum FoodandCab
	{
		Y,N
	}

	private int empId;
	private String empName;
	private String designation;
	private String foodFacility;
	private String cabFacility;
	private String email;	
	private String pan;
	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
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
		return foodFacility;
	}
	public void setFoodFacility(String foodFacility) {
		this.foodFacility = foodFacility.trim();
	}
	public String getCabFacility() {
		return cabFacility;
	}
	public void setCabFacility(String cabFacility) {
		this.cabFacility = cabFacility.trim();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.trim();
	}
}
