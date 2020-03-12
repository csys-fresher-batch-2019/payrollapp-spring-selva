package com.chainsys.taskpayrollapp.dao.daoimplements;

import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.taskpayrollapp.dao.AccountantDAO;
import com.chainsys.taskpayrollapp.exception.DBException;
import com.chainsys.taskpayrollapp.util.GetDataUtil;

@Repository
public class AccountantDAOImpl implements AccountantDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * PF calculation by using two table(employee,deduction) data and update the PF
	 * column in deduction table
	 * 
	 * @throws DBException
	 * @throws SQLException
	 */
	public int calculatePF() throws DBException{

		GetDataUtil get = new GetDataUtil();

		List<Integer> ids = get.getAllId();
		for (int i : ids) {
			jdbcTemplate.update("call calculate_pf (?)", i);
		}
		return 1;
	}

	public int calculateIncrement() throws DBException {
		GetDataUtil get = new GetDataUtil();
		List<Integer> ids = get.getAllId();
		for (int i : ids) {
			jdbcTemplate.update("call calculate_increment (?)", i);
		}
		return 1;
	}

	public int calculatesalary() throws DBException {

		GetDataUtil get = new GetDataUtil();
		List<Integer> ids = get.getAllId();
		for (int i : ids) {
			jdbcTemplate.update("call calculate_salary (?)", i);
		}
		return 1;
	}

	public int markAttendance() throws DBException {
		GetDataUtil get = new GetDataUtil();

		List<Integer> ids = get.getAllId();
		for (int i : ids) {
			jdbcTemplate.update("call attendance_check (?)", i);
		}
		return 1;
	}

	public int generatePaySlip() throws DBException {
		PaySlipDAOImpl gp = new PaySlipDAOImpl();
		return gp.employeeDetails();
	}
}
