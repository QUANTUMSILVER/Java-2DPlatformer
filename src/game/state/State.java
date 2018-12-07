package game.state;

import java.awt.Graphics;

import game.Handler;

public abstract class State {
	
	private static State cState = null;
	
	public static State getcState() {
		return cState;
	}
	public static void setcState(State cState) {
		State.cState = cState;
	}
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void render(Graphics g);
	
	public abstract void update();
	
}
