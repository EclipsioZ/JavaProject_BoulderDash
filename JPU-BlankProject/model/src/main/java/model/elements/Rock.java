package model.elements;

public class Rock extends PhysicElement {

	public Rock() {
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

}
