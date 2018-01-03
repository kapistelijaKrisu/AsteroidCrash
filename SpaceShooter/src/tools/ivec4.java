package tools;

public class ivec4 {
	public int x;
	public int y;
	public int z;
	public int w;
	
	public ivec4() {}
	public ivec4(int x, int y, int z, int w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ", " + w + ")";
	}
}
