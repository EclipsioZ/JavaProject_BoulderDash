package model.elements;

public abstract class Mob extends Element {

	private int explosionRadius;
	private String explosionType;

	public Mob() {
		super();
		explosionRadius = 3;
		explosionType = "Air";
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
					if (!(element instanceof Player) && !(element instanceof Wall)) {
						if(this.getExplosionType() == "Diamond") {
							this.getMap().setElementAt(xCenter + x, yCenter + y, new Diamond());
						} else {
							this.getMap().setElementAt(xCenter + x, yCenter + y, new Air());
						}
					}
				}
			}
		}

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

	// The mob IA
	// Each mob will first try to move right, then up, then left, then down
	public void iaMove() {
		Element upEl = this.getMap().getElementAt(this.getX(), this.getY() - 1);
		Element downEl = this.getMap().getElementAt(this.getX(), this.getY() + 1);
		Element rightEl = this.getMap().getElementAt(this.getX() + 1, this.getY());
		Element leftEl = this.getMap().getElementAt(this.getX() - 1, this.getY());

		if (rightEl instanceof Air) {
			this.move(this.getX() + 1, this.getY());
		} else if (upEl instanceof Air) {
			this.move(this.getX(), this.getY() - 1);
		} else if (leftEl instanceof Air) {
			this.move(this.getX() - 1, this.getY());
		} else if (downEl instanceof Air) {
			this.move(this.getX(), this.getY() + 1);
		}
	}

}
