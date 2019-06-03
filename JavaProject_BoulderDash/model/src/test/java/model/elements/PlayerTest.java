package model.elements;

import org.junit.Assert;
import org.junit.Test;

import model.Map;

/**
 * The test class for the player
 * 
 * @author Florian Rossi
 * @author Baptiste Miquel
 *
 */
public class PlayerTest {

	/**
	 * Check every possible collisions for the player and his ability to move
	 */
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
	
	/**
	 * Check player movement
	 */
	@Test
	public void testMove() {
		Map map = new Map();
		map.setHeight(3);
		map.setWidth(4);
		String content = "2222\r\n2133\r\n2333";
		
		/* Map:
		 * 
		 * 2222
		 * 2133
		 * 2333
		 * 
		 */
		
		map.setMapFromString(content);
		Player player = map.getPlayer();
		
		// Move the player to the right bottom
		player.move(player.getX() + 1, player.getY());
		player.move(player.getX(), player.getY() - 1); // The player will not move because there is a wall
		player.move(player.getX(), player.getY() + 1);
		player.move(player.getX() + 1, player.getY());
		
		Assert.assertEquals(true, map.getElementAt(3, 2) instanceof Player);
		
	}
	
	/**
	 * Check collision with rocks
	 */
	@Test
	public void testMoveWithRocks() {
		Map map = new Map();
		map.setHeight(3);
		map.setWidth(4);
		String content = "0000\r\n1400\r\n3333";
		
		/* Map:
		 * 
		 * 0000
		 * 1400
		 * 3333
		 * 
		 */
		
		map.setMapFromString(content);
		Player player = map.getPlayer();
		
		// Push the rock once to the right
		player.move(player.getX() + 1, player.getY());

		// Move behind the rock
		player.move(player.getX(), player.getY() - 1);
		player.move(player.getX() + 1, player.getY());
		player.move(player.getX() + 1, player.getY());
		player.move(player.getX(), player.getY() + 1);

		// Push the rock even if it's on a wall
		player.move(player.getX() - 1, player.getY());
		player.move(player.getX() - 1, player.getY());
		player.move(player.getX() - 1, player.getY());
		player.move(player.getX() - 1, player.getY());

		Assert.assertEquals(true, map.getElementAt(0, 1) instanceof Rock);
		Assert.assertEquals(true, map.getElementAt(1, 1) instanceof Player);
		
	}

}
