package view.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	public GamePanel(JPanel fieldPanel, JPanel gameControlPanel) {
		setLayout(new BorderLayout());
		add(fieldPanel, BorderLayout.CENTER);
		add(gameControlPanel, BorderLayout.EAST);
	}

}
