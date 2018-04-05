package assignment1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Class that extends JPanel and implements Runnable and paints a Triangle and a
 * Thread moves the coordinates.
 * 
 * @author Mikael Lindfors
 *
 */
public class MoveTriangle extends JPanel implements Runnable {

	private GUIAssignment1 gui;
	private Graphics2D g;
	private Random rand = new Random();
	private boolean running = false;
	// Coordinates for the triangles three corners.
	private int x1, y1, x2, y2, x3, y3;
	private int x_direction = 1;
	private int y_direction = 1;
	private Thread thread;

	/**
	 * Constructor that sets the start-coordinates for the triangle
	 * 
	 * @param guiAssignment1
	 *            Link to the GUI.
	 */
	public MoveTriangle(GUIAssignment1 guiAssignment1) {
		this.gui = guiAssignment1;
		x1 = 50;
		y1 = 150;
		x2 = 100;
		y2 = 50;
		x3 = 150;
		y3 = 150;
	}

	/**
	 * Method that starts a new Thread if no thread is currently running.
	 */
	public void startMoving() {
		if (!running) {
			thread = new Thread(this);
			thread.start();
			running = true;
		}
	}

	/**
	 * Method that stops the thread from executing in the run-method, unless no
	 * thread exist.
	 */
	public void stopMe() {
		if (thread != null) {
			running = false;
		}
	}
	/**
	 * Method that overrides paintComponent in the GUI and draws the lines for the triangle.
	 * @param Graphics g Graphics object from the JPanel
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = (Graphics2D) g;
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x2, y2, x3, y3);
		g.drawLine(x3, y3, x1, y1);
	}

	/**
	 * Method that the thread runs with 40ms pause. Moves the x and y coordinates back
	 * and forth within the JPanel edges.
	 */
	public void run() {
		while (running) {
			if (x3 >= 200 || x1 <= 0) {
				x_direction = x_direction * -1;
			}
			if (y2 <= 0 || y1 >= 200) {
				y_direction = y_direction * -1;
			}
			x1 = x1 + x_direction;
			y1 = y1 + y_direction;
			x2 = x2 + x_direction;
			y2 = y2 + y_direction;
			x3 = x3 + x_direction;
			y3 = y3 + y_direction;
			repaint();
			try {
				Thread.sleep(40);
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
	}
}