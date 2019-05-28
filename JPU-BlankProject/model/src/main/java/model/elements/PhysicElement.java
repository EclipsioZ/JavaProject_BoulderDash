package model.elements;

public abstract class PhysicElement extends Element {

	PhysicElement() {
		super();
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
		
		if(downEl instanceof Air) {
			this.move(this.getX(), this.getY() + 1);
		} else if (leftEl instanceof Air && downLeftEl instanceof Air) {
			this.move(this.getX() - 1, this.getY() + 1);
		} else if (rightEl instanceof Air && downRightEl instanceof Air) {
			this.move(this.getX() + 1, this.getY() + 1);
		}
	}

}
