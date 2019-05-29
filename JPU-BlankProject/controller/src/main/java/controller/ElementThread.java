package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import model.Map;
import model.elements.Air;
import model.elements.Block;
import model.elements.Diamond;
import model.elements.Element;
import model.elements.Explode;
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
					if (mob.isAlive) {
						mob.iaMove();
					} else {
						toRemove.add(mob);
					}
				}
				for (Element toRem : toRemove) {
					mobs.remove(toRem);
				}

				toRemove = new ArrayList<Element>();
				List<PhysicElement> physicElementsClone = new ArrayList<PhysicElement>(physicElements);
				for (PhysicElement physicElement : physicElementsClone) {
					if (physicElement.isAlive) {
						physicElement.gravity();
					} else {
						toRemove.add(physicElement);
						this.map.setElementAt(physicElement.getX(), physicElement.getY(), new Explode(this.map));
					}
				}
				for (Element toRem : toRemove) {
					physicElements.remove(toRem);
				}

				toRemove = new ArrayList<Element>();
				List<Element> animatedElementsClone = new ArrayList<Element>(animatedElements);
				for (Element animatedElement : animatedElementsClone) {
					if (animatedElement.isAlive) {
						if(animatedElement.getIndexElementAnimation() <= animatedElement.getMaxAnimations() - 2) {
							animatedElement.setIndexElementAnimation(animatedElement.getIndexElementAnimation() + 1);
						} else {
							animatedElement.setIndexElementAnimation(0);
							if(animatedElement instanceof Explode) {
								map.setElementAt(animatedElement.getX(), animatedElement.getY(), new Air(map));
								toRemove.add(animatedElement);
							}
						}
					} else {
						toRemove.add(animatedElement);
					}
				}
				for (Element toRem : toRemove) {
					animatedElements.remove(toRem);
				}

				map.setMapHasChanged(this.map.getMap());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
