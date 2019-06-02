package model;

import java.awt.image.BufferedImage;

/**
 * The Class View
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */

public class Spritesheet {

	private BufferedImage image;
	
	/**
	 * Constructor of class spritesheet
	 * @param image BufferedImage for load
	 */
	public Spritesheet(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Get the sprite in spritesheet with the coordinate
	 * @param col Int for get the colone
	 * @param row Int for get the row
	 * @param width Int for get the width of sprite
	 * @param height Int for get the height of sprite
	 * @return
	 */
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img = image.getSubimage((col*width) - width, (row * height) - height , width, height);
		return img;
	}
}
