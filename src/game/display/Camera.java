package game.display;

import game.Handler;
import game.entity.Entity;
import lib.Vector;

public class Camera {
	
	private float cameraSpring = 15;
	
	private Handler handler;
	
	private Vector off;
	
	public Camera(Handler handler, Vector pos) {
		this.handler = handler;
		this.off = pos;
	}
	
	public void focusPosition(float x, float y) {
	
		float setX = x-handler.getWidth()/2;
		float setY = y-handler.getHeight()/2;
		move((setX-off.x)/cameraSpring, (setY-off.y)/cameraSpring);
		
	}
	
	public void focusEntity(Entity e) {
		
		float setX = e.getPos().x-handler.getWidth()/2 + e.getWidth()/2;
		float setY = e.getPos().y-handler.getHeight()/2 + e.getHeight()/2;
		move((setX-off.x)/cameraSpring,(setY-off.y)/cameraSpring);
		
	}
	
	public void move(float x, float y) {
		off.x += x;
		off.y += y;
	}
	
	public float getXoff() {
		return off.x;
	}

	public void setXoff(float xoff) {
		this.off.x = xoff;
	}

	public float getYoff() {
		return off.y;
	}

	public void setYoff(float yoff) {
		this.off.y = yoff;
	}
	
	public void setCameraSpring(float cameraSpring) {
		this.cameraSpring = cameraSpring;
	}
	
}
