package game.fx;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.gfx.MyColor;

public class Glow {
	
	private Color c;
	private Handler handler;
	
	private int x, y;
	private float radius;
	
	public Glow(Handler handler, int x, int y, int radius) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		int cycle_times = 25;
		for (int i = 1; i <= cycle_times; i++) {
            g.setColor(MyColor.TRANS_RED_10);
            g.fillOval((int)(y - radius*2/25*i/2 - handler.getCamera().getXoff()), (int)(x - radius*2/25*i/2 - handler.getCamera().getYoff()), 
            		(int)(radius*2/25*i), (int)(radius*2/25*i));
        }
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
	
}
