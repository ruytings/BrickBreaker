package khleuven.mobile.brickbreaker.collision;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.element.GameElement;

public abstract class CollisionDetector {
	
	public boolean willMovingElementCollideWithStandingElement (GameElement moving, GameElement standing) throws KHLeuvenMobileException{
		GameElement movingclone;
		try {
			movingclone = moving.clone();
			movingclone.move();
			return isCollision(movingclone, standing);
		} catch (CloneNotSupportedException e) {
			throw new KHLeuvenMobileException(e.getMessage(), e);
		}		
	}
	
	public CollisionType getCollisionType(GameElement element, GameElement otherElement)throws KHLeuvenMobileException{
		CollisionType collisionType = CollisionType.NONE;
		GameElement movingclone;
		try {
			movingclone = element.clone();
			//System.out.println("moving before: "+element.getLeft()+" - "+element.getRight()+" - "+element.getBottom()+" - "+element.getTop());
			movingclone.move();
			//System.out.println("moving after: "+movingclone.getLeft()+" - "+movingclone.getRight()+" - "+movingclone.getBottom()+" - "+movingclone.getTop());
			//System.out.println("other: "+otherElement.getLeft()+" - "+otherElement.getRight()+" - "+otherElement.getBottom()+" - "+otherElement.getTop());
			
			if(isCollision(movingclone, otherElement)){
				if(isHorizontalCollision(movingclone, otherElement)){
					collisionType = CollisionType.HORIZONTAL;
				} else {
					collisionType = CollisionType.VERTICAL;
				}
			}
		} catch (CloneNotSupportedException e) {
			throw new KHLeuvenMobileException(e.getMessage(), e);
		}			
		return collisionType;
	}
	
	protected abstract boolean isCollision(GameElement element, GameElement otherElement);	
	protected abstract boolean isHorizontalCollision(GameElement element, GameElement otherElement);
}
