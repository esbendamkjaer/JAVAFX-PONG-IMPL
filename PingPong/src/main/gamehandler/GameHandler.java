package main.gamehandler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import main.Game;
import main.objects.Ball;
import main.objects.pad.AIPaddle;
import main.objects.pad.Paddle;
import main.objects.pad.PlayerPaddle;

public class GameHandler {
	
	private Paddle left_paddle;
	private Paddle right_paddle;
	private Ball ball;

	public void init() {
		ball = new Ball();
		left_paddle = new PlayerPaddle();
		right_paddle = new AIPaddle(ball);
	}
		
	public void tick() {
		left_paddle.tick();
		right_paddle.tick();
		ball.tick();
				
		outsideBoard();
		
		checkCollisions();
	}
	
	public void render(Graphics2D g) {
		drawBackground(g);
		
		left_paddle.render(g);
		right_paddle.render(g);
		ball.render(g);
	}
	
	private void drawBackground(Graphics2D g) {
		g.setColor(new Color(51, 51, 51));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setColor(Color.WHITE);
		Stroke dashed = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{12, 8}, 0);
		g.setStroke(dashed);
		int lineX = Game.WIDTH / 2;
		g.drawLine(lineX, 0, lineX, Game.HEIGHT);
		
		g.setFont(new Font("Verdana", Font.BOLD, 20));
		FontMetrics fm = g.getFontMetrics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		String points = left_paddle.getPoints() + "";
		g.drawString(points, Game.WIDTH/4 - fm.stringWidth(points), fm.getHeight() + 20);
		points = right_paddle.getPoints() + "";
		g.drawString(points, Game.WIDTH - Game.WIDTH/4 - fm.stringWidth(points), fm.getHeight() + 20);
	}
	
	private void checkCollisions() {
		
		if (ball.getX() < left_paddle.getX() + left_paddle.getWidth()) {
			if (isInsidePaddle(left_paddle)) {
				ball.setX(left_paddle.getX() + left_paddle.getWidth());
				ball.setVelX(-ball.getVelX());
				calYVelFromPaddle(left_paddle);
				playSound();
			}
		} else if (ball.getX() + ball.getWidth() > right_paddle.getX()) {
			if (isInsidePaddle(right_paddle)) {
				ball.setX(right_paddle.getX() - ball.getWidth());
				ball.setVelX(-ball.getVelX());
				calYVelFromPaddle(right_paddle);
				playSound();
			}
		}
		
		if (ball.getY() < 0) {
			ball.setVelY(-ball.getVelY());
			ball.setY(0);
			playSound();
		} else if (ball.getY() + ball.getHeight()> Game.HEIGHT) {
			ball.setVelY(-ball.getVelY());
			ball.setY(Game.HEIGHT - ball.getHeight());
			playSound();
		}
	}
	
	private void calYVelFromPaddle(Paddle paddle) {
		ball.setVelY(-((paddle.getY() + paddle.getHeight()/ 2) - (ball.getY() + ball.getHeight()/ 2)) * 0.25f);
	}
	
	private boolean isInsidePaddle(Paddle paddle) {
		if (ball.getY() + ball.getHeight() >= paddle.getY() && ball.getY() <= paddle.getY() + paddle.getHeight()) {
			return true;
		}
		return false;
	}
	
	private void playSound() {
		try {
			InputStream in = GameHandler.class.getResourceAsStream("/plong.wav");
			AudioInputStream as = AudioSystem.getAudioInputStream(in);
			Clip clip = AudioSystem.getClip();
			clip.open(as);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void outsideBoard() {
		if (ball.getX() + ball.getWidth() < left_paddle.getX()) {
			right_paddle.setPoints(right_paddle.getPoints() + 1);
		} else if (ball.getX() > right_paddle.getX() + right_paddle.getWidth()) {
			left_paddle.setPoints(left_paddle.getPoints() + 1);
		} else return;
		ball.setVelX(-ball.getVelX());
		ball.setVelY((float) Math.random() * 12 - 6);
		ball.setX(Game.WIDTH / 2 - ball.getWidth() / 2);
		ball.setY(Game.HEIGHT / 2 - ball.getHeight() / 2); 
	}
	
}
