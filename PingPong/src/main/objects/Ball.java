package main.objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.window.Window;

public class Ball extends Object {
		
	private float yVel = 3;
	private float xVel = -16;
	
	public Ball() {
		
		x = Window.WIDTH / 2 - width / 2;
		y = Window.HEIGHT / 2 - height / 2; 
		
		width = 15;
		height = 15;
		
	}
	
	@Override
	public void tick() {
		
		y += yVel;
		x += xVel;
		
	}
	
	@Override
	public void render(GraphicsContext g) {
		g.setFill(Color.WHITE);
		g.fillRect((int) x, (int) y, width, height);
	}
	
	public float getVelY() {
		return yVel;
	}
	
	public void setVelY(float yVel) {
		this.yVel = yVel;
	}
	
	public float getVelX() {
		return xVel;
	}
	
	public void setVelX(float xVel) {
		this.xVel = xVel;
	}
	
}
