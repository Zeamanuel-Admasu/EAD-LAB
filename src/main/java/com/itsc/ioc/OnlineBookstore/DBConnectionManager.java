package com.itsc.ioc.OnlineBookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class DBConnectionManager {
	private static String url = "jdbc:mysql://localhost:3306/BookstoreDB";
	private static String user = "root";
	private static String password = "A12345678.";

	public static Connection openConnection() throws SQLException {
		try {
			// load my mysql driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("MySQL Driver not found", e);
		}
		return DriverManager.getConnection(url, user, password);
	}

//method to close connection
	public void closeConnection(Connection connection) throws SQLException {
		if (connection != null)
			connection.close();
	}
}
