package GUI;

import java.awt.Color;
import java.awt.Graphics;

import tools.ivec4;

public class HpBar {
	private static boolean toDraw;
	
	public static void setDraw(boolean draw) {
		toDraw = draw;
	}
	
public static void draw(Graphics g, int maxHp, int currentHp, ivec4 dims, int borderWidth) {
	if (toDraw) {
	try{
		int green = (int)(((float)currentHp / (float)maxHp) * 255.0f);
		int red = 255 - green;
		Color hpColor = new Color(red , green, 0);
		float hpLeft = ((float)currentHp/maxHp * dims.z);
		
		g.setColor(Color.BLACK);
		g.fillRect(dims.x, dims.y, dims.z, dims.w);
		g.setColor(hpColor);
		g.fillRect(dims.x + borderWidth, dims.y + borderWidth, (int)(hpLeft) - borderWidth*2, dims.w - borderWidth*2);
	}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
}
