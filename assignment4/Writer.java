package assignment4;

import javax.swing.JTextPane;

/**
 * Class that handles the writer thread.
 * 
 * @author Mikael Lindfors
 *
 */
public class Writer implements Runnable {

	private JTextPane jTextPaneSource;
	private Thread t1;
	private BoundedBuffer buffer;
	private String[] lines;
	private String[] words;

	/**
	 * Method that sets the buffer and txtPaneSource from the GUI.
	 * 
	 * @param buffer Buffer class
	 * @param txtPaneSource jTextPane with the source text.
	 */
	public Writer(BoundedBuffer buffer, JTextPane txtPaneSource) {
		this.buffer = buffer;
		this.jTextPaneSource = txtPaneSource;
		lines = fetchLinesFromTextPane();

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
	 * Method that stops the thread.
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
	 * Runmethod started by the thread. Will read String lines and split it into
	 * words and write it to the buffer. If a line at its end a newline will be
	 * send.
	 */
	public void run() {
		for (int i = 0; i < lines.length; i++) {
			buffer.writeData(lines[i] + "\n");
			
		}
		stopThread();
	}

	/**
	 * Method that fetch String lines from the TextPane and split it into separate
	 * lines.
	 * 
	 * @return String array with every line in the document.
	 */
	public String[] fetchLinesFromTextPane() {
		String line[] = jTextPaneSource.getText().split("\n");
		return line;
	}

	/**
	 * Method that fetch separate words from a String line.
	 * 
	 * @param lines String with lines from the document.
	 * @return String array with all words from a single String line.
	 */
	public String[] fetchWordsFromStringLines(String lines) {
		String[] words = null;
		words = lines.split(" ");
		return words;
	}

	/**
	 * Method that counts number of words in the document.
	 * 
	 * @return int number of words in the document.
	 */
	public int getNumberOfWords() {
		int nbrWords = 0;
		String[] str;
		for (int i = 0; i < fetchLinesFromTextPane().length; i++) {
			str = fetchLinesFromTextPane();
			nbrWords = nbrWords + fetchWordsFromStringLines(str[i]).length;
		}
		return nbrWords;
	}
	public int getNumberOfLines() {
		return lines.length;
	}
}
