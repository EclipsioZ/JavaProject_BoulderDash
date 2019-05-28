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

	public int getIndexAnimation() {
		return indexAnimation;
	}

	public void setIndexAnimation(int indexAnimation) {
		this.indexAnimation = indexAnimation;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(160);
				this.indexAnimation++;
				if(this.indexAnimation > 3) {
					this.indexAnimation = 0;
				}
				for (PhysicElement physicElement : physicElements) {
					physicElement.gravity();
					physicElement.setIndexAnimation(this.indexAnimation);
				}
				map.setMapHasChanged(this.map.getMap());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
