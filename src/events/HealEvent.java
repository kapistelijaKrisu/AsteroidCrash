package events;

import java.awt.Graphics;
import java.util.ArrayList;

import animations.Shine;
import assets.ImageAssets;
import object.entites.AbstractEntity;
import tools.RandomEngine;
import tools.vec2;

public class HealEvent extends AbstractEntity {
	private Shine animation;

	public HealEvent(vec2 center, float collisionRadius, ArrayList<AbstractEntity> entityList) {
		super(center, collisionRadius, entityList);
		hp = 600;
		speed = 0.3f;
		direction = RandomEngine.randomVectorLenghtOfOne();
		animation = new Shine(ImageAssets.greenBubble, collisionRadius, 10.0f, 25);

	}
	@Override
	public void update() {
		if (hp != 0) {
			hp--;
			move();
			animation.update();
			}		
	}
	@Override
	public void uponDeath(AbstractEntity causeOfDeath) {
		causeOfDeath.setHp(causeOfDeath.getHp() + 1);
	}
	
	@Override
	public void draw(Graphics g) {
		animation.draw(g, center);
	}
}
