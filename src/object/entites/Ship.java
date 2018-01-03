package object.entites;

import java.util.ArrayList;

import object.weapons.DefaultGun;
import object.weapons.Gun;
import tools.vec2;

public abstract class Ship extends AbstractEntity {
	public final Gun DEFAULT_GUN = new DefaultGun(this);
	protected Gun gun;
	protected int count = 0;
	
	public Ship(vec2 pos, float collisionRadius, ArrayList<AbstractEntity> bullets) {
		super(pos, collisionRadius, bullets);
		gun = DEFAULT_GUN;
	}
	protected abstract void init();

	@Override
	public void update() {
		move();	
		gun.update();
	}
	
	public void shoot(vec2 destination) {
		gun.shoot(destination);
	}
	
	public void setGun(Gun gun) {
		this.gun = gun;
	}
	
	public Gun getGun() {
		return gun;
	}
}
