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
import com.chainsys.taskpayrollapp.model.LeaveApplicationModel;
import com.chainsys.taskpayrollapp.util.Connections;
import com.chainsys.taskpayrollapp.util.ErrorMessages;

@Repository

public class LeaveApplicationDAOImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(LeaveApplicationDAOImpl.class);

	public int applyLeave(int empId, LeaveApplicationModel l) {
		String sql = "insert into leave_info(emp_id,from_leave_date,to_leave_date,reason)values(?,to_date(?,'MM/dd/yyyy'),to_date(?,'MM/dd/yyyy'),?)";
		int rows = 0;
		Object[] params = { empId, l.getFromDate(), l.getToDate(), l.getReasonForLeave() };
		rows = jdbcTemplate.update(sql, params);

		return rows;
	}

	public int leaveStatusUpdate(int eid, String status) {
		String sql = "update leave_info set status = ? where emp_id = ? and status = 'PENDING'";
		int rows = jdbcTemplate.update(sql, status, eid);
		return rows;
	}

	public int leaveCountUpdate(int eid) {
		int rows = 0;
		String query = "update employee set leaves_taken = leaves_taken+1,total_leaves = total_leaves-1 where emp_id = ?";
		rows = jdbcTemplate.update(query, eid);
		return rows;
	}

	public String getEmail(int eid) throws DBException {
		String sql1 = "select email from employee where emp_id = ?";
		String email = "";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = Connections.connect();
			pst = con.prepareStatement(sql1);
			pst.setInt(1, eid);
			rs = pst.executeQuery();
			if (rs.next()) {
				email = rs.getString("email");
			}
		} catch (SQLException e) {
			throw new DBException(ErrorMessages.ERROR);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				logger.error("Error in selecting Mail", e);
			}
		}
		return email;
	}

}
