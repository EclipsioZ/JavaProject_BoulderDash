package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import contract.IController;
import model.AnimatedText;
import model.IModel;
import model.Map;
import model.elements.Air;
import model.elements.Element;
import model.elements.EndBlock;
import model.elements.Explode;
import model.elements.Mob;
import model.elements.PhysicElement;
import model.elements.Player;
import model.elements.SoundEffect;

/**
 * The Class ElementThread
 * 
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public class ElementThread implements Runnable {

	List<Element> animatedElements;
	List<Mob> mobs;
	List<PhysicElement> physicElements;

	Map map;
	int indexElementAnimation;

	IModel model;
	IController controller;

	/**
	 * Instantiates a new element thread
	 * 
	 * @param model      The model interface
	 * @param controller The controller interface
	 */
	public ElementThread(IModel model, IController controller) {
		this.model = model;
		this.controller = controller;
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

	/**
	 * Executed when the thread is started
	 */
	public void run() {
		while (true) {
			if (map.running) {
				try {
					// Time between loop (160ms)
					Thread.sleep(160);

					// Elements that will be removed after the loop are in that list
					List<Element> toRemove = new ArrayList<Element>();

					// To avoid ConcurrentModificationException => We can't edit a list while
					// iterating it
					// We have to make a clone of that list
					List<Mob> mobsClone = new ArrayList<Mob>(mobs);

					// The mobs
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

					// The elements that have a physic
					toRemove = new ArrayList<Element>();
					List<PhysicElement> physicElementsClone = new ArrayList<PhysicElement>(physicElements);
					for (PhysicElement physicElement : physicElementsClone) {
						if (physicElement.isAlive && map.isInTheMap(physicElement)) {
							if (physicElement.hasMoved) {
								physicElement.checkCanKillPlayer(physicElement.getX(), physicElement.getY());
							}
							if (physicElement.gravity()) {
								physicElement.hasMoved = true;
							} else {
								physicElement.hasMoved = false;
							}
						} else {
							toRemove.add(physicElement);
							if (map.running) // Avoid strange bugs on map reloading
								this.map.setElementAt(physicElement.getX(), physicElement.getY(),
										new Explode(this.map));
						}
					}
					for (Element toRem : toRemove) {
						physicElements.remove(toRem);
					}

					// The animated elements
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

					this.checkLastDiamond();
					this.updateTimer();
					this.checkReturnToMenu();

					if (map.getPlayer().restIndex >= 1) {
						map.getPlayer().restIndex += 1;
					}
					
					if (map.getPlayer().restIndex > 3) {
						map.getPlayer().restIndex = 0;
						model.getMap().getPlayer().setMaxAnimations(2);
						map.getPlayer().setDirection("REST");
					}

					// Update the map
					map.setMapHasChanged(this.map.getMap());

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				this.resetMap();
			}
		}
	}

	/**
	 * Check if the player as collected the last diamond, and display the animated
	 * text "EXIT"
	 */
	private void checkLastDiamond() {
		if (map.getPlayer().getDiamonds() >= map.getRequiredDiamonds() && !map.levelEnded) {
			SoundEffect sound = new SoundEffect();
			map.getPlayer().getSound().setSoundName("DiamondReach");
			map.levelEnded = true;
			map.setElementAt(map.getPosEndblock()[0], map.getPosEndblock()[1], new EndBlock(map));
			model.setAnimatedText(new AnimatedText());
		}
		if (model.getAnimatedText() != null) {
			model.getAnimatedText().setLifeTime(model.getAnimatedText().getLifeTime() - 1);
			if (model.getAnimatedText().getLifeTime() < 0) {
				model.setAnimatedText(null);
			}
		}
	}

	/**
	 * Update the timer and kill the player if it's bellow 0
	 */
	private void updateTimer() {
		map.setTimer(map.getTimer() - 160);
		if (map.getTimer() < 0) {
			map.getPlayer().die();
		}
	}

	/**
	 * Check if the player wants to return to the level selector
	 */
	private void checkReturnToMenu() {
		if (map.getPlayer().getReturnLevelSelector() == 1) {
			map.getPlayer().setReturnLevelSelector(0);
			this.controller.returnToMenu();
		}
	}

	/**
	 * If the map is not running, reset it
	 */
	private void resetMap() {
		map.resetAllElements();
		map.running = true;
		try {
			model.resetMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
