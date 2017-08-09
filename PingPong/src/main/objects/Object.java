package main.objects;

import java.awt.Graphics2D;

public abstract class Object {

	protected float x, y;
	protected int width, height;
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
	
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
