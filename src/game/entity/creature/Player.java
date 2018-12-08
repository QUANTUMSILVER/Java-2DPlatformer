package game.entity.creature;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.Handler;
import game.block.Block;
import game.gfx.Assets;
import game.utils.Utils;

public class Player extends Creature{
	
	public static int PLAYER_WIDTH = 64, PLAYER_HEIGHT = 64;
	public static float PLAYER_MAX_SPEED = 5f;
	public static float PLAYER_JUMP_FORCE = 10;
	
	private float maxForce = 0.8f;
	
	private int facing = 0;
	
	private boolean moving = false, onGround = false;
	private float velx, vely;
	
	public Player(Handler handler, int x, int y) {
		super(handler, x, y);
		bounds.width = PLAYER_WIDTH;
		bounds.height = PLAYER_HEIGHT;
	}

	@Override
	public void update() {
		bounds.x = (int) x;
		keyMove();
		applyGravity();
		move();
		handler.getCamera().focusEntity(this);
	}

	@Override
	public void render(Graphics g) {
		if(facing > 0) {//facing right
			g.drawImage(Assets.playerRight, (int) (x - handler.getCamera().getXoff()), (int) (y - handler.getCamera().getYoff()), PLAYER_WIDTH, PLAYER_HEIGHT, null);
		}else {//facing left
			g.drawImage(Assets.playerLeft, (int) (x - handler.getCamera().getXoff()), (int) (y - handler.getCamera().getYoff()), PLAYER_WIDTH, PLAYER_HEIGHT, null);
		}
	}
	
	//movement
	
	private void applyGravity() {
		vely += 0.4;
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
			vely -= PLAYER_JUMP_FORCE;
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
			velx += tempx * Utils.truncate(mag, PLAYER_MAX_SPEED)/mag;
			vely += tempy * Utils.truncate(mag, PLAYER_MAX_SPEED)/mag;
		}
	}
	
	private void move() {
		moveX();
		moveY();
		if(onGround && !moving) {
			velx *= 0.7;
			vely *= 0.7;
		}else if(onGround) {
			float mag = (float) Math.sqrt(velx*velx + vely*vely);
			if(mag != 0) {
				velx = velx * Utils.truncate(mag, PLAYER_MAX_SPEED)/mag;
				vely = vely * Utils.truncate(mag, PLAYER_MAX_SPEED)/mag;
			}
		}
	}
	
	private void moveX() {
		ArrayList<Block>blocks = handler.getWorld().getBlocks();
		boolean collided = false;
		Rectangle currentCollision = null;
		for(Block b:blocks) {
			Rectangle bBound = b.getBounds();
			if(Utils.isCollided((int)(x + velx), (int)y, bounds.width, bounds.height, bBound.x, bBound.y, bBound.width, bBound.height)) {
				collided = true;
				currentCollision = bBound;
			}
		}
		if(collided) {
			if(velx > 0) {
				x = currentCollision.x-PLAYER_WIDTH;
			}else if(velx < 0){
				x = currentCollision.x+currentCollision.width;
			}
			velx = 0;
			bounds.x = (int) x;
		}else {
			x += velx;
		}
	}
	
	private void moveY() {
		ArrayList<Block>blocks = handler.getWorld().getBlocks();
		Rectangle currentCollision = null;
		boolean collided = false;
		for(Block b:blocks) {
			Rectangle bBound = b.getBounds();
			if(Utils.isCollided((int)x, (int)(y + vely), bounds.width, bounds.height, bBound.x, bBound.y, bBound.width, bBound.height)) {
				collided = true;
				currentCollision = bBound;
			}
		}
		if(collided) {
			if(vely > 0) {
				onGround = true;
				y = currentCollision.y-PLAYER_HEIGHT;
			}else if(vely < 0){
				y = currentCollision.y+currentCollision.height;
			}
			vely = 0;
			bounds.y = (int) y;
		}else {
			if(vely > 0)
				onGround = false;
			y += vely;
		}
	}
	
}
