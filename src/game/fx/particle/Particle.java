package game.fx.particle;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.fx.Glow;

public class Particle {
	
	@SuppressWarnings("unused")
	private Color c;
	private float x, y, radius;
	private int life, peak;
	@SuppressWarnings("unused")
	private Handler handler;
	
	private Glow glow;
	
	public Particle(Handler handler, Color c, float x, float y,  float radius, int life) {
		this.handler = handler;
		this.c = c;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.life = life;
		this.peak = (int) life/2;
		glow = new Glow(handler, c, (int) x,(int) y, 0);
	}
	
	public void update() {
		glow.setX(x);
		glow.setY(y);
		radius = Math.abs(peak-life)*radius/peak;
		glow.setRadius(radius);
		life--;
		glow.update();
	}
	
	public void render(Graphics g) {
		glow.render(g);
	}
	
	public boolean isDead() {
		if(life <= 0)
			return true;
		return false;
	}
	
}
