package main.objects.paddle;

import main.objects.Ball;
import main.window.Window;

public class AIPaddle extends Paddle {
	
	private Ball ball;
	
	public AIPaddle(Ball ball) {
		this.ball = ball;
		x = Window.WIDTH-width;
		y = 0;
		speed = 8;
	}

	@Override
	protected void move() {
		double distance = yDistance(y + height / 2, ball.getY() + ball.getHeight() / 2);
		double speed = this.speed;
		
		if (distance < this.speed) {
			speed = distance;
		}
		
		if (y + height / 2 > ball.getY() + ball.getHeight() / 2) {
			y -= speed;
		} else if (y + height / 2 < ball.getY() + ball.getHeight() / 2) {
			y += speed;
		}
	}

}
