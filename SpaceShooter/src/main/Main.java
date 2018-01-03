package main;

public class Main {

public static void main(String[] args) {
	
	MainGame game = new MainGame();
	Thread thread = new Thread(game);
	thread.start();
	
	}	
}
