package com.chainsys.taskpayrollapp.validation;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.taskpayrollapp.exceptions.DBExceptions;
import com.chainsys.taskpayrollapp.util.ErrorMessages;
import com.chainsys.taskpayrollapp.util.GetDataUtil;

@Repository

public class UserDetailsValidation {
	
	static GetDataUtil data = new GetDataUtil(); 
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean emailValidation(String email) throws DBExceptions, SQLException
	{
		boolean check = true;
		try 
		{
			ArrayList<String> emails = data.getAllEmail();
			if(emails.contains(email))
			{
				check = false;
			}
		}
		catch (Exception e){
			throw new DBExceptions(ErrorMessages.Error);
		}
		return check;
	}
	
	public boolean panValidation(String pan) throws DBExceptions, SQLException
	{
		boolean check = true;
		try 
		{
			ArrayList<String> pans = data.getAllPan();
			if(pans.contains(pan))
			{
				check = false;
			}
		}
		catch (Exception e){
			throw new DBExceptions(ErrorMessages.Error);
		}
		return check;
	}
	
	public boolean idValidation(int id) throws DBExceptions
	{
		boolean check = false;
		try 
		{
			ArrayList<Integer> ids = data.getAllId();
			if(ids.contains(id))
			{
				check = true;
			}
		}
		catch (Exception e){
			throw new DBExceptions(ErrorMessages.Error);
		}
		return check;	
	}

}
