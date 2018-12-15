package lib;

public class Vector {
	
	public float x, y;
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void normalize() {
		float mag = (float) Math.sqrt(x*x + y*y);
		x = x*1/mag;
		y = y*1/mag;
	}
	
	public void setMag(float m) {
		float mag = (float) Math.sqrt(x*x + y*y);
		x = x*m/mag;
		y = y*m/mag;
	}
	
	public void scale(float mag) {
		x *= mag;
		y *= mag;
	}
	
	public void subtract(Vector vector) {
		x -= vector.x;
		y -= vector.y;
	}
	
	public void add(Vector vector) {
		x += vector.x;
		y += vector.y;
	}
	
	public float getMag() {
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public Vector copy() {
		return new Vector(x, y);
	}
	
}
