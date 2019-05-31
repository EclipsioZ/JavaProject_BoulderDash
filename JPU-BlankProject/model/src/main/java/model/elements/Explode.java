package model.elements;

import model.Map;

public class Explode extends Block implements ElementStrategy {

	public Explode(Map map) {
		super(map);
		this.figure = "9";
		
		// Add this element to the animated elements
		this.getMap().getAnimatedElements().add(this);
	}

	@Override
	public Boolean handleCollision(Element element) {
		return true;
	}
	
}
