package object.weapons;

import object.entites.Player;
import object.entites.Ship;
import object.projectiles.Bullet;
import tools.vec2;

public class MultiLineGun extends Gun {
	private int amountOfShots;

	public MultiLineGun(Ship owner, int totalCooldown, int lifeTime, float bulletSpeed, int dmg, int amountOfShots) {
		super(owner, totalCooldown, lifeTime, bulletSpeed, dmg);
		this.amountOfShots = amountOfShots;
	}

	public MultiLineGun(Ship owner, int amountOfShots) {
		super(owner, 20, 600, 5, 1);
		this.amountOfShots = amountOfShots;
	}

	@Override
	public void shoot(vec2 destination) {
		if (currentCooldown == 0) {
			currentCooldown = TOTAL_CD;

			for (int i = 0; i < amountOfShots; i++) {
				owner.getEntities().add(createBullet(destination, i));
			}
		}

	}

	private Bullet createBullet(vec2 destination, int index) {
		Bullet b = new Bullet(Bullet.DEFAULT_BULLET_RADIUS, new vec2(owner.getCenter()), destination, bulletSpeed, dmg, owner.getEntities());
		if (owner.getClass() == Player.class) {
			b.MakePlayerProperty();
		}
		float shift = setMultiplier(index);
		shiftSpawnPos(b, shift);

		return b;
	}

	
	private void shiftSpawnPos(Bullet b, float shift) {
		vec2 forward = b.getDirection().createWithMultiply(owner.getCollisionRadius() + 5);
		b.getCenter().add(forward);

		vec2 crossDirection = b.getDirection().getCrossVec();
		crossDirection.x *= (shift * Bullet.DEFAULT_BULLET_RADIUS * 3);
		crossDirection.y *= -(shift * Bullet.DEFAULT_BULLET_RADIUS * 3);
		
		b.getCenter().add(crossDirection);
	}
	
	private float setMultiplier(float index) {
		
		if (amountOfShots % 2 == 0) {
			if (index == 0) {
				return 0.5f;
			}
			else if (index % 2 == 0) {
				return index / 2 + 0.5f;
				
			} else {
				return index / -2;
			}
			
		} else {
			if (index == 0) {
				return 0;
			} else if (index % 2 == 0) {
				return index / 2;
			} else {
				return (index + 1) / - 2;
			}
		}
	}

	@Override
	public String toString() {
		return "RatatatataGun: dmg: " + dmg + "Lines: " + amountOfShots;
	}

}
