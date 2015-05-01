package domain.collision;

import domain.DomainException;
import domain.element.GameElement;

public abstract class CollisionDetector {
	
	public boolean willMovingCollideWithStandingElement(GameElement moving, GameElement standing) throws DomainException, CloneNotSupportedException {
		GameElement clone = (GameElement) moving.clone();
		clone.move();
		return isCollision(clone, standing);
	}
	public boolean willMovingCollideWithStandingElementBricks(GameElement moving, GameElement standing) throws DomainException, CloneNotSupportedException {
		GameElement clone = (GameElement) moving.clone(); 
		clone.move();
		return isCollisionBricks(clone, standing);
	}
	
	protected abstract boolean isCollision(GameElement element, GameElement other);
	
	protected boolean isCollisionBricks(GameElement element, GameElement other) {
		if((element.getTop()) < (other.getBottom()) & element.getLeft() > other.getLeft() & element.getRight() < other.getRight()){
			return true;
		}
		else{     
			return false;
		}
	}
	public CollisionType getCollisionTypeBricks(GameElement element, GameElement other) {
		if(isCollisionBricks(element, other)) {
			if(isHorizontalCollision(element, other)) {
				return CollisionType.HORIZONTAL;
			} else return CollisionType.VERTICAL;
		} else return CollisionType.NONE;
	}
	public CollisionType getCollisionType(GameElement element, GameElement other) {
		if(isCollision(element, other)) {
			if(isHorizontalCollision(element, other)) {
				return CollisionType.HORIZONTAL;
			} else return CollisionType.VERTICAL;
		} else return CollisionType.NONE;
	}
	
	protected abstract boolean isHorizontalCollision(GameElement element, GameElement other);
	
	
}
