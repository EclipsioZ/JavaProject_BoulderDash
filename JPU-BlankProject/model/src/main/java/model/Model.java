package model;

import java.util.Observable;

import model.IModel;
import entity.HelloWorld;
import model.bdd.BDDGetData;
import model.elements.Air;

/**
 * The Class Model.
 *
 * @author Jean-Aymeric Diet
 */
public final class Model extends Observable implements IModel {

	/** The helloWorld. */
	private HelloWorld helloWorld;
	Map map;
	BDDGetData bdd;

	Texture tex = new Texture();

	public Model() throws Exception {
		tex.getTexture(3);
		this.helloWorld = new HelloWorld();
		this.map = new Map();
		bdd = new BDDGetData();
		bdd.loadLevel("17", map);
		map.setModel(this);
	}

	public HelloWorld getHelloWorld() {
		return this.helloWorld;
	}

	public void updateMap(Map map) {
		this.getMap().setMapHasChanged(map.map);
	}

	public Observable getObservable() {
		return this;
	}

	@Override
	public void loadHelloWorld(String code) {
		// TODO Auto-generated method stub

	}

	public Texture getInstanceTexture() {
		return tex;
	}

	@Override
	public Map getMap() {
		return this.map;
	}

	public void resetMap() {
		tex.getTexture(tex.idTexture);
		bdd.loadLevel(map.levelId, map);
		map.setModel(this);
	}
	
	public void loadMap(int idTexture, String mapId) {
		tex.getTexture(idTexture);
		bdd.loadLevel(mapId, map);
		map.setModel(this);
	}

}
