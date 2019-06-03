package model;

import java.util.Observable;

import model.IModel;
import model.db.DBGetData;
import model.elements.SoundEffect;

/**
 * The Class Model
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public final class Model extends Observable implements IModel {

	Map map;
	DBGetData db;
	
	AnimatedText animatedText;
	
	/** Current texture of the model */
	Texture tex = new Texture();
	
	int levelId;

	/**
	 * Instantiates a new model
	 * @throws Exception
	 */
	public Model() throws Exception {
		this.setLevelId(levelId);
		this.tex.getTexture(1);
		this.map = new Map();
		db = new DBGetData();
		db.loadLevel("1", map);
		map.setModel(this);
	}
	
	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	
	public void changeTexture(int id) {
		this.tex.getTexture(id);
	}

	public AnimatedText getAnimatedText() {
		return animatedText;
	}

	public void setAnimatedText(AnimatedText animatedText) {
		this.animatedText = animatedText;
	}

	public void updateMap(Map map) {
		this.getMap().setMapHasChanged(map.map);
	}

	public Observable getObservable() {
		return this;
	}

	public Texture getInstanceTexture() {
		return tex;
	}

	@Override
	public Map getMap() {
		return this.map;
	}

	public void resetMap() throws Exception {
		tex.getTexture(tex.idTexture);
		db.loadLevel(map.levelId, map);
		map.setModel(this);
		map.setTimer(120000);
		map.levelEnded = false;
	}
	
	public void loadMap(int idTexture, String mapId) throws Exception {
		tex.getTexture(idTexture);
		db.loadLevel(mapId, map);
		map.setModel(this);
		map.setTimer(120000);
		map.levelEnded = false;
		System.out.println("Map loaded: " + mapId);
	}

}
