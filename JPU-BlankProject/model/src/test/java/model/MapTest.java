package model;

import org.junit.Assert;
import org.junit.Test;

public class MapTest {

	@Test
	public void testGetMap() {
		Map map = new Map();
		map.setHeight(3);
		map.setWidth(5);
		String content = "00000\r\n00000\r\n00000";
		map.setMapFromString(content);
		
		// Check if the map has the correct size with the given content
        Assert.assertEquals(5, map.getMap().length);
        for (int i = 0; i < map.getMap().length; i++) {
            Assert.assertEquals(3, map.getMap()[i].length);
		}
	}
	
	@Test
	public void testRequiredDiamondsMin() {
		Map map = new Map();
		map.setRequiredDiamonds(-1);
        Assert.assertEquals(0, map.getRequiredDiamonds());
		map.setRequiredDiamonds(-9999);
        Assert.assertEquals(0, map.getRequiredDiamonds());
	}
	
	@Test
	public void testRequiredDiamondsMax() {
		Map map = new Map();
		map.setRequiredDiamonds(101);
        Assert.assertEquals(100, map.getRequiredDiamonds());
		map.setRequiredDiamonds(9999);
        Assert.assertEquals(100, map.getRequiredDiamonds());
	}

}
