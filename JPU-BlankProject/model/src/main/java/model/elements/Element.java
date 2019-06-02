package model.elements;

import java.awt.Image;
import java.util.ArrayList;
import java.util.UUID;

import model.Map;

/**
 * The class for the element
 * 
 * @author Florian Rossi
 * @author Baptiste Miquel
 *
 */
public class Element {

	private ArrayList<Image> sprites;
	private Map map;
	private int x;
	private int y;

	private int indexElementAnimation;
	private int maxAnimations;

	public UUID uuid;

	public String figure;

	public Boolean isAlive;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Instantiates a new element
	 */
	Element() {
		this.sprites = new ArrayList<Image>();
		this.indexElementAnimation = 0;
		this.uuid = UUID.randomUUID();
		this.isAlive = true;
		this.maxAnimations = 4;
	}

	/**
	 * Instantiates a new element with a map
	 * 
	 * @param map The map
	 */
	Element(Map map) {
		this.map = map;
		this.sprites = new ArrayList<Image>();
		this.indexElementAnimation = 0;
		this.uuid = UUID.randomUUID();
		this.isAlive = true;
		this.maxAnimations = 4;
	}

	public int getIndexElementAnimation() {
		return indexElementAnimation;
	}

	public void setIndexElementAnimation(int indexElementAnimation) {
		this.indexElementAnimation = indexElementAnimation;
	}

	public void move(int x, int y) {
		if (this.canMove(x, y)) {
			map.setElementAt(this.getX(), this.getY(), new Air(map));
			map.setElementAt(x, y, this);
		}
	}

	public Boolean handleCollision(Element element) {
		return false;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Boolean canMove(int x, int y) {
		return false;
	}

	public ArrayList<Image> getSprites() {
		return sprites;
	}

	public void setSprites(ArrayList<Image> sprites) {
		this.sprites = sprites;
	}

	public int getMaxAnimations() {
		return this.maxAnimations;
	}

	public void setMaxAnimations(int maxAnimations) {
		this.maxAnimations = maxAnimations;
	}

	public void pop() {

	}

}
