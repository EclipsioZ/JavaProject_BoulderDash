package model.elements;

public interface ElementStrategy {
	
	/**
	 * Check if it can move to given coordinates
	 * 
	 * @param x The x
	 * @param y The y
	 * @return True if it can move, false if not
	 */
	public Boolean canMove(int x, int y);
	
	/**
	 * Handle a collision
	 * 
	 * @param element The element that it handle the collision with
	 * @return False if it can go through, true if not
	 */
	public Boolean handleCollision(Element element);
	
	/**
	 * Remove the element from all array lists it belongs to
	 */
	public void pop();
}
