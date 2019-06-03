package model.elements;

import java.net.URL;

import model.Map;

/**
 * The class for the player
 * 
 * @author Florian Rossi
 * @author Baptiste Miquel
 *
 */
public class Player extends Element implements ElementStrategy {

	private SoundEffect sound;
	private int diamonds;
	private int score;
	
	/** Do the player wants to return to the menu */
	private int returnmenu;
	private String direction = "REST";
	
	public int restIndex = 0;

	/**
	 * Instantiates a new player
	 * 
	 * @param map The map
	 */
	public Player(Map map) {
		super(map);
		this.diamonds = 0;
		this.figure = "1";
		this.score = 0;

		this.setMaxAnimations(2);

		// Add this element to the animated elements
		this.getMap().getAnimatedElements().add(this);
		
		this.setSound(new SoundEffect());
		Thread soundThread = new Thread(this.getSound());
		soundThread.start();
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
		this.getSound().changeSound("Death");
		this.isAlive = false;
		this.getMap().running = false;
	}

	@Override
	public Boolean canMove(int x, int y) {

		int movementDirectionX = x - this.getX();

		Element el = this.getMap().getElementAt(x, y);

		if (el instanceof Air || el instanceof Explode) {
			return true;
		}
		if(el instanceof Dirt) {
			getSound().setSoundName("Dirt");
			return true;
		}
		if (el instanceof Rock) {
			if (el.canMove(el.getX() + movementDirectionX, el.getY())) {
				el.move(x + movementDirectionX, y);
				return true;
			}
		}
		if (el instanceof Diamond) {
			getSound().setSoundName("Diamond");
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
			getSound().setSoundName("EndBlock");
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public SoundEffect getSound() {
		return sound;
	}

	public void setSound(SoundEffect sound) {
		this.sound = sound;
	}
}
