package com.chainsys.taskpayrollapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.chainsys.taskpayrollapp.exception.DBException;

public class Connections {
	public static Connection connect() throws DBException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection("jdbc:oracle:thin:@ localhost:1521:XE", "system", "oracle");
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e.toString());
		} 
	}
}
