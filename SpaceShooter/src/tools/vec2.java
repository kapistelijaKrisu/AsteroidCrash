package tools;

public class vec2 {
	public float x, y;
	
	public vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public vec2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public vec2(vec2 vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public vec2() {
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(vec2 vec) {
		x += vec.x;
		y += vec.y;
	}
	public void subtract(vec2 vec) {
		x -= vec.x;
		y -= vec.y;
	}
	public void multiply(vec2 vec) {
		x *= vec.x;
		y *= vec.y;
	}
	public void divide(float amount) {
		if (amount == 0) {
			System.out.println("dont divide by 0");
			return;
		}
		this.x /= amount;
		this.y /= amount;
	}
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}
	public void subtract(float x, float y) {
		this.x -= x;
		this.y -= y;
	}
	public void multiply(float x, float y) {
		this.x *= x;
		this.y *= y;
	}
	public void add(float amount) {
		this.x += amount;
		this.y += amount;
	}
	public void subtract(float amount) {
		this.x -= amount;
		this.y -= amount;
	}
	public void multiply(float amount) {
		this.x *= amount;
		this.y *= amount;
	}
	
	public vec2 createWithAdd(vec2 vec) {
		return new vec2(vec.x + x, vec.y + y);
	}
	public vec2 createWithAdd(float amount) {
		return new vec2(x + amount, y + amount);
	}
	public vec2 createWithSubtract(vec2 vec) {
		return new vec2(x - vec.x, y - vec.y);
	}
	public vec2 createWithMultiply(vec2 vec) {
		return new vec2(vec.x * x, vec.y * y);
	}
	public vec2 createWithDivide(vec2 vec) {
		if (vec.x == 0 || vec.y == 0) {
			System.out.println("dont divide by 0");
			return vec;
		}
		return new vec2(x / vec.x, y / vec.y);
	}
	public vec2 createWithDivide(float amount) {
		return new vec2(x / amount, y / amount);
	}
	public vec2 createWithMultiply(float amount) {
		return new vec2(x * amount, y * amount);
	}
	
	public double getDistance(vec2 vec) {
		return getDistance(x, y, vec.x, vec.y);
	}
	public double getDistance(float x1, float y1, float x2, float y2) {
		float diffX = (x2 - x1);
		float diffY = (y2 - y1);
		return Math.sqrt((diffX * diffX) + (diffY * diffY));
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	public float length() {
		return Math.abs(x) + Math.abs(y);
	}
	
	public int calculateDegreeBetweenTwoVectors(vec2 b) {
		vec2 difference = this.createWithSubtract(b);
		double length = b.getDistance(this);
		vec2 direction = difference.createWithDivide((float)length);
		float angle = (float)Math.toDegrees(Math.atan2(direction.x, -direction.y));

		return (int) angle;
	}
	
	public vec2 getOppositeVec() {
		return new vec2 (-x, -y);
	}
	public vec2 getCrossVec() {
		return new vec2 (-y, -x);
	}
	
	public int convertToDegree() {
		return (int) Math.toDegrees(Math.atan2(x, -y));  
	}
	
}
