package com.chainsys.taskpayrollapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainsys.taskpayrollapp.dao.daoimplements.AdminDAOImpl;
import com.chainsys.taskpayrollapp.dao.daoimplements.AccountantDAOImpl;
import com.chainsys.taskpayrollapp.dao.daoimplements.HrDAOImpl;
import com.chainsys.taskpayrollapp.dao.daoimplements.LogMonitorDAOImpl;
import com.chainsys.taskpayrollapp.exception.DBException;
import com.chainsys.taskpayrollapp.exception.ServiceException;
import com.chainsys.taskpayrollapp.dao.daoimplements.LeaveApplicationDAOImpl;
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
	@Autowired
	private AccountantDAOImpl aco;
	@Autowired
	private HrDAOImpl hdo;
	@Autowired
	private LogMonitorDAOImpl lm;
	@Autowired
	private LeaveApplicationDAOImpl leave;
	private UserDetailsValidation user = new UserDetailsValidation();

	// Admin Services
	public int addEmployeeDetails(AdminModel a) throws ServiceException {
		int rows = 0;
		try {
			if (user.emailValidation(a.getEmail()) && user.panValidation(a.getPanNumber())) {
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
			throw new ServiceException(ErrorMessages.ERROR);
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
			result = user.idValidation(id);
			if (result) {
				rows = ado.resetPassword(id);
			} else {
				return 0;
			}
		} catch (DBException e) {
			throw new ServiceException(ErrorMessages.ERROR);
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
			rows = aco.generatePaySlip();
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	// HR Services

	public int addGrade(int id, int grade) throws ServiceException {
		int rows = 0;
		boolean result = false;
		try {
			result = user.idValidation(id);
			if (result) {
				rows = hdo.addGrade(id, grade);
			} else {
				rows = 0;
			}
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	public int addBasepay(int id, int basepay) throws ServiceException {
		int rows = 0;
		boolean result = false;
		try {
			result = user.idValidation(id);
			if (result) {
				rows = hdo.addBasepay(id, basepay);
			} else {
				rows = 0;
			}
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	public int addCredit(int id, int allowance) throws ServiceException {
		int rows = 0;
		boolean result = false;
		try {
			result = user.idValidation(id);
			if (result) {
				rows = hdo.addCredit(allowance, id);
			} else {
				rows = 0;
			}
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	public List<HrModel> viewLeaveApplication() throws ServiceException {
		List<HrModel> list = new ArrayList<>();
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
		boolean result = false;
		try {
			result = user.idValidation(id);
			if (result) {
				rows = lm.swipe(id);
			} else {
				rows = 0;
			}
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}

	// Leaveform

	public int applyLeave(int id, LeaveApplicationModel leavemodel) {
		return leave.applyLeave(id, leavemodel);
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
				email = leave.getEmail(id);
				result = SendMailSSL.send("payrollmavenproject@gmail.com", "Pass1234*", email,
						"Leave Application " + status, status, id);
				if (result) {
					rows = 1;
				} 
			}
			return rows;
		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
	}

	public int rejectLeave(int id) throws ServiceException {
		int rows = 0;
		boolean result = false;
		String status = "";
		String email = "";
		try {
			status = LeaveStatus.NOT_APPROVED.toString();
			if ((leave.leaveStatusUpdate(id, status)) > 0) {
				email = leave.getEmail(id);
				result = SendMailSSL.send("payrollmavenproject@gmail.com", "Pass1234*", email,
						"Leave Application " + status, status, id);
				if (result) {
					rows = 1;
				}
			}

		} catch (DBException e) {
			throw new ServiceException(e.toString());
		}
		return rows;
	}
}
