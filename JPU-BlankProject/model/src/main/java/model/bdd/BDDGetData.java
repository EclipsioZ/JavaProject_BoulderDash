package model.bdd;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BDDGetData {
	private BDDConnector bddConnector;
	CallableStatement state;

	public BDDGetData() {
		this.bddConnector = new BDDConnector();
		this.bddConnector.openBDD();
	}

	public void loadLevel(String id) { // ,Map map

		try {
			
			// Call the procedure
			CallableStatement state = bddConnector.getConnection().prepareCall("{call GET_MAP_FROM_ID(?)}");
			
			// Set argument for the procedure
			state.setString(1, id);
			
			state.execute();

			ResultSet result = state.getResultSet();

			// For each occurrence
			while (result.next()) {
				System.out.println("Loaded map:");
				System.out.println(result.getString("content"));
				System.out.println("height: " + result.getString("height"));
				System.out.println("width: " + result.getString("width"));
				System.out.println("id: " + result.getString("id"));
				
				// map.setHeight(result.getString("height"));
				// map.setWidth(result.getString("width"));
				// map.setMapFromString(result.getString("content"));
			}
			result.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
