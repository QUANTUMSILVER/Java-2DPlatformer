package game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import lib.Vector;

public abstract class Entity {
	
	protected int Width = 32, Height = 32;
	
	protected Vector pos;
	protected Handler handler;
	protected Rectangle bounds;
	
	public Entity(Handler handler, Vector pos) {
		this.handler = handler;
		this.pos = pos;
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

	public Vector getPos() {
		return pos;
	}

	public void setPos(Vector pos) {
		this.pos = pos;
	}
	
}
