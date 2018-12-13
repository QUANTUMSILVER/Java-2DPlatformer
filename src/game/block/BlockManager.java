package game.block;

import java.awt.Graphics;
import java.util.ArrayList;

public class BlockManager {
	
	private ArrayList<Block>blocks;
	
	public BlockManager(){
		blocks = new ArrayList<Block>();
	}
	
	public void update() {
		for(Block b:blocks) {
			b.update();
		}
	}
	
	public void render(Graphics g) {
		for(Block b:blocks) {
			if(b.onScreen()) {
				b.render(g);
			}
		}
	}
	
	public void addBlock(Block b) {
		blocks.add(b);
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}
	
}
