package com.chainsys.taskpayrollapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.exceptions.DBException;
import com.chainsys.taskpayrollapp.model.HrModel;

public interface HrDAO {
	@Autowired
	int addGrade(int grade, int id) throws DBException;

	int addBasepay(int basepay, int id) throws DBException;

	int addCredit(int allowance, int id) throws DBException;

	List<HrModel> viewLeaveApplication() throws Exception;
}
