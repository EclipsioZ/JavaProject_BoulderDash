package model.elements;

public class Player extends Element {

	private int diamonds;

	Player(int id) {
		super(id);
		this.diamonds = 0;
	}
	
	public int getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(int diamonds) {
		this.diamonds = diamonds;
	}
	
	public void die() {
		
	}

}
