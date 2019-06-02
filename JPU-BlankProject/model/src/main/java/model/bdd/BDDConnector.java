package model.bdd;

import java.sql.*;

/**
 * The Class BDDConnector
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public class BDDConnector {

	/**
	 * Database configuration
	 */
	private static String BDDNAME = "boulderdash";
	private static String URL = "jdbc:mysql://localhost/" + BDDNAME
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
	private static String USER = "root";
	private static String PASSWORD = "";

	/** The unique instance of BDDConnector */
	static BDDConnector INSTANCE;

	private Connection connection;

	/**
	 * Singleton BDDConnector
	 * 
	 * @return The instance of the BDDConnector
	 */
	public BDDConnector getInstance() {
		if (INSTANCE != null) {
			INSTANCE = new BDDConnector();
		}
		return INSTANCE;
	}

	/**
	 * Open database with given parameters
	 */
	public void openBDD() {
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
