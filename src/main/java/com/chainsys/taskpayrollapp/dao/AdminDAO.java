package com.chainsys.taskpayrollapp.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.chainsys.taskpayrollapp.exceptions.DBExceptions;
import com.chainsys.taskpayrollapp.model.AdminModel;

@Service
public interface AdminDAO {
	
	int addUsers(AdminModel a) throws DBExceptions;
	int removeUsers(int number);
	int calculateLOP() throws Exception;
	int resetPassword(int id) throws DBExceptions;
	ArrayList<AdminModel> viewDetails()throws Exception;

}
