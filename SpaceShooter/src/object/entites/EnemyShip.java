package object.entites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import assets.ImageAssets;
import tools.DrawImage;
import tools.vec2;

public class EnemyShip extends Ship {
	private float range = 100.0f;
	private Ship target;
	
	public EnemyShip(vec2 pos, float collisionRadius, vec2 dir, ArrayList<AbstractEntity> entList) {
		super(pos, collisionRadius, entList);
	//	this.target = target;
	//	setDirection(target.center);
		setDirection(dir);
		maxHP = 5;
		hp = 5;
		speed = 2.5f;
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}	
	public void update() {
		if (target != null) {
		setDirection(target.center);
		gun.update();
		if (center.getDistance(target.center) <= range) {
			gun.shoot(target.center);
		} else {
			move();
		}
		} else {
			move();
		}
	}
	
	@Override
	public void uponDeath(AbstractEntity causeOfDeath) {
		if (causeOfDeath.havePlayer) {
			try {
			Player p = (Player) entityList.get(0);
			p.addScore(300);
			} catch (Exception e) {
				System.out.println("failed to cast player at EnemyShip 51");
			}
		}
	}
	
	public void draw(Graphics g) {
		int degree = direction.convertToDegree();
		BufferedImage newImage = (BufferedImage) DrawImage.drawWithRotation(ImageAssets.eShip, degree, (int)(collisionRadius*2+4));
		g.drawImage(newImage, (int)(center.x - collisionRadius-2), (int)(center.y - collisionRadius+0), null);
		drawHP(g);
	}
	
	public void setTarget(Ship target) {
		this.target = target;
		setDirection(target.center);
	}
	public Ship getTarget() {
		return target;
	}
}
