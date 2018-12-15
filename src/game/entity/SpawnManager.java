package game.entity;

import java.util.ArrayList;

public class SpawnManager {
	
	private ArrayList<SpawnPoint>spawnPoints;
	
	public SpawnManager() {
		spawnPoints = new ArrayList<SpawnPoint>();
	}
	
	public void update() {
		for(SpawnPoint p:spawnPoints) {
			p.update();
		}
	}
	
	public void addSpawn(SpawnPoint p) {
		spawnPoints.add(p);
	}
	
	public void removeSpawn(SpawnPoint p) {
		spawnPoints.remove(p);
	}

	public ArrayList<SpawnPoint> getSpawns() {
		return spawnPoints;
	}

	public void setSpawns(ArrayList<SpawnPoint> entities) {
		this.spawnPoints = entities;
	}
	
}
