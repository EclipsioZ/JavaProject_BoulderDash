package model.db;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Map;

/**
 * The Class DBGetData
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public class DBGetData {

	private DBConnector dbConnector;
	CallableStatement state;

	/**
	 * Instantiates a new BDDGetData
	 */
	public DBGetData() {
		this.dbConnector = new DBConnector();
		this.dbConnector.openDB();
	}

	/**
	 * Load a level from database
	 * 
	 * @param id  The id of the level
	 * @param map The map that will load the content of the database
	 * @throws Exception 
	 */
	public void loadLevel(String id, Map map) throws Exception {
		try {
			/** Call the procedure */
			CallableStatement state = dbConnector.getConnection().prepareCall("{call GET_MAP_FROM_ID(?)}");

			/** Set argument for the procedure */
			state.setString(1, id);
			state.execute();

			ResultSet result = state.getResultSet();

			// For each occurrence
			while (result.next()) {
				this.applyToMap(result, map, id);
			}
			result.close();
			state.close();

			if (map.getMap() == null) {
				throw new Exception("Incorrect map id: " + id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Apply a resultset from database to the map
	 * 
	 * @param result The resultset
	 * @param map    The map
	 * @param id     The map id
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void applyToMap(ResultSet result, Map map, String id) throws NumberFormatException, SQLException {
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

	/**
	 * Get a map name from given id
	 * 
	 * @param idInt The id
	 * @return The map name
	 */
	public String getMapNameFromId(int idInt) {
		String mapName = "-- No map found --";
		try {
			String id = Integer.toString(idInt);

			CallableStatement state = dbConnector.getConnection().prepareCall("{call GET_MAP_FROM_ID(?)}");

			state.setString(1, id);
			state.execute();

			ResultSet result = state.getResultSet();

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
