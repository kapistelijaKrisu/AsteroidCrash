package gameSystem;

import java.awt.Graphics;
import java.util.ArrayList;

import GUI.HpBar;
import GUI.Input;
import assets.ImageAssets;
import level.*;
import tools.ivec2;

public class GameController {
	//holds the game itself and info about ui
	public final ImageAssets images = new ImageAssets();
	private final String title = "Space Shooter";
	private final ivec2 screenDims = new ivec2(800, 800);;
	
	private ArrayList<AbstractLevel> levelList = new ArrayList<AbstractLevel>();
	private int currentLevel = 0;
	
	public GameController() {
		levelList.add(new MainMenu(this));
		levelList.add(new GameLevel(this));
		HpBar.setDraw(false);
	}
	
	public synchronized void update(){
	
		levelList.get(currentLevel).update();
		Input.update();
	}
	public synchronized void draw(Graphics g) {
		levelList.get(currentLevel).draw(g);
	}
	
	public ivec2 getScreenDimenions(){
		return screenDims;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setPlay(int difficulty) {
		GameLevel l = (GameLevel)levelList.get(1);
		l.init(difficulty);
		currentLevel = 1;
	}
	
	public void setMenu() {
		this.currentLevel = 0;
	}

}
