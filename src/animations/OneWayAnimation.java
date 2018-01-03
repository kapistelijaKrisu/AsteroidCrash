package animations;
import java.awt.Graphics;

import assets.SpriteSheet;
import tools.DrawImage;
import tools.ivec2;
import tools.ivec4;
import tools.vec2;

public class OneWayAnimation extends VisualEffect{
	private final ivec2 cropsizes;
	private final ivec2 sizes;
	private final int spriteAmount;
	private final SpriteSheet sheet;
	
	public OneWayAnimation(SpriteSheet sheet, ivec2 cropsizes, int spriteAmount, int size, int cycleTime) {
		super(cycleTime);
		this.cropsizes = cropsizes;
		this.sizes = new ivec2(size);
		this.spriteAmount = spriteAmount-1;
		this.sheet = sheet;  
		}
	
	private int calculateIndex() {
		float switchIndex = TIME / (float)spriteAmount;
			float index = ((float)TIME - cycleTime)/ switchIndex;
		//	System.out.println(index);
			return (int)index;
	}
	
	private ivec4 calculateSpriteUVs() {
		int sum = calculateIndex();
		ivec4 v = new ivec4 ((sum % 4) * cropsizes.x, (sum / 4) * cropsizes.y, cropsizes.x, cropsizes.y);		
	//	System.out.println(v);
		return v;
	}
	
	public void draw(Graphics g, int centerX, int centerY) {
		ivec4 spriteUVs = calculateSpriteUVs();
		g.drawImage(DrawImage.drawImgScaled(sheet.crop(spriteUVs.x, spriteUVs.y , spriteUVs.z, spriteUVs.w), sizes.x, sizes.y),
				centerX - (int)(sizes.x /2), centerY - (int)(sizes.y /2), null);
	}
	public void draw(Graphics g, vec2 center) {
		ivec4 spriteUVs = calculateSpriteUVs();
		g.drawImage(DrawImage.drawImgScaled(sheet.crop(spriteUVs.x, spriteUVs.y , spriteUVs.z, spriteUVs.w), sizes.x, sizes.y),
				(int)(center.x - sizes.x /2), (int)(center.y - sizes.y /2), null);
	}
	public void draw(Graphics g, vec2 center, int angle) {
		ivec4 spriteUVs = calculateSpriteUVs();
		g.drawImage(DrawImage.drawWithRotation(sheet.crop(spriteUVs.x, spriteUVs.y , spriteUVs.z, spriteUVs.w), angle, sizes.x),
				(int)(center.x - sizes.x /2), (int)(center.y - sizes.y /2), null);
		g.drawRect((int)(center.x - sizes.x/2), (int)(center.y - sizes.y/2), sizes.x, sizes.y);
	}
	@Override
	public void update() {
		cycleTime--;
		if (cycleTime <= 0) {
			cycleTime = TIME;
		}
	}
	
	public ivec2 getSizes() {
		return sizes;
	}
}