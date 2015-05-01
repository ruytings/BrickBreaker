package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMainView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public GameMainView() throws ViewException{
		setSize(new Dimension(619,575));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("BrickBreaker");
	}
	
	public void setMainPanel(JPanel gamePanel){
        setContentPane(gamePanel);
	}

}
