package com.chainsys.taskpayrollapp.dao.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.taskpayrollapp.dao.HrDAO;
import com.chainsys.taskpayrollapp.exceptions.DBExceptions;
import com.chainsys.taskpayrollapp.model.HrModel;
import com.chainsys.taskpayrollapp.util.Connections;
@Repository

public class HrDAOImpl implements HrDAO
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public int addGrade(int id,int grade)
	{
		String sql = "update employee set performance_grade = ? where emp_id = ?";
		int rows = jdbcTemplate.update(sql,grade,id);
		return rows;
	}	
	public int addBasepay(int id,int basepay)
	{
		int rows = 0;
		String sql = "update employee set basepay = ? where emp_id = ?";
		rows = jdbcTemplate.update(sql,basepay,id);
		return rows;	
	}
	public int addCredit(int allowance,int id)
	{
		int rows = 0;
		String sql = "update credits set allowance = ? where emp_id = ?";
		rows = jdbcTemplate.update(sql, allowance,id);
		return rows;
	}
	public ArrayList<HrModel> viewLeaveApplication() throws Exception 
	{
		String sql = "select li.emp_id,from_leave_date,to_leave_date,reason from leave_info li " + 
				" inner join employee e on e.emp_id=li.emp_id and e.designation !='HR'and li.status='PENDING' ";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			con =  Connections.connect();
			pst = con.prepareStatement(sql);
			rs =  pst.executeQuery();
			ArrayList<HrModel> leaveDetails = new ArrayList<>();
			while(rs.next())
			{
				HrModel h = new HrModel();
				h.setId(rs.getInt("emp_id"));
				h.setFromDate(rs.getString("from_leave_date"));
				h.setToDate(rs.getString("to_leave_date"));
				h.setReason(rs.getString("reason"));
				leaveDetails.add(h);
			}
			return leaveDetails;
		}
		catch(SQLException e)
		{
			throw new DBExceptions(e.toString());
		}
		finally
		{
			con.close();
			pst.close();
			rs.close();
			
		}
	}
}