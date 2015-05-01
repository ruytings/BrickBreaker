package domain.element;

import java.awt.Color;

import domain.DomainException;
import domain.collision.CollisionDetector;
import domain.collision.InsideCollisionDetector;
import domain.collision.OutsideCollisionDetector;
import domain.element.Point;

public abstract class GameElement implements Cloneable{

	private Color color;
	private Color borderColor;
	private Point position;
	private Point originalPosition;
	private Point speedAndDirection;
	private Point originalSpeedAndDirection;
	private CollisionDetector collisionDetector;
	
	public GameElement(Point position, Color color, Color borderColor) throws DomainException{
		setPosition(position);
		setColor(color);
		setBorderColor(borderColor);
		setCollisionDetector(new InsideCollisionDetector());
	}
	
	public void setPosition(Point position)throws DomainException{
		if(position == null){
			throw new DomainException("Position may not be null");
		}
		this.position = position;
	}
	
	public void setColor(Color color) throws DomainException{
		if(color == null){
			throw new DomainException("color may not be null");
		}
		this.color = color;
	}
	
	public void setBorderColor(Color borderColor)throws DomainException{
		if(borderColor == null){
			throw new DomainException("BorderColor may not be null");
		}
		this.borderColor = borderColor;
	}
	
	public Point getPosition(){
		return position;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}
	
	protected int getX(){
		return (int)position.getX();
	}
	
	protected int getY(){
		return (int)position.getY();
	}
	
	public abstract int getLeft();
	public abstract int getTop();
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getBottom();
	public abstract int getRight();
	
	public void setDirectionToLeft() {
		if(this.getSpeedAndDirection().getX() > 0) {
			setSpeedAndDirection(new Point(this.getSpeedAndDirection().getX()*-1, this.getSpeedAndDirection().getY()));
		}
	}
	
	public void setDirectionToRight() {
		if(this.getSpeedAndDirection().getX() < 0) {
			setSpeedAndDirection(new Point(this.getSpeedAndDirection().getX()*-1, this.getSpeedAndDirection().getY()));
		}
	}
	
	public void move() throws DomainException {
		Point newPosition = new Point(getPosition().getX() + getSpeedAndDirection().getX(), getPosition().getY() + getSpeedAndDirection().getY());
		this.setPosition(newPosition);
	}
	
	public void reset() throws DomainException {
		this.setPosition(this.getOriginalPosition());
		this.setSpeedAndDirection(this.getOriginalSpeedAndDirection());
	}
	
	public GameElement(Point position, Color color, Color borderColor, Point speedAndDirection) throws DomainException {
		setPosition(position);
		setColor(color);
		setBorderColor(borderColor);
		setSpeedAndDirection(speedAndDirection);
		setOriginalSpeedAndDirection(speedAndDirection);
		setOriginalPosition(position);
		setCollisionDetector(new OutsideCollisionDetector());
	}
	
	private void setOriginalPosition(Point originalPosition) {
		this.originalPosition = originalPosition;
	}
	
	private Point getOriginalPosition() {
		return originalPosition;
	}
	
	private void setSpeedAndDirection(Point speedAndDirection) {
		this.speedAndDirection = speedAndDirection;
	}
	
	private Point getSpeedAndDirection() {
		return speedAndDirection;
	}
	
	private void setOriginalSpeedAndDirection(Point originalSpeedAndDirection) {
		this.originalSpeedAndDirection = originalSpeedAndDirection;
	}
	
	private Point getOriginalSpeedAndDirection() {
		return originalSpeedAndDirection;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public void bounceHorizontal() {
		this.setSpeedAndDirection(new Point(this.getSpeedAndDirection().getX()*-1,this.getSpeedAndDirection().getY()));
	}
	
	public void bounceVertical() {
		this.setSpeedAndDirection(new Point(this.getSpeedAndDirection().getX(),this.getSpeedAndDirection().getY()*-1));
	}
	
	public boolean willMovingCollideWithStandingElement(GameElement standing) throws DomainException, CloneNotSupportedException {
		return this.getCollisionDetector().willMovingCollideWithStandingElement(this, standing);
	}
	
	public void getCollisionType() {
		
	}
	
	public CollisionDetector getCollisionDetector() {
		return this.collisionDetector;
	}
	
	public void setCollisionDetector(CollisionDetector collisionDetector){
		this.collisionDetector = collisionDetector;
	}
	
	

}