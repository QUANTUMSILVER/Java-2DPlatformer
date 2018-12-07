package game.display;

import game.Handler;
import game.entity.Entity;

public class Camera {
	
	private float cameraSpring = 15;
	
	private Handler handler;
	
	private float xoff, yoff;
	
	public Camera(Handler handler, int x, int y) {
		this.handler = handler;
		this.xoff = x;
		this.yoff = y;
	}
	
	public void focusPosition(float x, float y) {
	
		float setX = x-handler.getWidth()/2;
		float setY = y-handler.getHeight()/2;
		move((setX-xoff)/cameraSpring, (setY-yoff)/cameraSpring);
		
	}
	
	public void focusEntity(Entity e) {
		
		float setX = e.getX()-handler.getWidth()/2 + e.getWidth()/2;
		float setY = e.getY()-handler.getHeight()/2 + e.getHeight()/2;
		move((setX-xoff)/cameraSpring,(setY-yoff)/cameraSpring);
		
	}
	
	public void move(float x, float y) {
		xoff += x;
		yoff += y;
	}
	
	public float getXoff() {
		return xoff;
	}

	public void setXoff(float xoff) {
		this.xoff = xoff;
	}

	public float getYoff() {
		return yoff;
	}

	public void setYoff(float yoff) {
		this.yoff = yoff;
	}
	
	public void setCameraSpring(float cameraSpring) {
		this.cameraSpring = cameraSpring;
	}
	
}
