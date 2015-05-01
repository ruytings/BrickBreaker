package domain.collision;

import domain.element.GameElement;

public class OutsideCollisionDetector extends CollisionDetector {

	@Override
	protected boolean isCollision(GameElement element, GameElement other) {
		return element.getBottom() > other.getTop() && element.getTop() < other.getBottom() && element.getLeft() < other.getRight() && element.getRight() > other.getLeft();
	}

	@Override
	protected boolean isHorizontalCollision(GameElement element, GameElement other) {
		return false;
	} 
	

}
