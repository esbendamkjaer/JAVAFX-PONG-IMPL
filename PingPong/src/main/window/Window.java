package main.window;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Game;

public class Window extends Application {
	
	private String title = "Ping Pong";
	private Game game;
	
	public static final int WIDTH = 800, HEIGHT = 600;
	
	public static final double SCALE = 1d;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(title);
		
		Group root = new Group();
		
		game = new Game(WIDTH * SCALE, HEIGHT * SCALE);
		
		root.getChildren().add(game);
		
		Scene scene = new Scene(root, WIDTH * SCALE, HEIGHT * SCALE);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
		
		game.start();
	}
	
}
