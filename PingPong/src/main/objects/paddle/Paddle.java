package main.objects.pad;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Game;
import main.objects.Object;

public abstract class Paddle extends Object {
	
	protected float speed = 10;
	private int points = 0;
	
	public Paddle() {
		width = 18;
		height = 120;
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, width, height);
	}
	
	protected abstract void move();
	
	@Override
	public void tick() {
		
		move();
		
		if (y <= 0) {
			y = 0;
		} else if (y + height >= Game.HEIGHT) {
			y = Game.HEIGHT - height;
		}
		
	}
	
	protected float yDistance(float y1, float y2) {
		return Math.max(y1, y2) - Math.min(y1, y2);
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
}
