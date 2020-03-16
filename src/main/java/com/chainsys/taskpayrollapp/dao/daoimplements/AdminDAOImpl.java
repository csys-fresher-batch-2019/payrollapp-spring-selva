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

import com.chainsys.taskpayrollapp.dao.AdminDAO;
import com.chainsys.taskpayrollapp.exception.DBException;
import com.chainsys.taskpayrollapp.model.AdminModel;
import com.chainsys.taskpayrollapp.util.Connections;
import com.chainsys.taskpayrollapp.util.ErrorMessages;
import com.chainsys.taskpayrollapp.util.GetDataUtil;

@Repository
public class AdminDAOImpl implements AdminDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);

	@Override
	public int addUsers(AdminModel a) throws DBException {
		String sql = "insert into employee(emp_id,emp_name,designation,"
				+ " email,food_subscription,cab_subscription,pan_number)" + "values(emp_id_seq.nextval,?,?,?,?,?,?)";
		int id = 0;
		Object[] params = { a.getEmpName(), a.getDesignation(), a.getEmail(), a.getFoodSubscription(),
				a.getCabSubscription(), a.getPanNumber() };
		jdbcTemplate.update(sql, params);
		id = selectId();
		insertDeductionDetails(id, a);
		insertLoginDetails(id, a.getDesignation());
		insertCreditDetails(id);
		insertBioDetails(id);
		insertSalaryDetails(id);

		return id;
	}

	public int selectId() throws DBException {
		String sql = "select max(emp_id) as current_id from employee";
		int id = 0;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = Connections.connect();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				id = rs.getInt("current_id");
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
				logger.debug("error in Seleting id", e);
			}
		}
		return id;
	}

	public void insertSalaryDetails(int id) {
		String sql = "insert into final_salary(emp_id)values(?)";
		jdbcTemplate.update(sql, id);
	}

	public void insertBioDetails(int id) {
		String sql = "insert into biometrices(emp_id)values(?)";
		jdbcTemplate.update(sql, id);
	}

	public void insertCreditDetails(int id) {
		String sql = "insert into credits(emp_id)values(?)";
		jdbcTemplate.update(sql, id);
	}

	public void insertLoginDetails(int id, String a) {
		String sql = "insert into user_login(emp_id,designation)values(?,?)";
		jdbcTemplate.update(sql, id, a);
	}

	public int insertDeductionDetails(int id, AdminModel a) {
		String sql = "insert into deductions(emp_id,food_deduction,cab_deduction,loss_of_pay,provident_fund)values(?,?,?,0,0)";
		int foodDeduction = 0;
		int cabDeduction = 0;
		int rows = 0;
		if (a.getFoodSubscription().contentEquals("Y")) {
			foodDeduction = 500;
		} else if (a.getCabSubscription().contentEquals("Y")) {
			cabDeduction = 2000;
		}
		rows = jdbcTemplate.update(sql, id, foodDeduction, cabDeduction);
		return rows;
	}

	@Override
	public int removeUsers(int empId) throws DBException {

		jdbcTemplate.update("call delete_employee (?)", empId);
		return 1;
	}

	@Override
	public int calculateLOP() throws DBException {
		GetDataUtil get = new GetDataUtil();
		List<Integer> ids = get.getAllId();
		for (int i : ids) {
			jdbcTemplate.update("call calculate_lop (?)", i);
		}
		return 1;
	}

	@Override
	public int resetPassword(int empId) throws DBException {
		String sql = "update user_login set password = 'pass123',active = 0 where emp_id = ?";
		return jdbcTemplate.update(sql, empId);
	}

	@Override
	public List<AdminModel> viewDetails() throws DBException {
		Connection con = null;
		List<AdminModel> list = new ArrayList<>();
		String sql = "select emp_id,emp_name,email,pan_number,designation from employee";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = Connections.connect();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				AdminModel ad = new AdminModel();
				ad.setEmpId(rs.getInt("emp_id"));
				ad.setEmpName(rs.getString("emp_name"));
				ad.setEmail(rs.getString("email"));
				ad.setPanNumber(rs.getString("pan_number"));
				ad.setDesignation(rs.getString("designation"));
				list.add(ad);
			}
		} catch (Exception e) {
			throw new DBException(e.toString());
		} finally {
			try {
				if (rs != null) {
					rs.close();
					pst.close();
					con.close();
				}
			} catch (SQLException e) {
				logger.error("error in View Details", e);
			}
		}
		return list;
	}
}
