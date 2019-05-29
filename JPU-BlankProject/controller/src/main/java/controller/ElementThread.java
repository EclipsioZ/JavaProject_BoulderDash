package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import model.Map;
import model.elements.Block;
import model.elements.Element;
import model.elements.Mob;
import model.elements.PhysicElement;

public class ElementThread implements Runnable {

	List<Element> animatedElements;
	List<Mob> mobs;
	List<PhysicElement> physicElements;
	Map map;
	int indexElementAnimation;

	public ElementThread(Map map) {
		this.map = map;
		this.animatedElements = Collections.synchronizedList(map.getAnimatedElements());
		this.mobs = Collections.synchronizedList(map.getMobs());
		this.physicElements = Collections.synchronizedList(map.getPhysicElements());
		this.indexElementAnimation = 0;
	}

	public int getIndexElementAnimation() {
		return indexElementAnimation;
	}

	public void setIndexElementAnimation(int indexElementAnimation) {
		this.indexElementAnimation = indexElementAnimation;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(160);
				
				synchronized (mobs) {
				    Iterator<Mob> i = mobs.iterator();
				    while (i.hasNext()) {
				    	i.next().iaMove();
				    }
				}

				for (PhysicElement physicElement : physicElements) {
					physicElement.gravity();
				}

				this.indexElementAnimation++;
				if (this.indexElementAnimation > 3) {
					this.indexElementAnimation = 0;
				}
				for (Element animatedElement : animatedElements) {
					animatedElement.setIndexElementAnimation(this.indexElementAnimation);
				}

				map.setMapHasChanged(this.map.getMap());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
