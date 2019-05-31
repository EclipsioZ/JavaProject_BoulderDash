package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.IModel;
import model.Map;
import model.elements.Air;
import model.elements.Element;
import model.elements.Explode;
import model.elements.Mob;
import model.elements.PhysicElement;
import model.elements.Player;

public class ElementThread implements Runnable {

	List<Element> animatedElements;
	List<Mob> mobs;
	List<PhysicElement> physicElements;
	Map map;
	int indexElementAnimation;
	public IModel model;

	public ElementThread(IModel model) {
		this.model = model;
		this.map = model.getMap();
		this.animatedElements = Collections.synchronizedList(model.getMap().getAnimatedElements());
		this.mobs = Collections.synchronizedList(model.getMap().getMobs());
		this.physicElements = Collections.synchronizedList(model.getMap().getPhysicElements());
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
			if (map.running) {
				try {
					Thread.sleep(160);

					List<Element> toRemove = new ArrayList<Element>();
					List<Mob> mobsClone = new ArrayList<Mob>(mobs);
					for (Mob mob : mobsClone) {
						if (mob.isAlive && map.isInTheMap(mob)) {
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
						if (physicElement.isAlive && map.isInTheMap(physicElement)) {
							if(physicElement.hasMoved) {
								physicElement.checkCanKillPlayer(physicElement.getX(), physicElement.getY());
							}
							if(physicElement.gravity()) {
								physicElement.hasMoved = true;
							} else {
								physicElement.hasMoved = false;
							}
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
						if (animatedElement.isAlive && map.isInTheMap(animatedElement)) {
							if (animatedElement.getIndexElementAnimation() <= animatedElement.getMaxAnimations() - 2) {
								animatedElement
										.setIndexElementAnimation(animatedElement.getIndexElementAnimation() + 1);
							} else {
								animatedElement.setIndexElementAnimation(0);
								if (animatedElement instanceof Explode) {
									if (!(map.getElementAt(animatedElement.getX(),
											animatedElement.getY()) instanceof Player)) {
										map.setElementAt(animatedElement.getX(), animatedElement.getY(), new Air(map));
									}
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
			} else {
				map.resetAllElements();
				map.running = true;
				model.resetMap();
			}
		}
	}

}
