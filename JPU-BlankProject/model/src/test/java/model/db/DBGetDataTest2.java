package model.db;

import org.junit.Before;
import org.junit.Test;

import model.Map;

/**
 * The test class for the database
 * 
 * @author Florian Rossi
 * @author Baptiste Miquel
 *
 */
public class DBGetDataTest2 {

	private Map map;
	private DBGetData bddGetData;

	/**
	 * Setting up a new map
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.map = new Map();
		this.bddGetData = new DBGetData();
	}

	/**
	 * There is no level with id 0, it should throws an exception
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testApplyToMap() throws Exception {
		this.bddGetData.loadLevel("0", this.map);
	}
}
