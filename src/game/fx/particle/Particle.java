package game.fx.particle;

import java.awt.Graphics;

import game.Handler;

public abstract class Particle {
	
	protected Handler handler;
	protected int life;
	
	public Particle(Handler handler, int life) {
		this.handler = handler;
		this.life = life;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public boolean isDead() {
		if(life <= 0)
			return true;
		return false;
	}
	
}
