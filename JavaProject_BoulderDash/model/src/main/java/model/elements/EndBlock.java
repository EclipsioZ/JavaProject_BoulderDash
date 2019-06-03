package model.elements;

import model.Map;

public class EndBlock extends Block implements ElementStrategy {

	public EndBlock(Map map) {
		super(map);
		this.figure = "8";
		
		// Add this element to the animated elements
		this.getMap().getAnimatedElements().add(this);
	}
	
	@Override
	public void pop() {
		this.getMap().getAnimatedElements().remove(this);
	}
	
	@Override
	public Boolean handleCollision(Element element) {
		return element instanceof Player;
	}

}
