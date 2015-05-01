package application;

import javax.swing.JOptionPane;
import domain.*;
import view.*;
import controller.*;


public class Launcher {
 
	public static void main(String[] args) {
		try {
			Chronometer chronometer = new Chronometer(10);
			GameBuilder gameBuilder = new GameBuilder();
			BrickBreaker brickBreaker = gameBuilder.createGame();
			GameFacadeImpl facade = new GameFacadeImpl(chronometer, brickBreaker);
			GameMainView view = new GameMainView();
			GamePanelBuilder gpb = new GamePanelBuilder();
			GameControllerImpl gameController = new GameControllerImpl(facade, view);
			view.setMainPanel(gpb.createPanel(chronometer, brickBreaker, gameController, facade));
			gameController.showView();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
