package view.panels;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.*;
import view.ViewException;
import domain.Observer;


public class InfoPanel extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	private JLabel timeLabel = new JLabel("Time");
	private JTextField timeTextField;
	private ChronometerController chronometerController;

	public InfoPanel(ChronometerController chronometerController) throws ViewException {
		setChronometerController(chronometerController);
		add(timeLabel);
		createField();
		add(timeTextField);
	}

	private void createField() {
		timeTextField = new JTextField("0", 8);
		timeTextField.setEditable(false);
		timeTextField.setFont(new Font("Arial", Font.BOLD, 20));
		timeTextField.setBackground(Color.YELLOW);
		timeTextField.setHorizontalAlignment(JTextField.CENTER);
	}

	private ChronometerController getChronometerController() {
		return chronometerController;
	}

	private void setChronometerController(ChronometerController chronometerController) throws ViewException {
		if (chronometerController == null) {
			throw new ViewException("Chronometer should not be null");
		}
		this.chronometerController = chronometerController;
	}

	public void update() {
		String info = Integer.toString(getChronometerController().getTime());
		timeTextField.setText(info);
	}
}
