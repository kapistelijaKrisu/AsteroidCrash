package object.projectiles;

import java.awt.Graphics;
import java.util.ArrayList;

import animations.TwoWayAnimation;
import object.entites.AbstractEntity;
import tools.ivec2;
import tools.vec2;

public class Blast extends AbstractEntity {
	public static final float DEFAULT_BLAST_RADIUS = 20.0f;
	int lifeTime = 35;
	private TwoWayAnimation explosion;

	public Blast(float collisionRadius, vec2 centerPos, ArrayList<AbstractEntity> entityList, int dmg) {
		super(centerPos, collisionRadius, entityList);
		speed = 0;
		this.dmg = dmg;
		explosion = new TwoWayAnimation(new ivec2(center), (int)(collisionRadius *2), lifeTime);
	}

	@Override
	public void update() {
		
		if (lifeTime != 0) {
			lifeTime--;
			explosion.update();
		} else {
			hp = 0;
		}
		if (lifeTime <34) {/////////////////////////////////////////////////////
			dmg =0;
		}
		
	}

	@Override
	public void uponDeath(AbstractEntity causeOfDeath) {
		if (lifeTime != 0) {
			hp = 1;
		} 
		
	}
		@Override
	public void draw(Graphics g) {
			explosion.draw(g);
	}
		public TwoWayAnimation getExplosion() {
			return explosion;
		}
	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
		explosion.setCycleTime(lifeTime);
	}
	
	public int getLifeTime() {
		return lifeTime;
	}

}
