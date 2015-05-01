package view.panels;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.listener.ResumeListener;
import controller.listener.StartListener;
import controller.listener.StopListener;
import domain.Chronometer;

public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton startButton, stopButton, resumeButton;
	private Chronometer timer;

	public ControlPanel(Chronometer timer) {
		setTimer(timer);

		createButtons();
		addListeners();
		addContent();
		update();
	}

	private void addContent() {
		this.setLayout(new GridLayout(3, 1));
		this.add(startButton);
		this.add(resumeButton);
		this.add(stopButton);
		enableButtons();
	}

	private void createButtons() {
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		resumeButton = new JButton("Resume");
	}

	private void addListeners() {
		startButton.addActionListener(new StartListener(getTimer(), startButton,
				stopButton, resumeButton));
		resumeButton.addActionListener(new ResumeListener(getTimer(), startButton,
				stopButton, resumeButton));
		stopButton.addActionListener(new StopListener(getTimer(), startButton,
				stopButton, resumeButton));
	}

	private Chronometer getTimer() {
		return timer;
	}

	private void setTimer(Chronometer controller) {
		this.timer = controller;
	}

	public void enableButtons() {
		startButton.setEnabled(true);
		resumeButton.setEnabled(false);
		stopButton.setEnabled(false);
	}

	public void update() {
		enableButtons();
	}

}
