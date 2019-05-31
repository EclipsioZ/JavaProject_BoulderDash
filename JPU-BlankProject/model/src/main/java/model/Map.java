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

public class Map extends Observable {
	
	IModel model;

	public Element[][] map;
	public List<PhysicElement> physicElements;
	public List<Element> animatedElements;
	public List<Mob> mobs;
	
	public String levelId;
	public Boolean running;

	public Map() {
		this.physicElements = Collections.synchronizedList(new ArrayList<PhysicElement>());
		this.animatedElements = Collections.synchronizedList(new ArrayList<Element>());
		this.mobs = Collections.synchronizedList(new ArrayList<Mob>());
		this.running = true;
		this.levelEnded = false;
	}

	// Size of the map
	private int height;

	public List<PhysicElement> getPhysicElements() {
		return physicElements;
	}

	public List<Element> getAnimatedElements() {
		return animatedElements;
	}

	public List<Mob> getMobs() {
		return mobs;
	}

	private int width;

	// Position of the end block
	private int[] endblock;

	// The player element
	Player player;

	private int requiredDiamonds;

	public Boolean levelEnded;

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

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].setMap(this);
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

	public int[] getPosEndblock() {
		return endblock;
	}

	public void setPosEndBlock(int[] endblock) {
		this.endblock = endblock;
	}

	public Element getElementAt(int x, int y) {
		// If the asked position is in the map
		if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
			return map[x][y];
		}
		return null;
	}

	public void setElementAt(int x, int y, Element element) {
		// If the asked position is in the map
		if (x >= 0 && x <= this.width && y >= 0 && y <= this.height) {
//			Element elementAt = this.getElementAt(x, y);
//			if(elementAt != null) {
//				elementAt.pop();
//			}
			element.setX(x);
			element.setY(y);
			element.setMap(this);
			map[x][y] = element;
		}
	}
	
	public Boolean isInTheMap(Element element) {
		Boolean ret = false;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j].uuid == element.uuid && map[i][j].getX() == element.getX() && map[i][j].getY() == element.getY()) {
					ret = true;
				}
			}
		}
		return ret;
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

	public void setModel(final IModel model) {
		this.model = model;
	}

	public IModel getModel() {
		return model;
	}
	
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

	public void setRequiredDiamonds(int reqDiamons) {
		this.requiredDiamonds = reqDiamons;
	}

}
