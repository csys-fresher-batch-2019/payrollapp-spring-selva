package com.chainsys.taskpayrollapp.dao.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.taskpayrollapp.dao.HrDAO;
import com.chainsys.taskpayrollapp.exception.DBException;
import com.chainsys.taskpayrollapp.model.HrModel;
import com.chainsys.taskpayrollapp.util.Connections;

@Repository

public class HrDAOImpl implements HrDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(HrDAOImpl.class);

	public int addGrade(int id, int grade) {
		String sql = "update employee set performance_grade = ? where emp_id = ?";
		return jdbcTemplate.update(sql, grade, id);
	}

	public int addBasepay(int id, int basepay) {
		int rows = 0;
		String sql = "update employee set basepay = ? where emp_id = ?";
		rows = jdbcTemplate.update(sql, basepay, id);
		return rows;
	}

	public int addCredit(int allowance, int id) {
		int rows = 0;
		String sql = "update credits set allowance = ? where emp_id = ?";
		rows = jdbcTemplate.update(sql, allowance, id);
		return rows;
	}

	public List<HrModel> viewLeaveApplication() throws DBException {
		String sql = "select li.emp_id,from_leave_date,to_leave_date,reason from leave_info li "
				+ " inner join employee e on e.emp_id=li.emp_id and e.designation !='HR'and li.status='PENDING' ";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = Connections.connect();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			List<HrModel> leaveDetails = new ArrayList<>();
			while (rs.next()) {
				HrModel h = new HrModel();
				h.setEmpId(rs.getInt("emp_id"));
				h.setFromDate(rs.getString("from_leave_date"));
				h.setToDate(rs.getString("to_leave_date"));
				h.setReasonForLeave(rs.getString("reason"));
				leaveDetails.add(h);
			}
			return leaveDetails;
		} catch (SQLException e) {
			throw new DBException(e.toString());
		} finally {
			try {
				if (rs != null) {
					pst.close();
					rs.close();
					con.close();
				}
			} catch (SQLException e) {
				logger.error("Error in View leave application", e);
			}

		}
	}
}