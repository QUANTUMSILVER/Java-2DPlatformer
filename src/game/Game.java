package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import game.display.Camera;
import game.display.Display;
import game.gfx.Assets;
import game.input.KeyManager;
import game.state.GameState;
import game.state.State;

public class Game implements Runnable{
	
	private Display display;
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private int width, height;
	private String title;
	
	//camera
	private Camera camera;
	
	//input
	private KeyManager keyManager;
	
	//handler
	private Handler handler;
	
	//states
	private State gameState;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getJFrame().addKeyListener(keyManager);
		Assets.init();
		
		handler = new Handler(this);
		camera = new Camera(handler, 0, 0);
		
		gameState = new GameState(handler);
		State.setcState(gameState);
	}

	private void update() {
		State.getcState().update();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		// Draw Crap
		
		State.getcState().render(g);
		
		// End Crap
		bs.show();
		g.dispose();
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
	
	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	//////////////////////////////////////////////////////

	//////////////////////////////////////////////////////

	public void run() {
		init();
		
		int fps = 60;
		double timeperTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime)/timeperTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000) {
				System.out.println(ticks);
				ticks = 0;
				timer = 0;
				
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

