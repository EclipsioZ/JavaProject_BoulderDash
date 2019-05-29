package model.elements;

import model.Map;

public abstract class Mob extends Element {

	private int explosionRadius;
	private String explosionType;

	public String direction;
	
	public Mob(Map map) {
		super(map);
		explosionRadius = 3;
		explosionType = "Air";
		this.direction = "down";
				
		// Add this element to the animated elements
		this.getMap().getAnimatedElements().add(this);
		
		// Add the mob to the mob list
		this.getMap().getMobs().add(this);
	}

	public void explode() {
		
		int xCenter = this.getX();
		int yCenter = this.getY();
		int radius = this.getExplosionRadius();

		// Create a circle in pixels
		for (int y = -radius; y <= radius; y++) {
			for (int x = -radius; x <= radius; x++) {
				if (x * x + y * y <= radius * radius) {
					Element element = this.getMap().getElementAt(xCenter + x, yCenter + y);
					if (element != null) {
						if (!(element instanceof Player) && !(element instanceof Wall)) {
							element.isAlive = false;
							if (this.getExplosionType() == "Diamond") {
								this.getMap().setElementAt(xCenter + x, yCenter + y, new Diamond(getMap()));
							} else {
								this.getMap().setElementAt(xCenter + x, yCenter + y, new Explode(getMap()));
							}
						}
					}

				}
			}
		}
		
		this.getMap().setElementAt(this.getX(), this.getY(), new Air(this.getMap()));
		this.isAlive = false;
		
	}

	public int getExplosionRadius() {
		return explosionRadius;
	}

	public void setExplosionRadius(int explosionRadius) {
		this.explosionRadius = explosionRadius;
	}

	public String getExplosionType() {
		return explosionType;
	}

	public void setExplosionType(String explosionType) {
		this.explosionType = explosionType;
	}

	@Override
	public Boolean canMove(int x, int y) {
		Element el = this.getMap().getElementAt(x, y);
		if (el instanceof Air) {
			return true;
		}
		return false;
	}

	// The mob IA
	// Each mob will first try to move right, then up, then left, then down
	public void iaMove() {
		Element upEl = this.getMap().getElementAt(this.getX(), this.getY() - 1);
		Element downEl = this.getMap().getElementAt(this.getX(), this.getY() + 1);
		Element rightEl = this.getMap().getElementAt(this.getX() + 1, this.getY());
		Element leftEl = this.getMap().getElementAt(this.getX() - 1, this.getY());
		Element rightDownEl = this.getMap().getElementAt(this.getX() + 1, this.getY() + 1);
		Element leftDownEl = this.getMap().getElementAt(this.getX() - 1, this.getY() + 1);
		Element rightUpEl = this.getMap().getElementAt(this.getX() + 1, this.getY() - 1);
		Element leftUpEl = this.getMap().getElementAt(this.getX() - 1, this.getY() - 1);

		if (downEl instanceof Air && direction != "up") {
			direction = "down";
			this.move(this.getX(), this.getY() + 1);
		} else if (leftEl instanceof Air && direction != "right") {
			direction = "left";
			this.move(this.getX() - 1, this.getY());
		} else if (upEl instanceof Air && direction != "down") {
			direction = "up";
			this.move(this.getX(), this.getY() - 1);
		} else if (rightEl instanceof Air && direction != "left") {
			direction = "right";
			this.move(this.getX() + 1, this.getY());
		} else {
			direction = "";
		}
	}

	@Override
	public Boolean handleCollision(Element element) {
		if (element instanceof Rock) {
			this.explode();
		}
		return true;
	}
	
	@Override
	public void pop() {
		this.getMap().getAnimatedElements().remove(this);
		this.getMap().getMobs().remove(this);
	}

}
