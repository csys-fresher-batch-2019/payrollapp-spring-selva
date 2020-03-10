package com.chainsys.taskpayrollapp.dao.daoimplements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.taskpayrollapp.dao.AccountantDAO;
import com.chainsys.taskpayrollapp.exceptions.DBExceptions;
import com.chainsys.taskpayrollapp.util.Connections;
import com.chainsys.taskpayrollapp.util.ErrorMessages;
import com.chainsys.taskpayrollapp.util.GetDataUtil;

public class AccountantDAOImpl implements AccountantDAO {
	/**
	 * PF calculation by using two table(employee,deduction) data and update the PF
	 * column in deduction table
	 * 
	 * @throws DBExceptions
	 * @throws SQLException
	 */
	public int calculatePF() throws ClassNotFoundException, DBExceptions, SQLException {

		int rows = 0;
		GetDataUtil get = new GetDataUtil();
		Connection con = null;
		CallableStatement statement = null;
		try {
			con = Connections.connect();
			ArrayList<Integer> ids = get.getAllId();
			for (int i : ids) {
				statement = con.prepareCall("{call calculate_pf(?)}");
				statement.setInt(1, i);
				rows = statement.executeUpdate();
			}
		} catch (Exception e) {
			throw new DBExceptions(ErrorMessages.INVALID_COLUMN_INDEX);
		} finally {
			con.close();
			statement.close();
		}
		return rows;
	}

	public int calculateIncrement() throws Exception {
		int rows = 0;
		GetDataUtil get = new GetDataUtil();
		Connection con = null;
		CallableStatement statement = null;
		try {
			con = Connections.connect();
			ArrayList<Integer> ids = get.getAllId();
			for (int i : ids) {
				statement = con.prepareCall("{call calculate_increment(?)}");
				statement.setInt(1, i);
				rows = statement.executeUpdate();
			}
		} catch (Exception e) {
			throw new DBExceptions(ErrorMessages.INVALID_COLUMN_INDEX);
		} finally {
			con.close();
			statement.close();
		}
		return rows;
	}

	public int calculatesalary() throws Exception {

		int rows = 0;
		GetDataUtil get = new GetDataUtil();
		Connection con = null;
		CallableStatement statement = null;
		try {
			con = Connections.connect();
			ArrayList<Integer> ids = get.getAllId();
			for (int i : ids) {
				statement = con.prepareCall("{call calculate_salary(?)}");
				statement.setInt(1, i);
				rows = statement.executeUpdate();
			}
		} catch (Exception e) {
			throw new DBExceptions(ErrorMessages.INVALID_COLUMN_INDEX);
		} finally {
			con.close();
			statement.close();
		}
		return rows;
	}

	public int markAttendance() throws Exception {
		int rows = 0;
		GetDataUtil get = new GetDataUtil();
		Connection con = null;
		CallableStatement statement = null;
		try {
			con = Connections.connect();
			ArrayList<Integer> ids = get.getAllId();
			for (int i : ids) {
				statement = con.prepareCall("{call attendance_check(?)}");
				statement.setInt(1, i);
				rows = statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DBExceptions(e.toString());
		} finally {
			con.close();
			statement.close();
		}
		return rows;
	}

	public int GeneratePaySlip() throws Exception {
		PaySlip gp = new PaySlip();
		int workDone = gp.EmployeeDetails();
		return workDone;
	}
}
