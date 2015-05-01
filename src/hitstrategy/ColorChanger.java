package hitstrategy;

import java.awt.Color;

import domain.*;
import domain.element.*;

public class ColorChanger implements HitStrategy {
	
	public void hitBrick(BrickBreaker game, SquareElement brick) throws DomainException {
		if(brick.getColor() != Color.DARK_GRAY) {
			game.getSquareElements().remove(brick);
			game.getBricks().remove(brick);
			brick.setColor(Color.DARK_GRAY);
			game.getSquareElements().add(brick);
			game.getBricks().add(brick);
		} else {
			game.getBricks().remove(brick);
			game.getSquareElements().remove(brick);
			game.setScore(game.getScore() + 50);
		}
		
	}
	


}
