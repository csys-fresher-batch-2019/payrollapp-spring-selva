package com.chainsys.taskpayrollapp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.taskpayrollapp.exceptions.DBException;

public class GetDataUtil {
	public static final String empId = "emp_id";
	public static final String emailid = "email";
	public static final String pan_number = "pan_number";

	public List<String> getAllEmail() throws DBException {
		String sql = "select email from employee";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		List<String> emails = new ArrayList<>();
		try {
			con = Connections.connect();
			pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			while (result.next()) {
				emails.add(result.getString(emailid));
			}
		} catch (SQLException e) {
			throw new DBException(ErrorMessages.Error);
		} finally {
			try {
				result.close();
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emails;
	}

	public List<String> getAllPan() throws DBException {
		String sql = "select pan_number from employee";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		List<String> panNumber = new ArrayList<>();
		try {
			con = Connections.connect();
			pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			while (result.next()) {
				panNumber.add(result.getString(pan_number));
			}
		} catch (SQLException e) {
			throw new DBException(ErrorMessages.Error);
		} finally {
			try {
				result.close();
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return panNumber;
	}

	public List<Integer> getAllId() throws DBException {
		String sql = "select emp_id from employee";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		List<Integer> ids = new ArrayList<>();
		try {
			con = Connections.connect();
			pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			while (result.next()) {
				ids.add(result.getInt(empId));
			}
		} catch (SQLException e) {
			throw new DBException(ErrorMessages.Error);
		} finally {
			try {
				result.close();
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return ids;
	}

}
