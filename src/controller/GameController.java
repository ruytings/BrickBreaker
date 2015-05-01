package controller;

import java.util.*;

import domain.element.RoundElement;
import domain.element.SquareElement;

public interface GameController {
	
	public List<SquareElement>getSquareElements();
	public List<RoundElement>getRoundElements();
	public int getNumberOfLives();
	public int getScore();
	
	
}
