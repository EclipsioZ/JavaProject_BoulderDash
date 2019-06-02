package model.bdd;

import org.junit.Before;
import org.junit.Test;

import model.Map;

public class BDDGetDataTest {

	private Map map;
	private BDDGetData bddGetData;

	@Before
	public void setUp() throws Exception {
		this.map = new Map();
		this.bddGetData = new BDDGetData();
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
