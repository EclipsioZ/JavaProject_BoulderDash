package model.bdd;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Map;

public class BDDGetData {
	private BDDConnector bddConnector;
	CallableStatement state;

	public BDDGetData() {
		this.bddConnector = new BDDConnector();
		this.bddConnector.openBDD();
	}

	public void loadLevel(String id, Map map) {

		try {
			
			// Call the procedure
			CallableStatement state = bddConnector.getConnection().prepareCall("{call GET_MAP_FROM_ID(?)}");
			
			// Set argument for the procedure
			state.setString(1, id);
			
			state.execute();

			ResultSet result = state.getResultSet();

			// For each occurrence
			while (result.next()) {
				
				String endBlockX = result.getString("endBlock").split(";")[0];
				String endBlockY = result.getString("endBlock").split(";")[1];
				
				map.setHeight(Integer.parseInt(result.getString("height")));
				map.setWidth(Integer.parseInt(result.getString("width")));
				map.setMapFromString(result.getString("content"));
				int[] posEndBlock = new int[2];
				posEndBlock[0] = Integer.parseInt(endBlockX);
				posEndBlock[1] = Integer.parseInt(endBlockY);
				map.setPosEndBlock(posEndBlock);
				map.setRequiredDiamonds(Integer.parseInt(result.getString("requiredDiamonds")));
				map.levelId = id;
			}
			result.close();
			state.close();
			
			if(map.getMap() == null) {
				try {
					throw new Exception("Incorrect map id: " + id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getMapNameFromId(int idInt) {
		String mapName = "-- No map found --";
		try {
			String id = Integer.toString(idInt);
			
			// Call the procedure
			CallableStatement state = bddConnector.getConnection().prepareCall("{call GET_MAP_FROM_ID(?)}");
			
			// Set argument for the procedure
			state.setString(1, id);
			state.execute();

			ResultSet result = state.getResultSet();
			
			// For each occurrence
			while (result.next()) {
				mapName = result.getString("name");
			}
			result.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapName;
	}

}
