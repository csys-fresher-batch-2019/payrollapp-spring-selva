package com.chainsys.taskpayrollapp.dao.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.taskpayrollapp.exceptions.DBExceptions;
import com.chainsys.taskpayrollapp.util.Connections;
import com.chainsys.taskpayrollapp.util.ErrorMessages;
import com.chainsys.taskpayrollapp.util.JdbcUtil;

public class Login 
{
	public static String login(int EmpId, String password) throws ClassNotFoundException, SQLException, DBExceptions 
	{
		String designation = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from user_login where emp_id = ?";
		try 
		{
			con = Connections.connect();
			stmt = con.prepareStatement(sql); 
			stmt.setInt(1,EmpId);
			rs = stmt.executeQuery();			
			String res = "activate";
			String res1 = "wrong password";
			String res2 = "Not a user";
			if (rs.next()) 
			{
				String dbPassword = rs.getString("passwd");
				int active = rs.getInt("active");
				designation = rs.getString("designation").toUpperCase();
				if (password.equals(dbPassword)) 
				{
					if (active == 1){
						return designation;
					}
					else{
						return res; //if user not in active state
					}
				} 
				else{
					return res1;// if password not matched
				}
			}
			else{
				return res2;// if user not found 
			}
		}
		catch(Exception e)
		{
			throw new DBExceptions(ErrorMessages.LOGIN_FAILED);
		}
		finally
		{
			con.close();
			stmt.close();
			rs.close();
		}
	}
	public static int updatePassword(String newPassword, String conPassword, int EmpId) throws DBExceptions {
		String sql = "update user_login set passwd = ?,active = 1 where emp_id = ?";
		int rows = JdbcUtil.executeUpdate(sql, newPassword,EmpId);
		return rows;
	}

}
