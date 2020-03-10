package com.chainsys.taskpayrollapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
	public Connections() {
		throw new IllegalStateException("Utility class");
	}

	public static Connection connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@
			// 13.235.147.120:1521:XE", "selva", "selva");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@ 13.235.147.120:1521:XE", "selva", "selva");
			// return connection;
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Unable to get connection");
		} 
	}
}
