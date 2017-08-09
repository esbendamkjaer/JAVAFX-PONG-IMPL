package main.objects.pad;

import main.Game;
import main.objects.Ball;

public class AIPaddle extends Paddle {
	
	private Ball ball;
	
	public AIPaddle(Ball ball) {
		this.ball = ball;
		x = Game.WIDTH-width;
		y = 0;
		speed = 8;
	}

	@Override
	protected void move() {
		float distance = yDistance(y + height / 2, ball.getY() + ball.getHeight() / 2);
		float speed = this.speed;
		
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
