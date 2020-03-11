package com.chainsys.taskpayrollapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.chainsys.taskpayrollapp.exceptions.DBException;

public class Connections {
	
	public static Connection connect() throws DBException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@
			// 13.235.147.120:1521:XE", "selva", "selva");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@ localhost:1521:XE", "system", "oracle");
			// return connection;
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(e.toString());
		} 
	}
}
