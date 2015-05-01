package application;

import java.awt.Color;
import java.util.ArrayList;

import domain.BrickBreaker;
import domain.DomainException;
import domain.element.Point;
import domain.element.RoundElement;
import domain.element.SquareElement;


public class GameBuilder {
	
	private int fieldHeight = 460;
	private int fieldWidth = 416;
	private Point fieldPosition = new Point(0,0);
	private Color fieldColor = Color.WHITE;
	private Color fieldBorder = Color.RED;
	
	private int paddleHeight = 10;
	private int paddleWidth = 60;
	private Point paddlePosition = new Point(183, 450); 
	private Color paddleColor = Color.GRAY;
	private Color paddleBorder = Color.BLACK;
	private int paddleSpeedX = 15;
	private int paddleSpeedY = 0;
	
	private int ballRadius = 10;
	private Point ballPosition = new Point(213, 440);
	private Color ballColor = Color.RED;
	private Color ballBorder = Color.RED;
	
	private int brickWidth = fieldWidth / 8;
	private int brickHeight = 15;
	private Color brickColor = Color.ORANGE;
	private Color brickBorder = Color.BLACK;
	
	public BrickBreaker createGame() throws DomainException {
		SquareElement paddle = new SquareElement(paddlePosition, paddleWidth, paddleHeight, paddleColor, paddleBorder, new Point(paddleSpeedX, paddleSpeedY));
		SquareElement field = new SquareElement(fieldPosition, fieldWidth, fieldHeight, fieldColor, fieldBorder);
		RoundElement ball = new RoundElement(ballPosition, ballRadius, ballColor, ballBorder, new Point(4,-4));
		SquareElement brick = new SquareElement(fieldPosition, brickWidth, brickHeight, brickColor, brickBorder);
		ArrayList<SquareElement> bricks = new ArrayList<SquareElement>();
		bricks = createLevel(brick);
		BrickBreaker game = new BrickBreaker(field, paddle, ball, bricks);
		return game;
	}
	
	private ArrayList<SquareElement> createLevel (SquareElement brick) throws DomainException {
		ArrayList<SquareElement> bricks = new ArrayList<SquareElement>();
		int extra = 0;
		for(int i = 1; i < 9; i++) {
			Point positionBrick = new Point(brick.getPosition().getX() + extra, brick.getPosition().getY());
			SquareElement newBrick = new SquareElement(positionBrick, brickWidth, brickHeight, brickColor, brickBorder);
			bricks.add(newBrick);
			extra = extra + brickWidth;
		}
		extra = 0;
		int yExtra = brickHeight;
		for(int i = 1; i < 9; i++) {
			Point positionBrick = new Point(brick.getPosition().getX() + extra, brick.getPosition().getY() + yExtra);
			SquareElement newBrick = new SquareElement(positionBrick, brickWidth, brickHeight, brickColor, brickBorder);
			bricks.add(newBrick);
			extra = extra + brickWidth;
		}
		return bricks;
	}
	

}
