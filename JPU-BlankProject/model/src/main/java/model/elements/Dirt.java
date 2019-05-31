package model.elements;

import model.Map;

public class Dirt extends Block implements ElementStrategy {

	public Dirt(Map map) {
		super(map);
		this.figure = "3";
		
		// Add this element to the animated elements
		this.getMap().getAnimatedElements().add(this);
	}

}
