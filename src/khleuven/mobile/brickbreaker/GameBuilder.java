package khleuven.mobile.brickbreaker;

import java.util.ArrayList;
import java.util.List;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.element.Brick;
import khleuven.mobile.brickbreaker.element.Field;
import khleuven.mobile.brickbreaker.element.Point;
import khleuven.mobile.brickbreaker.element.RoundElement;
import khleuven.mobile.brickbreaker.element.SquareElement;
import khleuven.mobile.observer.Subject;

public class GameBuilder {

	private int fieldBorder = 0xff888888;
	private int fieldColor = 0xffffffff;
	private int fieldHeight = 500;
	private int fieldWidth = 400;
	private Point fieldPosition = new Point();
	private int paddleBorder = 0xFF000000;
	private int paddleColor = 0xff444444;
	private int paddleWidth = 100;
	private int paddleHeight = 20;
	private Point paddlePosition = new Point(150, fieldHeight - paddleHeight);
	private int paddleSpeedX = 5;
	private int paddleSpeedY = 0;
	private int ballSpeed = 5;
	private int ballBorder = 0xffff0000;
	private int ballColor = 0xffff0000;
	private int ballRadius = 10;
	private Point ballPosition = new Point(200, fieldHeight - paddleHeight - ballRadius);
	private int brickColor = 0xffffff00;
	private int brickBorder = 0xff444444;
	private int brickValue = 50;
	private int brickHeight = 20;
	private int numberOfRows = 2;
	private int numberOfStones = 16;
	
	public BrickBreaker createGame(Subject subject) throws KHLeuvenMobileException{
		Field field = new Field(fieldPosition, fieldHeight, fieldWidth, fieldColor, fieldBorder);
		
		BrickBreaker game = new BrickBreaker(field);
		subject.registerObserver(game);
		return game;
	}

	public BrickBreaker initializeGame(BrickBreaker game) throws KHLeuvenMobileException{
		Field field = new Field(fieldPosition, fieldHeight, fieldWidth, fieldColor, fieldBorder);
		game.setField(field);
		
		Point paddleSpeedAndDirection = new Point(paddleSpeedX, paddleSpeedY);
		SquareElement paddle = new SquareElement(paddlePosition, paddleHeight, paddleWidth, paddleColor, paddleBorder, paddleSpeedAndDirection);
		game.setPaddle(paddle);
		
		Point ballSpeedAndDirection = new Point(ballSpeed, -ballSpeed);
		RoundElement ball = new RoundElement(ballPosition, ballRadius, ballColor, ballBorder, ballSpeedAndDirection);
		game.setBall(ball);

		game.setNumberOfLives(3);
		
		game.setBricks(createBricks());
		return game;
	}
	
	private List<Brick> createBricks() throws KHLeuvenMobileException {
		int numberOfStonesPerRow = numberOfStones / numberOfRows;
		int widthBrick = fieldWidth / numberOfStonesPerRow;

		List<Brick> bricks = new ArrayList<Brick>();
		
		int bricKY = 0;
		for (int i = 0; i < numberOfStonesPerRow; i++) {
			Point position = new Point(i * widthBrick, bricKY);
			Brick brick = new Brick(position, new Point(), brickHeight,
					widthBrick, brickValue, brickColor, brickBorder);
			bricks.add(brick);
		}

		bricKY = brickHeight;
		for (int i = 0; i < numberOfStonesPerRow; i++) {
			Point position = new Point(i * widthBrick, bricKY);
			Brick brick = new Brick(position, new Point(), brickHeight,
					widthBrick, brickValue, brickColor, brickBorder);
			bricks.add(brick);
		}
		return bricks;
	}
}