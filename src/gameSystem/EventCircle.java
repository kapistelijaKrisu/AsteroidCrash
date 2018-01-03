package gameSystem;

import java.awt.Graphics;
import java.util.ArrayList;

import events.ChangeWeaponEvent;
import events.GunType;
import events.HealEvent;
import events.AddScoreEvent;
import object.entites.AbstractEntity;
import tools.RandomEngine;
import tools.vec2;

//circle that hold eventspawn
public class EventCircle extends AbstractEntity{
	private int EVENT_CD;
	private int RESOURCE_CD;
	
	private int eventTimer = 0;
	private int resourceTimer = 0;
	
	private float maxOffSet;
	private final int EVENT_TYPES = 4;
	
	public EventCircle(float radius , vec2 center, ArrayList<AbstractEntity> eventList) {
		super(center, radius, eventList);
		this.entityList = eventList;

	}
	//use init before update
	public void init(float maxOffset, int eventCD, int resourceCD) {
		this.maxOffSet = maxOffset;
		EVENT_CD = eventCD;
		RESOURCE_CD = resourceCD;
		eventTimer = eventCD;
	}
	
	@Override
	public void update() {
		//spawn resource
		if (resourceTimer == 0) {
			spawnResource();
			resourceTimer = RESOURCE_CD;
		} else {
			resourceTimer--;
		}

		// spawn event
		if (eventTimer == 0) {
			eventTimer = EVENT_CD;
			spawnEvent();
		} else {
			eventTimer--;
		}
	}
	
	private void spawnEvent() {
		//change maxOffset for more accurate spawn towards center
		vec2 offSet = RandomEngine.randomVector(maxOffSet);
		vec2 position = center.createWithAdd(offSet);
		
		int ra = RandomEngine.random.nextInt(EVENT_TYPES);
		switch (ra) {
		
		case 0:
			ChangeWeaponEvent gunChange = new ChangeWeaponEvent(10, position, entityList, GunType.RANDOM, 600);
			entityList.add(gunChange);
			break;
		case 1:
			ChangeWeaponEvent gunChange2 = new ChangeWeaponEvent(10, position, entityList, GunType.RANDOM, 600);
			entityList.add(gunChange2);
			break;
			
		case 2:
			AddScoreEvent r = new AddScoreEvent(20, position, 5000, entityList);
			entityList.add(r);
			break;
		case 3:
			HealEvent h = new HealEvent(position, 10, entityList);
			entityList.add(h);
			break;
		}
	}
	
	private void spawnResource() {
		vec2 offSet = RandomEngine.randomVector(maxOffSet);
		vec2 position = center.createWithAdd(offSet);
		AddScoreEvent r = new AddScoreEvent(10, position, 500, entityList);
		entityList.add(r);
	}

	@Override
	public void uponDeath(AbstractEntity causeOfDeath) {
	//empty
	}
	
	public void draw(Graphics g) {
		g.drawOval((int)(center.x - collisionRadius), (int)(center.y - collisionRadius), (int)(collisionRadius * 2), (int)(collisionRadius * 2));
	}
}