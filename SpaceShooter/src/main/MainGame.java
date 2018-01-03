package main;
import GUI.GWindow;
import gameSystem.GameController;

public class MainGame implements Runnable {

	private GameController controller;
	private GWindow window;
	
	public MainGame() {
		controller = new GameController();
		window = new GWindow(controller);
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
   
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
            	
                update();/////////////////////
                draw();////////////////////////////////
                delta--;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
           //     System.out.println("FPS: " + frames + " TICKS: " + updates);       
            }
        }
	}
	
	public void update(){
		controller.update();
	}
	public void draw(){
		window.repaint();
	}
	
}
