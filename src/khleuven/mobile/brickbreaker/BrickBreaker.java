package khleuven.mobile.brickbreaker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.collision.CollisionType;
import khleuven.mobile.brickbreaker.element.Brick;
import khleuven.mobile.brickbreaker.element.Field;
import khleuven.mobile.brickbreaker.element.GameElement;
import khleuven.mobile.brickbreaker.element.RoundElement;
import khleuven.mobile.brickbreaker.element.SquareElement;
import khleuven.mobile.brickbreaker.hitstrategy.HitStrategy;
import khleuven.mobile.brickbreaker.hitstrategy.HitStrategyFactory;
import khleuven.mobile.chronometer.StartableObject;
import khleuven.mobile.chronometer.StartableStatus;
import khleuven.mobile.observer.Observer;
import khleuven.mobile.observer.Subject;

public class BrickBreaker extends StartableObject implements Subject, Observer {

	private Set<Observer> observers = new HashSet<Observer>();

	private Field field;
	private SquareElement paddle;
	private RoundElement ball;
	private boolean launched;
	private int numberOfLives;
	private List<Brick> bricks = new ArrayList<Brick>();
	private HitStrategy hitStrategy;

	public BrickBreaker(Field field) throws KHLeuvenMobileException {
		super();
		setField(field);
	}

	public List<SquareElement> getSquareElements() {
		List<SquareElement> elements = new ArrayList<SquareElement>();
		elements.add(getField());
		if (getPaddle() != null) {
			elements.add(getPaddle());
		}
		for (Brick b : getBricks()) {
			if (!b.isHit()) {
				elements.add(b);
			}
		}
		return elements;
	}

	public List<RoundElement> getRoundElements() {
		List<RoundElement> elements = new ArrayList<RoundElement>();
		if (getBall() != null) {
			elements.add(getBall());
		}
		return elements;
	}

	@Override
	public void start() throws KHLeuvenMobileException {
		super.start();
		reset();
		notifyObservers();
	}

	@Override
	public void update() {
		if (isLaunched()) {

			try {
				procesCollisions();
				getBall().move();
				if (ballNotCatched()) {
					looseLife();
				}
				notifyObservers();
			} catch (KHLeuvenMobileException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	private void procesCollisions() throws KHLeuvenMobileException {
		for (GameElement otherElement : getSquareElements()) {
			CollisionType collisionType = otherElement.getCollisionType(
					getBall(), otherElement);

			if (!collisionType.equals(CollisionType.NONE)) {
				if (collisionType.equals(CollisionType.VERTICAL)) {
					getBall().bounceVerticall();
				} else if (collisionType.equals(CollisionType.HORIZONTAL)) {
					getBall().bounceHorizontal();
				}
				if (otherElement instanceof Brick) {
					hitStrategy.processHit((Brick)otherElement); 
				}
			}
		}
	}

	private boolean ballNotCatched() {
		return getBall().getBottom() > getPaddle().getTop();
	}

	private void looseLife() throws KHLeuvenMobileException {
		setNumberOfLives(getNumberOfLives() - 1);
		if (getNumberOfLives() > 0) { 
			reset();
		} else {
			stop();
		}
	}

	public void movePaddleRight() throws KHLeuvenMobileException {
		getPaddle().setDirectionToRight();
		boolean willCollide = getField().willCollideWithMovingElement(
				getPaddle());
		if (isStarted() && !willCollide) {
			getPaddle().move();
			notifyObservers();
		}
	}

	public void movePaddleLeft() throws KHLeuvenMobileException {
		getPaddle().setDirectionToLeft();
		boolean willCollide = getField().willCollideWithMovingElement(
				getPaddle());
		if (isStarted() && !willCollide) {
			getPaddle().move();
			notifyObservers();
		}
	}

	public void launch() throws KHLeuvenMobileException {
		if (!isLaunched()) {
			setLaunched(true);
		}
	}

	public void reset() throws KHLeuvenMobileException {
		getPaddle().reset();
		getBall().reset();
		setLaunched(false);

		hitStrategy = new HitStrategyFactory().createHitStrategy(this);
	}

	private boolean isStarted() {
		return getStatus() == StartableStatus.STARTED
				|| getStatus() == StartableStatus.RESUMED;
	}

	public int getScore() {
		int score = 0;

		for (Brick b : getBricks()) {
			if (b.isHit()) {
				score += b.getValue();
			}
		}

		return score;
	}

	public boolean isGameOver() {
		return getNumberOfLives() <= 0;
	}


	protected void setNumberOfLives(int numberOfLives) {
		this.numberOfLives = numberOfLives;
	}

	public int getNumberOfLives() {
		return numberOfLives;
	}

	private SquareElement getPaddle() {
		return paddle;
	}

	private Field getField() {
		return field;
	}

	protected void setField(Field field) throws KHLeuvenMobileException {
		if (field == null) {
			throw new KHLeuvenMobileException("Field should not be null");
		}
		this.field = field;
	}

	protected void setPaddle(SquareElement paddle) throws KHLeuvenMobileException {
		if (paddle == null) {
			throw new KHLeuvenMobileException("Paddle should not be null");
		}
		this.paddle = paddle;
	}

	private RoundElement getBall() {
		return ball;
	}

	protected void setBall(RoundElement ball) {
		this.ball = ball;
	}

	protected void setBricks(List<Brick> bricks) {
		this.bricks = bricks;
	}

	private List<Brick> getBricks() {
		return bricks;
	}

	public void addBrick(Brick brick) throws KHLeuvenMobileException {
		if (brick == null) {
			throw new KHLeuvenMobileException("Brick should not be null");
		}
		this.bricks.add(brick);
	}

	private boolean isLaunched() {
		return launched;
	}

	private void setLaunched(boolean launched) {
		this.launched = launched;
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		Iterator<Observer> iterator = observers.iterator();
		while (iterator.hasNext()) {
			Observer o = iterator.next();
			if (o.equals(observer)) {
				iterator.remove();
			}
		}
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}
	}

	public void resizeComponents(int newFieldWidth, int newFieldHeight)
			throws KHLeuvenMobileException {
		double ratioWidth = (double) newFieldWidth / (double) getField().getWidth();
		double ratioHeight = (double) newFieldHeight / (double) getField().getHeight();
		
		for (GameElement element : getSquareElements()) {
			element.resize(ratioWidth, ratioHeight);
		}
		for(GameElement element: getRoundElements()){
			element.resize(ratioWidth, ratioHeight);
		}
	}

}
