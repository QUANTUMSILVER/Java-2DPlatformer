package game.entity.creature;

import java.awt.Graphics;
import java.util.ArrayList;

public class CreatureManager {
	
	private ArrayList<Creature>entities;
	
	public CreatureManager() {
		entities = new ArrayList<Creature>();
	}
	
	public void update() {
		for(Creature e:entities) {
			e.update();
		}
	}
	
	public void render(Graphics g) {
		for(Creature e:entities) {
			e.render(g);
		}
	}
	
	public void addEntity(Creature e) {
		entities.add(e);
	}
	
	public void removeEntity(Creature e) {
		entities.remove(e);
	}

	public ArrayList<Creature> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Creature> entities) {
		this.entities = entities;
	}
	
}
