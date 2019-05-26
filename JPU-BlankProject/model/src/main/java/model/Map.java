package model;

import model.elements.Air;
import model.elements.Diamond;
import model.elements.Dirt;
import model.elements.Element;
import model.elements.EndBlock;
import model.elements.Mob1;
import model.elements.Mob2;
import model.elements.Player;
import model.elements.Rock;
import model.elements.Wall;

public class Map {

	private Element[][] map;
	
	// Size of the map
	private int height;
	private int width;
	
	// Position of the end block
	private int[][] endblock;

	public void map(Element[][] existingMap) {
		this.height = 0;
		this.width = 0;
	}

	public void setMapFromString(String Contents) {

		// Setting size of the map
		this.map = new Element[this.height][this.width];

		// Cut the map for the width
		String[] mapString2D = Contents.split("\\r\\n");
				
		// Cut the map for the height
		char[][] mapString3D = new char[this.width][this.height];
		
		int y = 0;
		for (String lineTable : mapString2D) {
			for (int x = 0; x < lineTable.toCharArray().length; x++) {
				
				// Get the element at current x and y
				int element = Character.getNumericValue(lineTable.toCharArray()[x]);
				
				// Convert char to element
				switch (element) {
				case 0:
					this.map[x][y] = new Air();
					break;
				case 1:
					this.map[x][y] = new Player();
					break;
				case 2:
					this.map[x][y] = new Wall();
					break;
				case 3:
					this.map[x][y] = new Dirt();
					break;
				case 4:
					this.map[x][y] = new Rock();
					break;
				case 5:
					this.map[x][y] = new Diamond();
					break;
				case 6:
					this.map[x][y] = new Mob1();
					break;
				case 7:
					this.map[x][y] = new Mob2();
					break;
				case 8:
					this.map[x][y] = new EndBlock();
					break;
				default:
					this.map[x][y] = new Air();
					break;
				}
			}
			y++;
		}

		System.out.println(mapString3D[1][2]);
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
