package com.bfs45.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static Connection con;
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/testDB";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root123";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		con =  DriverManager.getConnection(URL, USERNAME,PASSWORD);
		return con;
	}

	public static void closeConnection() throws SQLException {
		if(con != null) {
			con.close();
			con.clearWarnings();
		}
	}
}
