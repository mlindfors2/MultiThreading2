package assignment4;

import java.util.LinkedList;
import javax.swing.JTextPane;

/**
 * Class that handles the reader thread.
 * 
 * @author Mikael Lindfors
 *
 */
public class Reader implements Runnable {

	private JTextPane jTextPaneDest;
	private Thread t1;
	private BoundedBuffer buffer;
	private int nbrOfStrings;
	private LinkedList<String> stringList = new LinkedList<String>();

	/**
	 * Constructor that sets the buffer and JTextPane and number of STrings from the
	 * GUI.
	 * 
	 * @param buffer Buffer class
	 * @param jTextPaneDest - JTextPane from the GUI used for the destinationtab.
	 * @param nbrOfStrings - int number of Strings to read from the queue.
	 */
	public Reader(BoundedBuffer buffer, JTextPane jTextPaneDest, int nbrOfStrings) {
		this.jTextPaneDest = jTextPaneDest;
		this.buffer = buffer;
		this.nbrOfStrings = nbrOfStrings;

	}

	/**
	 * Method that starts the thread
	 */
	public void startThread() {
		if (t1 == null) {
			t1 = new Thread(this);
			t1.start();
		}
	}

	/**
	 * Method that joins the thread.
	 */
	public void stopThread() {
		if (t1 != null) {
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t1 = null;
		}
	}

	/**
	 * Runmethod started by the thread. Will read words from the buffer and add it
	 * to a LinkedList one by one.
	 */
	public void run() {
		for (int i = 0; i < nbrOfStrings; i++) {
			stringList.add(buffer.readData());
		}
		for (int i = 0; i < stringList.size(); i++) {
			jTextPaneDest.setText(jTextPaneDest.getText() + stringList.get(i));
		}
		stopThread();
	}

	/**
	 * Method that returns the LinkedList with words. Not used at the moment.
	 * 
	 * @return LinkedList<String> with words.
	 */

	public LinkedList<String> getList() {
		return stringList;
	}

}
