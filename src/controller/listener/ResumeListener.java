package controller.listener;

import javax.swing.JButton;
import domain.DomainException;
import domain.Startable;

public class ResumeListener extends TimerListener {

	public ResumeListener(Startable startable, JButton startButton,
			JButton stopButton, JButton resumeButton) {
		super(startable, startButton, stopButton, resumeButton);
	}

	@Override
	protected void perfomAction() throws DomainException {
		getTimer().resume();
	}
	
	protected void enableButtons() {
		getStopButton().setEnabled(true);
	}
}
