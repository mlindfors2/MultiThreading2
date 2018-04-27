package assignment4;

import java.util.LinkedList;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Reader implements Runnable {

	private JTextPane jTextPaneDest;
	private Thread t1;
	private BoundedBuffer buffer;
	private int nbrOfStrings;
	private LinkedList<String> stringList = new LinkedList<String>();
	

	public Reader(BoundedBuffer buffer, JTextPane jTextPaneDest, int nbrOfStrings) {
		this.jTextPaneDest = jTextPaneDest;
		this.buffer = buffer;
		this.nbrOfStrings = nbrOfStrings;
	
	}

	public void startThread() {
		if (t1 == null) {
			t1 = new Thread(this);
			t1.start();
		}
	}

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

	public void run() {
	
		for (int i=0;i<nbrOfStrings;i++) {
				stringList.add(buffer.readData());
		}
		
		for (int i = 0;i<stringList.size();i++) {
			jTextPaneDest.setText(jTextPaneDest.getText() + stringList.get(i));
		}
	}

	public String reader() {
		return null;
	}
	public LinkedList<String> getList() {
		return stringList;
	}
	
}
