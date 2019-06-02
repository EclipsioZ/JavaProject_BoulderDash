package model;

import org.junit.Assert;
import org.junit.Test;

/**
 * The test class for the map
 * 
 * @author Florian Rossi
 * @author Baptiste Miquel
 *
 */
public class MapTest {

	/**
	 * Check if the map has the correct size with the given content
	 */
	@Test
	public void testGetMap() {
		Map map = new Map();
		map.setHeight(3);
		map.setWidth(5);
		String content = "00000\r\n00000\r\n00000";
		map.setMapFromString(content);
		
        Assert.assertEquals(5, map.getMap().length);
        for (int i = 0; i < map.getMap().length; i++) {
            Assert.assertEquals(3, map.getMap()[i].length);
		}
	}
	
	/**
	 * Check the minimum limit of diamonds required
	 */
	@Test
	public void testRequiredDiamondsMin() {
		Map map = new Map();
		map.setRequiredDiamonds(-1);
        Assert.assertEquals(0, map.getRequiredDiamonds());
		map.setRequiredDiamonds(-9999);
        Assert.assertEquals(0, map.getRequiredDiamonds());
	}
	
	/**
	 * Check the maximum limit of diamonds required
	 */
	@Test
	public void testRequiredDiamondsMax() {
		Map map = new Map();
		map.setRequiredDiamonds(101);
        Assert.assertEquals(100, map.getRequiredDiamonds());
		map.setRequiredDiamonds(9999);
        Assert.assertEquals(100, map.getRequiredDiamonds());
	}

}
