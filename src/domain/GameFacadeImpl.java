package domain;

import java.util.ArrayList;

import domain.element.*;

public class GameFacadeImpl implements GameFacade {
	
	private Chronometer chronometer;
	private BrickBreaker game;
	
	public GameFacadeImpl(Chronometer chronometer, BrickBreaker game) throws DomainException {
		setChronometer(chronometer);
		setGame(game);
	}

	public String getGameName() {
		return "BrickBreaker";
	}
	
	public int getTime() {
		return this.getChronometer().getTime();
	}
	
	public void start() throws DomainException {
		this.getGame().reset();
		this.getChronometer().start();
		this.getGame().start();
		this.getGame().reset();
	}
	
	public void resume() throws DomainException {
		this.getChronometer().resume();
		this.getGame().resume();
	}
	
	public void stop() throws DomainException {
		this.getChronometer().stop();
		this.getGame().stop();
	}
	
	public StartableStatus getStatus() {
		return this.getChronometer().getStatus();
	}
	
	private void setChronometer(Chronometer chronometer) throws DomainException {
		if(chronometer == null) {
			throw new DomainException("Chronometer is not allowed to be null");
		}
		this.chronometer = chronometer;
	}
	
	private Chronometer getChronometer() {
		return this.chronometer;
	}
	
	private void setGame(BrickBreaker game) throws DomainException {
		if(game == null) {
			throw new DomainException("Game is not allowed to be null");
		}
		this.game = game;
	}
	
	private BrickBreaker getGame() {
		return game;
	}
	
	public ArrayList<SquareElement> getSquareElements() {
		return this.getGame().getSquareElements();
	}
	
	public void right() throws DomainException, CloneNotSupportedException {
		this.getGame().movePaddleRight();
	}
	
	public void left() throws DomainException, CloneNotSupportedException {
		this.getGame().movePaddleLeft();
	}
	
	public ArrayList<RoundElement> getRoundElements() {
		return this.getGame().getRoundElements();
	}
	
	public int getNumberOfLives(){
		return this.getGame().getNumberOfLives();
	}
	
	public void launch() throws DomainException, CloneNotSupportedException {
		if(!game.isLaunched()) {
			game.setLaunched(true);
		}
	}
	
	public int getScore() {
		return this.getGame().getScore();
	}

}
