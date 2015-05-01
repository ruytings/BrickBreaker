package domain;

import domain.element.*;

import java.util.ArrayList;

public interface GameFacade extends Startable {

	public int getTime();
	public ArrayList<SquareElement> getSquareElements();
	public void right() throws DomainException, CloneNotSupportedException;
	public void left() throws DomainException, CloneNotSupportedException;
	public int getNumberOfLives();
	public int getScore();
	public void launch() throws DomainException, CloneNotSupportedException;
	
	
}
