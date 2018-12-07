package game.block;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;

public abstract class Block {
	
	protected int x, y, width, height;
	protected Rectangle bounds;
	protected Handler handler;
	
	public Block(Handler handler, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		bounds = new Rectangle(x, y, width, height);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
}
