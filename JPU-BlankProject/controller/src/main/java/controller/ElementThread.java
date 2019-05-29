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

				List<Element> toRemove = new ArrayList<Element>();
				for (Mob mob : mobs) {
					if(mob.isAlive) {
						mob.iaMove();
					}
					if (!map.isInTheMap(mob) || !mob.isAlive) {
						toRemove.add(mob);
					}
					System.out.println(mob.isAlive);
				}
				for (Element toRem : toRemove) {
					mobs.remove(toRem);
				}

				toRemove = new ArrayList<Element>();
				for (PhysicElement physicElement : physicElements) {
					physicElement.gravity();
					if (!map.isInTheMap(physicElement)) {
						toRemove.add(physicElement);
					}
				}
				for (Element toRem : toRemove) {
					physicElements.remove(toRem);
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
