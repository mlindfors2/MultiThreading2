



package assignment1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JPanel;

public class MoveRect extends JPanel implements Runnable {

	private GUIAssignment1 gui;
	private Graphics2D g;
	private Random rand = new Random();
	private boolean running = false;
	private int x1, y1, x2,y2,x3,y3;
	private int x_direction = 1;
	private int y_direction = 1;
	private Thread thread;
	private int speed;
	private int index = 0;
	
	public MoveRect(GUIAssignment1 guiAssignment1) {
		this.gui = guiAssignment1;
		// this.g = g;
		// this.setBackground(Color.BLACK);
		// this.setVisible(true);
		x1 = 50;
		y1 = 150;
		
		x2 = 100;
		y2 = 50;
		
		x3 = 150;
		y3 = 150;
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
		g.drawLine( x1, y1, x2, y2);
		g.drawLine(x2, y2, x3, y3);
		g.drawLine(x3, y3, x1, y1);
		
		
		
		
	

		// drawText(this.g);

	}

	public void run() {

		while (running) {
			
			if ( x3>200 || x1 < 0) {
				x_direction = x_direction * -1;
//				System.out.println("Byt riktning X led " + x3);
			}
			if (y2 <=0 || y1 >=200) {
				y_direction = y_direction * -1;
//				System.out.println("Byt riktning Y led "+ y1);
			}
				
//			speed = rand.nextInt(5);
			x1 = x1 + x_direction;
			y1 = y1 + y_direction ;
			
			x2 = x2 + x_direction; 
			y2 = y2 + y_direction ;
			x3 = x3 + x_direction ;
			y3 = y3 + y_direction ;
			
			
			
			repaint();
			try {
				Thread.sleep(40);
			} catch (Exception ee) {
				ee.printStackTrace();
			}

		}
//		System.out.println("TEST!!!");

	}
}