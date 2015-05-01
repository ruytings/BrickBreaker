package domain;


import hitstrategy.*;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import domain.collision.CollisionDetector;
import domain.collision.CollisionType;
import domain.collision.InsideCollisionDetector;
import domain.element.GameElement;
import domain.element.Point;
import domain.element.RoundElement;
import domain.element.SquareElement;

public class BrickBreaker extends StartableObject implements Subject, Observer {
	
	private SquareElement field;
	private SquareElement paddle;
	private ArrayList<SquareElement> squareElements = new ArrayList<SquareElement>();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private RoundElement ball;
	private ArrayList<SquareElement> bricks;
	private boolean launched;
	private int score;
	private int numberOfLives;
	private ArrayList<RoundElement> roundElements = new ArrayList<RoundElement>();
	private HitStrategy hitStrategy;
	private HitStrategyFactory factory;
	
	public BrickBreaker(SquareElement field, SquareElement paddle, RoundElement ball, ArrayList<SquareElement> bricks) throws DomainException {
		setField(field);
		setPaddle(paddle);
		setBall(ball);
		setLaunched(false);
		setHitStrategyFactory();
		setNumberOfLives(3);
		setHitStrategy();
		setBricks(bricks);
		notifyObservers();
		
	}
	

	private void setField(SquareElement field) throws DomainException {
		if(field == null) {
			throw new DomainException("Field is not allowed to be null");
		}
		this.field = field;
		squareElements.add(field);
	}
	
	private void setPaddle(SquareElement paddle) throws DomainException {
		if(paddle == null) {
			throw new DomainException("Paddle is not allowed to be null");
		}
		this.paddle = paddle;
		squareElements.add(paddle);
	}
	
	private void setBall(RoundElement ball) throws DomainException{
		if(ball == null) {
			throw new DomainException("ball is not allowed to be null");
		}
		this.ball = ball;
		roundElements.add(ball);
	}
	
	private void setBricks(ArrayList<SquareElement> bricks) {
		this.bricks = bricks;
		for(SquareElement brick : bricks) {
			squareElements.add(brick);
		}
	}
	
	private SquareElement getField(){
		return field;
	}
	
	private SquareElement getPaddle(){
		return paddle;
	}
	
	public RoundElement getBall(){
		return this.ball;
	}
	
	
	public ArrayList<SquareElement> getBricks() {
		return this.bricks;
	}
	
	public ArrayList<SquareElement> getSquareElements(){
		return squareElements;
	}
	
	public ArrayList<RoundElement> getRoundElements(){
		return roundElements;
	}
	
	public void movePaddleLeft() throws DomainException, CloneNotSupportedException {
		CollisionDetector collisionDetector = this.getField().getCollisionDetector();
		this.getPaddle().setDirectionToLeft();
		if(!collisionDetector.willMovingCollideWithStandingElement(paddle, field)) {
			
			this.getPaddle().move();
			notifyObservers();
		} else {
			this.getPaddle().setPosition(new Point(this.getField().getLeft(), this.getField().getBottom() - this.getPaddle().getHeight()));
			notifyObservers();
		}
	}
	
	public void movePaddleRight() throws DomainException, CloneNotSupportedException {
		CollisionDetector collisionDetector = this.getField().getCollisionDetector();
		this.getPaddle().setDirectionToRight();
		if(!collisionDetector.willMovingCollideWithStandingElement(paddle, field)) {
			
			this.getPaddle().move();
			notifyObservers();
		} else {
			this.getPaddle().setPosition(new Point(this.getField().getRight() - this.getPaddle().getWidth(), this.getField().getBottom() - this.getPaddle().getHeight()));
			notifyObservers();
		}
	}
	
	public void reset() throws DomainException {
		this.getPaddle().reset();
		this.getBall().reset();
		
	}
	
	public boolean isStarted(){
		if(this.getStatus() == StartableStatus.RESUMED || this.getStatus() == StartableStatus.STARTED) {
			return true;
		}
		else return false;
	}
	
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() throws DomainException {
		for(Observer observer : observers) {
			observer.update();
		}
	}
	
	public int getNumberOfLives(){
		return this.numberOfLives;	
	}
	
	private void setNumberOfLives(int numbersOfLives){
		this.numberOfLives = numbersOfLives;
	}
	
	public void launch() throws DomainException, CloneNotSupportedException {
		CollisionDetector collisionDetector = this.getField().getCollisionDetector();
		CollisionDetector paddlecollisionDetector = this.getPaddle().getCollisionDetector();
		//ball vs field
		if(collisionDetector.willMovingCollideWithStandingElement(ball, field)){
			if(collisionDetector.getCollisionType(ball, field) == CollisionType.VERTICAL){
				getBall().bounceVertical();
				getBall().move();
			} else {
				getBall().bounceHorizontal();
				getBall().move();
			}	
					
		}
		
		//ball vs paddle
		if(paddlecollisionDetector.willMovingCollideWithStandingElement(ball, paddle)) {
			if(paddlecollisionDetector.getCollisionType(ball, paddle) == CollisionType.VERTICAL){
				getBall().bounceVertical();
				getBall().move();
			}
			if(paddlecollisionDetector.getCollisionType(ball, paddle) == CollisionType.HORIZONTAL){	
				getBall().bounceHorizontal();
				getBall().move();
						
			}	
		}
		
		//ball vs brick
		for (Iterator<SquareElement> it = this.getBricks().iterator(); it.hasNext(); ) {
		    SquareElement brick = it.next();
				CollisionDetector brickcollisionDetector = brick.getCollisionDetector();
				if(brickcollisionDetector.willMovingCollideWithStandingElementBricks(ball, brick)){
					if(brickcollisionDetector.getCollisionType(ball, brick) == CollisionType.HORIZONTAL){	
						getBall().bounceHorizontal();
						getBall().move();
						this.getHitStrategy().hitBrick(this, brick);
						break;
					} else { 
						getBall().bounceVertical();	
						getBall().move();
						this.getHitStrategy().hitBrick(this, brick);
						break;
					}
				}
			}
			if(ballNotCatched()){
				this.reset();
			}
			getBall().move();
			notifyObservers();
		}
				
	public void moveBall() throws DomainException {
		if(isLaunched()) {
			this.getBall().move();
		}
	}
	public boolean isLaunched() {
		return launched;
	}
	
	public void setLaunched(boolean launched){
		this.launched = launched;
	}
	
	private boolean ballNotCatched() throws DomainException{
		boolean notcatched = false;
		if(ball.getBottom() > paddle.getTop() + 1) {
			notcatched = true;
			looseLife();
		}
		if(this.getNumberOfLives() == 0) {
			this.gameOver();
		}
		return notcatched;
	}
	
	private void looseLife() throws DomainException {
		setNumberOfLives(this.getNumberOfLives()-1);
		notifyObservers();
		setHitStrategy(); 
	}
	
	public int getScore() {
		return this.score;
	}
	
	@Override
	public void update() throws DomainException {
		try {
			if(isLaunched()) {
				launch();
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setHitStrategy() {
		hitStrategy = factory.create(this.getNumberOfLives());
	}
	
	public HitStrategy getHitStrategy() {
		return hitStrategy;
	}
	
	public void setHitStrategyFactory() {
		this.factory = new HitStrategyFactory();
	}
	
	public void setScore(int score) throws DomainException {
		if(score < 0) {
			throw new DomainException("Score is not allowed to be < 0.");
		} 
		this.score = score;
	}
	
	private void gameOver() throws DomainException {
		JOptionPane.showInputDialog("Please enter your name.");
		int stop = JOptionPane.showConfirmDialog(
	            null,
	            "Would you like to play again?",
	            "Game Over",
	            JOptionPane.YES_NO_OPTION);
		if(stop == 0) {
			this.reset();
			setScore(0);
			setNumberOfLives(3);
		} else {
		System.exit(0);
		}		
	}
	
	
}












