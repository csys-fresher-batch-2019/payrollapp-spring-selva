package com.chainsys.taskpayrollapp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.chainsys.taskpayrollapp.exceptions.DBException;
public class JdbcUtil {
	
	public static int executeUpdate(String sql, Object... params) throws DBException  {
		int rows =0;
		try(Connection con = Connections.connect();
				PreparedStatement pst = con.prepareStatement(sql);)
		{
			int i = 1;
			for (Object param : params) {
				pst.setObject(i, param);
				i++;
			}
			rows = pst.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DBException(ErrorMessages.Error);
		}
		return rows;
	}
/*	public static void main(String[] args) throws Exception {
		
	String sql2 = "insert into user_login(emp_id,passwd,designation)values(?,'pass123',?)";
	int rows1 = JdbcUtil.executeUpdate(sql2 );
	}*/
}
