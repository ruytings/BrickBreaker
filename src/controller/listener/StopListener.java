package controller.listener;

import javax.swing.JButton;
import domain.DomainException;
import domain.Startable;

public class StopListener extends TimerListener {

	public StopListener(Startable startable, JButton startButton,
			JButton stopButton, JButton resumeButton) {
		super(startable, startButton, stopButton, resumeButton);
	}

	@Override
	protected void perfomAction() throws DomainException {
		getTimer().stop();
	}

	@Override
	protected void enableButtons() throws DomainException {
		getStartButton().setEnabled(true);
		getResumeButton().setEnabled(true);
	}

}
