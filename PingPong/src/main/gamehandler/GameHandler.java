package main.gamehandler;

import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.objects.Ball;
import main.objects.paddle.AIPaddle;
import main.objects.paddle.Paddle;
import main.objects.paddle.PlayerPaddle;
import main.window.Window;

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
	
	public void render(GraphicsContext g) {
		drawBackground(g);
		
		left_paddle.render(g);
		right_paddle.render(g);
		ball.render(g);
	}
	
	private void drawBackground(GraphicsContext g) {
		g.setFill(Color.rgb(51, 51, 51));
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		
		g.setFill(Color.WHITE);
		//Stroke dashed = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{12, 8}, 0);
		//g.setStroke(dashed);
		
		g.setStroke(Color.WHITE);
		g.setLineWidth(5);
		g.setLineDashes(12);
		g.setLineDashOffset(6);
		g.setLineCap(StrokeLineCap.BUTT);
		g.setLineJoin(StrokeLineJoin.BEVEL);
		
		int lineX = Window.WIDTH / 2;
		g.strokeLine(lineX, 0, lineX, Window.HEIGHT);
		
		Font font = Font.font("Verdana", FontWeight.BOLD, 20);
		
		g.setFontSmoothingType(FontSmoothingType.GRAY);
		
		g.setFont(font);
		String points = left_paddle.getPoints() + "";
		
		Text message = new Text(points);
		message.setFont(font);
		
		//g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		
		g.fillText(points, Window.WIDTH/4 - message.getLayoutBounds().getWidth(), message.getLayoutBounds().getHeight() + 20);
		points = right_paddle.getPoints() + "";
		g.fillText(points, Window.WIDTH - Window.WIDTH/4 - message.getLayoutBounds().getWidth(), message.getLayoutBounds().getHeight() + 20);
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
		} else if (ball.getY() + ball.getHeight()> Window.HEIGHT) {
			ball.setVelY(-ball.getVelY());
			ball.setY(Window.HEIGHT - ball.getHeight());
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
			if (in == null) return;
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
		ball.setX(Window.WIDTH / 2 - ball.getWidth() / 2);
		ball.setY(Window.HEIGHT / 2 - ball.getHeight() / 2); 
	}
	
}
