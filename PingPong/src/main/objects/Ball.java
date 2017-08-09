package main.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Game;
import main.objects.paddle.Paddle;

public class Ball extends Object {
		
	private float yVel = 3;
	private float xVel = -16;
	
	public Ball() {
		
		x = Game.WIDTH / 2 - width / 2;
		y = Game.HEIGHT / 2 - height / 2; 
		
		width = 15;
		height = 15;
		
	}
	
	@Override
	public void tick() {
		
		y += yVel;
		x += xVel;
		
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.white);
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
