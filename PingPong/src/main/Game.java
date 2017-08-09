package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import main.gamehandler.GameHandler;
import main.listener.Listener;
import main.objects.Ball;
import main.objects.pad.AIPaddle;
import main.objects.pad.PlayerPaddle;
import main.window.Window;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -8395759457708163217L;
	
	private Thread game;
	private boolean running;
	
	private Window window;
		
	private final double scale = 1;
	public static final int WIDTH = 800, HEIGHT = 600;
		
	public static final int TICKS_PER_SECOND = 60;

	private GameHandler gameHandler;
	
	private Listener listener;
	
	public Game() {
		
		window = new Window("Pong", (int) (WIDTH*scale), (int) (HEIGHT*scale), this);
		
		listener = new Listener();
		addMouseMotionListener(listener);
		
		requestFocus();
	}
	
	
	public void init() {
		gameHandler = new GameHandler();
		gameHandler.init();
	}
	
	@Override
	public void run() {
		
		init();
		
		int fpsCount = 0;
		
		double nsPerTick = 1000000000/TICKS_PER_SECOND;
		
		double delta = 0;
		
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		
		long sleepTime = (long) (1000/TICKS_PER_SECOND*(1-0.1));
		
		while (running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/nsPerTick;
			lastTime = now;
			
			while (delta >= 1) {
				tick();
				render();
				fpsCount++;
				delta--;
			}
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (System.currentTimeMillis()-lastTimer >= 1000) {
				System.out.println("FPS: " + fpsCount);
				fpsCount = 0;
				lastTimer+=1000;
			}
			
		}
		
	}
	
	public void tick() {
		gameHandler.tick();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.scale(scale, scale);
		g.clearRect(0, 0, getWidth(), getHeight());
		
		gameHandler.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	/**
	 * Start the game thread.
	 */
	public void start() {
		if (running) return;
		game = new Thread(this, "Game");
		game.start();
		running = true;
	}
	
	/**
	 * Stop the game thread.
	 */
	public void stop() {
		if (!running) return;
		try {
			game.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		running = false;
	}
	
}
