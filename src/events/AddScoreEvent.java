package events;

import java.awt.Graphics;
import java.util.ArrayList;

import animations.Shine;
import assets.ImageAssets;
import object.entites.AbstractEntity;
import object.entites.Player;
import tools.RandomEngine;
import tools.vec2;

public class AddScoreEvent extends AbstractEntity {
	private int scoreAdd;
	private Shine animation;
	public AddScoreEvent(float collisionRadius, vec2 center, int scoreAdd ,ArrayList<AbstractEntity> entityList) {
		super(center, collisionRadius, entityList);
		this.scoreAdd = scoreAdd;
		hp = 400;
		speed = 0.3f;
		direction = RandomEngine.randomVectorLenghtOfOne();
		animation = new Shine(ImageAssets.blueBubble, collisionRadius, 10.0f, 40);

	}

	@Override
	public void update() {
		if (hp != 0) {
			move();
			hp--;
			}
		animation.update();
	}

	@Override
	public void uponDeath(AbstractEntity causeOfDeath) {
		if (causeOfDeath.getClass() == Player.class) {
			Player p = (Player) causeOfDeath;
			p.addScore(scoreAdd);
		}
	}

	@Override
	public void draw(Graphics g) {
		animation.draw(g, center);
	}

}
