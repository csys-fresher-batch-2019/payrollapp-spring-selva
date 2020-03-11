package com.chainsys.taskpayrollapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainsys.taskpayrollapp.dao.daoimplements.AdminDAOImpl;
import com.chainsys.taskpayrollapp.dao.daoimplements.AccountantDAOImpl;
import com.chainsys.taskpayrollapp.dao.daoimplements.HrDAOImpl;
import com.chainsys.taskpayrollapp.dao.daoimplements.LogMonitorDAOImpl;
import com.chainsys.taskpayrollapp.dao.daoimplements.LeaveApplicationDAOImpl;
import com.chainsys.taskpayrollapp.dao.*;
import com.chainsys.taskpayrollapp.exceptions.DBException;
import com.chainsys.taskpayrollapp.exceptions.ServiceException;
import com.chainsys.taskpayrollapp.model.AdminModel;
import com.chainsys.taskpayrollapp.model.HrModel;
import com.chainsys.taskpayrollapp.model.LeaveApplicationModel;
import com.chainsys.taskpayrollapp.model.LeaveApplicationModel.LeaveStatus;
import com.chainsys.taskpayrollapp.util.ErrorMessages;
import com.chainsys.taskpayrollapp.util.SendMailSSL;
import com.chainsys.taskpayrollapp.validation.UserDetailsValidation;

@Service
public class PayrollService {
	@Autowired
	private AdminDAOImpl ado;
	private AccountantDAO aco = new AccountantDAOImpl();
	@Autowired
	private HrDAOImpl hdo;
	private LogMonitorDAOImpl lm = new LogMonitorDAOImpl();
	@Autowired
	private LeaveApplicationDAOImpl leave;
	private UserDetailsValidation user = new UserDetailsValidation();;

	// Admin Services
	public int addEmployeeDetails(AdminModel a) throws ServiceException {
		int rows = 0;
		boolean result = true;
		try {
			result = user.emailValidation(a.getEmail());
			result = user.panValidation(a.getPan());
			if (result) {
				rows = ado.addUsers(a);
				return rows;
			} else {
				return 0;
			}
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
	}

	public int deleteEmployee(int id) throws ServiceException {
		int rows = 0;
		boolean result = false;
		try {
			result = user.idValidation(id);
			if (result) {
				rows = ado.removeUsers(id);
				return rows;
			} else {
				return 0;
			}
		} catch (DBException e) {
			throw new ServiceException(ErrorMessages.Error);
		}
	}

	public int calculateLOP() throws ServiceException {
		int rows = 0;
		try {
			rows = ado.calculateLOP();
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	public int unlockUserAccount(int id) throws ServiceException {
		int rows = 0;
		boolean result = false;
		try {
			if (result) {
				rows = ado.resetPassword(id);
			} else {
				return 0;
			}
			result = user.idValidation(id);
		} catch (DBException e) {
			throw new ServiceException(ErrorMessages.Error);
		}
		return rows;
	}

	public List<AdminModel> display() throws ServiceException {
		List<AdminModel> list = new ArrayList<>();
		try {
			list = ado.viewDetails();
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return list;
	}

	// Accountant Services

	public int calculatePF() throws ServiceException {
		int rows = 0;
		try {
			rows = aco.calculatePF();
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	public int calculateIncrement() throws ServiceException {
		int rows = 0;
		try {
			rows = aco.calculateIncrement();
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	public int markAttandence() throws ServiceException {
		int rows = 0;
		try {
			rows = aco.markAttendance();
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	public int calculateSalary() throws ServiceException {
		int rows = 0;
		try {
			rows = aco.calculatesalary();
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	public int generatePaySlip() throws ServiceException {
		int rows = 0;
		try {
			rows = aco.GeneratePaySlip();
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	// HR Services

	public int addGrade(int id, int grade) {
		int rows = hdo.addGrade(id, grade);
		return rows;
	}

	public int addBasepay(int id, int basepay) {
		int rows = hdo.addBasepay(id, basepay);
		return rows;
	}

	public int addCredit(int id, int allowance) {
		int rows = hdo.addCredit(allowance, id);
		return rows;
	}

	public List<HrModel> viewLeaveApplication() throws ServiceException {
		List<HrModel> list = new ArrayList<HrModel>();
		try {
			list = hdo.viewLeaveApplication();
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return list;
	}

	// Swipe

	public int swipe(int id) throws ServiceException {
		int rows = 0;
		try {
			rows = lm.swipe(id);
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	// Leaveform

	public int applyLeave(int id, LeaveApplicationModel leavemodel) {
		int rows = leave.applyLeave(id, leavemodel);
		return rows;
	}

	// Leave

	public int acceptLeave(int id) throws ServiceException {
		int rows = 0;
		boolean result = false;
		String status = "";
		String email = "";
		try {
			status = LeaveStatus.APPROVED.toString();
			if ((leave.leaveStatusUpdate(id, status)) > 0 && (leave.leaveCountUpdate(id)) > 0) {
				email = leave.sendMail(id);
				result = SendMailSSL.send("payrollmavenproject@gmail.com", "Pass1234*", email,
						"Leave Application " + status, status, id);
				if (result) {
					rows = (leave.leaveStatusUpdate(id, status));
				} else {
					rows = 0;
				}
			}

		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;

	}

	public int RejectLeave(int id) throws ServiceException {
		int rows = 0;
		boolean result = false;
		String status = "";
		String email = "";
		try {
			status = LeaveStatus.NOT_APPROVED.toString();
			if ((leave.leaveStatusUpdate(id, status)) > 0) {
				email = leave.sendMail(id);
				result = SendMailSSL.send("payrollmavenproject@gmail.com", "Pass1234*", email,
						"Leave Application " + status, status, id);
				if (result) {
					rows = (leave.leaveStatusUpdate(id, status));
				} else {
					rows = 0;
				}
			}

		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}
}
