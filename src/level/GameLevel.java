package level;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import GUI.HpBar;
import GUI.InGameStats;
import GUI.Input;
import animations.TwoWayAnimation;
import assets.ImageAssets;
import gameSystem.*;
import object.entites.*;
import object.projectiles.*;
import tools.*;

public class GameLevel extends AbstractLevel {
	private SpawnCircle spawnCircle;
	private EventCircle eventCircle;
	private ArrayList<AbstractEntity> obj;
	private ArrayList<AbstractEntity> events;
	private ArrayList<TwoWayAnimation> explosions;

	private boolean running = true;
	private int diff;
	
	public GameLevel(GameController game) {
		super(game);
	}

	public void init(int difficulty) {
		obj = new ArrayList<>();
		events = new ArrayList<>();
		 explosions = new ArrayList<>();
		
		diff = difficulty;
		HpBar.setDraw(true);
		running = true;
		ivec4 playerBorders = new ivec4(0, 150, (int)game.getScreenDimenions().x, (int)game.getScreenDimenions().y);
		Player player = new Player(new vec2(400f, 400f), 28.0f, playerBorders, obj);
		obj.add(player);
		InGameStats.init(player, new Rectangle(0, 0, (int) game.getScreenDimenions().x, 150));
		
		spawnCircle = new SpawnCircle(game.getScreenDimenions().x / 1.3f, new vec2(game.getScreenDimenions().x /2, game.getScreenDimenions().y / 2), player, obj);
		spawnCircle.init(100, 1 * difficulty, 190 - 10 * difficulty, 1, 0.0001 * difficulty, initMobSpawnRatioMap(), initMobSpawnSizeMap());
		
		eventCircle = new EventCircle(game.getScreenDimenions().x / 2.3f, new vec2 (game.getScreenDimenions().x / 2.3f, game.getScreenDimenions().y / 2.3f), events);
		eventCircle.getCenter().add(50, 100);
		eventCircle.init(game.getScreenDimenions().x / 2.3f, 500 - 100 * difficulty, 600 - difficulty * 100);
	}
	
	private HashMap<SpawnType, Integer> initMobSpawnRatioMap() {
		HashMap<SpawnType, Integer> mobMap;
		mobMap = new HashMap<>();

		mobMap.put(SpawnType.ASTEROID, 30);
		mobMap.put(SpawnType.ENEMY_SHIP, 5);
		mobMap.put(SpawnType.MEGA_ASTEROID, 1);
		return mobMap;
	}
	private HashMap<SpawnType, Float> initMobSpawnSizeMap() {
		HashMap<SpawnType, Float> sizeMap;
		sizeMap = new HashMap<>();

		sizeMap.put(SpawnType.ASTEROID, (float)game.getScreenDimenions().x /17);
		sizeMap.put(SpawnType.ENEMY_SHIP, (float)game.getScreenDimenions().x /28);
		sizeMap.put(SpawnType.MEGA_ASTEROID, (float)game.getScreenDimenions().x /38);
		return sizeMap;
	}
	
	@Override
	public void update() {
		if (Input.keyIsClicked(KeyEvent.VK_P)) {
			if (running) { running = false;}
			else { running = true;}
		} else if (Input.keyIsClicked(KeyEvent.VK_ESCAPE)) {
			HpBar.setDraw(false);
			game.setMenu();
		} else if (Input.keyIsClicked(KeyEvent.VK_ENTER)) {
			this.init(diff);
			System.out.println(diff);
		}
		
		
		if (running) {
		spawnCircle.update();
		eventCircle.update();
		updateEvent();
		updateObjects();		
		}
	}

	private void updateEvent() {
		// event collider
		for (int i = 0; i < events.size(); i++) {
			events.get(i).update();
			if (obj.size() > 0 && obj.get(0).circleCollide(events.get(i))) {
				events.get(i).uponDeath(obj.get(0));
				
				events.remove(i);
				i--;
			} else if (events.get(i).getHp() == 0) {
				events.remove(i);
				i--;
			}
		}
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if (explosions.get(i).getCycleProgress() == 0) {
				explosions.remove(i);
				i--;
			}
		}
	}

	private void updateObjects() {
		
		for (int i = 0; i < obj.size(); i++) {
			obj.get(i).update();
			for (int j = i + 1; j < obj.size(); j++) {
				if (obj.get(i).circleCollide(obj.get(j))) {
					obj.get(i).setHp(obj.get(i).getHp() - obj.get(j).getDmg());
					obj.get(j).setHp(obj.get(j).getHp() - obj.get(i).getDmg());
					
					if (obj.get(j).getHp() <= 0) {
						obj.get(j).uponDeath(obj.get(i));
						SetExplosionEffect(obj.get(j), obj.get(i));
					}
					if (obj.get(i).getHp() <= 0) {
						obj.get(i).uponDeath(obj.get(j));
						SetExplosionEffect(obj.get(i), obj.get(j));
					}
					break;
				}
			}
		}
		for (int a = 0; a < obj.size(); a++) {
			if (!obj.get(a).circleCollide(spawnCircle)) {				
				obj.remove(a);			
				a--;		
			} else if (obj.get(a).getHp() == 0) {
				obj.remove(a);
				a--;			
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ImageAssets.bg1, 0, 0, (int)game.getScreenDimenions().x, (int)game.getScreenDimenions().y ,null);
		for (AbstractEntity e : events) {
			e.draw(g);
		}
		for (AbstractEntity e : obj) {
			e.draw(g);
		}
		for (TwoWayAnimation e : explosions) {			
			e.draw(g);	
		}
		//spawnCircle.draw(g);
		//eventCircle.draw(g);
		InGameStats.draw(g);
		
	}
	
	private void SetExplosionEffect(AbstractEntity e1, AbstractEntity e2) {
		if (e1.getClass()== Player.class || e1.getClass() == Missile.class || e1.getClass() == Blast.class) {
			return;
		} 
		
		vec2 difference = e2.getCenter().createWithSubtract(e1.getCenter());
		float length = difference.length();
		vec2 direction = new vec2(difference.createWithDivide(length));

		vec2 spawnPos = e1.getCenter().createWithAdd(direction.createWithMultiply(e1.getCollisionRadius()));
		TwoWayAnimation ex = new TwoWayAnimation(new ivec2(spawnPos), (int)(RandomEngine.randomNumber(e1.getCollisionRadius() * 2, e1.getCollisionRadius() * 4)), 30);

		ex.setCycleTime(ex.getCycleProgress() / 5 * 4);
		explosions.add(ex);
	}

}
