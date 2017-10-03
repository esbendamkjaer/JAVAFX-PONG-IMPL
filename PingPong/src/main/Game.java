package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import main.gamehandler.GameHandler;
import main.listener.Listener;
import main.window.Window;

public class Game extends Canvas {
	
	private boolean running;
	
	public static final int TICKS_PER_SECOND = 60;

	private GameHandler gameHandler;
	
	private Listener listener;
	
	private GraphicsContext g;
	
	private AnimationTimer gameLoop = new AnimationTimer() {

		int fpsCount = 0;
		
		double nsPerTick = 1000000000/TICKS_PER_SECOND;
		
		double delta = 0;
		
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
				
		@Override
		public void start() {
			init();
			super.start();
		}
		
		@Override
		public void handle(long now) {

			delta += (now-lastTime)/nsPerTick;
			lastTime = now;
			
			while (delta >= 1) {
				tick();
				render();
				fpsCount++;
				delta--;
			}
			
			if (System.currentTimeMillis()-lastTimer >= 1000) {
				System.out.println("FPS: " + fpsCount);
				fpsCount = 0;
				lastTimer+=1000;
			}
			
		}
		
	};
	
	public Game(double width, double height) {
		super(width, height);
		
		listener = new Listener();
		
		setOnMouseMoved(listener);
		setOnMouseDragged(listener);
		
		requestFocus();
		
	}
	
	
	public void init() {
		g = getGraphicsContext2D();
		g.scale(Window.SCALE, Window.SCALE);

		gameHandler = new GameHandler();
		gameHandler.init();
	}
	
	public void tick() {
		gameHandler.tick();
	}
	
	public void render() {
		
		g.clearRect(0, 0, getWidth(), getHeight());
		gameHandler.render(g);
		
	}
	
	public void start() {
		if (running) return;
		
		gameLoop.start();
		
		running = true;
	}

	public void stop() {
		if (!running) return;
		
		gameLoop.stop();
		
		running = false;
	}
	
}
