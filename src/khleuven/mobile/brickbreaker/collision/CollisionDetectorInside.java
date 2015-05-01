package khleuven.mobile.brickbreaker.collision;

import khleuven.mobile.brickbreaker.element.GameElement;

public class CollisionDetectorInside extends CollisionDetector {

	// this only will work for the field!
	
	@Override
	protected boolean isCollision(GameElement element, GameElement otherElement) {
		return element.getBottom() > otherElement.getBottom() 
				|| element.getTop() < otherElement.getTop()
				|| element.getRight() > otherElement.getRight() 
				|| element.getLeft() < otherElement.getLeft();
	}

	@Override
	protected boolean isHorizontalCollision(GameElement element,
			GameElement otherElement) {
		return element.getRight() > otherElement.getRight() 
				|| element.getLeft() < otherElement.getLeft();
	}

}
