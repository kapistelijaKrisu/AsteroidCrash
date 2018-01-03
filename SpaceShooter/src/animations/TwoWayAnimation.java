package animations;

import java.awt.Graphics;

import assets.ImageAssets;
import assets.SpriteSheet;
import tools.DrawImage;
import tools.ivec2;
import tools.ivec4;

public class TwoWayAnimation extends VisualEffect{
	private final ivec2 cropsizes;
	private final ivec2 sizes;
	private final int spriteAmount;
	private final SpriteSheet sheet;
	
	private ivec2 center;
	
	public TwoWayAnimation(int size, int cycleTime) {
		super(cycleTime);
		this.cropsizes = new ivec2(64);
		this.sizes = new ivec2(size);
		this.spriteAmount = 15;
		sheet = ImageAssets.explosion;  
		}
	public TwoWayAnimation(ivec2 center, int size, int cycleTime) {
		super(cycleTime);
		this.cropsizes = new ivec2(64);
		this.sizes = new ivec2(size);
		this.spriteAmount = 15;
		sheet = ImageAssets.explosion;
		this.center = center;
		}
	public TwoWayAnimation(SpriteSheet sheet, ivec2 center, ivec2 cropsizes, int spriteAmount, int size, int cycleTime) {
		super(cycleTime);
		this.cropsizes = cropsizes;
		this.sizes = new ivec2(size);
		this.spriteAmount = spriteAmount-1;
		this.sheet = sheet;  
		this.center = center;
		}
	
	private int calculateIndex() {
		float switchIndex = TIME / (float)spriteAmount;
		float index = 0;
		if (cycleTime >= TIME / 2) {
			index -= ((float)TIME - cycleTime * 2)/ switchIndex;
		//	System.out.println(index);
			return (int)index;
			
		} else if (cycleTime >= 0) {
			index = ((float)TIME - cycleTime * 2)/ switchIndex;
			//System.out.println(index);
			return (int)index;
		}
		return 0;
	}
	
	private ivec4 calculateSpriteUVs() {
		int sum = calculateIndex();
		ivec4 v = new ivec4 ((sum % 4) * cropsizes.x, (sum / 4) * cropsizes.y, cropsizes.x, cropsizes.y);		
	//	System.out.println(v);
		return v;
	}
	
	public void draw(Graphics g) {
		try {
		ivec4 spriteUVs = calculateSpriteUVs();
		g.drawImage(DrawImage.drawImgScaled(sheet.crop(spriteUVs.x, spriteUVs.y , spriteUVs.z, spriteUVs.w), sizes.x, sizes.y),
				center.x - (int)(sizes.x /2), center.y- (int)(sizes.y /2), null);
		} catch (Exception e) {
			System.out.println("center hasnt been initialized in constructor!\nUse draw(g, centerPos) or add center to constructor!");
		}
	}
	
	public void draw(Graphics g, ivec2 centerPos) {
		ivec4 spriteUVs = calculateSpriteUVs();
		g.drawImage(DrawImage.drawImgScaled(sheet.crop(spriteUVs.x, spriteUVs.y , spriteUVs.z, spriteUVs.w), sizes.x, sizes.y),
				centerPos.x - (int)(sizes.x /2), centerPos.y- (int)(sizes.y /2), null);
	}
	@Override
	public void update() {
		cycleTime--;	
	}
}
