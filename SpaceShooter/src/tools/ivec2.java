package tools;

public class ivec2 {
	public int x;
	public int y;
	
	public ivec2() {}
	public ivec2(int x, int y) {
		this.x = x;
		this.y = y;

	}
	public ivec2(vec2 convert) {
		this.x = (int) convert.x;
		this.y = (int) convert.y;

	}
	public ivec2(int size) {
		this.x = size;
		this.y = size;
	}
	public ivec2(float size) {
		this.x = (int) size;
		this.y = (int) size;
	}
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
