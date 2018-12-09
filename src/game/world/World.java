package game.world;

import java.awt.Graphics;
import java.util.ArrayList;

import game.Handler;
import game.block.Block;
import game.block.BlockManager;
import game.block.GroundBlock;
import game.entity.creature.Player;
import game.fx.particle.Particle;
import game.fx.particle.ParticleManager;
import game.utils.Utils;
import lib.Vector;

public class World {
	
	private int width, height;
	
	private BlockManager blockManager;
	private ParticleManager particleManager;
	
	private Player player;
	private Handler handler;
	
	public World(Handler handler, String path) {
		
		blockManager = new BlockManager();
		particleManager = new ParticleManager();
		
		this.handler = handler;
		handler.setWorld(this);
		loadWorld(path);
	}
	
	public void update() {
		addParticle();
		particleManager.update();
		blockManager.update();
		player.update();
	}
	
	public void render(Graphics g) {
		particleManager.render(g);
		blockManager.render(g);
		player.render(g);
	}
	
	private void addParticle() {
		particleManager.addParticle(new Particle(handler, Utils.Color(0, 0, 0, 10), new Vector((float)(Math.random()*width), (float)(Math.random()*height)), 10, 300));
		particleManager.addParticle(new Particle(handler, Utils.Color(0, 0, 0, 10), new Vector((float)(Math.random()*width), (float)(Math.random()*height)), 10, 300));
		particleManager.addParticle(new Particle(handler, Utils.Color(0, 0, 0, 10), new Vector((float)(Math.random()*width), (float)(Math.random()*height)), 10, 300));
		particleManager.addParticle(new Particle(handler, Utils.Color(0, 0, 0, 10), new Vector((float)(Math.random()*width), (float)(Math.random()*height)), 10, 300));
	}
	
	public void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] parts = file.split(":");
		String[] worldData = parts[0].split("\\s+");
		player = new Player(handler, new Vector(Utils.parseInt(worldData[0]), Utils.parseInt(worldData[1])));
		width = Utils.parseInt(worldData[2]);
		height = Utils.parseInt(worldData[3]);
		
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
