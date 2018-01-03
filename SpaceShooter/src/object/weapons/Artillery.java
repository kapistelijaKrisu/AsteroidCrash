package object.weapons;

import object.entites.Player;
import object.entites.Ship;
import object.projectiles.Missile;
import tools.RandomEngine;
import tools.vec2;

public class Artillery extends Gun {
	private final int BUFFER = 3;
	private int cbuffer = 3;
	private final int AMOUNT_OF_SHOTS;
	private int shotsLeft = 0;

	private float missileRadius;
	private vec2 savedDestination;

	public Artillery(Ship owner, int totalCooldown, int lifeTime, float bulletSpeed, int dmg, float missileRadius) {
		super(owner, totalCooldown, lifeTime, bulletSpeed, dmg);
		this.missileRadius = missileRadius;
		AMOUNT_OF_SHOTS = 8;
	}
	public Artillery(Ship owner) {
		super(owner, 30, 600, 5, 3);
		this.missileRadius = 5;
		AMOUNT_OF_SHOTS = 8;
	}
	public Artillery(Ship owner, int amountOfShots) {
		super(owner, 30, 600, 5, 10);
		this.missileRadius = 5;
		AMOUNT_OF_SHOTS = amountOfShots;
	}
	
	@Override
	public void update() {
		shootAuto();
		super.update();
	}

	@Override
	public void shoot(vec2 destination) {
		if (currentCooldown == 0) {
			shotsLeft = AMOUNT_OF_SHOTS - 1;
			currentCooldown = TOTAL_CD;
			cbuffer = BUFFER;
			savedDestination = new vec2(destination);
			
			owner.getEntities().add(createMissile());
		}
	}
	
	private void shootAuto() {
		if (cbuffer != 0) {
			cbuffer--;
		} else if (shotsLeft > 0) {
			cbuffer = BUFFER;
			owner.getEntities().add(createMissile());
			shotsLeft--;
		}
	}
	
	private Missile createMissile() {
		vec2 offSetDestination = savedDestination.createWithAdd(new vec2(RandomEngine.randomNumber(-100, 100), RandomEngine.randomNumber(-100, 100)));
		
		Missile m = new Missile(missileRadius, new vec2(owner.getCenter()), new vec2(offSetDestination), bulletSpeed, dmg, owner.getEntities());
		m.getCenter().add(m.getDirection().createWithMultiply(owner.getCollisionRadius()  + missileRadius + 10));
		if (owner.getClass() == Player.class) {
			m.MakePlayerProperty();
		}
		
		return m;
	}
	
	@Override
	public String toString() {
		return "pewpewpewBOOOOM: dmg: " + dmg;
	}

}
