package model.elements;

import model.Map;

public class Player extends Element implements ElementStrategy {

	private int diamonds;
	private int score;
	private int returnmenu;

	public Player(Map map) {
		super(map);
		this.diamonds = 0;
		this.figure = "1";
		this.score = 0;

		this.setMaxAnimations(2);

		// Add this element to the animated elements
		this.getMap().getAnimatedElements().add(this);
	}

	public int getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(int diamonds) {
		this.diamonds = diamonds;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void die() {
		this.isAlive = false;
//		this.getMap().setElementAt(this.getX(), this.getY(), new Explode(this.getMap()));
		this.getMap().running = false;
	}

	@Override
	public Boolean canMove(int x, int y) {

		int movementDirectionX = x - this.getX();
		int movementDirectionY = y - this.getY();

		Element el = this.getMap().getElementAt(x, y);

		if (el instanceof Air || el instanceof Dirt || el instanceof Explode) {
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
			this.setScore(this.getScore() + 10);
			this.getMap().setElementAt(x, y, new Air(getMap()));
			this.getMap().getPhysicElements().remove(el);
			return true;
		}
		if (el instanceof Mob) {
			this.die();
			return true;
		}
		if (el instanceof EndBlock) {
			this.setScore(this.getScore() + 100);
			this.setScore((int) this.getScore() + Math.round(this.getMap().getTimer() / 1000));
			this.setReturnLevelSelector(1);
			return true;
		}
		return false;
	}

	@Override
	public void pop() {
		this.getMap().getAnimatedElements().remove(this);
	}
	
	public void setReturnLevelSelector(int returnm) {
		this.returnmenu = returnm;
	}
	
	public int getReturnLevelSelector() {
		return returnmenu;
	}

	@Override
	public Boolean handleCollision(Element element) {
		if (element instanceof PhysicElement || element instanceof Mob) {
			this.die();
		}
		return true;
	}
}
