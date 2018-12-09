package game.fx.particle;

import java.awt.Graphics;
import java.util.ArrayList;

public class ParticleManager {
	
	private ArrayList<Particle>particlesToRemove;
	private ArrayList<Particle>particles;
	
	public ParticleManager(){
		particlesToRemove = new ArrayList<Particle>();
		particles = new ArrayList<Particle>();
	}
	
	public void update() {
		for(Particle p:particles) {
			if(p.isDead())
				particlesToRemove.add(p);
			p.update();
		}
		particles.removeAll(particlesToRemove);
		particlesToRemove = new ArrayList<Particle>();
	}
	
	public void render(Graphics g) {
		for(Particle p:particles) {
			p.render(g);
		}
	}
	
	public void addParticle(Particle p) {
		particles.add(p);
	}
	
	public void removeParticle(Particle p) {
		particles.remove(p);
	}
	
}
