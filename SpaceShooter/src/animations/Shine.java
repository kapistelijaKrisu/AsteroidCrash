package animations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import tools.DrawImage;
import tools.ivec2;
import tools.vec2;

public class Shine extends VisualEffect {
	private boolean decrease = true;
	private float maxExpand;
	private float radius;
	private BufferedImage pic;
	
	public Shine(BufferedImage pic, float radius, float maxExpand ,int cycleTime) {
		super(cycleTime);
		this.maxExpand = maxExpand;
		this.pic = pic;
		this.radius = radius;
	}
	@Override
	public void update() {
		updateShine();
	}
	private void updateShine() {
		if (cycleTime == 0) {
			decrease = false;
		} else if (cycleTime == TIME) {
			decrease = true;
		}
		if (decrease) {
			cycleTime--; 
		} else {
			cycleTime++;
		}
	}
	
	public void draw(Graphics g, ivec2 center) {
		float shine = (float)cycleTime / TIME * maxExpand;
		g.drawImage(DrawImage.drawImgScaled(pic, (int)(radius * 2 + shine), (int)(radius * 2 + shine)), (int) (center.x -  (int)(radius +shine/2)), (int) (center.y - (int)(radius + shine/2)), null);
	//  g.drawRect((int) (center.x-radius), center.y-(int)radius, (int)(radius*2), (int)(radius*2));
	//	g.drawRect(center.x-(int)radius - (int)(shine/2), center.y-(int)radius - (int)(shine/2), (int)(radius*2+shine), (int)(radius*2+shine));
	}
	public void draw(Graphics g, vec2 center) {
		float shine = (float)cycleTime / TIME * maxExpand;
		g.drawImage(DrawImage.drawImgScaled(pic, (int)(radius * 2 + shine), (int)(radius * 2 + shine)), (int) (center.x -  (int)(radius +shine/2)), (int) (center.y - (int)(radius + shine/2)), null);
	//  g.drawRect((int) (center.x-radius), center.y-(int)radius, (int)(radius*2), (int)(radius*2));
	//	g.drawRect(center.x-(int)radius - (int)(shine/2), center.y-(int)radius - (int)(shine/2), (int)(radius*2+shine), (int)(radius*2+shine));
	}
	
	public void reset() {
		decrease = true;
		cycleTime = TIME;
	}
	
	

}
