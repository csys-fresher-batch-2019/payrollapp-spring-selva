package com.chainsys.taskpayrollapp.dao.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.taskpayrollapp.exceptions.DBException;
import com.chainsys.taskpayrollapp.model.LeaveApplicationModel;
import com.chainsys.taskpayrollapp.model.LeaveApplicationModel.LeaveStatus;
import com.chainsys.taskpayrollapp.util.Connections;
import com.chainsys.taskpayrollapp.util.ErrorMessages;
import com.chainsys.taskpayrollapp.util.SendMailSSL;

@Repository

public class LeaveApplicationDAOImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int applyLeave(int empId, LeaveApplicationModel l) {
		String sql = "insert into leave_info(emp_id,from_leave_date,to_leave_date,reason)values(?,to_date(?,'MM/dd/yyyy'),to_date(?,'MM/dd/yyyy'),?)";
		int rows = 0;
		Object[] params = { empId, l.getFromDate(), l.getToDate(), l.getReasonForLeave() };
		rows = jdbcTemplate.update(sql, params);

		return rows;
	}

	public int leaveStatusUpdate(int eid, int option) {
		String sql = "update leave_info set status = ? where emp_id = ? and status = 'PENDING'";
		int rows = 0;
		String status = "";
		if (option == 1) {
			status = LeaveStatus.APPROVED.toString();
		} else {
			status = LeaveStatus.NOT_APPROVED.toString();
		}
		rows = jdbcTemplate.update(sql, status, eid);
		return rows;
	}

	public int leaveCountUpdate(int eid) {
		int rows = 0;
		String query = "update employee set leaves_taken = leaves_taken+1,total_leaves = total_leaves-1 where emp_id = ?";
		rows = jdbcTemplate.update(query, eid);
		return rows;
	}

	public boolean sendMail(int eid, String status) throws DBException {
		String sql1 = "select email from employee where emp_id = ?";
		boolean mailStatus = false;
		String email = "";
		try {
			Connection con = Connections.connect();
			PreparedStatement pst = con.prepareStatement(sql1);
			pst.setInt(1, eid);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				email = rs.getString("email");
				SendMailSSL.send("payrollmavenproject@gmail.com", "Pass1234*", email, "Leave Application " + status,
						status, eid);
				mailStatus = true;
			}
		} catch (SQLException e) {
			throw new DBException(ErrorMessages.Error);
		}
		return mailStatus;
	}

}
