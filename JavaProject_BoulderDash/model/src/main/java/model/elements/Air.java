package model.elements;

import model.Map;

public class Air extends Block implements ElementStrategy {

	public Air(Map map) {
		super(map);
		this.setSprites(null);
		this.figure = "0";
		
		// Add this element to the animated elements
		this.getMap().getAnimatedElements().add(this);
	}

	@Override
	public void pop() {

	}

}
