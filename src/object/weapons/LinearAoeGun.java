package object.weapons;

import object.entites.Player;
import object.entites.Ship;
import object.projectiles.Blast;
import tools.vec2;

public class LinearAoeGun extends Gun {
	private final int BUFFER = 2;
	private int cbuffer = 0;
	private final int AMOUNT_OF_SHOTS = 30;
	private int shotsLeft = 0;

	private float blastRadius;
	private vec2 savedDestination;
	private vec2 savedStartPos;

	public LinearAoeGun(Ship owner, int totalCooldown, int lifeTime, float bulletSpeed, int dmg, float blastRadius) {
		super(owner, totalCooldown, lifeTime, bulletSpeed, dmg);
		this.blastRadius = blastRadius;
	}

	public LinearAoeGun(Ship owner) {
		super(owner, 60, 600, 5, 10);
		this.blastRadius = Blast.DEFAULT_BLAST_RADIUS;
	}

	@Override
	public void update() {
		shootAuto();
		super.update();
	}

	public void shoot(vec2 destination) {
		// indicates it's a new shot
		if (currentCooldown == 0) {
			shotsLeft = AMOUNT_OF_SHOTS;
			currentCooldown = TOTAL_CD;
			cbuffer = BUFFER;
			
			savedStartPos = new vec2(owner.getCenter());
			savedDestination = new vec2(destination);
			owner.getEntities().add(createBlast());
			shotsLeft--;
		}
	}

	private void shootAuto() {
		if (cbuffer != 0) {
			cbuffer--;
		} else if (shotsLeft > 0) {
			cbuffer = BUFFER;
			owner.getEntities().add(createBlast());
			shotsLeft--;
		}
	}

	private Blast createBlast() {

		vec2 spawnPos = createSpawnLocation(savedDestination);

		Blast b = new Blast(blastRadius, spawnPos, owner.getEntities(), dmg);
		if (owner.getClass() == Player.class) {
			b.MakePlayerProperty();
		}
		return b;
	}

	private vec2 createSpawnLocation(vec2 destination) {
		vec2 difference = destination.createWithSubtract(savedStartPos);
		double distance = savedStartPos.getDistance(destination);

		vec2 offSet = difference.createWithDivide((float) distance);
		
		vec2 moveFromPlayer = new vec2(offSet.createWithMultiply(owner.getCollisionRadius() *2.1f + owner.getSpeed()));
		offSet.multiply(blastRadius * (AMOUNT_OF_SHOTS - (shotsLeft)));
		offSet.add(moveFromPlayer);

		vec2 startPos = new vec2(savedStartPos);
		startPos.add(offSet);
		return startPos;
	}

	@Override
	public String toString() {
		return "LinearHoe: dmg: " + dmg;
	}

}
