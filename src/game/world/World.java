package game.world;

import java.awt.Graphics;
import java.util.ArrayList;

import game.Handler;
import game.block.Block;
import game.block.BlockManager;
import game.block.GrassBlock;
import game.entity.creature.Creature;
import game.entity.creature.CreatureManager;
import game.entity.creature.enemy.Enemy;
import game.entity.player.Player;
import game.entity.staticEntity.StaticEntity;
import game.entity.staticEntity.StaticManager;
import game.entity.staticEntity.plant.Grass;
import game.fx.particle.ParticleGlow;
import game.fx.particle.ParticleManager;
import game.utils.Utils;
import lib.Vector;

public class World {
	
	private int width, height;
	private float ppfpp;//particles per frame per pixel
	
	private BlockManager blockManager;
	private StaticManager staticManager;
	private CreatureManager creatureManager;
	private ParticleManager particleManager;
	
	private Player player;
	private Handler handler;
	
	public World(Handler handler, String path) {
		
		blockManager = new BlockManager();
		staticManager = new StaticManager();
		creatureManager = new CreatureManager();
		particleManager = new ParticleManager();
		
		this.handler = handler;
		handler.setWorld(this);
		loadWorld(path);
	}
	
	public void update() {
		addParticle();
		particleManager.update();
		staticManager.update();
		creatureManager.update();
		blockManager.update();
		player.update();
	}
	
	public void render(Graphics g) {
		particleManager.render(g);
		staticManager.render(g);
		creatureManager.render(g);
		blockManager.render(g);
		player.render(g);
	}
	
	private void addParticle() {
		for(int i = 0;i < width*height*ppfpp;i++) {
			if(i > width*height*ppfpp) {
				if(Math.random() < i-width*height*ppfpp)
					particleManager.addParticle(new ParticleGlow(handler, Utils.Color(0, 0, 0, 5), new Vector((float)(Math.random()*width), (float)(Math.random()*height)), (int)(Math.random()*3+6), 300));
			}else {
				particleManager.addParticle(new ParticleGlow(handler, Utils.Color(0, 0, 0, 5), new Vector((float)(Math.random()*width), (float)(Math.random()*height)), (int)(Math.random()*3+6), 300));
			}
		}
	}
	
	public void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] parts = file.split(":");
		String[] worldData = parts[0].split("\\s+");
		player = new Player(handler, new Vector(Utils.parseInt(worldData[0]), Utils.parseInt(worldData[1])));
		width = Utils.parseInt(worldData[2]);
		height = Utils.parseInt(worldData[3]);
		
		ppfpp = (float)Utils.parseInt(worldData[4])/(float)Utils.parseInt(worldData[5]);;//particles per frame per pixel
		//gets the border blocks
		Block leftBorder = new GrassBlock(handler, new Vector(-handler.getWidth()/2,-handler.getHeight()/2), handler.getWidth()/2, handler.getHeight()+height);
		blockManager.addBlock(leftBorder);
		Block rightBorder = new GrassBlock(handler, new Vector(width,-handler.getHeight()/2), handler.getWidth()/2, handler.getHeight()+height);
		blockManager.addBlock(rightBorder);
		Block topBorder = new GrassBlock(handler, new Vector(0,-handler.getHeight()/2), width, handler.getHeight()/2);
		blockManager.addBlock(topBorder);
		Block bottomBorder = new GrassBlock(handler, new Vector(0,height), width, handler.getHeight()/2);
		blockManager.addBlock(bottomBorder);
		//generates all blocks
		String[] blockData = parts[1].split("\\s+");
		for(int i = 1;i < blockData.length-1;i+=5) {
			Block b = null;
			System.out.println(blockData[i+0]);
			if(Utils.parseInt(blockData[i+0]) == 0)
				b = new GrassBlock(handler, new Vector(Utils.parseInt(blockData[i+1]), Utils.parseInt(blockData[i+2])), Utils.parseInt(blockData[i+3]), Utils.parseInt(blockData[i+4]));
			if(b != null)
				blockManager.addBlock(b);
		}
		//generates all entities
		String[] entityData = parts[2].split("\\s+");
		for(int i = 1;i < entityData.length-1;i+=4) {
			if(Utils.parseInt(entityData[i+0]) == 0) {//checks for static entities
				StaticEntity e = null;
				if(Utils.parseInt(entityData[i+1]) == 0)
					e = new Grass(handler, new Vector(Utils.parseInt(entityData[i+2]), Utils.parseInt(entityData[i+3])));
				if(e != null)
					staticManager.addEntity(e);
			}else if(Utils.parseInt(entityData[i+0]) == 1) {//checks for creatures
				Creature e = null;
				if(Utils.parseInt(entityData[i+1]) == 0)
					e = new Enemy(handler, new Vector(Utils.parseInt(entityData[i+2]), Utils.parseInt(entityData[i+3])));
				if(e != null)
					creatureManager.addEntity(e);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<Block> getBlocks() {
		return blockManager.getBlocks();
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blockManager.setBlocks(blocks);;
	}

	public Player getPlayer() {
		return player;
	}

	public BlockManager getBlockManager() {
		return blockManager;
	}

	public StaticManager getStaticManager() {
		return staticManager;
	}

	public ParticleManager getParticleManager() {
		return particleManager;
	}
	
}
