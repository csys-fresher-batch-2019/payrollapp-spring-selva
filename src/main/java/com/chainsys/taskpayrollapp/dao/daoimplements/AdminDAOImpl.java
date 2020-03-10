package com.chainsys.taskpayrollapp.dao.daoimplements;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.taskpayrollapp.model.AdminModel;
import com.chainsys.taskpayrollapp.dao.AdminDAO;
import com.chainsys.taskpayrollapp.exceptions.DBException;
import com.chainsys.taskpayrollapp.model.AdminModel.FoodandCab;
import com.chainsys.taskpayrollapp.util.Connections;
import com.chainsys.taskpayrollapp.util.ErrorMessages;
import com.chainsys.taskpayrollapp.util.GetDataUtil;

@Repository
public class AdminDAOImpl implements AdminDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int addUsers(AdminModel a) throws DBException {
		String sql = "insert into employee(emp_id,emp_name,designation,"
				+ " email,leaves_taken,basepay,total_leaves,food_subscription,cab_subscription,pan_number)"
				+ "values(emp_id_seq.nextval,?,?,?,0,0,12,?,?,?)";
		int id = 0;
		Object[] params = { a.getEmpName(), a.getDesignation(), a.getEmail(), a.getFoodFacility(), a.getCabFacility(),
				a.getPan() };
		// JdbcUtil.executeUpdate(sql,params);
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
		try {
			Connection con = Connections.connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				id = rs.getInt("current_id");
				System.out.println(id);
			}
		} catch (SQLException e) {
			throw new DBException(ErrorMessages.Error);
		}
		return id;
	}

	public void insertSalaryDetails(int id) throws DBException {
		String sql = "insert into final_salary(emp_id)values(?)";
		jdbcTemplate.update(sql, id);
	}

	public void insertBioDetails(int id) throws DBException {
		String sql = "insert into biometrices(emp_id,swipe_count)values(?,0)";
		jdbcTemplate.update(sql, id);
	}

	public void insertCreditDetails(int id) throws DBException {
		String sql = "insert into credits(emp_id)values(?)";
		jdbcTemplate.update(sql, id);
	}

	public void insertLoginDetails(int id, String a) throws DBException {
		String sql = "insert into user_login(emp_id,passwd,designation)values(?,'pass123',?)";
		jdbcTemplate.update(sql, id, a);
	}

	public int insertDeductionDetails(int id, AdminModel a) {
		String sql = "insert into deductions(emp_id,food_deduction,cab_deduction,loss_of_pay,provident_fund)values(?,?,?,0,0)";
		int foodDeduction = 0;
		int cabDeduction = 0;
		int rows = 0;
		if (a.getFoodFacility().contentEquals("Y") || a.getFoodFacility().contentEquals("y")) {
			a.setFoodFacility(FoodandCab.Y.toString());
			foodDeduction = 500;
		} else
			a.setFoodFacility(FoodandCab.N.toString());
		if (a.getCabFacility().contentEquals("Y") || a.getCabFacility().contentEquals("y")) {
			a.setCabFacility(FoodandCab.Y.toString());
			cabDeduction = 2000;
		} else
			a.setCabFacility(FoodandCab.N.toString());
		try {
			rows = jdbcTemplate.update(sql, id, foodDeduction, cabDeduction);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return rows;
	}

	public int removeUsers(int empId) throws DBException {
		Connection con = null;
		CallableStatement statement = null;
		int rows = 0;
		try {
			con = Connections.connect();
			statement = con.prepareCall("{call delete_employee(?)}");
			statement.setInt(1, empId);
			rows = statement.executeUpdate();
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

	public int calculateLOP() throws DBException {
		int rows = 0;
		GetDataUtil get = new GetDataUtil();
		Connection con = null;
		CallableStatement statement = null;
		try {
			con = Connections.connect();
			List<Integer> ids = get.getAllId();
			for (int i : ids) {
				statement = con.prepareCall("{call calculate_lop(?)}");
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

	public int resetPassword(int empId) throws DBException {
		String sql = "update user_login set passwd = 'pass123',active = 0 where emp_id = ?";
		int rows = jdbcTemplate.update(sql, empId);
		return rows;
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
				ad.setPan(rs.getString("pan_number"));
				ad.setDesignation(rs.getString("designation"));
				list.add(ad);
			}
		} catch (Exception e) {
			throw new DBException(e.toString());
		} finally {
			try {
				rs.close();
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
