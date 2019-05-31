package model.elements;

import model.Map;

public class Rock extends PhysicElement implements ElementStrategy {

	public Rock(Map map) {
		super(map);
		this.figure = "4";
		
		// Add this element to the animated elements
		this.getMap().getAnimatedElements().add(this);
	}

}
