package controller.listener;

import javax.swing.JButton;
import domain.DomainException;
import domain.Startable;

public class StartListener extends TimerListener {

	public StartListener(Startable startable, JButton startButton,
			JButton stopButton, JButton resumeButton) {
		super(startable, startButton, stopButton, resumeButton);
	}

	@Override
	protected void perfomAction() throws DomainException {
		getTimer().start();
	}

	@Override
	protected void enableButtons() throws DomainException {
		getStopButton().setEnabled(true);
	}

}
