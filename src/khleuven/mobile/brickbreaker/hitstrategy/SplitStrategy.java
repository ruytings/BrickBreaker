package khleuven.mobile.brickbreaker.hitstrategy;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.BrickBreaker;
import khleuven.mobile.brickbreaker.element.Brick;

public class SplitStrategy implements HitStrategy {
	private BrickBreaker game;

	public SplitStrategy(BrickBreaker game) throws KHLeuvenMobileException{
		setGame(game);
	}
	
	@Override
	public void processHit(Brick element) throws KHLeuvenMobileException {
		if(element.getHeight() > element.getWidth()){
			element.setHit(true);
		} else {
			Brick newElement = element.split();
			getGame().addBrick(newElement);
		}

	}

	private BrickBreaker getGame() {
		return game;
	}

	private void setGame(BrickBreaker game) {
		this.game = game;
	}
}
