package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import domain.GameFacade;
import domain.StartableStatus;

public class DirectionListener implements KeyListener {
	
	private GameFacade model;
	
	public DirectionListener(GameFacade model) {
		setGame(model);
	}
	
	private void setGame(GameFacade game) {
		this.model = game;
	}
	
	protected GameFacade getGame() {
		return model;
	}
	
	public void keyPressed(KeyEvent event) { 
		 try { 
			 switch (event.getKeyCode()) { 
			 	case KeyEvent.VK_LEFT: 
			 		if(model.getStatus() == StartableStatus.STARTED || model.getStatus() == StartableStatus.RESUMED) {
			 			model.left();
			 		}
			 		break; 
			 	case KeyEvent.VK_RIGHT: 
			 		if(model.getStatus() == StartableStatus.STARTED || model.getStatus() == StartableStatus.RESUMED) {
			 			model.right();
			 		}
			 		break; 
			 	case KeyEvent.VK_SPACE: 
			 		if(model.getStatus() == StartableStatus.STARTED || model.getStatus() == StartableStatus.RESUMED) {
			 			model.launch();
			 		}
			 } 
		 } catch (Exception e) { 
			 JOptionPane.showMessageDialog(null, "Het beweegt niet :("); 
		 } 
	} 
		 
	public void keyTyped(KeyEvent event) { 
	} 
		 
	public void keyReleased(KeyEvent event) { 
	} 

	
	
	

}
