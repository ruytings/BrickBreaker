package khleuven.mobile.brickbreaker.hitstrategy;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.element.Brick;

public interface HitStrategy {
	public void processHit(Brick element) throws KHLeuvenMobileException;
}
