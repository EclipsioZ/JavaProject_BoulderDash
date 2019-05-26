package model.elements;

import java.awt.Image;
import java.util.ArrayList;

public class Element {
	
	private int idElement;
	private ArrayList<Image> sprites;

	Element(int id) {
		this.idElement = id;
	}
	
	public void move(int x, int y) {
		
	}
	
	public Boolean canMove(int x, int y) {
		return true;
	}

	public int getIdElement() {
		return idElement;
	}

	public void setIdElement(int idElement) {
		this.idElement = idElement;
	}

	public ArrayList<Image> getSprites() {
		return sprites;
	}

	public void setSprites(ArrayList<Image> sprites) {
		this.sprites = sprites;
	}

}
