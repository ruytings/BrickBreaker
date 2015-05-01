package view.panels;

import java.awt.GridLayout;
import javax.swing.JPanel;
import view.ViewException;

public class ChronometerPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public ChronometerPanel(JPanel infoPanel, JPanel controlPanel) throws ViewException{
		setLayout(new GridLayout(2, 1));
		add(infoPanel);
        add(controlPanel);
	}
}
