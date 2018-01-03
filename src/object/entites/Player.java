package object.entites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GUI.Input;
import assets.ImageAssets;
import assets.ScoreWriter;
//import object.weapons.*;
import tools.vec2;
import tools.ivec4;
import tools.DrawImage;

public class Player extends Ship {
	private ivec4 borders;
	private int score;

	public Player(vec2 pos, float collisionRadius, ivec4 borders, ArrayList<AbstractEntity> entityList) {
		super(pos, collisionRadius, entityList);
		this.borders = borders;
		hp = 5;
		maxHP = hp;
		speed = 2;
		init();
	}
	@Override
	protected void init() {
//		gun = new LinearAoeGun(this);
		//	gun = new MultiLineGun(this, 8);
		//	gun = new TargetLockGun(this);
		//	gun = new Artillery(this, 11);
		//	gun = new DefaultGun(this, 12, -1, 16, 111);		
	}

	public void update() {
		handleInput();
		super.update();
		followBounds();
	}
	
	private void followBounds() {
		if (center.x < borders.x) {
			center.x = borders.x;
		} else if (center.x > borders.w){
			center.x = borders.w;
		}
		if (center.y < borders.y) {
			center.y = borders.y;
		} else if (center.y > borders.z){
			center.y = borders.z;
		}
	}

	private void handleInput() {
		if (Input.keyIsDown(KeyEvent.VK_W)) {
			direction.y = -1;
		} else if (Input.keyIsDown(KeyEvent.VK_S)) {
			direction.y = 1;
		} else {
			direction.y = 0;
		}
		if (Input.keyIsDown(KeyEvent.VK_D)) {
			direction.x = 1;
		} else if (Input.keyIsDown(KeyEvent.VK_A)) {
			direction.x = -1;
		} else {
			direction.x = 0;
		}
		if (direction.x != 0 && direction.y != 0) {
			direction.multiply(0.7071068f);;
		}
		
		if (Input.mousePressed) {
			gun.shoot(Input.mouseCoords);
		}
	}

	@Override
	public void uponDeath(AbstractEntity causeOfDeath) {
		if (causeOfDeath.havePlayer) {
			hp+= causeOfDeath.getDmg(); 
			if (hp > maxHP) {
				hp = maxHP;
			}
		} else {
			ScoreWriter.writeTopTen(score);
		}
	}
	
	@Override
	public void draw(Graphics g) {	
		int degree = center.calculateDegreeBetweenTwoVectors(Input.mouseCoords);
		Image newImage = DrawImage.drawWithRotation(ImageAssets.player, degree + 90, (int)(collisionRadius *2));
		g.drawImage(newImage, (int)(center.x - collisionRadius - 2), (int)(center.y - collisionRadius), null);	
		drawHP(g);
}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int amount) {
		//can be negative if want to add punishment
		score+= amount;
	}	
}


