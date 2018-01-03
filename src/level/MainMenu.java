package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.LineUnavailableException;

import GUI.*;
import animations.TwoWayAnimation;

import assets.ScoreWriter;
import assets.SoundAssets;
import gameSystem.GameController;
import gameSystem.SpawnCircle;
import object.entites.*;
import object.projectiles.*;
import tools.*;

public class MainMenu extends AbstractLevel{
	
	private SpawnCircle circle;
	private ArrayList<AbstractEntity> list = new ArrayList<>();
	private ArrayList<TwoWayAnimation> explosions = new ArrayList<>();
	
	private MENU index = MENU.MAIN;
	private MetalButton[] mainButtons;
	private MetalButton[] optionButtons;
	private MetalButton[] highScores;
	
	private int buttonWidth;
	private int buttonHeight;
	private int gameDifficulty = 2;

	private ivec2 borders;
	
	private Font scoreFont;
	private Font labelFont;

	public MainMenu(GameController game) {
		super(game);
		borders = game.getScreenDimenions();
		buttonWidth = borders.x / 4;
		buttonHeight = borders.y / 20;
		
		circle = new SpawnCircle(game.getScreenDimenions().x / 1.2f, new vec2(game.getScreenDimenions().x / 2f, game.getScreenDimenions().y / 2f), null, list);
		circle.init(200, 1, 30, 1, 0, initMobMap(), initMobSpawnSizeMap());
		HpBar.setDraw(false);
		initButtons();
		scoreFont = new Font("Impact", Font.BOLD, buttonHeight);
		labelFont = new Font("Impact", Font.BOLD, buttonHeight * 2);
	}
	
	private void initButtons() {
			
		mainButtons = new MetalButton[4];
		mainButtons[0] = new MetalButton("Play", Color.green, new ivec4(borders.x / 2 - buttonWidth /2, borders.y / 2 + buttonHeight, buttonWidth, buttonHeight));
		mainButtons[1] = new MetalButton("Options", Color.yellow, new ivec4( borders.x / 2 - buttonWidth /2, borders.y / 2 + buttonHeight * 2 + 15, buttonWidth, buttonHeight));
		mainButtons[2]	= new MetalButton("HighScore", Color.magenta, new ivec4(borders.x / 2 - buttonWidth /2, borders.y / 2 + buttonHeight * 3 + 30, buttonWidth, buttonHeight));
		mainButtons[3]	= new MetalButton("Exit", Color.red, new ivec4(borders.x / 2 - buttonWidth /2, borders.y / 2 + buttonHeight * 4 + 45, buttonWidth, buttonHeight));
		
		optionButtons = new MetalButton[8];
		optionButtons[0] = new MetalButton("Mode", Color.red, new ivec4(borders.x / 4 - buttonWidth /2, borders.y / 2 + buttonHeight, buttonWidth, buttonHeight));
		optionButtons[1] = new MetalButton("Easy", Color.green, new ivec4( borders.x / 4 - buttonWidth /2, borders.y / 2 + buttonHeight * 2 + 15, buttonWidth, buttonHeight));
		optionButtons[2] = new MetalButton("Normal", Color.yellow, new ivec4(borders.x / 4 - buttonWidth /2, borders.y / 2 + buttonHeight * 3 + 30, buttonWidth, buttonHeight));
		optionButtons[3] = new MetalButton("Hard", Color.magenta, new ivec4(borders.x / 4 - buttonWidth /2, borders.y / 2 + buttonHeight * 4 + 45, buttonWidth, buttonHeight));
		optionButtons[4] = new MetalButton("Sounds", Color.red, new ivec4(borders.x / 4 *3 - buttonWidth /2, borders.y / 2 + buttonHeight, buttonWidth, buttonHeight));
		optionButtons[5] = new MetalButton("On", Color.green, new ivec4(borders.x / 4 *3- buttonWidth /2, borders.y / 2 + buttonHeight * 2 + 15, buttonWidth, buttonHeight));
		optionButtons[6] = new MetalButton("Off", Color.red, new ivec4(borders.x / 4 *3- buttonWidth /2, borders.y / 2 + buttonHeight * 3 + 30, buttonWidth, buttonHeight));
		optionButtons[7] = new MetalButton("Back", Color.blue, new ivec4(0, borders.y - buttonHeight, buttonWidth, buttonHeight));
		
		highScores = new MetalButton[1];
		highScores[0] = new MetalButton("Back", Color.blue, new ivec4(0, borders.y - buttonHeight, buttonWidth, buttonHeight));
	}
	private HashMap<SpawnType, Integer> initMobMap() {
		HashMap<SpawnType, Integer> mobMap = new HashMap<>();
		mobMap.put(SpawnType.ASTEROID, 40);
		mobMap.put(SpawnType.ENEMY_SHIP, 3);
		mobMap.put(SpawnType.MEGA_ASTEROID, 1);
		return mobMap;		
	}
	private HashMap<SpawnType, Float> initMobSpawnSizeMap() {
		HashMap<SpawnType, Float> sizeMap;
		sizeMap = new HashMap<>();
		sizeMap.put(SpawnType.ASTEROID, 28.0f);
		sizeMap.put(SpawnType.ENEMY_SHIP, 15f);
		sizeMap.put(SpawnType.MEGA_ASTEROID, 30f);
		return sizeMap;
	}


	@Override
	public void update() {
		checkHotKeys();
		updateObjects();
		updateEvents();
		circle.update();
		switch (index) {
		case MAIN:
			updateMenu();
			break;
		case OPTIONS:
			updateOptions();
			break;
		case HIGH_SCORES:
			updateHighScoreMenu();
		}
		
	}
	
	private void checkHotKeys() {
		if (Input.keyIsClicked(KeyEvent.VK_ENTER)) {
			game.setPlay(gameDifficulty);
		
		} else if (Input.keyIsClicked(KeyEvent.VK_ESCAPE)) {
			if (index == MENU.MAIN) {
				System.exit(0);
			} else {
				index = MENU.MAIN;
			}
		} 
	}
	
	
	
	private void updateHighScoreMenu() {
		if (buttonCollision(highScores) == 0) {
			index = MENU.MAIN;
		}
	}
	
	private void updateOptions() {
		
		switch (buttonCollision(optionButtons)){
		case -1:		
			return;
		case 0:		
			return;
		case 4:		
			return;
		case 1:
			gameDifficulty = 1;
			break;
		case 2:  
			gameDifficulty = 2;
			break;
		case 3:
			gameDifficulty = 3;
			break;
		case 5:
			try {
				SoundAssets.test.open();
			} catch (LineUnavailableException e) {
				
				e.printStackTrace();
			}
				SoundAssets.test.loop(-1);		
			break;
		case 6:
			SoundAssets.test.close();
			break;
		case 7:
			index = MENU.MAIN;
			break;
		}
		index = MENU.MAIN;
	}
	
	private void updateMenu() {
		switch (buttonCollision(mainButtons)){
		case -1:		
			return;
		case 0:
			game.setPlay(gameDifficulty);
			break;
		case 1:
			index = MENU.OPTIONS;
			break;
		case 2:  
			index = MENU.HIGH_SCORES;
			break;
		case 3:
			System.exit(0);
			break;
		}
	}
	
	private int buttonCollision(MetalButton[] list) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].vec2PointCollide(Input.mouseCoords) && Input.mousePressed) {
					return i;				
			}
		}
			return -1;
	}
	
	@Override
	public void draw(Graphics g) {
		drawGame(g);
		
		g.setFont(labelFont);
		g.setColor(Color.red);
		g.drawString("SPACE", borders.x / 2 - (5 * labelFont.getSize() / 4), borders.y / 8);
		g.drawString("BLASTER", borders.x / 2 - (7 * labelFont.getSize() / 4), borders.y / 8 * 2);
		
		switch (index) {
			case MAIN: 
				drawMenu(g, mainButtons);
				break;
			case OPTIONS:
				drawMenu(g, optionButtons);
				break;
		case HIGH_SCORES:
			drawMenu(g, highScores);
			showScores(g);
			break;
		default:
			break;
		}
	}
	
	private void showScores(Graphics g) {
		g.setFont(scoreFont);
		g.setColor(Color.red);
		
		String s = ScoreWriter.readTopTen();
		String[] scores = s.split("\n");
		for (int i = 0; i < scores.length; i++) {
		g.drawString(scores[i], borders.x / 2, borders.y / 5 * 2 + i*40);
		}

	}
	
	private void drawMenu(Graphics g, MetalButton[] list) {
		for (int i = 0; i < list.length; i++) {
			list[i].draw(g);
		}
	}
	
	private void drawGame(Graphics g) {
		g.setColor(new Color(0,0,40));
		g.fillRect(0, 0, game.getScreenDimenions().x, game.getScreenDimenions().y);
		for (AbstractEntity e : list) {
			e.draw(g);
		}
		for (TwoWayAnimation e : explosions) {
			e.draw(g);
		}
	}
	
	private void updateObjects() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).update();
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).circleCollide(list.get(j))) {
					list.get(i).setHp(list.get(i).getHp() - list.get(j).getDmg());
					list.get(j).setHp(list.get(j).getHp() - list.get(i).getDmg());
					
					if (list.get(j).getHp() <= 0) {
						list.get(j).uponDeath(list.get(i));
						SetExplosionEffect(list.get(j), list.get(i));
					}
					if (list.get(i).getHp() <= 0) {
						list.get(i).uponDeath(list.get(j));
						SetExplosionEffect(list.get(i), list.get(j));
					}
					break;
				}
			}
		}
		for (int a = 0; a < list.size(); a++) {
			if (!list.get(a).circleCollide(circle)) {		
				list.remove(a);		
				
				a--;		
			} else if (list.get(a).getHp() == 0) {
				list.remove(a);
				a--;			
			}
		}
	}
	
	private void updateEvents() {
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if (explosions.get(i).getCycleProgress() == 0) {
				explosions.remove(i);
				i--;
			}
		}
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
