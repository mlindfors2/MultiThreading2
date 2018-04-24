package assignment4;

import java.util.LinkedList;

import javax.swing.JTextArea;

public class Reader implements Runnable {

	private JTextArea textArea;
	private Thread t1;
	private boolean running;
	private BoundedBuffer buffer;
	private int nbrOfStrings;
	private LinkedList<String> stringList = new LinkedList<String>();
	

	public Reader(JTextArea textArea, BoundedBuffer buffer, int nbrOfStrings) {
		this.textArea = textArea;
		this.buffer = buffer;
		this.nbrOfStrings = nbrOfStrings;
		running = false;
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
		for (int i=0;i<nbrOfStrings;i++) {
			stringList.add(buffer.readData());
		}
	}

	public String reader() {
		return null;
	}
}
