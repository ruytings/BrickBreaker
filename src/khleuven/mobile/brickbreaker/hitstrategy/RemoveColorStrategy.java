package khleuven.mobile.brickbreaker.hitstrategy;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.element.Brick;

public class RemoveColorStrategy implements HitStrategy {
	
	@Override
	public void processHit(Brick element) throws KHLeuvenMobileException{
		if(element.isColorRemoved()){
			element.setHit(true);
		} else {
			element.removeColor();
		}
	}
}
