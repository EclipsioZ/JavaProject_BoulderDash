package model.elements;

import java.awt.Image;
import java.util.ArrayList;

import model.Map;

public class Element {
	
	private ArrayList<Image> sprites;
	private Map map;
	private int x;
	private int y;

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

	Element() {
		
	}
	
	Element(Map map) {
		this.map = map;
	}
	
	public void move(int x, int y) {
		if(this.canMove(x, y)) {
			
		}
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

}
