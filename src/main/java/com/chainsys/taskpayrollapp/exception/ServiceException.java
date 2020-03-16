package com.chainsys.taskpayrollapp.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceException(String msg, DBException e) {
		super(msg, e);
	}
}
