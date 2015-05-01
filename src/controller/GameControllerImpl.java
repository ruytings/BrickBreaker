package controller;

import java.util.List;

import javax.swing.JFrame;

import view.GameMainView;
import domain.*;
import domain.element.*;

public class GameControllerImpl implements ChronometerController, GameController {
	
	private GameFacadeImpl model;
	private GameMainView mainView;

	public GameControllerImpl(GameFacadeImpl model, GameMainView mainview) {
		setModel(model);
		setMainView(mainview);
	}
	
	public void showView() {
		getMainView().setVisible(true);
	}
	
	public int getTime() {
		return this.getModel().getTime();
	}
	
	private void setModel(GameFacadeImpl model) {
		this.model = model;
	}
	
	private GameFacadeImpl getModel() {
		return model;
	}
	
	private void setMainView(GameMainView mainView) {
		this.mainView = mainView;
	}
	
	private JFrame getMainView() {
		return mainView;
	}
	
	public List<SquareElement> getSquareElements() {
		return model.getSquareElements();
	}

	public List<RoundElement> getRoundElements() {
		return model.getRoundElements();
	}

	public int getNumberOfLives() {
		return model.getNumberOfLives();
	}
	
	public int getScore() {
		return model.getScore();
	}
	

}