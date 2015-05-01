package hitstrategy;

import domain.*;
import domain.element.*;

public class BrickCutter implements HitStrategy {

	public void hitBrick(BrickBreaker game, SquareElement brick) throws DomainException {
		if(brick.getWidth() <= brick.getHeight()) {
			game.getBricks().remove(brick);
			game.getSquareElements().remove(brick);
			game.setScore(game.getScore() + 50);
		} else {
			game.getBricks().remove(brick);
			game.getSquareElements().remove(brick);
			SquareElement newBrick1 = new SquareElement(brick.getPosition(), brick.getWidth()/2, brick.getHeight(), brick.getColor(), brick.getBorderColor());
			SquareElement newBrick2 = new SquareElement(new Point(newBrick1.getPosition().getX() + newBrick1.getWidth(), newBrick1.getHeight()), newBrick1.getWidth(), brick.getHeight(), brick.getColor(), brick.getBorderColor());
			game.getBricks().add(newBrick1);
			game.getSquareElements().add(newBrick1);
			game.getBricks().add(newBrick2);
			game.getSquareElements().add(newBrick2);
		}
	}
	
}
