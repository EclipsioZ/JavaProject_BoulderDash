package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import model.IModel;
import model.Map;
import model.Texture;
import model.elements.Air;
import model.elements.Diamond;
import model.elements.Dirt;
import model.elements.Element;
import model.elements.EndBlock;
import model.elements.Explode;
import model.elements.Mob1;
import model.elements.Mob2;
import model.elements.Player;
import model.elements.Rock;
import model.elements.Wall;

/**
 * The class for the graphic builder
 * 
 * @author Florian Rossi
 * @author Baptiste Miquel
 *
 */
public class GraphicBuilder {

	private Map map;
	private IModel model;
	private int camX;
	private int camY;
	private int spriteSize;

	/**
	 * Get the map element
	 * 
	 * @return The map element
	 */
	public Map getMap() {
		return this.map;
	}
	
	/**
	 * Set the model interface and get its map
	 * 
	 * @param iModel The model interface
	 */
	public void setMap(IModel iModel) {
		this.model = iModel;
		this.map = iModel.getMap();
	}

	/**
	 * Instantiates a new graphic builder
	 */
	public GraphicBuilder() {
		this.spriteSize = 80;
	}

	/**
	 * Draw the model
	 * 
	 * @param graphics The graphic
	 */
	public void applyModelToGraphic(Graphics graphics) {

		if (map.getPlayer() != null) {
			this.moveCamera(graphics);
		}

		this.showMapElements(graphics, this.spriteSize);

		if (model.getAnimatedText() != null) {
			this.showAnimatedText(graphics);
		}

		showHUD(graphics);

	}

	/**
	 * Show all the elements of the map
	 * 
	 * @param graphics   The graphic
	 * @param spriteSize The size of each sprite
	 */
	public void showMapElements(Graphics graphics, int spriteSize) {
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {

				Element element = map.getElementAt(x, y);

				if (element instanceof Player) {
					
					if(getMap().getPlayer().getDirection() == "REST") {
						// Background
						graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
								y * spriteSize, spriteSize, spriteSize, null);
						// Texture for Player
						graphics.drawImage(Texture.playerrest[element.getIndexElementAnimation()%2], x * spriteSize,
								y * spriteSize, spriteSize, spriteSize, null);
					}
					else if(getMap().getPlayer().getDirection() == "UP") {
						// Background
						graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
								y * spriteSize, spriteSize, spriteSize, null);
						// Texture for Player
						graphics.drawImage(Texture.playerup[element.getIndexElementAnimation()], x * spriteSize,
								y * spriteSize, spriteSize, spriteSize, null);
					}
					else if(getMap().getPlayer().getDirection() == "DOWN") {
						// Background
						graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
								y * spriteSize, spriteSize, spriteSize, null);
						// Texture for Player
						graphics.drawImage(Texture.playerlowers[element.getIndexElementAnimation()], x * spriteSize,
								y * spriteSize, spriteSize, spriteSize, null);
					}
					else if(getMap().getPlayer().getDirection() == "LEFT") {
						// Background
						graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
								y * spriteSize, spriteSize, spriteSize, null);
						// Texture for Player
						graphics.drawImage(Texture.playerleft[element.getIndexElementAnimation()], x * spriteSize,
								y * spriteSize, spriteSize, spriteSize, null);
					}
					else if(getMap().getPlayer().getDirection() == "RIGHT") {
						// Background
						graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
								y * spriteSize, spriteSize, spriteSize, null);
						// Texture for Player
						graphics.drawImage(Texture.playerright[element.getIndexElementAnimation()], x * spriteSize,
								y * spriteSize, spriteSize, spriteSize, null);
					}
				}

				if (element instanceof Rock) {
					// Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
							y * spriteSize, spriteSize, spriteSize, null);
					// Texture for Rock
					graphics.drawImage(Texture.rock[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize,
							spriteSize, spriteSize, null);
				}

				if (element instanceof Dirt) {
					// Texture for Dirt
					graphics.drawImage(Texture.dirt[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize,
							spriteSize, spriteSize, null);
				}

				if (element instanceof Diamond) {
					// Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
							y * spriteSize, spriteSize, spriteSize, null);
					// Texture for Diamond
					graphics.drawImage(Texture.diamond[element.getIndexElementAnimation()], x * spriteSize,
							y * spriteSize, spriteSize, spriteSize, null);
				}

				if (element instanceof Wall) {

					// Texture for Wall
					graphics.drawImage(Texture.wall[0], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}

				if (element instanceof EndBlock) {
					// Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
							y * spriteSize, spriteSize, spriteSize, null);
					graphics.drawImage(Texture.endblock[element.getIndexElementAnimation()], x * spriteSize,
							y * spriteSize, spriteSize, spriteSize, null);
				}

				if (element instanceof Mob1) {
					// Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
							y * spriteSize, spriteSize, spriteSize, null);
					graphics.drawImage(Texture.mob1[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize,
							spriteSize, spriteSize, null);
				}

				if (element instanceof Mob2) {
					// Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
							y * spriteSize, spriteSize, spriteSize, null);
					graphics.drawImage(Texture.mob2[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize,
							spriteSize, spriteSize, null);
				}

				if (element instanceof Explode) {
					// Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
							y * spriteSize, spriteSize, spriteSize, null);
					graphics.drawImage(Texture.explode[element.getIndexElementAnimation()], x * spriteSize,
							y * spriteSize, spriteSize, spriteSize, null);
				}

				if (element instanceof Air) {
					// Texture for Air
					graphics.drawImage(Texture.air[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize,
							spriteSize, spriteSize, null);
				}
			}
		}
	}

	/**
	 * Show the "EXIT" text with animation
	 * 
	 * @param graphics The graphic
	 */
	public void showAnimatedText(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(map.getPlayer().getX() * 80 - 4,
				map.getPlayer().getY() * 80 + 40 + model.getAnimatedText().getLifeTime() * -1, 84, 50);
		Font font = new Font("Arial", Font.BOLD, 35);
		graphics.setFont(font);
		graphics.setColor(new Color(80, 255, 80, 255));
		graphics.drawString(model.getAnimatedText().getText(), map.getPlayer().getX() * 80 + 0,
				map.getPlayer().getY() * 80 + 80 + model.getAnimatedText().getLifeTime() * -1);
	}

	/**
	 * Show the HUD (Sprite and text)
	 * 
	 * @param graphics The graphic
	 */
	public void showHUD(Graphics graphics) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		InputStream leftHudFile = classLoader.getResourceAsStream("Hud1.png");	
		BufferedImage leftHudImage;
		try {
			leftHudImage = ImageIO.read(leftHudFile);
			graphics.drawImage(leftHudImage, camX + 10, camY + 100, (int) Math.round(leftHudImage.getWidth() / 1.5),
					(int) Math.round(leftHudImage.getHeight() / 1.5), null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		InputStream topHudFile = classLoader.getResourceAsStream("Hud2.png");	
		BufferedImage topHudImage = null;
		try {
			topHudImage = ImageIO.read(topHudFile);
			graphics.drawImage(topHudImage, (camX + ((80 * 10) / 2)) - (topHudImage.getWidth() / 5), camY + 10,
					(int) Math.round(topHudImage.getWidth() / 1.5), (int) Math.round(topHudImage.getHeight() / 1.5),
					null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Font hudFont = new Font("Arial", Font.BOLD, 35);
		graphics.setFont(hudFont);
		graphics.setColor(Color.WHITE);

		int diamonds = map.getRequiredDiamonds() - map.getPlayer().getDiamonds();
		if (diamonds < 0)
			diamonds = 0;
		graphics.drawString(String.format("%03d", diamonds), camX + 72, camY + 138);

		int timer = Math.round(map.getTimer() / 1000);
		if (timer < 0)
			timer = 0;
		graphics.drawString(String.format("%03d", timer), camX + 72, camY + 190);

		int score = this.getMap().getPlayer().getScore();
		graphics.drawString(String.format("%06d", score), (camX + ((80 * 10) / 2)) - (topHudImage.getWidth() / 5) + 40,
				camY + 48);
	}

	/**
	 * Move the camera
	 * 
	 * @param graphics The graphic
	 */
	public void moveCamera(Graphics graphics) {
		Graphics2D gg = (Graphics2D) graphics;

		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int viewPortSizeX = 10 * this.spriteSize;
		int viewPortSizeY = 10 * this.spriteSize;

		int offsetMinX = 10 * this.spriteSize - viewPortSizeX;
		int offsetMaxX = (int) (map.getWidth() * this.spriteSize - viewPortSizeX);

		int offsetMinY = 10 * this.spriteSize - viewPortSizeY;
		int offsetMaxY = map.getHeight() * this.spriteSize - viewPortSizeY;

		this.camX = this.findCamPos(viewPortSizeX, offsetMinX, offsetMaxX, map.getPlayer().getX(), this.spriteSize);
		this.camY = this.findCamPos(viewPortSizeY, offsetMinY, offsetMaxY, map.getPlayer().getY(), this.spriteSize);

		gg.translate(-camX, -camY);

		AffineTransform old = gg.getTransform();
		gg.setTransform(old);
	}

	/**
	 * Get the camera position
	 * 
	 * @param viewPortSize Size of the viewport
	 * @param offsetMin    Minimum camera position
	 * @param offsetMax    Maximum camera position
	 * @param playerPos    Player position
	 * @param spriteSize   Size of sprite
	 * @return The camera position
	 */
	public int findCamPos(int viewPortSize, int offsetMin, int offsetMax, int playerPos, int spriteSize) {
		int cam = (int) (playerPos * spriteSize - Math.floor(viewPortSize / 2)) + spriteSize / 2;
		if (cam > offsetMax) {
			cam = offsetMax;
		}
		if (cam < offsetMin) {
			cam = offsetMin;
		}
		return cam;
	}

}
