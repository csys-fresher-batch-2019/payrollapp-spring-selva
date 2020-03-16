package com.chainsys.taskpayrollapp.model;

import lombok.Data;

@Data
public class LeaveApplicationModel {
	public enum LeaveStatus {
		APPROVED, NOT_APPROVED;
	}

	private AdminModel aModel;
	private HrModel hModel;
	private String fromDate;
	private String toDate;
	private String reasonForLeave;
	LeaveStatus status;
}
