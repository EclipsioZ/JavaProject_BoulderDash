package model.elements;

import model.Map;

public class Player extends Element {

	private int diamonds;

	public Player() {
		super();
		this.diamonds = 0;
		this.figure = "1";
	}

	public int getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(int diamonds) {
		this.diamonds = diamonds;
	}

	public void die() {

	}

	@Override
	public Boolean canMove(int x, int y) {
		
		int movementDirectionX = x - this.getX();
		int movementDirectionY = y - this.getY();
		
		Element el = this.getMap().getElementAt(x, y);
		if (el instanceof Air || el instanceof Dirt) {
			return true;
		}
		if (el instanceof Rock) {
			if (el.canMove(el.getX() + movementDirectionX, el.getY())) {
				el.move(x + movementDirectionX, y);
				return true;
			}
		}
		if (el instanceof Diamond) {
			this.setDiamonds(this.getDiamonds() + 1);
			this.getMap().setElementAt(x, y, new Air());
			this.getMap().getPhysicElements().remove(el);
			return true;
		}
		if (el instanceof Mob) {
			this.die();
			return true;
		}
		if (el instanceof EndBlock) {
			// TODO: end level, load next level
			return true;
		}
		return false;
	}

}
