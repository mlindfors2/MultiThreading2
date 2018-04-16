package assignment2;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextArea;
/**
 * Class that writes characters to a Buffer in sync/async mode.
 * @author mlind
 *
 */
public class Writer implements Runnable {
	private boolean running = false;
	private Thread t1;
	private boolean async = false;
	private String text;
	private CharacterBuffer buffer;
	private Random rand = new Random();
	private JTextArea listW;
	private JLabel lblTrans;

	public Writer(CharacterBuffer buffer, JTextArea listW, JLabel lblTrans) {
		this.buffer = buffer;
		this.listW = listW;
		this.lblTrans = lblTrans;
		this.lblTrans.setText("");
	}
/**
 * Method that starts the thread in writer and set the mode to async or sync.
 * @param text String to be send to the buffer
 * @param async Boolean async on or off.
 */
	public void startWriter(String text, boolean async) {
		if (!running) {
			this.async = async;
			this.text = text;
			t1 = new Thread(this);
			t1.start();
			running = true;
		}
	}
/**
 * Method that joins the thread and reset booleans.
 */
	public void stopWriter() {
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
 * Method that write characters from a string to the buffer, one character at a time.
 * If last character is send, the stopWriter() method will be run.
 */
	@Override
	public void run() {
		while (!text.equals("")) {
			char firstChar = text.charAt(0);
			text = text.substring(1, text.length());
			if (async) {
				buffer.push(firstChar);
			
			} else {
					
				buffer.setChar(firstChar);
			}
				listW.append("Writing: " + firstChar + "\n");
				lblTrans.setText(lblTrans.getText() + firstChar);
				System.out.println("writer run");
				try {
					Thread.sleep(rand.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		stopWriter();
		}
	}

