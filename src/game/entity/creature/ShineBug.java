package game.entity.creature;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.Handler;
import game.block.Block;
import game.gfx.Assets;
import game.utils.Utils;
import lib.Vector;

public class ShineBug extends Creature{

	private int facing = 0;
	
	private int ENEMY_WIDTH = 64, ENEMY_HEIGHT = 64;
	private float ENEMY_MAX_SPEED = 6f, ENEMY_ACC = 1f;
	
	private float direction = -1;
	
	public ShineBug(Handler handler, Vector pos) {
		super(handler, pos);
	}

	@Override
	public void update() {
		AIMove();
		move();
	}

	@Override
	public void render(Graphics g) {
		if(facing > 0) {//facing right
			g.drawImage(Assets.playerRight, (int) Math.floor(pos.x - handler.getCamera().getXoff()), (int) Math.floor(pos.y - handler.getCamera().getYoff()), ENEMY_WIDTH, ENEMY_HEIGHT, null);
		}else {//facing left
			g.drawImage(Assets.playerLeft, (int) Math.floor(pos.x - handler.getCamera().getXoff()), (int) Math.floor(pos.y - handler.getCamera().getYoff()), ENEMY_WIDTH, ENEMY_HEIGHT, null);
		}
	}
	
	private void AIMove() {
			
	}
	
	private void move() {
		moveX();
		moveY();
	}
	
	private void moveX() {
		ArrayList<Block>blocks = handler.getWorld().getBlocks();
		boolean collided = false;
		Rectangle currentCollision = null;
		for(Block b:blocks) {
			Rectangle bBound = b.getBounds();
			if(Utils.isCollided((int)(pos.x + vel.x), (int)pos.y, bounds.width, bounds.height, bBound.x, bBound.y, bBound.width, bBound.height)) {
				collided = true;
				currentCollision = bBound;
			}
		}
		if(!collided) {
			pos.x += vel.x;
		}else {
			if(vel.x > 0) {
				pos.x = currentCollision.x - ENEMY_WIDTH;
			}else if(vel.x < 0){
				pos.x = currentCollision.x + currentCollision.width;
			}
			vel.x = 0;
		}
	}
	
	private void moveY() {
		ArrayList<Block>blocks = handler.getWorld().getBlocks();
		Rectangle currentCollision = null;
		boolean collided = false;
		for(Block b:blocks) {
			Rectangle bBound = b.getBounds();
			if(Utils.isCollided((int)pos.x, (int)(pos.y + vel.y), bounds.width, bounds.height, bBound.x, bBound.y, bBound.width, bBound.height)) {
				collided = true;
				currentCollision = bBound;
			}
		}
		if(!collided) {
			pos.y += vel.y;
		}else {
			if(vel.y > 0) {
				pos.y = currentCollision.y - ENEMY_HEIGHT + 0.8f;
			}else if(vel.y < 0){
				pos.y = currentCollision.y + currentCollision.height;
			}
			vel.y = 0;
		}
	}

}
