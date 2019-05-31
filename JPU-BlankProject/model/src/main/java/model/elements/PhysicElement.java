package model.elements;

import model.Map;

public abstract class PhysicElement extends Element {

	PhysicElement(Map map) {
		super(map);

		// Add this element to the physic elements
		this.getMap().getPhysicElements().add(this);
	}

	@Override
	public Boolean canMove(int x, int y) {
		Element el = this.getMap().getElementAt(x, y);
		if (el instanceof Air) {
			return true;
		}
		return false;
	}

	public void gravity() {
		Element downEl = this.getMap().getElementAt(this.getX(), this.getY() + 1);
		Element rightEl = this.getMap().getElementAt(this.getX() + 1, this.getY());
		Element leftEl = this.getMap().getElementAt(this.getX() - 1, this.getY());
		Element downLeftEl = this.getMap().getElementAt(this.getX() - 1, this.getY() + 1);
		Element downRightEl = this.getMap().getElementAt(this.getX() + 1, this.getY() + 1);

		if (downEl instanceof Air || downEl instanceof Mob) {
			downEl.handleCollision(this);
			this.move(this.getX(), this.getY() + 1);
		} else if ((leftEl instanceof Air && downLeftEl instanceof Air) || downLeftEl instanceof Mob) {
			downLeftEl.handleCollision(this);
			this.move(this.getX() - 1, this.getY());
		} else if ((rightEl instanceof Air && downRightEl instanceof Air) || downRightEl instanceof Mob) {
			downRightEl.handleCollision(this);
			this.move(this.getX() + 1, this.getY());
		}
		
		

	}
	
	public void checkCanKillPlayer(int x, int y) {
		Element downEl = this.getMap().getElementAt(x, y + 1);
		// If there is a down element
		if(downEl != null) {
			downEl.handleCollision(this);
		}
	}

	@Override
	public Boolean handleCollision(Element element) {
		return true;
	}

	@Override
	public void pop() {
		this.getMap().getPhysicElements().remove(this);
		this.getMap().getAnimatedElements().remove(this);
	}

}
