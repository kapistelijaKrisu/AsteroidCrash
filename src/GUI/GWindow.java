package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import gameSystem.GameController;

public class GWindow extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private GameController game;

	//window designed for gamecontroller class
	public GWindow(GameController game) {
		this.game = game;
		initFrame();
	}
	
	public void initFrame() {
		Dimension dim = new Dimension((int)game.getScreenDimenions().x, (int)game.getScreenDimenions().y);
		setPreferredSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        
        frame = new JFrame(game.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.add(this);
        Input input = new Input();
		frame.addMouseListener(input);
        frame.addMouseMotionListener(input);
        frame.addKeyListener(input);

        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		game.draw(g);
		//g.drawImage(ImageAssets.loadImage("bg.png"), 0,0,null);	
	//	g.drawImage(ImageAssets.bg1, 0,0,null);
		//System.out.println(ImageAssets.bg1);
	}

	public void setGameController(GameController game) {
		this.game = game;
	}
}