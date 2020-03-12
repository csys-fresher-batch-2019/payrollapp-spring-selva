package com.chainsys.taskpayrollapp.dao.daoimplements;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class LogMonitorDAOImpl {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public int swipe(int empId) {
			jdbcTemplate.update("call bio_entry (?)", empId);
		return 1;
	}
}
