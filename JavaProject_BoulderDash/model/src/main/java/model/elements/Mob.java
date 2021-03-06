package model.elements;

import model.Map;

public abstract class Mob extends Element implements ElementStrategy {

	private int explosionRadius;
	private String explosionType;

	public String direction;

	public Mob(Map map) {
		super(map);
		explosionRadius = 2;
		explosionType = "Air";
		this.direction = "down";

		// Add this element to the animated elements
		this.getMap().getAnimatedElements().add(this);

		// Add the mob to the mob list
		this.getMap().getMobs().add(this);
	}

	/**
	 * Explode the mob in a circle with a radius
	 */
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

	/**
	 * The mob IA
	 */
	public void iaMove() {
		Element upEl = this.getMap().getElementAt(this.getX(), this.getY() - 1);
		Element downEl = this.getMap().getElementAt(this.getX(), this.getY() + 1);
		Element rightEl = this.getMap().getElementAt(this.getX() + 1, this.getY());
		Element leftEl = this.getMap().getElementAt(this.getX() - 1, this.getY());

		if ((downEl instanceof Air || downEl instanceof Player) && direction != "up") {
			direction = "down";
			this.getMap().getElementAt(this.getX(), this.getY() + 1).handleCollision(this);
			this.move(this.getX(), this.getY() + 1);
		} else if ((leftEl instanceof Air || leftEl instanceof Player) && direction != "right") {
			direction = "left";
			this.getMap().getElementAt(this.getX() - 1, this.getY()).handleCollision(this);
			this.move(this.getX() - 1, this.getY());
		} else if ((upEl instanceof Air || upEl instanceof Player) && direction != "down") {
			direction = "up";
			this.getMap().getElementAt(this.getX(), this.getY() - 1).handleCollision(this);
			this.move(this.getX(), this.getY() - 1);
		} else if ((rightEl instanceof Air || rightEl instanceof Player) && direction != "left") {
			direction = "right";
			this.getMap().getElementAt(this.getX() + 1, this.getY()).handleCollision(this);
			this.move(this.getX() + 1, this.getY());
		} else {
			direction = "";
		}
	}

	@Override
	public Boolean handleCollision(Element element) {
		if (element instanceof PhysicElement) {
			this.getMap().getPlayer().getSound().changeSound("Explode");
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
