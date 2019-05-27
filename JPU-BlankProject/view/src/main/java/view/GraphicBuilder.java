package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.TexturePaint;
import contract.IModel;
import model.Map;
import model.Model;
import model.Texture;
import model.elements.Air;
import model.elements.Diamond;
import model.elements.Dirt;
import model.elements.Element;
import model.elements.Player;
import model.elements.Rock;

public class GraphicBuilder {
	public void applyModelToGraphic(Graphics graphics) {

		Map map = new Map();
		map.setHeight(10);
		map.setWidth(15);
		String content = "433111111111111\r\n141111111111111\r\n4415111111111111\r\n141111111111111\r\n415111111111111\r\n141111111111111\r\n415111111111111\r\n141111111111111\r\n415111111111111\r\n415111111111111";
		map.setMapFromString(content);

		graphics.setColor(Color.BLACK);
		graphics.drawRect(10, 10, 50, 200);

		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
								
				Element element = map.getElementAt(x, y);
				if(element instanceof Player) {
					System.out.println(x + " ; " + y);
					graphics.drawImage(Texture.playerrest[0], x * 16, y * 16, null);
				}
				
				if(element instanceof Rock) {
					System.out.println("test");
					graphics.drawImage(Texture.rock[0], x *16, y *16, null);
				}
				
				if(element instanceof Dirt) {
					System.out.println("test");
					graphics.drawImage(Texture.dirt[0], x *16, y *16, null);
				}
				
				if(element instanceof Diamond) {
					System.out.println("test");
					graphics.drawImage(Texture.diamond[0], x *16, y *16, null);
				}
			}
		}
	}

}
