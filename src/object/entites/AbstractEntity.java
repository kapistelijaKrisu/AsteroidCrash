package object.entites;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import GUI.HpBar;
import tools.ivec4;
import tools.vec2;

public abstract class AbstractEntity {
	protected float collisionRadius;
	protected vec2 center;
	protected vec2 direction = new vec2();
	protected float speed = 1.5f;
	protected int maxHP = 1;
	protected int hp = 1;
	protected int dmg = 1;
	protected ArrayList<AbstractEntity> entityList;
	protected boolean havePlayer = false;

	public AbstractEntity(vec2 center , float collisionRadius, ArrayList<AbstractEntity> entityList) {
		this.entityList = entityList;
		this.collisionRadius = collisionRadius;
		this.center = center;
	}
	
	public abstract void update();
	
	public abstract void uponDeath(AbstractEntity causeOfDeath);
	
	public void draw(Graphics g) {
		
	g.setColor(Color.white);
		g.drawOval((int)(center.x - collisionRadius), (int)(center.y - collisionRadius), (int)(collisionRadius*2), (int)(collisionRadius*2));	
	}
	
	protected void drawHP(Graphics g) {
		if (hp != maxHP) {
			HpBar.draw(g, maxHP, hp, new ivec4((int)(center.x - collisionRadius), (int)(center.y - collisionRadius *2), (int)(collisionRadius *2), (int)(collisionRadius / 3.0f)), 2);
		}
	}

	protected void move() {
		center.add(direction.x * speed, direction.y * speed);
	}
	
	public boolean circleCollide(AbstractEntity entity) {
		if (Math.abs(entity.center.x - center.x) > entity.collisionRadius + collisionRadius) {
			return false;
		} else if (Math.abs(entity.center.y - center.y) > entity.collisionRadius + collisionRadius) {
			return false;
		}
		//	(x2-x1)^2 + (y1-y2)^2 <= (r1+r2)^2 
			final float MIN_DISTANCE = collisionRadius + entity.getCollisionRadius();
			return center.getDistance(entity.getCenter()) <= MIN_DISTANCE;
		}
	
	public void setDirection(vec2 destination) {
		vec2 difference = destination.createWithSubtract(center);
		double length = destination.getDistance(center);
		direction = difference.createWithDivide((float)length);
		
	//	float angleFromVector = (float)Math.atan2(direction.x, -direction.y);
	//	System.out.println(Math.toDegrees(angleFromVector));
	}
	
	public void setDirection(float angle) {
		this.direction.x = (float)Math.cos(angle);
		this.direction.y = (float)Math.sin(angle);
		//System.out.println(direction + " absent 60 " + angle);
	}
	
	public float getCollisionRadius() {
		return collisionRadius;
	}

	public vec2 getCenter() {
		return center;
	}
	public vec2 getDirection() {
		return direction;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getDmg() {
		return dmg;
	}
	
	public float getSpeed() {
		return speed;
	}
	public ArrayList<AbstractEntity> getEntities() {
		return entityList;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public void setPosition(vec2 pos) {
		setPosition(pos.x, pos.y);
	}
	public void setPosition(float x, float y) {
		center.set(x, y);
	}
	public void setHp(int hp) {
		this.hp = hp;
		if (this.hp < 0) {
			this.hp = 0;
		} else if (this.hp > maxHP) {
			this.hp = maxHP;
		}
	}
	
	public boolean HavePlayer() {
		return havePlayer;
	}
	public void MakePlayerProperty() {
		havePlayer = true;
	}

}
