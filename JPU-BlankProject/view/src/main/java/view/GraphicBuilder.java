package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.AnimatedText;
import model.Animation;
import model.IModel;
import model.Map;
import model.Model;
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

public class GraphicBuilder {

	Map map;
	Animation animation;
	IModel model;
	int camX;
	int camY;

	public void setMap(IModel iModel) {
		this.model = iModel;
		this.map = iModel.getMap();
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public Map getMap() {
		return this.map;
	}

	public void applyModelToGraphic(Graphics graphics) {

		int spriteSize = 80;
		if (map.getPlayer() != null) {
			moveCamera(graphics);
		}

		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {

				Element element = map.getElementAt(x, y);

				if (element instanceof Player) {
					// Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize,
							y * spriteSize, spriteSize, spriteSize, null);
					// Texture for Player
					graphics.drawImage(Texture.playerrest[element.getIndexElementAnimation()], x * spriteSize,
							y * spriteSize, spriteSize, spriteSize, null);
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

		// SHOW CAMERA
//		graphics.setColor(Color.RED);
//		graphics.fillRect(camX + VIEWPORT_SIZE_X / 2 - 10, camY + VIEWPORT_SIZE_Y / 2 - 10, 20, 20);
//		graphics.fillRect(camX + VIEWPORT_SIZE_X / 2 + 80 * 3 - 10, camY + VIEWPORT_SIZE_Y / 2 - 10, 20, 20);

		if (model.getAnimatedText() != null) {
			Font font = new Font("Arial", Font.BOLD, 35);
			graphics.setFont(font);
			graphics.setColor(new Color(80, 255, 80, 255));
			graphics.drawString(model.getAnimatedText().getText(), map.getPlayer().getX() * 80 + 0,
					map.getPlayer().getY() * 80 + 80 + model.getAnimatedText().getLifeTime() * -1);
		}

		showHUD(graphics);

	}

	public void showHUD(Graphics graphics) {
		File hud1 = new File(getClass().getClassLoader().getResource("Hud1.png").getFile());
		BufferedImage hudImage;
		try {
			hudImage = ImageIO.read(hud1);
			graphics.drawImage(hudImage, camX + 10, camY + 100, (int) Math.round(hudImage.getWidth() / 1.5),
					(int) Math.round(hudImage.getHeight() / 1.5), null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		File hud2 = new File(getClass().getClassLoader().getResource("Hud2.png").getFile());
		BufferedImage hud2Image = null;
		try {
			hud2Image = ImageIO.read(hud2);
			graphics.drawImage(hud2Image, (camX + ((80 * 10) / 2)) - (hud2Image.getWidth() / 5), camY + 10,
					(int) Math.round(hud2Image.getWidth() / 1.5), (int) Math.round(hud2Image.getHeight() / 1.5), null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Font diamondsFont = new Font("Arial", Font.BOLD, 35);
		graphics.setFont(diamondsFont);
		graphics.setColor(Color.WHITE);
		int diamonds = map.getRequiredDiamonds() - map.getPlayer().getDiamonds();
		if (diamonds < 0)
			diamonds = 0;
		graphics.drawString(String.format("%03d", diamonds), camX + 72, camY + 138);

		Font timerFont = new Font("Arial", Font.BOLD, 35);
		graphics.setFont(timerFont);
		graphics.setColor(Color.WHITE);
		int timer = Math.round(map.getTimer() / 1000);
		if (timer < 0)
			timer = 0;
		graphics.drawString(String.format("%03d", timer), camX + 72, camY + 190);
		
		Font scoreFont = new Font("Arial", Font.BOLD, 35);
		graphics.setFont(scoreFont);
		graphics.setColor(Color.WHITE);
		int score = this.getMap().getPlayer().getScore();
		graphics.drawString(String.format("%06d", score), (camX + ((80 * 10) / 2)) - (hud2Image.getWidth() / 5) + 40, camY + 48);
	}

	public void loadAnimation() {

		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				Element element = map.getElementAt(x, y);

				if (element instanceof Rock) {
					for (int j = 0; j < Texture.rock.length; j++) {

						element.getSprites().add(Texture.rock[j]);
					}
				}

				if (element instanceof Diamond) {

					for (int j = 0; j < Texture.diamond.length; j++) {
						element.getSprites().add(Texture.diamond[j]);
					}
				}
			}
		}
	}

	public void moveCamera(Graphics graphics) {

		Graphics2D gg = (Graphics2D) graphics;

		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int VIEWPORT_SIZE_X = 10 * 80;
		int VIEWPORT_SIZE_Y = 10 * 80;

		int offsetMaxX = (int) (map.getWidth() * 80 - VIEWPORT_SIZE_X);
		int offsetMaxY = map.getHeight() * 80 - VIEWPORT_SIZE_Y;

		int offsetMinX = 10 * 80 - VIEWPORT_SIZE_X;
		int offsetMinY = 10 * 80 - VIEWPORT_SIZE_Y;

		this.camX = (int) (map.getPlayer().getX() * 80 - Math.floor(VIEWPORT_SIZE_X / 2)) + 40;
		this.camY = (int) (map.getPlayer().getY() * 80 - Math.floor(VIEWPORT_SIZE_Y / 2)) + 40;

		if (camX > offsetMaxX) {
			camX = offsetMaxX;
		}

		if (camY > offsetMaxY) {
			camY = offsetMaxY;
		}

		if (camX < offsetMinX) {
			camX = offsetMinX;
		}

		if (camY < offsetMinY) {
			camY = offsetMinY;
		}

		gg.translate(-camX, -camY);

		AffineTransform old = gg.getTransform();
		gg.setTransform(old);
	}

	public void graphicsAnimation(Graphics graphics) {

		loadAnimation();
//		while(true) {

		int a = 0;

		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {

				Element element = map.getElementAt(x, y);
				if (element instanceof Rock) {
					graphics.drawImage(Texture.background[0], x * 80, y * 80, 80, 80, null);
					// Texture for Rock
					graphics.drawImage(element.getSprites().get(a), x * 80, y * 80, 80, 80, null);

				}
			}
		}
		a++;
		if (a > Texture.rock.length) {
			a = 0;
		}
	}
//	}
}
