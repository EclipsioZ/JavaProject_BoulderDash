package model.db;

import java.sql.*;

/**
 * The Class DBConnector
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public class DBConnector {

	/**
	 * Database configuration
	 */
	private static String BDDNAME = "boulderdash";
	private static String URL = "jdbc:mysql://localhost/" + BDDNAME
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
	private static String USER = "root";
	private static String PASSWORD = "";

	/** The unique instance of BDDConnector */
	static DBConnector INSTANCE;

	private Connection connection;

	/**
	 * Singleton DBConnector
	 * 
	 * @return The instance of the BDDConnector
	 */
	public DBConnector getInstance() {
		if (INSTANCE != null) {
			INSTANCE = new DBConnector();
		}
		return INSTANCE;
	}

	/**
	 * Open database with given parameters
	 */
	public void openDB() {
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Successfully connected to database " + BDDNAME);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the connection
	 * 
	 * @return The connection to the database
	 */
	public Connection getConnection() {
		return connection;
	}

}
