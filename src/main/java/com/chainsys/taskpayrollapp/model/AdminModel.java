package com.chainsys.taskpayrollapp.model;

import lombok.Data;

@Data
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

}
