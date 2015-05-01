package khleuven.mobile.brickbreaker.element;

import khleuven.mobile.KHLeuvenMobileException;

public class SquareElement extends GameElement implements Cloneable {
	private double height;
	private double width;

	public SquareElement(Point position, int height, int width, int color,
			int borderColor) throws KHLeuvenMobileException {
		super(position, color, borderColor);
		setHeight(height);
		setWidth(width);
	}

	public SquareElement(Point position, int height, int width, int color,
			int borderColor, Point speedAndDirection) throws KHLeuvenMobileException {
		super(position, color, borderColor, speedAndDirection);
		setHeight(height);
		setWidth(width);
	}

	public int getHeight() {
		return (int)height;
	}

	private void setHeight(double height) {
		this.height = height;
	}

	public int getWidth() {
		return  (int)width;
	}

	protected void setWidth(double width) {
		this.width = width;
	}
	
	@Override
	public int getLeft() {
		return getX();
	}

	@Override
	public int getTop() {
		return getY();
	}

	@Override
	public int getRight() {
		return (int)(getX() + getWidth());
	}

	@Override
	public int getBottom() {
		return (int)(getY() + getHeight());
	}

	@Override
	public void resize(double ratioWidth, double ratioHeight) throws KHLeuvenMobileException {
		super.resize(ratioWidth, ratioHeight);
		width = width * ratioWidth;
		height = height * ratioHeight;		
	}
	
	@Override
	public SquareElement clone() throws CloneNotSupportedException {
		SquareElement clone =  (SquareElement)super.clone();
		try {
			clone.setPosition(getPosition().clone());
		} catch (KHLeuvenMobileException e) {
			e.printStackTrace();
		}
		
		return clone;
	}
}
