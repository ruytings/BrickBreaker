package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.GameController;
import domain.DomainException;
import domain.Observer;

public class GameInfoPanel extends JPanel implements Observer {
	
	private GameController gameController;
	private JTextField livesTextField;
	private JTextField scoreTextField;
	private JLabel livesLabel = new JLabel("Lives ");
	private JLabel scoreLabel = new JLabel("Score ");
	
	
	public GameInfoPanel(GameController gameController) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		createLivesField();
		createScoreField();
		c.gridx = 0;
		c.gridy = 0;
		this.add(livesLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		
		GridBagConstraints c2 = new GridBagConstraints();
		this.add(livesTextField, c2);
		c2.gridx = 0;
		c2.gridy = 1;
		this.add(scoreLabel, c2);
		c2.gridx = 1;
		c2.gridy = 1;
		this.add(scoreTextField, c2);
		setController(gameController);
		update();
	}
	
	private void setController(GameController gameController) {
		this.gameController = gameController;
	}
	
	private GameController getController() {
		return this.gameController;
	}
	
	private void createLivesField() {
		livesTextField = new JTextField("0", 8);
		livesTextField.setEditable(false);
		livesTextField.setFont(new Font("Arial", Font.BOLD, 20));
		livesTextField.setBackground(Color.YELLOW);
		livesTextField.setHorizontalAlignment(JTextField.CENTER);
	}
	
	private void createScoreField() {
		
		scoreTextField = new JTextField("0", 8);
		scoreTextField.setEditable(false);
		scoreTextField.setFont(new Font("Arial", Font.BOLD, 20));
		scoreTextField.setBackground(Color.YELLOW);
		scoreTextField.setHorizontalAlignment(JTextField.CENTER);
	}
	
	public void update() {
		String lives = Integer.toString(getController().getNumberOfLives());
		String score = Integer.toString(getController().getScore());
		livesTextField.setText(lives);
		scoreTextField.setText(score);
	}
	
	
	
}