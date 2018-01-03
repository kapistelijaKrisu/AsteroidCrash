package object.weapons;

import object.entites.Ship;
import tools.vec2;

public abstract class Gun {
	public static final int DEFAULT_LIFETIME = 600;
	protected final int INFINITY = -1;	
	public final int TOTAL_LIFETIME;
	protected int dmg;
	protected int lifeTime;
	protected final int TOTAL_CD;
	protected int currentCooldown;
	protected float bulletSpeed;

	protected Ship owner;

	public Gun(Ship owner, int totalCooldown, int lifeTime, float bulletSpeed, int dmg) {
		this.owner = owner;
		this.dmg = dmg;
		this.lifeTime = lifeTime;
		TOTAL_LIFETIME = lifeTime;
		this.bulletSpeed = bulletSpeed;
		this.TOTAL_CD = totalCooldown;
		currentCooldown = totalCooldown;
	}

	public void update() {
		if (currentCooldown != 0) {
			currentCooldown--;
		}
		
		if (lifeTime > 0) {
			lifeTime--;
		}
		else if (lifeTime == 0) {
			owner.setGun(owner.DEFAULT_GUN);
		}
	}

	public abstract void shoot(vec2 destination);	
	
	public int getLifeTime() {
		return lifeTime;
	}
	//give string to all guns
	public abstract String toString();
}
