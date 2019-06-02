package model;

import model.elements.Diamond;
import model.elements.Element;
import model.elements.Mob1;
import model.elements.Player;
import model.elements.Rock;

/**
 * The Class Animation
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public class Animation implements Runnable {
	
	Map map;
	int indexAnimationRock;
	
	public Animation(Map map) {
		this.map = map;
		this.indexAnimationRock = 0;
	}

	public void loadAnimation(){
		
		
		System.out.println(map);
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				Element element = map.getElementAt(x, y);
				
				if(element instanceof Rock) {	
					for(int j=0; j < Texture.rock.length; j++) {
						
					element.getSprites().add(Texture.rock[j]);
					}
				}
				
				if(element instanceof Diamond) {	
					
					for(int j=0; j < Texture.diamond.length; j++) {
						
					element.getSprites().add(Texture.diamond[j]);
					}
				}
			}
		}
	}

	@Override
	public void run() {
		while(true) {
			if(this.indexAnimationRock >= 3) {
				this.indexAnimationRock = 0;
			}
			this.indexAnimationRock++;
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getIndexAnimationRock() {
		return indexAnimationRock;
	}

/*	private void runAnimation() {
		
		loadAnimation();
		
	}*/
	
}
