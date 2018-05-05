package assignment5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class StartAssignment5 {
//
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					createAndShowUI();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	
	
		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JFrame jFrame = new JFrame("PoolGUI");
					PoolGUI gui2 = new PoolGUI();
					jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					jFrame.setVisible(true);
					jFrame.add(gui2);
					jFrame.pack();
				}
			});
		}
	}









