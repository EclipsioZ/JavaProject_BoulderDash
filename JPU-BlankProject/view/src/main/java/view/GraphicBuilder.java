package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;

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
	
	public void setMap(IModel iModel) {
		this.map = iModel.getMap();
	}
	
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	
	public Map getMap() {
		return this.map;
	}
	
	public void applyModelToGraphic(Graphics graphics) {
		
//		Map map = new Map();
//		map.setHeight(10);
//		map.setWidth(15);
//		String content = "433111111111111\r\n141111111111111\r\n415111111111111\r\n141111111111111\r\n415111111111111\r\n141111111111111\r\n415111111111111\r\n141111111111111\r\n415111111111111\r\n415111111111111";
//		map.setMapFromString(content);
		
		int spriteSize = 80;
		
		Graphics2D gg = (Graphics2D) graphics;

		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int VIEWPORT_SIZE_X = 15 * 80;

		int VIEWPORT_SIZE_Y = 15 * 80;

		int offsetMaxX = map.getWidth() * 80 - VIEWPORT_SIZE_X;
		int offsetMaxY = map.getHeight() * 80 - VIEWPORT_SIZE_Y;

		int offsetMinX = 8 * 80 - VIEWPORT_SIZE_X / 2;
		int offsetMinY = 8 * 80 - VIEWPORT_SIZE_Y / 2;

		int camX = map.getPlayer().getX() * 80 - VIEWPORT_SIZE_X / 2;
		int camY = map.getPlayer().getY() * 80 - VIEWPORT_SIZE_Y / 2;

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
		
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				
				Element element = map.getElementAt(x, y);
				
				if(element instanceof Player) {
					//Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
					//Texture for Player
					graphics.drawImage(Texture.playerrest[element.getIndexElementAnimation()%2], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Rock) {
					//Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
					//Texture for Rock
					graphics.drawImage(Texture.rock[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Dirt) {
					//Texture for Dirt
					graphics.drawImage(Texture.dirt[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Diamond) {
					//Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
					//Texture for Diamond
					graphics.drawImage(Texture.diamond[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Wall) {
					
					//Texture for Wall
					graphics.drawImage(Texture.wall[0], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof EndBlock) {
					//Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
					//Texture for Wall
					graphics.drawImage(Texture.endblock[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Mob1) {
					//Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
					//Texture for Wall
					graphics.drawImage(Texture.mob1[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Mob2) {
					//Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
					//Texture for Wall
					graphics.drawImage(Texture.mob2[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Explode) {
					//Background
					graphics.drawImage(Texture.background[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
					//Texture for Air
					graphics.drawImage(Texture.explode[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Air) {
					//Texture for Air
					graphics.drawImage(Texture.air[element.getIndexElementAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
			}
		}
	}
	
		public void loadAnimation(){
			
			for (int y = 0; y < map.getHeight(); y++) {
				for (int x = 0; x < map.getWidth(); x++) {
					Element element = map.getElementAt(x, y);
					
					if(element instanceof Rock) {	
						for(int j=0; j < Texture.rock.length; j++) {
							
						element.getSprites().add(Texture.rock[j]);
						}
					}
					
					if(element instanceof Diamond) {	
						
						for(int j=0; j < Texture.diamond.length; j++) {
						element.getSprites().add(Texture.diamond[j]);
						}
					}
				}
			}
		}
	
	public void graphicsAnimation(Graphics graphics) {
		
		loadAnimation();
//		while(true) {
		
			
			
		int a = 0;
			
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				
				Element element = map.getElementAt(x, y);
			if(element instanceof Rock) {
				graphics.drawImage(Texture.background[0], x * 80, y * 80, 80, 80, null);
				//Texture for Rock
				graphics.drawImage(element.getSprites().get(a), x * 80, y * 80, 80, 80, null);
				
			}
		}
		}
		a++;
		if(a > Texture.rock.length) {
			a = 0;
		}
		}
//	}
}
