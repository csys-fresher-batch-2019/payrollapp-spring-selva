package com.chainsys.taskpayrollapp.dao.daoimplements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.chainsys.taskpayrollapp.exceptions.DBException;
import com.chainsys.taskpayrollapp.util.Connections;

public class LogMonitorDAOImpl {

	public int swipe(int empId) throws DBException {
		int rows = 0;
		Connection con = null;
		CallableStatement stmt = null;
		try {
			con = Connections.connect();
			stmt = con.prepareCall("{call entry_gate(?)}");
			stmt.setInt(1, empId);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e.toString());
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}
}
