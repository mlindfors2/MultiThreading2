package assignment1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;
/**
 * Class that extends JPanel and implements Runnable and paints "test" and a
 * Thread runs randomization of the coordinates.
 * 
 * @author Mikael Lindfors
 *
 */

public class MoveText extends JPanel implements Runnable {

	private GUIAssignment1 gui;
	private Graphics2D g;
	private Random rand = new Random();
	private boolean running = false;
	private int x, y;
	private Thread thread;

	/**
	 * Constructor that makes a link to the GUI.
	 * 
	 * @param guiAssignment1 Link to the GUI.
	 */
	public MoveText(GUIAssignment1 guiAssignment1) {
		this.gui = guiAssignment1;
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
		if (thread != null)
			running = false;
	}

	/**
	 * Method that overrides paintComponent in the GUI and draws "Test" in the
	 * JPanel
	 * 
	 * @param Graphics
	 *            g Graphics object from the JPanel
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = (Graphics2D) g;
		g.drawString("Test", x, y);
	}

	/**
	 * Method that the thread runs with 40ms pause. Randomizes the x and y
	 * coordinates.
	 */
	public void run() {

		while (running) {
			repaint();
			x = rand.nextInt(200);
			y = rand.nextInt(200);
			try {
				Thread.sleep(500);
			} catch (Exception ee) {
				ee.printStackTrace();
			}

		}

	}
}