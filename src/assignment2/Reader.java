package assignment2;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextArea;


/**
 * Class that reads Characters from the buffer and sends them as a string to the UI.
 * @author mlind
 *
 */
public class Reader implements Runnable {
	private boolean running = false;
	private Thread t1;
	private boolean async = false;
	private CharacterBuffer buffer;
	private String result = "";
	private Random rand = new Random();
	private JTextArea listR;
	private JLabel lblRec;
	private char c;
	private int stringLength;
	private JLabel lblStatus;
	private JLabel lblTrans;

	public Reader(CharacterBuffer buffer, JTextArea listR, JLabel lblRec) {
		this.listR = listR;
		this.buffer = buffer;
		this.lblRec = lblRec;
		
	}
/**
 * Method that starts the thread 
 * @param async boolean async on/off
 * @param length numbers of characters to receieve
 */
	public void startReader(boolean async, int length) {
		if (!running) {
			this.async = async;
			this.stringLength = length;
			t1 = new Thread(this);
			t1.start();
			running = true;
		}
	}

	/**
	 * Method that join the thread.
	 */
	public void stopReader() {
		if (t1 != null) {
			running = false;
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method that fetch characters from the buffer sync/async and store them in
	 * a result string and sends it to the UI.
	 */
	@Override
	public void run() {
		int i = 0;
		while (running && i<stringLength) {
			if (async) {
				c = buffer.pop();
			} else {
				c = buffer.getChar();
			}
			listR.append("Reading: " + c + "\n");
			result = result + c;
			lblRec.setText(result);
			i++;
			try {
				Thread.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stopReader();
		
	}
}
