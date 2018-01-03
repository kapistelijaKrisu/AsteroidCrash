package object.weapons;


import object.entites.Player;
import object.entites.Ship;
import object.projectiles.Blast;
import tools.vec2;

public class TargetLockGun extends Gun {
	private float blastRadius = 200;

	public TargetLockGun(Ship owner, int totalCooldown, int lifeTime, float blastRadius, int dmg) {
		super(owner, totalCooldown, lifeTime, 0, dmg);
		this.blastRadius = blastRadius;
	}
	public TargetLockGun(Ship owner) {
		super(owner, 60, 600, 0, 15);
	}

	@Override
	public void shoot(vec2 destination) {
		if (currentCooldown == 0) {
			if (lifeTime == INFINITY || lifeTime > 0) {
				currentCooldown = TOTAL_CD;
					owner.getEntities().add(createBlast(destination));
			}
			if (lifeTime == INFINITY) {
				return;
			}
			lifeTime--;
		}
	}

	private Blast createBlast(vec2 destination) {

		Blast b = new Blast(blastRadius, destination, owner.getEntities(), dmg);
		b.setLifeTime(b.getLifeTime() / 3 * 2);
		
		if (owner.getClass() == Player.class) {
			b.MakePlayerProperty();
		}
			return b;
	}

	@Override
	public String toString() {
		return "NUKE in a Spot: dmg: " + dmg;
	}

}
