package view.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class GameControlPanel extends JPanel {
	
	public GameControlPanel(JPanel gameInfoPanel, JPanel chronometerPanel) {
		setLayout(new GridLayout(2,1));
		add(gameInfoPanel);
        add(chronometerPanel);
	}

}
