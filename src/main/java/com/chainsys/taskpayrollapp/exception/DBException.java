package com.chainsys.taskpayrollapp.exception;

public class DBException extends Exception {
	private static final long serialVersionUID = 1L;

	public DBException(String msg, Exception e) {
		super(msg, e);
	}
}
