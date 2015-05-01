package hitstrategy;

import domain.BrickBreaker;
import domain.DomainException;
import domain.element.SquareElement;

public interface HitStrategy {
	
	public void hitBrick(BrickBreaker game, SquareElement brick) throws DomainException;

}
