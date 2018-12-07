package game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;

public abstract class Entity {
	
	protected int Width = 32, Height = 32;
	
	protected float x, y;
	protected Handler handler;
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		bounds = new Rectangle(0, 0, Width, Height);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
