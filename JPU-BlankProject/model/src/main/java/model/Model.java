package model;

import java.util.Observable;

import model.IModel;
import entity.HelloWorld;
import model.bdd.BDDGetData;

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

	public Model() {
        tex.getTexture(5);
		this.helloWorld = new HelloWorld();
		this.map = new Map();
		bdd = new BDDGetData();
        bdd.loadLevel("20", map);
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
        tex.getTexture(5);
		for (int i = 0; i < map.getMap().length; i++) {
			for (int j = 0; j < map.getMap()[i].length; j++) {
				map.getPhysicElements().clear();
				map.getMobs().clear();
				map.getAnimatedElements().clear();
			}
		}
//		map = new Map();
        bdd.loadLevel("20", map);
        map.setModel(this);
    }
}
