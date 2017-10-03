package main.objects.paddle;

import main.listener.Listener;
import main.window.Window;

public class PlayerPaddle extends Paddle {
	
	public PlayerPaddle() {
		x = 0;
		y = 0;
	}

	@Override
	protected void move() {
		double distance = yDistance(y + height / 2, Listener.mouse_y);
		double speed = this.speed;
		
		if (distance < this.speed) {
			speed = distance;
		}
		
		if (y + height / 2 - speed/2 > Listener.mouse_y / Window.SCALE) {
			y -= speed;
		} else if (y + height / 2  + speed/2 < Listener.mouse_y / Window.SCALE) {
			y += speed;
		}
	}
	
}
