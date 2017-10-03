package main.objects.paddle;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.objects.Object;
import main.window.Window;

public abstract class Paddle extends Object {
	
	protected float speed = 10;
	private int points = 0;
	
	public Paddle() {
		width = 18;
		height = 120;
	}
	
	@Override
	public void render(GraphicsContext g) {
		g.setFill(Color.WHITE);
		g.fillRect((int) x, (int) y, width, height);
	}
	
	protected abstract void move();
	
	@Override
	public void tick() {
		
		move();
		
		if (y <= 0) {
			y = 0;
		} else if (y + height >= Window.HEIGHT) {
			y = Window.HEIGHT - height;
		}
		
	}
	
	protected double yDistance(double y1, double y2) {
		return Math.max(y1, y2) - Math.min(y1, y2);
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
}
