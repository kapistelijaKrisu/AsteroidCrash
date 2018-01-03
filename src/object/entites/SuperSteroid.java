package object.entites;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import assets.ImageAssets;
import tools.DrawImage;
import tools.vec2;

public class SuperSteroid extends AbstractEntity {
	private boolean original = true;
	protected AbstractEntity target;
	protected float degree = 0;
	protected float rotationSpeed = 3;

	public SuperSteroid(vec2 center, float collisionRadius, vec2 direction, boolean original ,ArrayList<AbstractEntity> entityList) {
		super(center, collisionRadius, entityList);
		this.direction = direction;
		this.original = original;
		maxHP = 6;
		hp = maxHP;
	}
	
	@Override
	public void draw(Graphics g) {
		Image newImage = DrawImage.drawWithRotation(ImageAssets.plate, (int)degree, (int) (collisionRadius*2 * 1.1f));
		g.drawImage(newImage, (int)(center.x - collisionRadius * 1.1f), (int)(center.y - collisionRadius * 1.1f), null);
		drawHP(g);
	}
	
	@Override
	public void update() {
		if (target != null) {
		setDirection(target.getCenter());
		}
		move();
		rotate();
	}
	private void rotate() {
		degree += rotationSpeed;
		if (degree > 360) {
			degree -= 360;
		}
	}
	
	@Override
	public void uponDeath(AbstractEntity causeOfDeath) {
		//System.out.println(causeOfDeath);
		if (causeOfDeath.havePlayer) {
			try {
				Player p = (Player) entityList.get(0);
				if (original) {
				p.addScore(1000);
				} else {
					p.addScore(100);
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		if (original) {	
			original = false;
			for (int i = 0; i < 10; i++) {
			
			//new directions
			double degree = 36 * i * (Math.PI /180);			
			vec2 newDira = new vec2((float)(Math.cos(degree)), (float)(Math.sin(degree)));
	
			//get offsets for new spawn locations
			vec2 offSet = newDira.createWithMultiply(collisionRadius * 2f);

			//new spawn locations
			vec2 spawna = center.createWithAdd(offSet);

			SuperSteroid a = new SuperSteroid(new vec2(spawna), collisionRadius / 3*2, newDira, false ,entityList);
			
			setScatter(false);
			
			a.setHp(maxHP);
			a.dmg = this.dmg / 2;
			entityList.add(a);
		
			}
			
		}
	}
	private void setScatter(boolean scatter) {
		this.original = scatter;
	}
	
	public void setTarget(AbstractEntity target) {
		this.target = target;
		setDirection(target.center);
	}

}
