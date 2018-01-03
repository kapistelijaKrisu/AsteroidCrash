package object.entites;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import assets.ImageAssets;
import tools.RandomEngine;
import tools.DrawImage;
import tools.vec2;

public class Asteroid extends AbstractEntity {
	protected int lives = 2;
	protected float degree = 0;
	protected float rotationSpeed;
	private int imgX, imgY;
	
	public Asteroid(vec2 centerPos, float collisionRadius, vec2 destination, float speed,
			ArrayList<AbstractEntity> entityList) {
		super(centerPos, collisionRadius, entityList);
		setDirection(destination);	
		this.speed = speed;
		init();
	}

	public Asteroid(vec2 center, float collisionRadius, vec2 direction, ArrayList<AbstractEntity> entityList) {
		super(center, collisionRadius, entityList);
		this.direction = direction;
		init();
	}

	private void init() {
		
		dmg = 8;
		maxHP = 8;
		hp = maxHP;
		rotationSpeed = RandomEngine.randomNumber(0.1f, 1.0f);
		int posOrNeg = RandomEngine.random.nextInt(2);
		if (posOrNeg == 0) {
			rotationSpeed *= -1;
		}
		imgX = RandomEngine.random.nextInt(2);
		imgY = RandomEngine.random.nextInt(2);
	}
	
	public void update() {
		move();
		rotate();
	}

	private void rotate() {
		degree += rotationSpeed;
		if (degree > 360) {
			degree -= 360;
		}
	}
	@Override
	public void uponDeath(AbstractEntity causeOfDeath) {
	
		if (causeOfDeath.havePlayer) {
			try {
				Player p = (Player) entityList.get(0);
				p.addScore(100 * (lives + 1));
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		if (lives > 0) {
			//new size
			float newSize = collisionRadius / 3 * 2;
			//new dest
			vec2 newDest1 = center.createWithAdd(direction.createWithMultiply(100));
			vec2 newDest2 = center.createWithAdd(direction.createWithMultiply(100));
			newDest1.add(-direction.x * 80, direction.y * 80);
			newDest2.add(direction.x * 80, -direction.y * 80);
			
			//backtrack a bit
			center.createWithSubtract(direction.createWithMultiply(collisionRadius * 2.5f));//////////////
		//	vec2 oldDir = new vec2(direction);

			//set dirs based on newdests
			Asteroid a = new Asteroid(new vec2(center), newSize, newDest1, 1.5f ,entityList);
			setDirection(newDest2);
			
			a.center.add(a.getDirection().createWithMultiply(collisionRadius *1.5f));
			center.add(direction.createWithMultiply(collisionRadius *1.5f));
			
		//	center.add(oldDir.x * collisionRadius, -oldDir.y * collisionRadius *1.2f);
		//	a.center.add(-oldDir.x * collisionRadius, oldDir.y * collisionRadius* 1.2f);
		
			lives--;
			a.setLives(lives);
			maxHP /= 2;
			a.maxHP = maxHP;
			hp = maxHP;
			a.hp = maxHP;
			dmg /= 2;
			a.dmg = this.dmg;
			a.setImgCoordsX(imgX, imgY);
			entityList.add(a);
	
			collisionRadius = newSize;
		//	direction = newDirb;
			rotationSpeed = RandomEngine.randomNumber(-2f, 2f);
		}
	}
	
	public void draw(Graphics g) {
	Image newImage = DrawImage.drawWithRotation(ImageAssets.asteroids.crop((imgX * 53), imgY * 53, 53, 53), (int)degree, (int) (collisionRadius*2 * 1.8f));
		g.drawImage(newImage, (int)(center.x - collisionRadius * 1.8f), (int)(center.y - collisionRadius * 1.8f), null);	
		drawHP(g);
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void setImgCoordsX(int imgX, int imgY) {
		this.imgX = imgX;
		this.imgY = imgY;
	}
	/*System.out.println(rotation.calculateDegreeOfEntity(this) + " " + direction);
			double degree1 =  (rotation.calculateDegreeOfEntity(this));		
			vec2 d1 = new vec2((float)(Math.cos(degree1)), (float)(Math.sin(degree1)));
			System.out.println(degree1 + " " + d1);*/
}