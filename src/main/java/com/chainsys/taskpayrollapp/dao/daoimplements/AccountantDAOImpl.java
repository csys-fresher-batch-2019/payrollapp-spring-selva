package com.chainsys.taskpayrollapp.dao.daoimplements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.chainsys.taskpayrollapp.dao.AccountantDAO;
import com.chainsys.taskpayrollapp.exceptions.DBException;
import com.chainsys.taskpayrollapp.util.Connections;
import com.chainsys.taskpayrollapp.util.ErrorMessages;
import com.chainsys.taskpayrollapp.util.GetDataUtil;

public class AccountantDAOImpl implements AccountantDAO {
	/**
	 * PF calculation by using two table(employee,deduction) data and update the PF
	 * column in deduction table
	 * 
	 * @throws DBException
	 * @throws SQLException
	 */
	public int calculatePF() throws DBException {

		int rows = 0;
		GetDataUtil get = new GetDataUtil();
		Connection con = null;
		CallableStatement statement = null;
		try {
			con = Connections.connect();
			List<Integer> ids = get.getAllId();
			for (int i : ids) {
				statement = con.prepareCall("{call calculate_pf(?)}");
				statement.setInt(1, i);
				rows = statement.executeUpdate();
			}
		} catch (Exception e) {
			throw new DBException(ErrorMessages.INVALID_COLUMN_INDEX);
		} finally {

			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	public int calculateIncrement() throws DBException {
		int rows = 0;
		GetDataUtil get = new GetDataUtil();
		Connection con = null;
		CallableStatement statement = null;
		try {
			con = Connections.connect();
			List<Integer> ids = get.getAllId();
			for (int i : ids) {
				statement = con.prepareCall("{call calculate_increment(?)}");
				statement.setInt(1, i);
				rows = statement.executeUpdate();
			}
		} catch (Exception e) {
			throw new DBException(ErrorMessages.INVALID_COLUMN_INDEX);
		} finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	public int calculatesalary() throws DBException {

		int rows = 0;
		GetDataUtil get = new GetDataUtil();
		Connection con = null;
		CallableStatement statement = null;
		try {
			con = Connections.connect();
			List<Integer> ids = get.getAllId();
			for (int i : ids) {
				statement = con.prepareCall("{call calculate_salary(?)}");
				statement.setInt(1, i);
				rows = statement.executeUpdate();
			}
		} catch (Exception e) {
			throw new DBException(ErrorMessages.INVALID_COLUMN_INDEX);
		} finally {

			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	public int markAttendance() throws DBException {
		int rows = 0;
		GetDataUtil get = new GetDataUtil();
		Connection con = null;
		CallableStatement statement = null;
		try {
			con = Connections.connect();
			List<Integer> ids = get.getAllId();
			for (int i : ids) {
				statement = con.prepareCall("{call attendance_check(?)}");
				statement.setInt(1, i);
				rows = statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DBException(e.toString());
		} finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	public int GeneratePaySlip() throws DBException {
		PaySlipDAOImpl gp = new PaySlipDAOImpl();
		int workDone = gp.EmployeeDetails();
		return workDone;
	}
}
