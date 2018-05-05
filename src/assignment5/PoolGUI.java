package assignment5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PoolGUI extends JPanel {
	private JPanel pnlTop = new JPanel();
	private JPanel pnlCenter = new JPanel();
	private JPanel pnlBottom = new JPanel();
	
	private JPanel pnlCenterLeft = new JPanel();
	private ThreadController pnlMain = new ThreadController();
	private JPanel pnlCenterRight = new JPanel();
	private JButton newButton = new JButton("TEST");
	
	public PoolGUI() {
		this.setPreferredSize(new Dimension(1600,800));
//		pnlMain.setPreferredSize(new Dimension(1400,600));
		
		this.setLayout(new BorderLayout());
		this.add(pnlTop, BorderLayout.NORTH);
		this.add(pnlCenter, BorderLayout.CENTER);
		this.add(pnlBottom, BorderLayout.SOUTH);
		
		pnlCenter.setLayout(new BorderLayout());
		pnlCenter.add(pnlCenterLeft, BorderLayout.WEST);
		pnlCenter.add(pnlMain, BorderLayout.CENTER);
		pnlCenter.add(pnlCenterRight, BorderLayout.EAST);
//		pnlMain.add(newButton);
//		pnlMain.setBackground(Color.GREEN);
		pnlTop.setBackground(Color.BLACK);
		pnlBottom.setBackground(Color.BLACK);
		pnlCenterLeft.setBackground(Color.BLACK);
		pnlCenterRight.setBackground(Color.BLACK);
		}
}
