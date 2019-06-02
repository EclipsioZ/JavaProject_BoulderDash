package model;

/**
 * The Class AnimatedText
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public class AnimatedText {
	
	/** The life time of the text. 1 lifetime = 160ms */
	private int lifeTime;
	
	/** The current text value */
	private String text;
	
	/** The position of the text */
	private int x;
	private int y;

	/**
	 * Instantiates a new animated text
	 */
	public AnimatedText() {
		this.lifeTime = 5;
		this.text = "EXIT";
	}
	
	public int getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
