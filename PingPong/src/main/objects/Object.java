package main.objects;

import javafx.scene.canvas.GraphicsContext;

public abstract class Object {

	protected float x, y;
	protected int width, height;
	
	public abstract void tick();
	
	public abstract void render(GraphicsContext g);
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
		
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
