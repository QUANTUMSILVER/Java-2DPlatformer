package game.block;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import lib.Vector;

public class GroundBlock extends Block{

	public GroundBlock(Handler handler, Vector pos, int width, int height) {
		super(handler, pos, width, height);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) (pos.x - handler.getCamera().getXoff()),(int) (pos.y - handler.getCamera().getYoff()), width, height);
	}

}
