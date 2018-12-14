package game.entity.creature;

import game.Handler;
import game.entity.Entity;
import lib.Vector;

public abstract class Creature extends Entity{
	
	protected Vector vel;
	protected int HEALTH;
	
	public Creature(Handler handler, Vector pos) {
		super(handler, pos);
		vel = new Vector(0,0);
		// TODO Auto-generated constructor stub
	}
	
	protected void applyGravity() {
		vel.y += 0.4;
	}
	
	public boolean isEnemy() {
		return false;
	}

}
