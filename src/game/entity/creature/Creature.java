package game.entity.creature;

import game.Handler;
import game.entity.Entity;
import lib.Vector;

public abstract class Creature extends Entity{
	
	protected int HEALTH;
	
	public Creature(Handler handler, Vector pos) {
		super(handler, pos);
		// TODO Auto-generated constructor stub
	}

}
