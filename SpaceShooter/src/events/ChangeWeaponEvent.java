package events;

import java.awt.Graphics;
import java.util.ArrayList;

import animations.Shine;
import assets.ImageAssets;
import object.entites.AbstractEntity;
import object.entites.Ship;
import object.weapons.*;
import tools.RandomEngine;
import tools.vec2;

public class ChangeWeaponEvent extends AbstractEntity {
	
	private Shine animation;
	private GunType type;
	//private int gunLifeTime = 1200;
	
	public ChangeWeaponEvent(float collisionRadius, vec2 center, ArrayList<AbstractEntity> entityList, GunType type, int gunLifeTime) {
		super(center, collisionRadius, entityList);
		this.type = type;
		//this.gunLifeTime = gunLifeTime;
		hp = 600;
		speed = 0.3f;
		direction = RandomEngine.randomVectorLenghtOfOne();
		animation = new Shine(ImageAssets.orangeBubble, collisionRadius, 10.0f, 25);
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
		//killing this event object is what launches the gun change
		try {
			Ship ship = (Ship) causeOfDeath;
			rollGun(ship);
		} catch (Exception e) {
			System.out.println("not a ship class cant have a gun");
		}	
	}
	
	private void rollGun(Ship ship) {
		switch (type) {
		case RANDOM:
			reRoll(ship);
			break;
		case MULTILINE:
			ship.setGun(new MultiLineGun(ship, RandomEngine.random.nextInt(6) + 3));
			break;
		case LINEAR:
			ship.setGun(new LinearAoeGun(ship));
			break;
		case EXPLOSIVE:
			ship.setGun(new TargetLockGun(ship));
			break;
		case ARTILLERY:
			ship.setGun(new Artillery(ship));
		
		}
		
		
	}
	
	private void reRoll(Ship ship) {
		int r = RandomEngine.random.nextInt(4);
		switch(r) {
		case 0:
			ship.setGun(new MultiLineGun(ship, RandomEngine.random.nextInt(6) + 3));
			break;
		case 1:
			ship.setGun(new Artillery(ship));
			break;
		case 2: 
			ship.setGun(new TargetLockGun(ship));
			break;
		case 3:
			ship.setGun(new LinearAoeGun(ship));
			break;
		}
	}

	@Override
	public void draw(Graphics g) {
		animation.draw(g, center);
	//	g.drawImage(ImageAssets.gun1, (int)(center.x - collisionRadius / 2 * 1.5 ), (int)(center.y + collisionRadius - collisionRadius - collisionRadius / 6 * 1.5 ), (int)(collisionRadius * 1.5), (int)(collisionRadius / 3 * 1.5), null);
		
	}
	

	
}
