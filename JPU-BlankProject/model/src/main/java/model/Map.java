package model;

import java.awt.Graphics;

import model.elements.Element;
import model.elements.Player;;

public class Map {

	private Element[][] map;
	private int height;
	private int width;
	private int[][] endblock;

	public void map(Element[][] existingMap) {
		this.height = 0;
		this.width = 0;
	}

	public void setMapFromString(String Contents) {

		String[] mapString = Contents.split("\\r\\n");
		char[][] mapString2 = new char[this.width][this.height];
		int y = 0;
		for (String ligneTableau : mapString) {
			for (int x = 0; x < ligneTableau.toCharArray().length; x++) {
				char element = ligneTableau.toCharArray()[x];
				switch (element) {
				case 1:
					map[x][y] = new Player();
					break;

				default:
					break;
				}
			}
			y++;
		}
		
		System.out.println(mapString2[1][2]);

//		for(int x = 0; x < this.height; x++) {
//			for(int y = 0; y < this.width; y++) {
//				map[x][y] = new Element();
//			}
//		}
	}

	private void setMap(Element[][] map) {
		this.map = map;

	}

	public Element[][] getMap() {
		return this.map;
	}


	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[][] getPosEndblock() {
		return endblock;
	}

	public void setPosEndBlock(int[][] endblock) {
		this.endblock = endblock;
	}

}
