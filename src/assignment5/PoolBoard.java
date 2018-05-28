package assignment5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PoolBoard extends JPanel implements ActionListener {

	private JButton myButton = new JButton("Knapp");
	private ArrayList<PoolBall> ballList = new ArrayList<PoolBall>();
	private Timer timer;
	private Graphics2D g;
	private PoolController controller = new PoolController(ballList);

	public PoolBoard() {
		this.setBackground(Color.GREEN);
		this.setPreferredSize(new Dimension(1600, 800));
		this.add(myButton);
		timer = new Timer(20, this);
		timer.start();
		for (int i = 0; i < 5; i++) {
			ballList.add(new PoolBall());
		}
	}

	public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		g = (Graphics2D) gg;
		// Used for smoother animations
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHints(rh);
		drawBalls(g);
		drawEdges(g);
		drawHoles(g);
	}

	public void drawBalls(Graphics2D g) {
		for (int i = 0; i < ballList.size(); i++) {
			g.setColor(Color.RED);
			g.fillOval(ballList.get(i).getX(), ballList.get(i).getY(), ballList.get(i).getDiameter(),
					ballList.get(i).getDiameter());
		}
	}

	public void drawEdges(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1600, 50); // Top
		g.fillRect(0, 50, 50, 700); // Left
		g.fillRect(1550, 50, 50, 700); // Right
		g.fillRect(0, 750, 1600, 50); // Bottom
	}

	public void drawHoles(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillOval(10, 10, 50, 50);
		g.fillOval(800, 00, 50, 50);
		g.fillOval(1520, 10, 50, 50);
		g.fillOval(10, 740, 50, 50);
		g.fillOval(800, 740, 50, 50);
		g.fillOval(1520, 740, 50, 50);
	}
	// public void run() {

	// }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		controller.moveAll();
	}

}
