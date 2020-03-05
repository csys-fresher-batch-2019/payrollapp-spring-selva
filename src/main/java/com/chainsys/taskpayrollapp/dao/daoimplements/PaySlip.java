package com.chainsys.taskpayrollapp.dao.daoimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chainsys.taskpayrollapp.exceptions.DBExceptions;
import com.chainsys.taskpayrollapp.model.PaySlipModel;
import com.chainsys.taskpayrollapp.util.Connections;
import com.chainsys.taskpayrollapp.util.GeneratePaySlip;
import com.chainsys.taskpayrollapp.util.GetDataUtil;

public class PaySlip {
	public int EmployeeDetails() throws Exception {
		GetDataUtil get = new GetDataUtil();
		GeneratePaySlip gen = new GeneratePaySlip();
		int workDone = 0;
		String sql = "select e.emp_id , emp_name , basepay , performance_grade ,"
				+ " salary_increment , allowance , leaves_taken , loss_of_pay ,"
				+ " food_deduction , cab_deduction , provident_fund ," + " salary_to_be_credited from deductions d"
				+ "  inner join employee e  on e.emp_id= ? " + " inner join credits c on c.emp_id = ? and d.emp_id = ? "
				+ "inner join final_salary s on  s.emp_id= ? ";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			ArrayList<Integer> ids = get.getAllId();
			con = Connections.connect();
			for (int id : ids) {
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				pst.setInt(2, id);
				pst.setInt(3, id);
				pst.setInt(4, id);
				rs = pst.executeQuery();
				while (rs.next()) {
					PaySlipModel pm = new PaySlipModel();
					pm.setId(rs.getInt("emp_id"));
					pm.setName(rs.getString("emp_name"));
					pm.setBasePay(rs.getInt("basepay"));
					pm.setPerformanceGrade(rs.getInt("performance_grade"));
					pm.setSalaryIncrement(rs.getInt("salary_increment"));
					pm.setAllowance(rs.getInt("allowance"));
					pm.setLeavesTaken(rs.getInt("leaves_taken"));
					pm.setLossOfPay(rs.getInt("loss_of_pay"));
					pm.setFoodDeduction(rs.getInt("food_deduction"));
					pm.setCabDeduction(rs.getInt("cab_deduction"));
					pm.setProvidentFund(rs.getInt("provident_fund"));
					pm.setSalaryToBeCredited(rs.getInt("salary_to_be_credited"));
					workDone = gen.paySlip(pm, rs.getInt("emp_id"));
				}
			}
		} catch (Exception e) {
			throw new DBExceptions(e.toString());
		} finally {
			con.close();
			pst.close();
			rs.close();
		}
		return workDone;
	}
}