package khleuven.mobile.brickbreaker.hitstrategy;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.element.Brick;

public class MarkHitStrategy implements HitStrategy {

	@Override
	public void processHit(Brick element) throws KHLeuvenMobileException {
		element.setHit(true);
	}

}
