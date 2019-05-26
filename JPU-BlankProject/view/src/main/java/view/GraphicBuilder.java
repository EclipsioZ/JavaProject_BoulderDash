package view;

import java.awt.Color;
import java.awt.Graphics;

public class GraphicBuilder {

	public void applyModelToGraphic(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.drawRect(10, 10, 50, 200);
	}

}
