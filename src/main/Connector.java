package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	static String host = "localhost";
	static String bd = "animearena";
	static final String username = "root";
	static final String pass = "admin";

	public static String drv = "com.mysql.cj.jdbc.Driver";
	public static String jdbcUrl = "jdbc:mysql://" + host + "/" + bd
			+ "?xdevapi.connect-timeout=0&allowMultiQueries=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Lisbon";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, username, pass);
	}

	public static String hashFromPass(String pass) {
		return String.format("%10d", pass.hashCode()).replace(' ', '0');
	}

}
