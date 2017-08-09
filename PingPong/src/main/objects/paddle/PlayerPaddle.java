package main.objects.paddle;

import main.listener.Listener;

public class PlayerPaddle extends Paddle {
	
	public PlayerPaddle() {
		x = 0;
		y = 0;
	}

	@Override
	protected void move() {
		float distance = yDistance(y + height / 2, Listener.mouse_y);
		float speed = this.speed;
		
		if (distance < this.speed) {
			speed = distance;
		}
		
		if (y + height / 2 - speed/2 > Listener.mouse_y) {
			y -= speed;
		} else if (y + height / 2  + speed/2 < Listener.mouse_y) {
			y += speed;
		}
	}
	
}
