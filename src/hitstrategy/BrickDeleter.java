package hitstrategy;

import domain.*;
import domain.element.*;

public class BrickDeleter implements HitStrategy {
	
	public void hitBrick(BrickBreaker game, SquareElement brick) throws DomainException {
		game.getBricks().remove(brick);
		game.getSquareElements().remove(brick);
		game.setScore(game.getScore() + 50);
	}

}
