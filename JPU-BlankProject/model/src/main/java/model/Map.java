package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import model.elements.Air;
import model.elements.Diamond;
import model.elements.Dirt;
import model.elements.Element;
import model.elements.EndBlock;
import model.elements.Mob;
import model.elements.Mob1;
import model.elements.Mob2;
import model.elements.PhysicElement;
import model.elements.Player;
import model.elements.Rock;
import model.elements.Wall;

/**
 * The Class Map
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public class Map extends Observable {

	IModel model;

	/** 2D Array corresponding the map */
	public Element[][] map;

	public List<PhysicElement> physicElements;
	public List<Element> animatedElements;
	public List<Mob> mobs;

	public String levelId;

	/** State of the map */
	public Boolean running;

	/** Timer in ms */
	private int timer;

	/** Size of the map */
	private int height;
	private int width;

	/** Position of the end block */
	private int[] endblock;

	/** The player element */
	Player player;

	/** Amount of diamonds required to finish the map */
	private int requiredDiamonds;

	/** Is the level ended */
	public Boolean levelEnded;

	/**
	 * Instantiates a new map
	 */
	public Map() {
		this.physicElements = Collections.synchronizedList(new ArrayList<PhysicElement>());
		this.animatedElements = Collections.synchronizedList(new ArrayList<Element>());
		this.mobs = Collections.synchronizedList(new ArrayList<Mob>());
		this.running = true;
		this.levelEnded = false;
		this.timer = 120000;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public List<PhysicElement> getPhysicElements() {
		return physicElements;
	}

	public List<Element> getAnimatedElements() {
		return animatedElements;
	}

	public List<Mob> getMobs() {
		return mobs;
	}

	public void map(Element[][] existingMap) {
		this.height = 0;
		this.width = 0;
	}

	public int getRequiredDiamonds() {
		return requiredDiamonds;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Create a new map from given content
	 * 
	 * @param Contents The content of the map read from the database
	 */
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
				switch (element) {
				case 0:
					this.setElementAt(x, y, new Air(this));
					break;
				case 1:
					this.setElementAt(x, y, new Player(this));
					this.player = (Player) this.map[x][y]; // Add the player
					break;
				case 2:
					this.setElementAt(x, y, new Wall(this));
					break;
				case 3:
					this.setElementAt(x, y, new Dirt(this));
					break;
				case 4:
					this.setElementAt(x, y, new Rock(this));
					break;
				case 5:
					this.setElementAt(x, y, new Diamond(this));
					break;
				case 6:
					this.setElementAt(x, y, new Mob1(this));
					break;
				case 7:
					this.setElementAt(x, y, new Mob2(this));
					break;
				case 8:
					this.setElementAt(x, y, new EndBlock(this));
					break;
				default:
					this.setElementAt(x, y, new Air(this));
					break;
				}
			}
			y++;
		}

		// Add the map object to elements
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].setMap(this);
			}
		}

	}

	public Player getPlayer() {
		return player;
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

	public int[] getPosEndblock() {
		return endblock;
	}

	public void setPosEndBlock(int[] endblock) {
		this.endblock = endblock;
	}

	/**
	 * Get the element at given position
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return The element
	 */
	public Element getElementAt(int x, int y) {
		// If the asked position is in the map
		if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
			return map[x][y];
		}
		return null;
	}

	/**
	 * Set an element at at given position
	 * 
	 * @param x       The x coordinate
	 * @param y       The y coordinate
	 * @param element The element
	 */
	public void setElementAt(int x, int y, Element element) {
		// If the asked position is in the map
		if (x >= 0 && x <= this.width && y >= 0 && y <= this.height) {
			element.setX(x);
			element.setY(y);
			element.setMap(this);
			map[x][y] = element;
		}
	}

	/**
	 * Check if the element exists in the map
	 * 
	 * @param element The element
	 * @return True if the element is in the map, false if not
	 */
	public Boolean isInTheMap(Element element) {
		Boolean ret = false;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].uuid == element.uuid && map[i][j].getX() == element.getX()
						&& map[i][j].getY() == element.getY()) {
					ret = true;
				}
			}
		}
		return ret;
	}

	/**
	 * Print the map into the console
	 */
	public void printConsole() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				System.out.print(map[j][i].figure);
			}
			System.out.println();
		}
	}

	/**
	 * Notify observers when map has changed
	 * 
	 * @param map The map (array)
	 */
	public final void setMapHasChanged(Element[][] map) {
		this.map = map;
		this.setChanged();
		this.notifyObservers();
	}

	public Observable getObservable() {
		return this;
	}

	public void setModel(final IModel model) {
		this.model = model;
	}

	public IModel getModel() {
		return model;
	}

	/**
	 * Remove all the elements in the map
	 */
	public void resetAllElements() {
		this.getPhysicElements().clear();
		this.getMobs().clear();
		this.getAnimatedElements().clear();
		for (int i = 0; i < this.getMap().length; i++) {
			for (int j = 0; j < this.getMap()[i].length; j++) {
				this.getElementAt(i, j).pop();
				this.setElementAt(i, j, new Air(this));
			}
		}
	}

	/**
	 * Set the required amount of diamonds. Must be between 0 and 100
	 * 
	 * @param reqDiamonds The number of diamonds
	 */
	public void setRequiredDiamonds(int reqDiamonds) {
		if (reqDiamonds < 0) {
			reqDiamonds = 0;
		}
		if (reqDiamonds > 100) {
			reqDiamonds = 100;
		}
		this.requiredDiamonds = reqDiamonds;
	}

}
