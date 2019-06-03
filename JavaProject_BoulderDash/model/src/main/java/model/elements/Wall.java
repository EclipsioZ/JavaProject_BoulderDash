package model.elements;

import model.Map;

public class Wall extends Block implements ElementStrategy {

	public Wall(Map map) {
		super(map);
		this.figure = "2";
	}

	@Override
	public void pop() {

	}

}
