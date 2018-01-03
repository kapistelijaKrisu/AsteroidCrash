package object.projectiles;

import java.awt.Graphics;
import java.util.ArrayList;

import animations.Shine;
import assets.ImageAssets;
import object.entites.AbstractEntity;
import tools.vec2;

public class Bullet extends AbstractEntity {	
	public static final float DEFAULT_BULLET_RADIUS = 3;
	private Shine animation;
	
	public Bullet(float collisionRadius, vec2 centerPos, vec2 destination,
			float speed, int dmg, ArrayList<AbstractEntity> entityList) {	
		super(centerPos, collisionRadius, entityList);
		this.speed = speed;
		this.dmg = dmg;
		setDirection(destination);
		this.animation = new Shine(ImageAssets.redBullet, this.collisionRadius, 4.0f,25);
	}
	
	@Override
	public void update() {
		move();
		animation.update();
	}
	
	@Override
	public void draw(Graphics g) {
		animation.draw(g, center);
	}

	@Override
	public void uponDeath(AbstractEntity causeOfDeath) {

	}
}
