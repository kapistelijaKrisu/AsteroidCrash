package object.projectiles;

import java.awt.Graphics;
import java.util.ArrayList;

import animations.Shine;
import assets.ImageAssets;
import object.entites.AbstractEntity;
import tools.vec2;

public class Missile extends AbstractEntity {
	public static final float DEFAULT_MISSILE_RADIUS = 4;
		private vec2 destination;
		private boolean isDead = false;
		private Shine animation;
		
	public Missile(float collisionRadius, vec2 centerPos, vec2 destination,
			float speed, int dmg, ArrayList<AbstractEntity> entityList) {	
		super(centerPos, collisionRadius, entityList);
		this.speed = speed;
		this.dmg = dmg;
		setDirection(destination);
		this.destination = destination;
		this.animation = new Shine(ImageAssets.blueBubble, this.collisionRadius, 4.0f,25);
	}
	@Override
	public void uponDeath(AbstractEntity causeOfDeath) {
		if (causeOfDeath == null || causeOfDeath.getClass() != Blast.class) {
			explode();
		} else if (!isDead) {
			hp++;
		}
		
		
	}
	
	private void explode() {
		if (!isDead) {
			isDead = true;
		Blast b = new Blast(Blast.DEFAULT_BLAST_RADIUS, new vec2(center), entityList, dmg);
		if (havePlayer) {
			b.MakePlayerProperty();
		}
		entityList.add(b);
		}
	}
	
	@Override
	public void update() {
		
		if (center.getDistance(destination) < speed) {
			hp = 0;
			uponDeath(null);
		}
		move();	
	}
	@Override
	public void draw(Graphics g) {
		animation.draw(g, center);
	}
}
