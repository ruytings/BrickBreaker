package controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import domain.DomainException;
import domain.Chronometer;
import domain.GameFacadeImpl;
import domain.Startable;

public abstract class TimerListener implements ActionListener {
	
	private Chronometer timer;
	private JButton startButton, stopButton, resumeButton;

	public TimerListener(Startable startable, JButton startButton,
			JButton stopButton, JButton resumeButton) {
		super();
		this.setTimer((Chronometer)startable);
		this.setStartButton(startButton);
		this.setResumeButton(resumeButton);
		this.setStopButton(stopButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			perfomAction();
			disableButtons();
			enableButtons();
		} catch (DomainException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	protected abstract void perfomAction() throws DomainException;
	protected abstract void enableButtons() throws DomainException;

	private void disableButtons() {
		getStartButton().setEnabled(false);
		getResumeButton().setEnabled(false);
		getStopButton().setEnabled(false);
	}

	protected Chronometer getTimer() {
		return timer;
	}

	private void setTimer(Chronometer timer) {
		this.timer = timer;
	}

	protected JButton getResumeButton() {
		return resumeButton;
	}

	private void setResumeButton(JButton resumeButton) {
		this.resumeButton = resumeButton;
	}

	protected JButton getStopButton() {
		return stopButton;
	}

	private void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}

	protected JButton getStartButton() {
		return startButton;
	}

	private void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

}
