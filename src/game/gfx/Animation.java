package game.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	private int index = 0, speed;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation(int speed, int index, BufferedImage[] frames) {
		this.index = index;
		this.speed = speed;
		this.lastTime = System.currentTimeMillis();
		this.frames = frames;
	}
	
	public void update() {
		timer+=System.currentTimeMillis()-lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer -= speed;
			if(index >= frames.length) {
				index = 0;
			}
		}
	}
	
	public BufferedImage getCurretnFrame() {
		return frames[index];
	}
}
