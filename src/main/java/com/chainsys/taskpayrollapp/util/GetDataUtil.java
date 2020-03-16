package com.chainsys.taskpayrollapp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chainsys.taskpayrollapp.exception.DBException;

public class GetDataUtil {
	public static final String EMPID = "emp_id";
	public static final String EMAILID = "email";
	public static final String PANNUMBER = "pan_number";
	private static final Logger logger = LoggerFactory.getLogger(GetDataUtil.class);

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
				emails.add(result.getString(EMAILID));
			}
		} catch (SQLException e) {
			throw new DBException(ErrorMessages.ERROR, e);
		} finally {
			try {
				if (result != null) {
					result.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				logger.error("Error in Get all mail", e);
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
				panNumber.add(result.getString(PANNUMBER));
			}
		} catch (SQLException e) {
			throw new DBException(ErrorMessages.ERROR, e);
		} finally {
			try {
				if (result != null) {
					result.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				logger.error("Error", e);
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
				ids.add(result.getInt(EMPID));
			}
		} catch (SQLException e) {
			throw new DBException(ErrorMessages.ERROR, e);
		} finally {
			try {
				if (result != null) {
					result.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				logger.error("Error", e);
			}

		}
		return ids;
	}

}
