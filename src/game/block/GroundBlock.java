package game.block;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;

public class GroundBlock extends Block{

	public GroundBlock(Handler handler, int x, int y, int width, int height) {
		super(handler, x, y, width, height);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) (x - handler.getCamera().getXoff()),(int) (y - handler.getCamera().getYoff()), width, height);
	}

}
