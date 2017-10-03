package main.listener;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Listener implements EventHandler<MouseEvent> {

	public static double mouse_x;
	public static double mouse_y;
	
	@Override
	public void handle(MouseEvent event) {
		
		mouse_x = event.getX();
		mouse_y = event.getY();
		
	}
	
	/*@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse_x = e.getX();
		mouse_y = e.getY();
	}*/
	
}
