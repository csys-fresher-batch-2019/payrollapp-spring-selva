package com.chainsys.taskpayrollapp.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.chainsys.taskpayrollapp.exceptions.DBExceptions;
import com.chainsys.taskpayrollapp.model.HrModel;

public interface HrDAO 
{@Autowired
	int addGrade(int grade,int id) throws DBExceptions;
	int addBasepay(int basepay,int id) throws DBExceptions;
	int addCredit(int allowance,int id)throws DBExceptions;
	ArrayList<HrModel> viewLeaveApplication()throws DBExceptions;
}
