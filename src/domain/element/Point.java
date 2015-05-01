package domain.element;

public class Point {
	
	private double x;
	private double y;
	
	public Point(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public double getX() {
		return x;
	}
	private void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	private void setY(double y) {
		this.y = y;
	}
	
	public boolean equals(Object object){
		boolean res = false;
		if (object instanceof Point){
			if(((Point) object).getX() == this.getX() && ((Point) object).getY() == this.getY()){
				res = true;
			}
		}
		return res;
	}
	
}