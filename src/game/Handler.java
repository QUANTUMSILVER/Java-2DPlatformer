package game;

import java.awt.Canvas;

import javax.swing.JFrame;

import game.display.Camera;
import game.input.KeyManager;
import game.world.World;

public class Handler {
	
	private Game game;
	private World world;

	public Handler(Game game) {
		this.game = game;
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}

	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public JFrame getJFrame() {
		return game.getDisplay().getJFrame();
	}
	
	public Canvas getCanvas() {
		return game.getDisplay().getCanvas();
	}
	
	public Camera getCamera() {
		return game.getCamera();
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
}
