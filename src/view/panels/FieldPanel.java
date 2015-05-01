package view.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.GameController;
import domain.Observer;
import domain.element.RoundElement;
import domain.element.SquareElement;
import view.ViewException;
import controller.DirectionListener;

public class FieldPanel extends JPanel implements Observer {
	
	private GameController controller;
	
	public FieldPanel(GameController controller, DirectionListener keyListener) throws ViewException {
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));
		setBackground(Color.YELLOW);	
		repaint(); 
		setController(controller);
		update();
		setFocusable(true);
		addKeyListener(keyListener);
	}
	
	@Override 
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics); 
		for(SquareElement element : this.getController().getSquareElements()) {
			paintSquareElement(graphics, element);
		}
		for(RoundElement element : this.getController().getRoundElements()) {
			paintRoundElement(graphics, element);
		}
	}
	
	private void paintSquareElement(Graphics graphics, SquareElement element) {
		graphics.setColor(element.getColor());
		graphics.fillRect(element.getLeft(), element.getTop(), element.getWidth(), element.getHeight());
		graphics.setColor(element.getBorderColor());
		graphics.drawRect(element.getLeft(), element.getTop(), element.getWidth(), element.getHeight());
	}
	
	private void setController(GameController controller) throws ViewException {
		if(controller == null) {
			throw new ViewException("Controller is not allowed to be null");
		}
		this.controller = controller;
	}
	
	private GameController getController() {
		return controller;
	}
	
	public void update() {
		this.requestFocus();
		repaint();
	}
	
	private void paintRoundElement(Graphics graphics, RoundElement roundelement){
		graphics.setColor(roundelement.getColor());
		graphics.fillOval(roundelement.getLeft(), roundelement.getTop(), roundelement.getWidth(), roundelement.getHeight());
		graphics.setColor(roundelement.getBorderColor());
		graphics.drawOval(roundelement.getLeft(), roundelement.getTop(), roundelement.getWidth(), roundelement.getHeight());
		
	}
	
}
