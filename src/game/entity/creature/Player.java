package game.entity.creature;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.Handler;
import game.block.Block;
import game.fx.particle.ParticleWalk;
import game.gfx.Assets;
import game.utils.Utils;
import lib.Vector;

public class Player extends Creature{
	
	private int PLAYER_WIDTH = 64, PLAYER_HEIGHT = 64;
	private float PLAYER_MAX_SPEED = 4, PLAYER_ACC = 1f;
	private float PLAYER_JUMP_FORCE = 15;
	
	private int facing = 0;
	private int jumpsCounter = 0;
	
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
		keyMove();
		applyGravity();
		move();
		vel.x *= 0.9;
		handler.getCamera().focusEntity(this);
	}

	@Override
	public void render(Graphics g) {
		if(facing > 0) {//facing right
			g.drawImage(Assets.playerRight, (int) Math.floor(pos.x - handler.getCamera().getXoff()), (int) Math.floor(pos.y - handler.getCamera().getYoff()), PLAYER_WIDTH, PLAYER_HEIGHT, null);
		}else {//facing left
			g.drawImage(Assets.playerLeft, (int) Math.floor(pos.x - handler.getCamera().getXoff()), (int) Math.floor(pos.y - handler.getCamera().getYoff()), PLAYER_WIDTH, PLAYER_HEIGHT, null);
		}
	}
	
	/////////////////movement/////////////////
	
	private void move() {
		moveX();
		moveY();
		float mag = (float) Math.sqrt(vel.x*vel.x + vel.y*vel.y);
		Utils.truncate(vel.x, PLAYER_MAX_SPEED);
	}
	
	/////////////////utils/////////////////
	
	private void applyGravity() {
		vel.y += 0.4;
	}
	
	private void keyMove() {
		float tempx = 0;
		float tempy = 0;
		
		boolean pressUp = handler.getKeyManager().isKeyPressed(KeyEvent.VK_SPACE),
				pressLeft = handler.getKeyManager().isKeyPressed(KeyEvent.VK_A),
				pressRight = handler.getKeyManager().isKeyPressed(KeyEvent.VK_D);
		moving = false;
		
		if(pressUp) {
			if(jumpsCounter > 0) {
				jumpsCounter--;
				vel.y -= PLAYER_JUMP_FORCE;
			}
		}if(pressLeft) {
			facing = -1;
			vel.x -= PLAYER_ACC;
		}if(pressRight) {
			facing = 1;
			vel.x += PLAYER_ACC;
		}
		
		float mag = (float) Math.sqrt(tempx*tempx + tempy*tempy);
		if(mag != 0) {
			vel.x += tempx * Utils.truncate(mag, PLAYER_MAX_SPEED)/mag;
			vel.y += tempy * Utils.truncate(mag, PLAYER_MAX_SPEED)/mag;
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
		if(!collided) {
			pos.x += vel.x;
		}else {
			if(vel.x > 0) {
				pos.x = currentCollision.x - PLAYER_WIDTH;
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
				pos.y = currentCollision.y - PLAYER_HEIGHT + 0.8f;
				onGround = true;
				jumpsCounter = 1;
			}else if(vel.y < 0){
				onGround = false;
				pos.y = currentCollision.y + currentCollision.height;
			}
			vel.y = 0;
		}
	}
	
	private void addPlayerParticle(float chance) {
		System.out.println(Math.abs(vel.x));
		if(onGround && Math.abs(vel.x) > PLAYER_MAX_SPEED-0.1 && Math.random() < 0.2) {
			handler.getWorld().getParticleManager().addParticle(new ParticleWalk(handler, Utils.Color(27, 12, 40, 10), new Vector(pos.x+PLAYER_WIDTH/2, pos.y+PLAYER_HEIGHT), new Vector(vel.x*-0.5f, (float) -(Math.random()*2+0.5f)), 10, 50));
		}
	}
}
