package domain.collision;

import domain.element.GameElement;

public class InsideCollisionDetector extends CollisionDetector {

	@Override
	protected boolean isCollision(GameElement element, GameElement other) {
		boolean collision = false;
		if(element.getRight() > other.getRight() || element.getLeft() < other.getLeft()) {
			collision = true;
		}
		if(element.getTop() < other.getTop() || element.getBottom() > other.getBottom()) {
			collision = true;
		}
		return collision;
	}

	@Override
	protected boolean isHorizontalCollision(GameElement element, GameElement other) {
		if(element.getRight() > other.getRight() || element.getLeft() < other.getLeft()) {
			return true;
		} else return false;
	} 
	

}
