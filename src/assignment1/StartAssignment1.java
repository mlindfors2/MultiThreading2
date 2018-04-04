package assignment1;

import javax.swing.SwingUtilities;



public class StartAssignment1 {

	
	public static void main (String [] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUIAssignment1().Start();
				} catch (Exception e) {
					System.out.println("Program: " + e);
				}
			}
		});
	}
}
