package view;

import org.junit.Assert;
import org.junit.Test;

public class GraphicBuilderTest {

	/**
	 * Testing if the camera position is correct when the player is not on the boundaries of the map
	 */
	@Test
	public void testFindCamPosCenterOfScreen() {
		
		GraphicBuilder graphicBuilder = new GraphicBuilder();
		
		int playerX = 20;
		int playerY = 15;
		int spriteSize = 80;
		int viewPortSize = 10 * spriteSize;
		int mapSize = 100;
		int offsetMin = 10 * spriteSize - viewPortSize;
		int offsetMax = (int) (mapSize * spriteSize - viewPortSize);

		Assert.assertEquals(1240, graphicBuilder.findCamPos(viewPortSize, offsetMin, offsetMax, playerX, spriteSize));
		Assert.assertEquals(840, graphicBuilder.findCamPos(viewPortSize, offsetMin, offsetMax, playerY, spriteSize));
	}
	
	/**
	 * Testing if the camera position is correct when the player IS near the boundaries
	 */
	@Test
	public void testFindCamPosOnBoundaries() {
		
		GraphicBuilder graphicBuilder = new GraphicBuilder();
		
		int playerX = 0;
		int playerY = 0;
		int spriteSize = 80;
		int viewPortSize = 10 * spriteSize;
		int mapSize = 100;
		int offsetMin = 10 * spriteSize - viewPortSize;
		int offsetMax = (int) (mapSize * spriteSize - viewPortSize);

		Assert.assertEquals(0, graphicBuilder.findCamPos(viewPortSize, offsetMin, offsetMax, playerX, spriteSize));
		Assert.assertEquals(0, graphicBuilder.findCamPos(viewPortSize, offsetMin, offsetMax, playerY, spriteSize));
		
		playerX = 2;
		playerY = 3;
		Assert.assertEquals(0, graphicBuilder.findCamPos(viewPortSize, offsetMin, offsetMax, playerX, spriteSize));
		Assert.assertEquals(0, graphicBuilder.findCamPos(viewPortSize, offsetMin, offsetMax, playerY, spriteSize));
	}

}
