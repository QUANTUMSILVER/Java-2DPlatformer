package game.entity;

import java.awt.Rectangle;

import game.Handler;
import game.entity.creature.Creature;
import game.entity.creature.HornedBeetle;
import game.entity.creature.ShineBug;
import game.utils.Utils;
import lib.Vector;

public class SpawnPoint {
	
	private Handler handler;
	private int id;
	private Vector pos;
	
	private int respawnTime = -1;

	
	private double last = System.nanoTime(), current, delta;
	
	public SpawnPoint(Handler handler, int id, Vector pos, int respawnTime) {
		this.respawnTime = respawnTime;
		this.handler = handler;
		this.pos = pos;
		this.id = id;
		spawn(id);
	}
	
	public void update() {
		current = System.nanoTime();
		delta += (current - last)/1000000000;
		last = current;
		if(delta >= respawnTime && respawnTime > 0 && new Rectangle((int) (handler.getCamera().getXoff()-handler.getWidth()/2),
				(int) (handler.getCamera().getYoff()-handler.getHeight()/2), handler.getWidth(), handler.getHeight()).contains(pos.x, pos.y)) {
			spawn(id);
			delta -= respawnTime;
		}
	}
	
	private void spawn(int id) {
		Creature e = null;
		if(id == 0)
			e = new HornedBeetle(handler, pos.copy());
		else if(id == 1)
			e = new ShineBug(handler, pos.copy());
		if(e != null) {
			handler.getWorld().getCreatureManager().addEntity(e);
		}
	}
	
}
