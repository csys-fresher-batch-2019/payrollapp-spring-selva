package com.chainsys.taskpayrollapp.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chainsys.taskpayrollapp.exceptions.DBException;
import com.chainsys.taskpayrollapp.model.AdminModel;

@Service
public interface AdminDAO {

	int addUsers(AdminModel a) throws DBException;

	int removeUsers(int number) throws DBException;

	int calculateLOP() throws DBException;

	int resetPassword(int id) throws DBException;

	List<AdminModel> viewDetails() throws DBException;

}
