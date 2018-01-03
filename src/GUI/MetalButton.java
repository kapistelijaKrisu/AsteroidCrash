package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import assets.ImageAssets;
import tools.ivec4;
import tools.vec2;

public class MetalButton extends Rectangle {
	private static final long serialVersionUID = 1L;
	private static Rectangle MousePointRect = new Rectangle(-1, -1, 1, 1);
	
	private static Font buttonFont = new Font("Impact", Font.BOLD, 40);
	private String text;
	private Color fontColor;
	
	public MetalButton(String text, Color fontColor, ivec4 dims) {
		x = dims.x;
		y = dims.y;
		width = dims.z;
		height = dims.w;
		this.text = text;
		this.fontColor = fontColor;
	}
	public void draw(Graphics g) {
		g.setFont(buttonFont);
		g.setColor(fontColor);
		g.drawImage(ImageAssets.metal, x, y, width, height, null);
		
		
		g.drawString(text, x + width / 2 - offSet(text), y + height - height /8);
		
	}
	
	public boolean vec2PointCollide(vec2 point) {
		MousePointRect.x = (int)point.x;
		MousePointRect.y = (int)point.y;
		return this.intersects(MousePointRect);
	}
	
	private int offSet(String s) {
		int width = s.length();
		return width * buttonFont.getSize() / 4;
	}
}
