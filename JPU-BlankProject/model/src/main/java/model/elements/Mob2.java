package model.elements;

import model.Map;

public class Mob2 extends Mob implements ElementStrategy {

	public Mob2(Map map) {
		super(map);
		this.figure = "7";
		this.setExplosionType("Diamond");
	}

}
