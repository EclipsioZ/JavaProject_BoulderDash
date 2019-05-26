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
				System.out.println("endBlockX: " + result.getString("endBlock").split(";")[0]);
				System.out.println("endBlockY: " + result.getString("endBlock").split(";")[1]);
				
				String endBlockX = result.getString("endBlock").split(";")[0];
				String endBlockY = result.getString("endBlock").split(";")[1];
				
				
				// map.setHeight(result.getString("height"));
				// map.setWidth(result.getString("width"));
				// map.setMapFromString(result.getString("content"));
				// map.setPosEndBlock(new int[Integer.parseInt(endBlockX)][Integer.parseInt(endBlockY)]);
			}
			result.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
