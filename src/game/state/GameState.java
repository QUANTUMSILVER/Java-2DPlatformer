package game.state;

import java.awt.Graphics;

import game.Handler;
import game.fx.Glow;
import game.world.World;

public class GameState extends State{
	
	private World world;
	private Glow glow;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/StartingWorld.chen");
		glow = new Glow(handler, 100, 100, 10);
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		glow.render(g);
	}

	@Override
	public void update() {
		world.update();
	}
	
}
