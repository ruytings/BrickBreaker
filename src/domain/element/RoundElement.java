package domain.element;

import java.awt.Color;

import domain.DomainException;

public class RoundElement extends GameElement {

	private int radius;
	
	public RoundElement(Point position, int radius, Color color, Color bordercolor)throws DomainException{
		super(position, color, bordercolor);
		setRadius(radius);
	}
	
	private void setRadius(int radius) throws DomainException{
		if(radius < 0) {
			throw new DomainException("Radius must be > 0.");
		}
		this.radius = radius;
	}
	
	public int getRadius(){
		return this.radius;
	}
	
	public int getTop(){
		return (int)this.getPosition().getY() - radius;
	}
	
	public int getRight(){
		return (int)this.getPosition().getX() + radius;
	}
	
	public int getBottom(){
		return (int)this.getPosition().getY() + radius;
	}
	
	public int getLeft(){
		return (int)this.getPosition().getX() - radius;
	}
	
	public int getWidth(){
		return 2*radius;
	}
	
	public int getHeight(){
		return 2*radius;
	}
	
	public RoundElement(Point position, int radius, Color color, Color borderColor, Point speedAndDirection) throws DomainException {
		super(position, color, borderColor, speedAndDirection);
		setRadius(radius);
	}
}
