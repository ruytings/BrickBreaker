package khleuven.mobile.brickbreaker.collision;

import khleuven.mobile.brickbreaker.element.GameElement;


public class CollisionDetectorOutside extends CollisionDetector {	

	
	@Override
	protected boolean isCollision(GameElement element, GameElement otherElement){
		int lm = element.getLeft();
		int rm = element.getRight();
		int bm = element.getBottom();
		int tm = element.getTop();
		int l = otherElement.getLeft();
		int r = otherElement.getRight();
		int b = otherElement.getBottom();
		int t = otherElement.getTop();
		
		return //(lm >= l && lm <= r && bm >= t && bm <= b)||
			   //(lm >= l && lm <= r && tm >= t && tm <= b)||
			   //(rm >= l && rm <= r && bm >= t && bm <= b)||
			   //(rm >= l && rm <= r && tm >= t && tm <= b);	
				(rm >= l && lm <= r && bm >= t && tm <= b);	
	}
	
	@Override
	protected boolean isHorizontalCollision(GameElement element, GameElement otherElement){
		int lm = element.getLeft();
		int rm = element.getRight();
		int l = otherElement.getLeft();
		int r = otherElement.getRight();
		
		return !((lm+rm)/2 >= l && (lm+rm)/2 <= r);	
	}
}
