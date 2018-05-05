package assignment5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ThreadController extends JPanel implements Runnable {

	private JButton myButton = new JButton("TEST");
	private ArrayList<PoolBall> ballList = new ArrayList<PoolBall> ();
	
	public ThreadController() {
		this.setBackground(Color.GREEN);
		this.setPreferredSize(new Dimension(1400, 700));
		this.add(myButton);
	}
	
	
	public void PaintComponent(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;
		drawBalls(g);
	}
	
	public void drawBalls(Graphics2D g) {
		
	}
	
	public void run() {

	}

}
