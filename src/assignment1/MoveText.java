package assignment1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JPanel;

public class MoveText extends JPanel implements Runnable {

	private GUIAssignment1 gui;
	private Graphics2D g;
	private Random rand = new Random();
	private boolean running = false;
	private int x, y;
	private Thread thread;

	public MoveText(GUIAssignment1 guiAssignment1) {
		this.gui = guiAssignment1;
		// this.g = g;
		// this.setBackground(Color.BLACK);
		// this.setVisible(true);
	}

	public void setGraphics(Graphics g) {
		this.g = (Graphics2D) g;
	}

	public void drawText(Graphics2D g) {

		// System.out.println("X: " + x + " "+ "Y: "+y);

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

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		System.out.println(g.getClipBounds());
		this.g = (Graphics2D) g;

		g.drawString("Test", x, y);
		x = rand.nextInt(200);
		y = rand.nextInt(200);

		// drawText(this.g);

	}

	public void run() {

		while (running) {
			repaint();
			try {
				Thread.sleep(500);
			} catch (Exception ee) {
				ee.printStackTrace();
			}

		}

	}
}