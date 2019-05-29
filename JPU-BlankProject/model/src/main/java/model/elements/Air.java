package model.elements;

import model.Map;

public class Air extends Block {

	public Air(Map map) {
		super(map);
		this.setSprites(null);
		this.figure = "0";
		
		// Add this element to the animated elements
		this.getMap().getAnimatedElements().add(this);
	}

}
