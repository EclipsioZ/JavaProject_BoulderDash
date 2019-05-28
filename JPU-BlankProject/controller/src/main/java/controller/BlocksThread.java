package controller;

import java.util.ArrayList;

import model.Map;
import model.elements.Rock;

public class BlocksThread implements Runnable {

	ArrayList<Rock> rocks;
	Map map;
	
	public BlocksThread(Map map) {
		this.map = map;
		this.rocks = map.getRocks();
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(200);
				for (Rock rock : rocks) {
					rock.gravity();
				}
				map.setMapHasChanged(this.map.getMap());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
