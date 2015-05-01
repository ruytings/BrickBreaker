package khleuven.mobile.brickbreaker;

import java.util.List;

import khleuven.mobile.KHLeuvenMobileException;
import khleuven.mobile.brickbreaker.element.RoundElement;
import khleuven.mobile.brickbreaker.element.SquareElement;
import khleuven.mobile.chronometer.Startable;
import khleuven.mobile.observer.Observer;

public interface GameFacade extends Startable {

	public abstract int getTime();

	public abstract List<SquareElement> getSquareElements();

	public void right() throws KHLeuvenMobileException;

	public void left() throws KHLeuvenMobileException;

	public List<RoundElement> getRoundElements();

	public void launch() throws KHLeuvenMobileException;

	public int getNumberOfLives();
	
	public int getScore();
	
	public void registerGameObserver(Observer observer);
	
	public void registerPlayer(String playerName) throws KHLeuvenMobileException;

	public boolean isGameOver();

	public void resizeGame(int newFieldWidth, int newFieldHeight)
			throws KHLeuvenMobileException;

}