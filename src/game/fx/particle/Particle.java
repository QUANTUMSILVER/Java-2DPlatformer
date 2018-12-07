package game.fx.particle;

import java.awt.Graphics;

import game.Handler;

public class Particle {
	
	private float x, y;
	private int life, peak;
	private Handler handler;
	
	public Particle(Handler handler, float x, float y, int life) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.life = life;
		this.peak = (int) life/2;
	}
	
	public void update() {
		life--;
	}
	
	public void render(Graphics g) {
		
	}
	
	public boolean isDead() {
		if(life <= 0)
			return true;
		return false;
	}
	
}
