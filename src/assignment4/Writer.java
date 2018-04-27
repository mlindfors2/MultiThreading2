package assignment4;

import java.util.LinkedList;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Writer implements Runnable {

	private JTextPane jTextPaneSource;
	private Thread t1;
	private boolean running;
	private LinkedList<String> textToWrite = new LinkedList<String>();
	private BoundedBuffer buffer;
	private String[] lines;
	private String[] words;

	public Writer(BoundedBuffer buffer, JTextPane txtPaneSource) {
		this.buffer = buffer;
		this.jTextPaneSource = txtPaneSource;
		running = false;
		lines = fetchLinesFromTextPane();

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
		
		for (int i = 0; i < lines.length; i++) {
			words = fetchWordsFromStringLines(lines[i]);
			for (int a = 0; a < words.length; a++) {
				
				buffer.writeData(words[a] + " ");
				
			}
			buffer.writeData("\n");

		}
	
	}

	public String[] fetchLinesFromTextPane() {
		String line[] = jTextPaneSource.getText().split("\n");
		return line;
	}

	public String[] fetchWordsFromStringLines(String lines) {
		String[] words = null;
		words = lines.split(" "); //TAR BORT ALLA MELLANRUM =(
		return words;
	}

	public int getNumberOfWords() {
		int nbrWords = 0;
		String[] str;
		for (int i = 0; i < fetchLinesFromTextPane().length; i++) {
			str = fetchLinesFromTextPane();
			nbrWords = nbrWords + fetchWordsFromStringLines(str[i]).length;
		}
		return nbrWords;
	}
}
