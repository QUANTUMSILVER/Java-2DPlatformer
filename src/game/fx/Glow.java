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
