package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import assets.ImageAssets;
import object.entites.Player;

//visible on top as a show of progress and life and current gun
public class InGameStats {
	static Player player;
	static Rectangle rect;
	
	public static void init(Player player, Rectangle rect) {
		InGameStats.player = player;
		InGameStats.rect = rect;
	}
	
	public static void draw (Graphics g) {
		//create circle that shows CD in a piechart

		g.drawImage(ImageAssets.metal, (int)rect.x, (int)rect.y, (int)rect.width, (int)rect.height, null);
		
		g.setColor(Color.green);
		for (int i = 0; i < player.getHp(); i++) {
			g.fillRect(i * 40 + 40, 60, 40, 30);
		}
		g.setColor(Color.yellow);
		g.drawString("" + player.getScore(), (int)rect.getWidth() /2, (int)rect.getHeight()/2);
		
		g.drawString(player.getGun().toString(), rect.width -120, rect.y + 50);
		
		float startPos = rect.width - 220;
		float endPos = rect.width - 20;
		float difference = endPos - startPos;
		
		int width = (int)(difference / player.getGun().TOTAL_LIFETIME * player.getGun().getLifeTime());
		
		g.fillRect((int)(startPos + (difference - width)), 70, width, 30);

	}
	
	public static Player getPlayer() {
		return player;
	}
}
