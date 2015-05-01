package domain.element;

import java.awt.Color;

import domain.DomainException;

public class SquareElement extends GameElement {

	private int width;
	private int height;
	
	public SquareElement(Point position, int width, int height, Color color, Color borderColor) throws DomainException {
		super(position, color, borderColor);
		setWidth(width);
		setHeight(height);

	}
	
	private void setWidth(int width) throws DomainException {
		if(width < 0) {
			throw new DomainException("Width must be > 0.");
		}
		else this.width = width;
	}
	
	private void setHeight(int height) throws DomainException {
		if(height < 0) {
			throw new DomainException("Height must be > 0.");
		} 
		else this.height = height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getLeft() {
		return (int)this.getPosition().getX();
	}
	
	public int getTop() {
		return (int)this.getPosition().getY();
	}
	
	public int getRight() {
		return (int)this.getPosition().getX() + this.getWidth();
	}
	
	public int getBottom() {
		return (int)this.getPosition().getY() + this.getHeight();
	}
	
	public SquareElement(Point position, int width, int height, Color color, Color borderColor, Point speedAndDirection) throws DomainException {
		super(position, color, borderColor, speedAndDirection);
		setWidth(width);
		setHeight(height);
	}

	
}
