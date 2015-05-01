package khleuven.mobile.brickbreaker.element;

import khleuven.mobile.KHLeuvenMobileException;

public class Brick extends SquareElement {
	private int value;
	private boolean hit;

	public Brick(Point initialPosition, Point distanceAndSpeed, int height,
			int width, int value, int color, int borderColor)
			throws KHLeuvenMobileException {
		super(initialPosition, height, width, color, borderColor, distanceAndSpeed);
		setValue(value);
	}

	public int getValue() {
		return value;
	}

	private void setValue(int value) {
		this.value = value;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public void removeColor() throws KHLeuvenMobileException {
		setColor(0xFF000000);
	}

	public boolean isColorRemoved() {
		return getColor() == (0xFF000000);
	}

	public Brick split() throws KHLeuvenMobileException {
		Brick clone = null;
		try {
			int newWidth = getWidth() / 2;
			setWidth(newWidth);

			Point newPosition = getPosition().clone();
			newPosition.setX(getLeft() + newWidth);

			clone = (Brick)clone();
			clone.setPosition(newPosition);
		} catch (CloneNotSupportedException e) {
			throw new KHLeuvenMobileException(e.getMessage(), e);
		}
		return clone;
	}

	public SquareElement clone() throws CloneNotSupportedException {
		return (SquareElement) super.clone();
	}
}
