package game.entity.staticEntity.plant;

import java.awt.Graphics;
import java.util.ArrayList;

import game.entity.staticEntity.StaticEntity;

public class StaticManager {
	
	private ArrayList<StaticEntity>entities;
	
	public StaticManager() {
		entities = new ArrayList<StaticEntity>();
	}
	
	public void update() {
		for(StaticEntity e:entities) {
			e.update();
		}
	}
	
	public void render(Graphics g) {
		for(StaticEntity e:entities) {
			e.render(g);
		}
	}
	
	public void addEntity(StaticEntity e) {
		entities.add(e);
	}
	
	public void removeEntity(StaticEntity e) {
		entities.remove(e);
	}

	public ArrayList<StaticEntity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<StaticEntity> entities) {
		this.entities = entities;
	}
	
}
