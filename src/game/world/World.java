package game.world;

import java.awt.Graphics;
import java.util.ArrayList;

import game.Handler;
import game.block.Block;
import game.block.BlockManager;
import game.block.GroundBlock;
import game.entity.creature.Player;
import game.entity.staticEntity.plant.Grass;
import game.entity.staticEntity.StaticEntity;
import game.entity.staticEntity.plant.StaticManager;
import game.fx.particle.ParticleGlow;
import game.fx.particle.ParticleManager;
import game.utils.Utils;
import lib.Vector;

public class World {
	
	private int width, height;
	private float ppfpp;//particles per frame per pixel
	
	private BlockManager blockManager;
	private StaticManager staticManager;
	private ParticleManager particleManager;
	
	private Player player;
	private Handler handler;
	
	public World(Handler handler, String path) {
		
		blockManager = new BlockManager();
		staticManager = new StaticManager();
		particleManager = new ParticleManager();
		
		this.handler = handler;
		handler.setWorld(this);
		loadWorld(path);
	}
	
	public void update() {
		addParticle();
		particleManager.update();
		staticManager.update();
		blockManager.update();
		player.update();
	}
	
	public void render(Graphics g) {
		particleManager.render(g);
		staticManager.render(g);
		blockManager.render(g);
		player.render(g);
	}
	
	private void addParticle() {
		for(int i = 0;i < width*height*ppfpp;i++) {
			if(i > width*height*ppfpp) {
				if(Math.random() < i-width*height*ppfpp) {
					particleManager.addParticle(new ParticleGlow(handler, Utils.Color(0, 0, 0, 5), new Vector((float)(Math.random()*width), (float)(Math.random()*height)), (int)(Math.random()*3+6), 300));
				}
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
		
		ppfpp = (float)Utils.parseInt(worldData[4])/(float)Utils.parseInt(worldData[5]);;
		
		Block leftBorder = new GroundBlock(handler, new Vector(-handler.getWidth()/2,-handler.getHeight()/2), handler.getWidth()/2, handler.getHeight()+height);
		blockManager.addBlock(leftBorder);
		Block rightBorder = new GroundBlock(handler, new Vector(width,-handler.getHeight()/2), handler.getWidth()/2, handler.getHeight()+height);
		blockManager.addBlock(rightBorder);
		Block topBorder = new GroundBlock(handler, new Vector(0,-handler.getHeight()/2), width, handler.getHeight()/2);
		blockManager.addBlock(topBorder);
		Block bottomBorder = new GroundBlock(handler, new Vector(0,height), width, handler.getHeight()/2);
		blockManager.addBlock(bottomBorder);
		
		String[] blockData = parts[1].split("\\s+");
		for(int i = 1;i < blockData.length-1;i+=4) {
			Block b = new GroundBlock(handler, new Vector(Utils.parseInt(blockData[i+0]), Utils.parseInt(blockData[i+1])), Utils.parseInt(blockData[i+2]), Utils.parseInt(blockData[i+3]));
			blockManager.addBlock(b);
		}
		String[] entityData = parts[2].split("\\s+");
		for(int i = 1;i < entityData.length-1;i+=2) {
			StaticEntity e = new Grass(handler, new Vector(Utils.parseInt(entityData[i+0]), Utils.parseInt(entityData[i+1])));
			staticManager.addEntity(e);
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
	
}
