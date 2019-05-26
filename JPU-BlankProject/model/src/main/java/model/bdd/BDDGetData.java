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
	
	public void getLevel(String id) {
		
	    try {
			CallableStatement state = bddConnector.getConnection().prepareCall("{call GET_MAP_FROM_ID(?)}");
			state.setString(1, id);
			state.execute();
			
			ResultSet result = state.getResultSet();
			while(result.next()) {
				System.out.println("Loaded map:");
				System.out.println(result.getString("content"));
			}
			result.close();
			state.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
