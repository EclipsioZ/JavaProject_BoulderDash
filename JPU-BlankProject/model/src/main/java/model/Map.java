package model;

import java.awt.Graphics;

import model.elements.Air;
import model.elements.Diamond;
import model.elements.Dirt;
import model.elements.Element;
import model.elements.EndBlock;
import model.elements.Mob1;
import model.elements.Mob2;
import model.elements.Player;
import model.elements.Rock;
import model.elements.Wall;;

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
				case 0:
					map[x][y] = new Air();
					break;
				case 1:
					map[x][y] = new Player();
					break;
				case 2:
					map[x][y] = new Wall();
					break;
				case 3:
					map[x][y] = new Dirt();
					break;
				case 4:
					map[x][y] = new Rock();
					break;
				case 5:
					map[x][y] = new Diamond();
					break;
				case 6:
					map[x][y] = new Mob1();
					break;
				case 7:
					map[x][y] = new Mob2();
					break;
				case 8:
					map[x][y] = new EndBlock();
					break;
				default:
					map[x][y] = new Air();
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
