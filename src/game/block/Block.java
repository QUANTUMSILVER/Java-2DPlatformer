package game.block;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import game.utils.Utils;
import lib.Vector;

public abstract class Block {
	
	protected int width, height;
	protected Vector pos;
	
	protected Rectangle bounds;
	protected Handler handler;
	
	public Block(Handler handler, Vector pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.handler = handler;
		bounds = new Rectangle((int)pos.x, (int)pos.y, width, height);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	protected boolean onScreen() {
		return Utils.isCollided(bounds.x, bounds.y, bounds.width, bounds.height, (int) handler.getCamera().getXoff(), (int) handler.getCamera().getYoff(), handler.getWidth(), handler.getHeight());
	}
	
}
