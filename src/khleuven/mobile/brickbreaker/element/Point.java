package khleuven.mobile.brickbreaker.element;


public class Point implements Cloneable{
	private double x,y;

	public Point() {
	}
	
	public Point(double x, double y) {
		super();
		this.setX(x);
		this.setY(y);
	}

	public void setXNegative(){
		setX(0 - Math.abs(getX()));
	}
	
	public void setXPositive(){
		setX(Math.abs(getX()));
	}
	
	public void move (Point distance) {
		setX(getX() + distance.getX());
		setY(getY() + distance.getY());
	}

	public void invertX() {
		setX(0 - getX());
	}

	public void invertY() {
		setY(0 - getY());
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object object){
		boolean equal = false;
		if(object instanceof Point){
			Point other = (Point)object;
			equal = getX() == other.getX() && getY() == other.getY(); 
		}
		return equal;
	}

	@Override
	protected Point clone() throws CloneNotSupportedException {
		return (Point)super.clone();
	}
	
}
