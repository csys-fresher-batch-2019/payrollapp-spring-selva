package com.chainsys.taskpayrollapp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.taskpayrollapp.exceptions.DBExceptions;

public class GetDataUtil {
	public static final String empId = "emp_id";
	public static final String emailid = "email";
	public static final String pan_number = "pan_number";
	public ArrayList<String> getAllEmail() throws DBExceptions, SQLException
	{
		String sql = "select email from employee";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ArrayList<String> emails = new ArrayList<>();
		try		
		{
			con = Connections.connect();
			pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			while(result.next())
			{
				emails.add(result.getString(emailid));
			}
		}
		catch(Exception e)
		{
			throw new DBExceptions(ErrorMessages.Error);
		}
		finally
		{
			con.close();
			pst.close();
			result.close();
		}
		return emails;
	}
	public ArrayList<String> getAllPan() throws DBExceptions, SQLException
	{
		String sql = "select pan_number from employee";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ArrayList<String> panNumber = new ArrayList<>();
		try		
		{
			con = Connections.connect();
			pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			while(result.next())
			{
				panNumber.add(result.getString(pan_number));
			}
		}
		catch(Exception e)
		{
			throw new DBExceptions(ErrorMessages.Error);
		}
		finally
		{
			con.close();
			pst.close();
			result.close();
		}
		return panNumber;
	}
	public ArrayList<Integer> getAllId() throws DBExceptions, SQLException
	{
		String sql = "select emp_id from employee";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ArrayList<Integer> ids = new ArrayList<>();
		try		
		{
			con = Connections.connect();
			pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			while(result.next())
			{
				ids.add(result.getInt(empId));
			}
		}
		catch(Exception e)
		{
			throw new DBExceptions(ErrorMessages.Error);
		}
		finally
		{
			con.close();
			pst.close();
			result.close();
		}
		return ids;
	}

}
