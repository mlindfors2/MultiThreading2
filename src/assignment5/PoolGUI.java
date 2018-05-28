package assignment5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PoolGUI extends JPanel implements KeyListener {
	private JPanel pnlTop = new JPanel();
	private JPanel pnlCenter = new JPanel();
	private JPanel pnlBottom = new JPanel();
	
	private JPanel pnlCenterLeft = new JPanel();
	private PoolBoard pnlMain = new PoolBoard();
	private JPanel pnlCenterRight = new JPanel();
	private JButton newButton = new JButton("TEST");
	
	
	public PoolGUI() {
		
		
		
//		pnlFind.setBounds(12, 32, 436, 122);
//		pnlFind.setLayout(null);
		
		this.setPreferredSize(new Dimension(1600,800));
//		pnlMain.setPreferredSize(new Dimension(1400,600));
		
//		pnlMain.setBounds(0, 0, 1600, 800);
//		pnlTop.setbound(0,0,1600)
//		pnlCenter.setPreferredSize(new Dimension(50,700));
//		this.setLayout(new BorderLayout());
//		this.add(pnlTop, BorderLayout.NORTH);
//		this.add(pnlCenter, BorderLayout.CENTER);
//		this.add(pnlBottom, BorderLayout.SOUTH);
//		pnlTop.setLayout(new FlowLayout());
//		pnlCenter.setLayout(new FlowLayout());
//		pnlCenter.add(pnlCenterLeft, BorderLayout.WEST);
		this.add(pnlMain, BorderLayout.CENTER);
//		pnlCenter.add(pnlCenterRight, BorderLayout.EAST);
//		pnlMain.add(newButton);
//		pnlMain.setBackground(Color.GREEN);
//		pnlCenterLeft.setPreferredSize(new Dimension(50,700));
//		pnlCenterRight.setPreferredSize(new Dimension(50,700));
//		pnlTop.setPreferredSize(new Dimension(1600,50));
//		pnlTop.setBackground(Color.BLACK);
		
//		pnlBottom.setBackground(Color.BLACK);
//		pnlCenterLeft.setBackground(Color.BLACK);
//		pnlCenterRight.setBackground(Color.BLACK);
	
	}
	@Override
	public void setFocusable(boolean b) {
		super.setFocusable(b);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
			pnlMain.moveStick(1, 0);
		}
		if (key.getKeyCode() == KeyEvent.VK_LEFT) {
			pnlMain.moveStick(-1, 0);
		}
		if (key.getKeyCode() == KeyEvent.VK_UP) {
			pnlMain.moveStick(0, -1);
		}
		if (key.getKeyCode() == KeyEvent.VK_DOWN) {
			pnlMain.moveStick(0, 1);
		}
		if (key.getKeyCode() == KeyEvent.VK_ENTER) {
			// if (enterKeyActivated) {
			int[] coords = pnlMain.shoot();
		
		}
	}
}
