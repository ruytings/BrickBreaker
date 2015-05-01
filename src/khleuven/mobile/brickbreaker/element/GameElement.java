package khleuven.mobile.brickbreaker.element;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.collision.CollisionDetector;
import khleuven.mobile.brickbreaker.collision.CollisionDetectorOutside;
import khleuven.mobile.brickbreaker.collision.CollisionType;

public abstract class GameElement {

	private Point originalPosition;
	private Point position;
	private int color;
	private int borderColor;
	private Point speedAndDirection = new Point();
	private Point originalSpeedAndDirection = new Point();
	private CollisionDetector collisionDetector;

	public GameElement(Point position, int color, int borderColor) throws KHLeuvenMobileException {
		setColor(color);
		setBorderColor(borderColor);
		setOriginalPosition(position);
		reset();
		setCollisionDetector(new CollisionDetectorOutside());
	}
	
	public GameElement(Point position, int color, int borderColor, Point speedAndDirection) throws KHLeuvenMobileException {
		this(position, color, borderColor);
		setOriginalSpeedAndDirection(speedAndDirection);
	}
	
	public void move() throws KHLeuvenMobileException{
		getPosition().move(getSpeedAndDirection()); 
	}
	
	public void bounceHorizontal() throws KHLeuvenMobileException{
		getSpeedAndDirection().invertX();
	}
	
	public void bounceVerticall() throws KHLeuvenMobileException{
		getSpeedAndDirection().invertY();
	}
	
	public void setDirectionToRight(){
		getSpeedAndDirection().setXPositive();
	}
	
	public void setDirectionToLeft(){
		getSpeedAndDirection().setXNegative();
	}
	
	public void reset() throws KHLeuvenMobileException {
		try {
			setPosition(getOriginalPosition().clone());
			setSpeedAndDirection(getOriginalSpeedAndDirection().clone());
		} catch (CloneNotSupportedException e) {
			throw new KHLeuvenMobileException("Problem resetting");
		}
	}
	
	public boolean willCollideWithMovingElement (GameElement moving) throws KHLeuvenMobileException{
		return getCollisionDetector().willMovingElementCollideWithStandingElement(moving, this);
	}
	
	public CollisionType getCollisionType(GameElement element, GameElement otherElement) throws KHLeuvenMobileException {
		return getCollisionDetector().getCollisionType(element, otherElement);
	}

	public abstract int getLeft() ;
	public abstract int getTop();
	public abstract int getRight();
	public abstract int getBottom();
	public abstract int getWidth();
	public abstract int getHeight();
	
	public CollisionDetector getCollisionDetector(){
		return collisionDetector;
	}

	protected void setCollisionDetector(CollisionDetector collisionDetector){
		this.collisionDetector = collisionDetector;
	}
	
	
	public int getBorderColor() {
		return borderColor;
	}

	private void setBorderColor(int borderColor) throws KHLeuvenMobileException {
		this.borderColor = borderColor;
	}

	public int getColor() {
		return color;
	}

	protected void setColor(int color) throws KHLeuvenMobileException {
		this.color = color;
	}

	protected Point getPosition() {
		return position;
	}

	protected void setPosition(Point position) throws KHLeuvenMobileException {
		if (position == null) {
			throw new KHLeuvenMobileException("Positon should not be null");
		}
		this.position = position;
	}

	private Point getOriginalPosition() {
		return originalPosition;
	}

	private void setOriginalPosition(Point originalPosition) {
		this.originalPosition = originalPosition;
	}

	protected int getX() {
		return (int)getPosition().getX();
	}

	protected int getY() {
		return (int)getPosition().getY();
	}
	
	protected Point getSpeedAndDirection() {
		return speedAndDirection;
	}
	
	protected void setSpeedAndDirection(Point speedAndDirection) throws KHLeuvenMobileException {
		if(speedAndDirection == null){
			throw new KHLeuvenMobileException("Speed and Direction should not be null");
		}
		this.speedAndDirection = speedAndDirection;
	}

	private Point getOriginalSpeedAndDirection() {
		return originalSpeedAndDirection;
	}

	private void setOriginalSpeedAndDirection(Point speedAndDirection) {
		this.originalSpeedAndDirection = speedAndDirection;
	}
	
	@Override
	public GameElement clone() throws CloneNotSupportedException {
		GameElement clone =  (GameElement)super.clone();
		try {
			clone.setPosition(getPosition().clone());
			clone.setSpeedAndDirection(getSpeedAndDirection().clone());
		} catch (KHLeuvenMobileException e) {
			e.printStackTrace();
		}
		
		return clone;
	}

	public void resize(double ratioWidth, double ratioHeight) throws KHLeuvenMobileException{
		double newX = getPosition().getX() * ratioWidth;
		double newY = getPosition().getY() * ratioHeight;
		setPosition(new Point(newX, newY));
		
		double newStartingX = getOriginalPosition().getX() * ratioWidth;
		double newStartingY = getOriginalPosition().getY() * ratioHeight;
		setOriginalPosition(new Point(newStartingX, newStartingY));
		
		double newSpeedX = getSpeedAndDirection().getX() * ratioWidth;
		double newSpeedY = getSpeedAndDirection().getY() * ratioHeight;
		setSpeedAndDirection(new Point(newSpeedX, newSpeedY));
	}
}

