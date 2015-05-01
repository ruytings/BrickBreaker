package khleuven.mobile.brickbreaker.element;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.collision.CollisionDetectorInside;

public class Field extends SquareElement {
	public Field(Point position, int height, int width, int color,
			int borderColor) throws KHLeuvenMobileException {
		super(position, height, width, color, borderColor);
		setCollisionDetector(new CollisionDetectorInside());
	}
}
