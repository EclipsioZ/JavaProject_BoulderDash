package model.bdd;

import java.sql.*;

public class BDDConnector {

	private static String BDDNAME = "boulderdash";
	private static String URL = "jdbc:mysql://localhost/" + BDDNAME
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
	private static String USER = "root";
	private static String PASSWORD = "";
	
	static BDDConnector INSTANCE;
	private Connection connection;

	// Singleton BDDConnector
	public BDDConnector getInstance() {
		if (INSTANCE != null) {
			INSTANCE = new BDDConnector();
		}
		return INSTANCE;
	}

	public void openBDD() {
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Successfully connected to database " + BDDNAME);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

}
