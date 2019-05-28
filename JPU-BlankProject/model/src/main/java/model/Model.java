package model;

import java.sql.SQLException;
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
        tex.getTexture();
		this.helloWorld = new HelloWorld();
		this.map = new Map();
		bdd = new BDDGetData();
        bdd.loadLevel("13", map);
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
}
