package model.elements;

public interface ElementStrategy {
	public Boolean canMove(int x, int y);
	public Boolean handleCollision(Element element);
	public void pop();
}
