package model.elements;

public abstract class Mob extends Element {

	public Mob() {
		super();
	}
	
	public void explode() {
		
	}
	
	public void iaMove() {
		Element upEl = this.getMap().getElementAt(this.getX(), this.getY() - 1);
		Element downEl = this.getMap().getElementAt(this.getX(), this.getY() + 1);
		Element rightEl = this.getMap().getElementAt(this.getX() + 1, this.getY());
		Element leftEl = this.getMap().getElementAt(this.getX() - 1, this.getY());
		
		if(leftEl instanceof Air) {
			this.move(this.getX() + 1, this.getY());
		}
	}

}
