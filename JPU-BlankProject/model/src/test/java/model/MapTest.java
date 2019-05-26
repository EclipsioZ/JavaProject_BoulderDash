package model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class MapTest {

	@Test
	public void testGetMap() {
		Map map = new Map();
		map.setHeight(5);
		map.setWidth(3);
		String content = "00000\r\n00000\r\n00000";
		map.setMapFromString(content);
		
		// Check if the map has the correct size with the given content
        Assert.assertEquals(5, map.getMap().length);
        for (int i = 0; i < map.getMap().length; i++) {
            Assert.assertEquals(3, map.getMap()[i].length);
		}
        
	}

}
