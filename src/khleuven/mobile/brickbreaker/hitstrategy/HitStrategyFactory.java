package khleuven.mobile.brickbreaker.hitstrategy;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.BrickBreaker;


public class HitStrategyFactory {
	public HitStrategy createHitStrategy(BrickBreaker game) throws KHLeuvenMobileException{
		HitStrategy hitStrategy = null;
		
		switch(game.getNumberOfLives()){
		case 2:
			hitStrategy = new RemoveColorStrategy();
			break;
		case 1:
			hitStrategy = new SplitStrategy(game);
			break;
		default:
			hitStrategy = new MarkHitStrategy();
		}
		return hitStrategy;
	}
}
