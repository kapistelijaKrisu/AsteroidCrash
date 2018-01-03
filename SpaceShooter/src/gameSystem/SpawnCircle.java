package gameSystem;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import object.entites.AbstractEntity;
import object.entites.Asteroid;
import object.entites.EnemyShip;
import object.entites.Player;
import object.entites.SpawnType;
import object.entites.SuperSteroid;
import tools.RandomEngine;
import tools.vec2;

//holds spawn of mobs 
public class SpawnCircle extends AbstractEntity{
	private int spawn_cd;
	private int maxSpawnAtOnce;
	private double spawnTimer = 0;
	private double acceleration;
	private double progressSpeed;
	private HashMap<SpawnType, Integer> mobMap;
	private HashMap<SpawnType, Float> mobRadiusMap;
	
	private float maxOffSet;
	private Player player;
	private ArrayList<AbstractEntity> entityList;
	
	public SpawnCircle(float radius , vec2 center, Player player ,ArrayList<AbstractEntity> entityList) {
		super(center, radius, entityList);
		this.player = player;
		this.entityList = entityList;
		mobMap = new HashMap<>();
		mobRadiusMap = new HashMap<>();
	}
	
	public void init(float maxOffSet, int maxSpawnsAtOnce, int spawnCD, double initialProgressSpeed, double acceleration,
			HashMap<SpawnType, Integer> mobSpawmMap, HashMap<SpawnType, Float> sizeMap) {
		this.maxOffSet = maxOffSet;
		this.maxSpawnAtOnce = maxSpawnsAtOnce;
		spawn_cd = spawnCD;
		mobMap = mobSpawmMap;
		mobRadiusMap = sizeMap;
		this.acceleration = acceleration;
		progressSpeed = initialProgressSpeed;
	}

	public void setMobMap(HashMap<SpawnType, Integer> mobMap) {
		this.mobMap = mobMap;
	}
	
	public void setMobRadiusMap(HashMap<SpawnType, Float> mobRadiusMap) {
		this.mobRadiusMap = mobRadiusMap;
	}
	
	@Override
	public void update() {
		if (spawnTimer <= 0) {
			spawnTimer = spawn_cd;
			
			int times = RandomEngine.random.nextInt((int)maxSpawnAtOnce + 1);
			if (maxSpawnAtOnce != 0 && times == 0) {times = 1;}
			for (int i = 0; i < times; i++) {
				entityList.add(spawnMob(RandomEngine.genRandomType(mobMap)));
				
			}
		} else {
			spawnTimer -= progressSpeed;
			
		}
		progressSpeed += acceleration;
		
	}	
	
	private AbstractEntity spawnMob(SpawnType type) {
		
		float angle = RandomEngine.randomNumber(0.0f, 2.0f * 3.14f);
		double x = Math.cos(angle) * collisionRadius;
		double y = Math.sin(angle) * collisionRadius;
		vec2 pos = new vec2((float)x + center.x, (float)y + center.y);

		vec2 offSet = RandomEngine.randomVector(maxOffSet);
		vec2 destination = center.createWithAdd(offSet);
		
		switch (type) {
		//to do different size of asteroids
		case ASTEROID:
			int chanceOfTargetingPlayer = RandomEngine.random.nextInt(2);

			if (chanceOfTargetingPlayer == 0 && player != null) {
				return new Asteroid(pos, mobRadiusMap.get(SpawnType.ASTEROID), player.getCenter(), 1.5f, entityList);	
			} else {
				return new Asteroid(pos, mobRadiusMap.get(SpawnType.ASTEROID),destination, 1.5f, entityList);				
			}
		case ENEMY_SHIP:
			 EnemyShip e =  new EnemyShip(pos, mobRadiusMap.get(SpawnType.ENEMY_SHIP), destination, entityList);
			if (player != null) {
		    e.setTarget(player);
			}
			return e;
		case MEGA_ASTEROID:
			SuperSteroid s =  new SuperSteroid(pos, mobRadiusMap.get(SpawnType.MEGA_ASTEROID), null, true, entityList);
			if (player != null) {
			    s.setTarget(player);
				} else {
					s.setDirection(destination);
				}
			return s;
		default:
			return new Asteroid(pos, mobRadiusMap.get(SpawnType.ASTEROID), destination, 1.5f, entityList);
			
		}
	}

	public void draw(Graphics g) {
		g.drawOval((int)(center.x - collisionRadius), (int)(center.y - collisionRadius), (int)(collisionRadius * 2), (int)(collisionRadius * 2));
	}
	
	@Override
	public void uponDeath(AbstractEntity causeOfDeath) {
		//empty
	}
}
