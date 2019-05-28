package model;

import java.util.ArrayList;
import java.util.Observable;

import model.elements.Air;
import model.elements.Diamond;
import model.elements.Dirt;
import model.elements.Element;
import model.elements.EndBlock;
import model.elements.Mob1;
import model.elements.Mob2;
import model.elements.PhysicElement;
import model.elements.Player;
import model.elements.Rock;
import model.elements.Wall;

public class Map extends Observable {

	public Element[][] map;
	public ArrayList<PhysicElement> physicElements;
	
	public Map() {
		this.physicElements = new ArrayList<PhysicElement>();
	}
	
	// Size of the map
	private int height;
	public ArrayList<PhysicElement> getPhysicElements() {
		return physicElements;
	}

	private int width;
	
	// Position of the end block
	private int[][] endblock;
	
	// The player element
	Player player;

	public void map(Element[][] existingMap) {
		this.height = 0;
		this.width = 0;
	}

	public void setMapFromString(String Contents) {

		// Setting size of the map
		this.map = new Element[this.width][this.height];

		// Cut the map for the width
		String[] mapString2D = Contents.split("\\r\\n");
		
		int y = 0;
		for (String lineTable : mapString2D) {
			for (int x = 0; x < lineTable.toCharArray().length; x++) {
				
				// Get the element at current x and y
				int element = Character.getNumericValue(lineTable.toCharArray()[x]);
				
				// Convert char to element
				// TODO: factory: map[x][y] = Factory.createElement("air");
				switch (element) {
				case 0:
					this.map[x][y] = new Air();
					break;
				case 1:
					this.map[x][y] = new Player();
					this.player = (Player) this.map[x][y]; // Add the player
					break;
				case 2:
					this.map[x][y] = new Wall();
					break;
				case 3:
					this.map[x][y] = new Dirt();
					break;
				case 4:
					this.map[x][y] = new Rock();
					this.physicElements.add((PhysicElement) this.map[x][y]);
					break;
				case 5:
					this.map[x][y] = new Diamond();
					this.physicElements.add((PhysicElement) this.map[x][y]);
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
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].setMap(this);
				map[i][j].setX(i);
				map[i][j].setY(j);
			}
		}
		
	}

	public Player getPlayer() {
		return player;
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
	
	public Element getElementAt(int x, int y) {
		// If the asked position is in the map
		if(x >= 0 && x < this.width && y >= 0 && y < this.height) {
			return map[x][y];
		}
		return null;
	}
	
	public Element setElementAt(int x, int y, Element element) {
		// If the asked position is in the map
		if(x >= 0 && x <= this.width && y >= 0 && y <= this.height) {
			map[x][y] = element;
		}
		return null;
	}
	
	public void printConsole() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				System.out.print(map[j][i].figure);
			}
			System.out.println();
		}
	}
	
	public final void setMapHasChanged(Element[][] map) {
		this.map = map;
		this.setChanged();
		this.notifyObservers();
	}
	
	public Observable getObservable() {
		return this;
	}
	
}
