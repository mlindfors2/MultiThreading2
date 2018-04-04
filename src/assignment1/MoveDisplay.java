package assignment1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

public class MoveDisplay extends JPanel implements Runnable {

	private GUIAssignment1 gui;
	private Graphics2D g;
	private Random rand = new Random();
	private boolean running = false;
	private int x, y;
	private Thread thread;

	public MoveDisplay(GUIAssignment1 guiAssignment1) {
		this.gui = guiAssignment1;
	}

	public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		g = (Graphics2D) gg;
//		revalidate();
		gg.drawString("hej", x, y);
//		repaint();
	}

	public void startMoving() {
		if (!running) {
			thread = new Thread(this);
			thread.start();
			running = true;
		}

	}

	public void stopMe() {
		if (thread != null)
		running = false;

	}

	public void run() {
		try {
			while (running) {
				// 200x200
				x = rand.nextInt(200);
				y = rand.nextInt(200);
				gui.updateDisplay();
				 revalidate();
				// g.drawString("Hejhopp", x, y);
				// g.drawString("hej", 100, 100);
				 repaint();
				System.out.println(x + " " + y);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception ee) {
		}
	}

}
