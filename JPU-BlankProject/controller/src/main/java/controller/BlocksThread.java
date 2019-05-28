package controller;

import java.util.ArrayList;

import model.Map;
import model.elements.PhysicElement;
import model.elements.Rock;

public class BlocksThread implements Runnable {

	ArrayList<PhysicElement> physicElements;
	Map map;
	int indexAnimation;
	
	public BlocksThread(Map map) {
		this.map = map;
		this.physicElements = map.getPhysicElements();
		this.indexAnimation = 0;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(200);
				for (PhysicElement physicElement : physicElements) {
					physicElement.gravity();
				}
				map.setMapHasChanged(this.map.getMap());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
