package application;


import javax.swing.JPanel;

import controller.ChronometerController;
import view.ViewException;
import view.panels.ChronometerPanel;
import view.panels.ControlPanel;
import view.panels.InfoPanel;
import domain.Chronometer;
import domain.Startable;

public class ChronometerPanelBuilder {
	
	public JPanel createPanel(Startable startable, ChronometerController chronometerController) throws ViewException {
		Chronometer chronometer = (Chronometer)startable;
		JPanel controlPanel = createControlPanel(chronometer);
		InfoPanel infoPanel = new InfoPanel(chronometerController);
		chronometer.registerObserver(infoPanel);
		ChronometerPanel chronometerPanel = new ChronometerPanel(infoPanel,controlPanel);
		return chronometerPanel;
	}

	private JPanel createControlPanel(Startable startable) {
		ControlPanel controlPanel = new ControlPanel((Chronometer)startable);
		return controlPanel;
	}
}
