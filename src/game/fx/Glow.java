package game.fx;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;

public class Glow {
	
	private Color c;
	private Handler handler;
	
	private int x, y;
	private float radius;
	
	public Glow(Handler handler, Color c,  int x, int y, int radius) {
		this.handler = handler;
		this.c = c;
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		if(!(x - handler.getCamera().getXoff() < -radius || x - handler.getCamera().getXoff() > handler.getWidth() + radius || 
				y - handler.getCamera().getYoff() < -radius || y - handler.getCamera().getYoff() > handler.getHeight() + radius)) {
			int cycle_times = 25;
			for (int i = 1; i <= cycle_times; i++) {
	            g.setColor(c);
	            g.fillOval((int)(y - radius*2/25*i/2 - handler.getCamera().getXoff()), (int)(x - radius*2/25*i/2 - handler.getCamera().getYoff()), 
	            		(int)(radius*2/25*i), (int)(radius*2/25*i));
	        }
		}
	}

	public int getX() {
		return x;
	}

	public void setX(float x) {
		this.x = (int) x;
	}

	public int getY() {
		return y;
	}

	public void setY(float y) {
		this.y = (int) y;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
	
}
