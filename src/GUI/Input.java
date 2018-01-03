package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import tools.vec2;

public class Input implements MouseListener, MouseMotionListener, KeyListener {
	private static final int SUM_OF_KEYS = 256;
	
	public static vec2 mouseCoords = new vec2(-1, -1);
	public static boolean mousePressed = false;
	private static boolean[] keys = new boolean[SUM_OF_KEYS];
	private static boolean[] keysClicked = new boolean[SUM_OF_KEYS];
	
	public static void update() {
		for (int i = 0; i < keys.length; i++) {
			keysClicked[i] = keys[i];
		}
	}
	
	public static boolean keyIsClicked(int key) {
		return (keys[key] && !keysClicked[key]);
		}
	public static boolean keyIsDown(int key) {
		return (keys[key]);
		}
	
	@Override
	public void mouseClicked(MouseEvent e) {e.consume();
	}

	@Override
	public void mouseEntered(MouseEvent e) {e.consume();
	}

	@Override
	public void mouseExited(MouseEvent e) {e.consume();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		e.consume();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
		e.consume();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseCoords.x = e.getX() - 3;
		mouseCoords.y = e.getY() - 26;
		e.consume();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseCoords.x = e.getX() - 3;
		mouseCoords.y = e.getY() - 26;
		e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		e.consume();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();
	}

}
