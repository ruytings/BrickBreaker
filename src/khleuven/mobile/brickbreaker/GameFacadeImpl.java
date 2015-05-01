package khleuven.mobile.brickbreaker;

import java.util.List;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.element.RoundElement;
import khleuven.mobile.brickbreaker.element.SquareElement;
import khleuven.mobile.chronometer.Chronometer;
import khleuven.mobile.chronometer.StartableStatus;
import khleuven.mobile.observer.Observer;
import khleuven.mobile.player.Player;

public class GameFacadeImpl implements GameFacade {
	private Chronometer chronometer;
	private BrickBreaker game;
	private GameBuilder gamebuilder;

	public GameFacadeImpl(Chronometer chronometer, GameBuilder gameBuilder)
			throws KHLeuvenMobileException {
		setGamebuilder(gameBuilder);
		setGame(gameBuilder.createGame(chronometer));
		setChronometer(chronometer);
	}

	@Override
	public int getTime() {
		return getChronometer().getTime();
	}

	@Override
	public void resume() throws KHLeuvenMobileException {
		getChronometer().resume();
		getGame().resume();
	}

	@Override
	public void start() throws KHLeuvenMobileException {
		getGamebuilder().initializeGame(getGame());
		getChronometer().start();
		getGame().start();
	}

	@Override
	public void stop() throws KHLeuvenMobileException {
		getChronometer().stop();
		getGame().stop();
	}

	@Override
	public StartableStatus getStatus() {
		return getChronometer().getStatus();
	}

	private Chronometer getChronometer() {
		return chronometer;
	}

	private void setChronometer(Chronometer chronometer) throws KHLeuvenMobileException {
		if (chronometer == null) {
			throw new KHLeuvenMobileException("Chronometer should not be null");
		}
		this.chronometer = chronometer;
	}

	@Override
	public List<SquareElement> getSquareElements() {
		return getGame().getSquareElements();
	}

	@Override
	public List<RoundElement> getRoundElements() {
		return getGame().getRoundElements();
	}

	@Override
	public void right() throws KHLeuvenMobileException {
		getGame().movePaddleRight();
	}

	@Override
	public void left() throws KHLeuvenMobileException {
		getGame().movePaddleLeft();
	}
	
	@Override
	public void launch() throws KHLeuvenMobileException{
		getGame().launch();
	}

	private BrickBreaker getGame() {
		return game;
	}

	private void setGame(BrickBreaker game) throws KHLeuvenMobileException {
		if (game == null) {
			throw new KHLeuvenMobileException("Game should not be null");
		}
		this.game = game;
	}

	private GameBuilder getGamebuilder() {
		return gamebuilder;
	}

	private void setGamebuilder(GameBuilder gamebuilder) {
		this.gamebuilder = gamebuilder;
	}

	@Override
	public int getNumberOfLives() {
		return getGame().getNumberOfLives();
	}
	
	@Override
	public int getScore() {
		return getGame().getScore();
	}

	@Override
	public void registerGameObserver(Observer observer) {
		getGame().registerObserver(observer);
	}

	@Override
	public void registerPlayer(String playerName) throws KHLeuvenMobileException{
		Player player = new Player(playerName);
		player.setScore(getScore());
		player.setTime(getTime());
	}

	@Override
	public boolean isGameOver() {
		return getGame().isGameOver();
	}

	@Override
	public void resizeGame(int newFieldWidth, int newFieldHeight) throws KHLeuvenMobileException {
		getGame().resizeComponents(newFieldWidth, newFieldHeight);
	}
}
