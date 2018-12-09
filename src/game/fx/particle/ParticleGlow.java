package game.fx.particle;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.utils.Utils;
import lib.Vector;

public class ParticleGlow extends Particle{
	
	private Color c;
	
	private Vector pos, vel;
	private float max_speed = 2;
	private float max_force = 0.001f;
	
	private float radius;
	private int peak;
	
	public ParticleGlow(Handler handler, Color c, Vector pos,  float radius, int life) {
		super(handler, life);
		this.pos = pos;
		vel = new Vector((float) ((Math.random()-0.5)*2), (float) ((Math.random()-0.5)*2));
		this.handler = handler;
		this.c = c;
		this.radius = radius;
		this.peak = (int) life/2;
	}
	
	public void update() {
		updateVel();
		pos.x += vel.x;
		pos.y += vel.y;
		life--;
	}
	
	public void render(Graphics g) {
		if(pos.x - handler.getCamera().getXoff() > -radius && pos.x - handler.getCamera().getXoff() < handler.getWidth()+radius && 
				pos.y - handler.getCamera().getYoff() > -radius && pos.y - handler.getCamera().getYoff() < handler.getHeight()+radius) {
			int cycle_times = 25;
			float cRadius = (peak-Math.abs(peak-life))*radius/peak;
			for (int i = 1; i <= cycle_times; i++) {
				g.setColor(c);
				g.fillOval((int)(pos.x - cRadius*2/25*i/2 - handler.getCamera().getXoff()), 
						(int)(pos.y - cRadius*2/25*i/2 - handler.getCamera().getYoff()), 
						(int)(cRadius*2/25*i), 
						(int)(cRadius*2/25*i));
			}
		}
	}
	
	private Vector getDesiredVel(Vector target) {
		Vector desired = new Vector(target.x, target.y);
		desired.subtract(pos);
		desired.scale(max_speed);
		return desired;
	}
	
	private Vector getSteering(Vector desired) {
		Vector steering = new Vector(desired.x, desired.y);
		steering.subtract(vel);
		steering = Utils.truncate(steering, max_force);
		return steering;
	}
	
	private void updateVel() {
		Vector desired = getDesiredVel(new Vector(handler.getWorld().getPlayer().getPos().x, handler.getWorld().getPlayer().getPos().y));
		desired.scale(-1);
		Vector steering = getSteering(desired);
		steering.add(vel);
		vel = Utils.truncate(steering, max_speed);
	}
	
}
