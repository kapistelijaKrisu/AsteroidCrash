package object.weapons;

import object.entites.Player;
import object.entites.Ship;
import object.projectiles.Bullet;
import tools.vec2;

public class DefaultGun extends Gun {

	public DefaultGun(Ship owner, int totalCooldown, int ammo, float bulletSpeed, int dmg) {
		super(owner, totalCooldown, ammo, bulletSpeed, dmg);
	}

	public DefaultGun(Ship owner) {
		super(owner, 20, -1, 5, 1);
	}

	public void shoot(vec2 destination) {
		if (currentCooldown == 0) {
			currentCooldown = TOTAL_CD;
			owner.getEntities().add(createBullet(destination));

		}
	}

	protected Bullet createBullet(vec2 destination) {
		Bullet b = new Bullet(Bullet.DEFAULT_BULLET_RADIUS, new vec2(owner.getCenter()), destination, bulletSpeed, dmg,
				owner.getEntities());
		if (owner.getClass() == Player.class) {
			b.MakePlayerProperty();
		}
		shiftPos(b, destination);
		return b;
	}

	private void shiftPos(Bullet b, vec2 destination) {
		vec2 offSetAmount = b.getDirection().createWithMultiply(owner.getCollisionRadius() + b.getCollisionRadius() + 3);
		b.getCenter().add(offSetAmount);
	}

	@Override
	public String toString() {
		return "BasicBitch: dmg: " + dmg;
	}

}
