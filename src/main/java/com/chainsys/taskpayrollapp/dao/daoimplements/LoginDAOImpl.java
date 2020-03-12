package com.chainsys.taskpayrollapp.dao.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.taskpayrollapp.exception.DBException;
import com.chainsys.taskpayrollapp.util.Connections;
@Repository
public class LoginDAOImpl {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(LoginDAOImpl.class);
	public String login(int empId, String password) throws DBException {
		String designation = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from user_login where emp_id = ?";
		try {
			con = Connections.connect();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, empId);
			rs = stmt.executeQuery();
			String res = "activate";
			String res1 = "wrong password";
			String res2 = "Not a user";
			if (rs.next()) {
				String dbPassword = rs.getString("password");
				int active = rs.getInt("active");
				designation = rs.getString("designation").toUpperCase();
				if (password.equals(dbPassword)) {
					if (active == 1) {
						return designation;
					} else {
						return res; // if user not in active state
					}
				} else {
					return res1;// if password not matched
				}
			} else {
				return res2;// if user not found
			}
		} catch (SQLException e) {
			throw new DBException(e.toString());
		} finally {
			try {
				if (rs != null) {
					rs.close();
					stmt.close();
					con.close();
				}
			} catch (SQLException e) {
				logger.error("Error in Login",e);
			}
		}
	}

	public int updatePassword(String newPassword, String confirmNewPassword, int empId) {
		String sql = "update user_login set password = ?,active = 1 where emp_id = ?";
		int rows = 0;
		if (newPassword.equals(confirmNewPassword)) {
			rows = jdbcTemplate.update(sql, newPassword, empId);
		}
		return rows;
	}

}
