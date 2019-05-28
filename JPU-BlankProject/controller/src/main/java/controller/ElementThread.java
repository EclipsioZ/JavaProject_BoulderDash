package controller;

import java.util.ArrayList;

import model.Map;
import model.elements.Element;
import model.elements.PhysicElement;

public class ElementThread implements Runnable {
	
	ArrayList<Element> Elements;
	Map map;
	int indexElementAnimation;
	
	public ElementThread(Map map) {
		this.map = map;
		this.Elements = map.getElements();
		this.indexElementAnimation = 0;
	}
	
	public int getIndexElementAnimation() {
		return indexElementAnimation;
	}

	public void setIndexElementAnimation(int indexElementAnimation) {
		this.indexElementAnimation = indexElementAnimation;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(160);
				this.indexElementAnimation++;
				if(this.indexElementAnimation > 3) {
					this.indexElementAnimation = 0;
				}
				for (Element Element : Elements) {
					Element.setIndexElementAnimation(this.indexElementAnimation);
				}
				map.setMapHasChanged(this.map.getMap());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
