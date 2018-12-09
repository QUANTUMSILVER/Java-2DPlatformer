package game.entity.creature;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.Handler;
import game.block.Block;
import game.gfx.Assets;
import game.utils.Utils;
import lib.Vector;

public class Player extends Creature{
	
	public static int PLAYER_WIDTH = 64, PLAYER_HEIGHT = 64;
	public static float PLAYER_MAX_SPEED = 5f;
	public static float PLAYER_JUMP_FORCE = 10;
	
	private float maxForce = 0.8f;
	
	private int facing = 0;
	
	private boolean moving = false, onGround = false;
	private Vector vel;
	
	public Player(Handler handler, Vector pos) {
		super(handler, pos);
		vel = new Vector((float) (Math.random()-0.5f)*2, (float) (Math.random()-0.5f)*2);
		bounds.width = PLAYER_WIDTH;
		bounds.height = PLAYER_HEIGHT;
	}

	@Override
	public void update() {
		bounds.x = (int) pos.x;
		keyMove();
		applyGravity();
		move();
		handler.getCamera().focusEntity(this);
	}

	@Override
	public void render(Graphics g) {
		if(facing > 0) {//facing right
			g.drawImage(Assets.playerRight, (int) (pos.x - handler.getCamera().getXoff()), (int) (pos.y - handler.getCamera().getYoff()), PLAYER_WIDTH, PLAYER_HEIGHT, null);
		}else {//facing left
			g.drawImage(Assets.playerLeft, (int) (pos.x - handler.getCamera().getXoff()), (int) (pos.y - handler.getCamera().getYoff()), PLAYER_WIDTH, PLAYER_HEIGHT, null);
		}
	}
	
	//movement
	
	private void applyGravity() {
		vel.y += 0.4;
	}
	
	private void keyMove() {
		float tempx = 0;
		float tempy = 0;
		
		boolean pressUp = handler.getKeyManager().isKeyPressed(KeyEvent.VK_SPACE),
				//pressDown = handler.getKeyManager().isKeyPressed(KeyEvent.VK_S),
				pressLeft = handler.getKeyManager().isKeyPressed(KeyEvent.VK_A),
				pressRight = handler.getKeyManager().isKeyPressed(KeyEvent.VK_D);
		moving = false;
		if(pressUp && onGround) {
			onGround = false;
			vel.y -= PLAYER_JUMP_FORCE;
			moving = true;
		}if(pressLeft) {
			facing = -1;
			if(onGround) {
				tempx -= maxForce;
			}else {
				tempx -= maxForce/20;
			}
			moving = true;
		}else if(pressRight) {
			facing = 1;
			if(onGround) {
				tempx += maxForce;
			}else {
				tempx += maxForce/20;
			}
			moving = true;
		}
		
		float mag = (float) Math.sqrt(tempx*tempx + tempy*tempy);
		if(mag != 0) {
			vel.x += tempx * Utils.truncate(mag, PLAYER_MAX_SPEED)/mag;
			vel.y += tempy * Utils.truncate(mag, PLAYER_MAX_SPEED)/mag;
		}
	}
	
	private void move() {
		moveX();
		moveY();
		if(onGround && !moving) {
			vel.scale(0.7f);
		}else if(onGround) {
			float mag = (float) Math.sqrt(vel.x*vel.x + vel.y*vel.y);
			if(mag != 0) {
				vel.scale(Utils.truncate(mag, PLAYER_MAX_SPEED)/mag);
			}
		}
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
		if(collided) {
			if(vel.x > 0) {
				pos.x = currentCollision.x-PLAYER_WIDTH;
			}else if(vel.x < 0){
				pos.x = currentCollision.x+currentCollision.width;
			}
			vel.x = 0;
			bounds.x = (int) pos.x;
		}else {
			pos.x += vel.x;
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
		if(collided) {
			if(vel.y > 0) {
				onGround = true;
				pos.y = currentCollision.y-PLAYER_HEIGHT;
			}else if(vel.y < 0){
				pos.y = currentCollision.y+currentCollision.height;
			}
			vel.y = 0;
			bounds.y = (int) pos.y;
		}else {
			if(vel.y > 0)
				onGround = false;
			pos.y += vel.y;
		}
	}
	
}
