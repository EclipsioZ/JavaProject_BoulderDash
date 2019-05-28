package controller;

import java.util.ArrayList;

import model.Map;
import model.elements.Block;
import model.elements.Element;
import model.elements.Mob;
import model.elements.PhysicElement;

public class ElementThread implements Runnable {

	ArrayList<Element> animatedElements;
	ArrayList<Mob> mobs;
	ArrayList<PhysicElement> physicElements;
	Map map;
	int indexElementAnimation;

	public ElementThread(Map map) {
		this.map = map;
		this.animatedElements = map.getAnimatedElements();
		this.mobs = map.getMobs();
		this.physicElements = map.getPhysicElements();
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

				for (Mob mob : mobs) {
					mob.iaMove();
				}

				for (PhysicElement physicElement : physicElements) {
					physicElement.gravity();
				}

				this.indexElementAnimation++;
				if (this.indexElementAnimation > 3) {
					this.indexElementAnimation = 0;
				}
				for (Element Element : animatedElements) {
					Element.setIndexElementAnimation(this.indexElementAnimation);
				}

				map.setMapHasChanged(this.map.getMap());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
