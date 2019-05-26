package model;

import java.awt.Graphics;

import model.elements.Element;;

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
		char[][] mapString2 = null;
		int y = 0;
		for (String ligneTableau : mapString) {
			for (int x = 0; x < ligneTableau.toCharArray().length; x++) {
				mapString2[x][y] = ligneTableau.toCharArray()[x];
			}
			y++;
		}

//		for(int x = 0; x < this.height; x++) {
//			for(int y = 0; y < this.width; y++) {
//				map[x][y] = new Element(mapString2[x][y]);
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

	public void setPosEndblock(int[][] endblock) {
		this.endblock = endblock;
	}

}
