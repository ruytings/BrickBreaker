package application;

import javax.swing.JPanel;

import view.ViewException;
import view.panels.FieldPanel;
import view.panels.GameControlPanel;
import view.panels.GameInfoPanel;
import view.panels.GamePanel;
import controller.DirectionListener;
import controller.GameControllerImpl;
import domain.BrickBreaker;
import domain.Chronometer;
import domain.DomainException;
import domain.GameFacade;
import domain.Observer;
import domain.Subject;

public class GamePanelBuilder {
	
	public JPanel createPanel(Chronometer chronometer, BrickBreaker brickBreaker, GameControllerImpl gameController, GameFacade facade) throws ViewException, DomainException {
		chronometer.registerObserver(brickBreaker);
		ChronometerPanelBuilder panelBuilder = new ChronometerPanelBuilder();
		JPanel chronometerPanel = panelBuilder.createPanel(chronometer, gameController);
		JPanel gameInfoPanel = new GameInfoPanel(gameController);
		GameControlPanel gameControlPanel = new GameControlPanel(gameInfoPanel, chronometerPanel);
		DirectionListener keyListener = new DirectionListener(facade);
		FieldPanel fieldPanel = new FieldPanel(gameController, keyListener);
		GamePanel gamePanel = new GamePanel(fieldPanel, gameControlPanel);
		brickBreaker.registerObserver(fieldPanel);
		brickBreaker.registerObserver((Observer)gameInfoPanel);
		return gamePanel;
	}

}
