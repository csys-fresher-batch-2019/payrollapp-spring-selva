package com.chainsys.taskpayrollapp.validation;

import java.util.List;

import com.chainsys.taskpayrollapp.exceptions.DBException;
import com.chainsys.taskpayrollapp.util.GetDataUtil;

public class UserDetailsValidation {

	static GetDataUtil data = new GetDataUtil();

	public boolean emailValidation(String email) throws DBException {
		boolean check = true;
		List<String> emails = data.getAllEmail();
		if (emails.contains(email)) {
			check = false;
		}
		return check;
	}

	public boolean panValidation(String pan) throws DBException {
		boolean check = true;
		List<String> pans = data.getAllPan();
		if (pans.contains(pan)) {
			check = false;
		}
		return check;
	}

	public boolean idValidation(int id) throws DBException {
		boolean check = false;
		List<Integer> ids = data.getAllId();
		if (ids.contains(id)) {
			check = true;
		}
		return check;
	}

}
