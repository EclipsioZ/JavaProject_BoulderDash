package model.elements;

public abstract class PhysicElement extends Element {

	PhysicElement() {
		super();
	}

	public void gravity() {
		Element downEl = this.getMap().getElementAt(this.getX(), this.getY() + 1);
		Element downLeftEl = this.getMap().getElementAt(this.getX() - 1, this.getY() + 1);
		Element downRightEl = this.getMap().getElementAt(this.getX() + 1, this.getY() + 1);
		
		if(downEl instanceof Air) {
			this.move(this.getX(), this.getY() + 1);
		}
	}

}
