package model.elements;

import model.Map;

public class Diamond extends PhysicElement implements ElementStrategy {

	public Diamond(Map map) {
		super(map);
		this.figure = "5";
		
		// Add this element to the animated elements
		this.getMap().getAnimatedElements().add(this);
	}

}
