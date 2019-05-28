package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.TexturePaint;

import model.Animation;
import model.IModel;
import model.Map;
import model.Model;
import model.Texture;
import model.elements.Air;
import model.elements.Diamond;
import model.elements.Dirt;
import model.elements.Element;
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
		
		int spriteSize = 50;
		
		
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				
				Element element = map.getElementAt(x, y);
				
				if(element instanceof Player) {
					//Background
					graphics.drawImage(Texture.background[0], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
					//Texture for Player
					graphics.drawImage(Texture.playerdietime[0], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Rock) {
					//Background
					graphics.drawImage(Texture.background[0], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
					//Texture for Rock
					graphics.drawImage(Texture.rock[element.getIndexAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Dirt) {
					//Texture for Dirt
					graphics.drawImage(Texture.dirt[0], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Diamond) {
					//Background
					graphics.drawImage(Texture.background[0], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
					//Texture for Diamond
					graphics.drawImage(Texture.diamond[element.getIndexAnimation()], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				
				if(element instanceof Wall) {
					
					//Texture for Wall
					graphics.drawImage(Texture.wall[0], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
				}
				if(element instanceof Air) {
					//Background
					graphics.drawImage(Texture.background[0], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
					//Texture for Air
					graphics.drawImage(Texture.air[0], x * spriteSize, y * spriteSize, spriteSize, spriteSize, null);
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
