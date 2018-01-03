package level;

import java.awt.Graphics;

import gameSystem.GameController;

public abstract class AbstractLevel {
	protected GameController game;
	
	public AbstractLevel(GameController game){
		this.game = game;
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
	
}
