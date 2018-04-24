package assignment4;

import java.util.LinkedList;

import javax.swing.JTextArea;

public class Writer implements Runnable {
	private JTextArea textArea;
	private Thread t1;
	private boolean running;
	private LinkedList<String> textToWrite = new LinkedList<String>();
	private BoundedBuffer buffer;

	public Writer(JTextArea textArea, BoundedBuffer buffer) {
		this.textArea = textArea;
		running = false;
		this.buffer = buffer;
	}

	public void startThread() {
		if (t1 == null) {
			t1 = new Thread(this);
			running = true;
			t1.start();
		}
	}

	public void stopThread() {
		if (t1 != null) {
			running = false;
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t1 = null;
		}
	}

	public void run() {
		while (running) {

		}
	}

	public String fetchWordFromTextArea() {
		String str;
		// str = substring(
		return null;
	}
}
