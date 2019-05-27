package model.elements;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import model.Map;

public class PlayerTest {

	// These tests will check every possile collisions for the player and his ability to move
	@Test
	public void testCanMove() {
		Map map = new Map();
		map.setHeight(3);
		map.setWidth(4);
		String content = "0300\r\n0140\r\n0500";
		
		/* Map:
		 * 
		 * 0300
		 * 0140
		 * 0500
		 * 
		 */
		
		// Player is surrounded by:
		// Left: air (can move)
		// Up: Dirt (can move)
		// Right: Rock with air behind (can move)
		// Down: Diamond (can move)
		
		map.setMapFromString(content);
		Player player = map.getPlayer();

		Assert.assertEquals(true, player.canMove(player.getX() - 1, player.getY()));
		Assert.assertEquals(true, player.canMove(player.getX() + 1, player.getY()));
		Assert.assertEquals(true, player.canMove(player.getX(), player.getY() - 1));
		Assert.assertEquals(true, player.canMove(player.getX(), player.getY() + 1));
		
		content = "0200\r\n6142\r\n0800";
		map.setMapFromString(content);
		player = map.getPlayer();
		
		/* Map:
		 * 
		 * 0200
		 * 6142
		 * 0800
		 * 
		 */
		
		// Player is surrounded by:
		// Left: Mob1 (can move and die)
		// Up: Wall (can't move)
		// Right: Rock with wall behind (can't move)
		// Down: Endblock (can move and finish the level)
		
		Assert.assertEquals(true, player.canMove(player.getX() - 1, player.getY()));
		Assert.assertEquals(false, player.canMove(player.getX() + 1, player.getY()));
		Assert.assertEquals(false, player.canMove(player.getX(), player.getY() - 1));
		Assert.assertEquals(true, player.canMove(player.getX(), player.getY() + 1));
		
		
	}

}
