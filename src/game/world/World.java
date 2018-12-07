package game.world;

import java.awt.Graphics;
import java.util.ArrayList;

import game.Handler;
import game.block.Block;
import game.block.GroundBlock;
import game.entity.creature.Player;
import game.utils.Utils;

public class World {
	
	private int width, height;
	
	private ArrayList<Block>blocks;
	private Player player;
	private Handler handler;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		handler.setWorld(this);
		blocks = new ArrayList<Block>();
		loadWorld(path);
	}
	
	public void update() {
		for(Block b:blocks) {
			b.update();
		}
		player.update();
	}
	
	public void render(Graphics g) {
		for(Block b:blocks) {
			b.render(g);
		}
		player.render(g);
	}
	
	public void loadWorld(String path){		
		String file = Utils.loadFileAsString(path);
		String[] parts = file.split(":");
		String[] playerData = parts[0].split("\\s+");
		player = new Player(handler, Utils.parseInt(playerData[0]), Utils.parseInt(playerData[1]));
		String[] blockData = parts[1].split("\\s+");
		for(int i = 1;i < blockData.length-1;i+=4) {
			Block b = new GroundBlock(handler, Utils.parseInt(blockData[i+0]), Utils.parseInt(blockData[i+1]), Utils.parseInt(blockData[i+2]), Utils.parseInt(blockData[i+3]));
			blocks.add(b);
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
		return blocks;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}
	
}
