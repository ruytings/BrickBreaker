package khleuven.mobile.brickbreaker.element;

import khleuven.mobile.KHLeuvenMobileException;

public class RoundElement extends GameElement implements Cloneable {
	private double radius;

	public RoundElement(Point position, int radius, int color, int borderColor)
			throws KHLeuvenMobileException {
		super(position, color, borderColor);
		setRadius(radius);		
	}

	public RoundElement(Point position, int radius, int color, int borderColor,
			Point speedAndDirection) throws KHLeuvenMobileException {
		super(position, color, borderColor, speedAndDirection);
		setRadius(radius);
	}

	public int getRadius() {
		return (int)radius;
	}

	private void setRadius(double radius) {
		this.radius = radius;
	}
	
	@Override
	public int getLeft() {
		return (int)(getX() - getRadius());
	}

	@Override
	public int getTop() {
		return (int)(getY() - getRadius());
	}

	@Override
	public int getRight() {
		return (int)(getX() + getRadius());
	}

	@Override
	public int getBottom() {
		return (int)(getY() + getRadius());
	}

	@Override
	public int getWidth() {
		return getRight() - getLeft();
	}

	@Override
	public int getHeight() {
		return getBottom() - getTop();
	}
	
	public int getXMid(){
		return getX();
	}
	
	public int getYMid(){
		return getY();
	}
	
	@Override
	public void resize(double ratioWidth, double ratioHeight) throws KHLeuvenMobileException {
		super.resize(ratioWidth, ratioHeight);
		radius = radius * ratioWidth;		
	}
}
