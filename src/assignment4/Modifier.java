package assignment4;

/**
 * Class that handles the modifier thread.
 * 
 * @author Mikael Lindfors
 *
 */
public class Modifier implements Runnable {

	private int nbrOfStrings;
	private BoundedBuffer buffer;
	private Thread t1;

	/**
	 * Constructor that sets the buffer and nbrOfStrings from the GUI.
	 * 
	 * @param buffer - Buffer class
	 * @param nbrOfStrings - int number of Strings to check.
	 */
	public Modifier(BoundedBuffer buffer, int nbrOfStrings) {
		this.buffer = buffer;
		this.nbrOfStrings = nbrOfStrings;
	}

	/**
	 * Method that starts the thread.
	 */
	public void startThread() {
		if (t1 == null) {
			t1 = new Thread(this);
			t1.start();
		}
	}

	/**
	 * Method that stops the joins the thread.
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
	 * Runmethod started by the thread that runs modify() in the buffer for every
	 * nbrOfStrings.
	 */
	public void run() {
		for (int i = 0; i < nbrOfStrings; i++) {
			buffer.modify();
		}
		stopThread();
	}
}
