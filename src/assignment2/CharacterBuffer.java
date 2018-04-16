package assignment2;

import java.util.concurrent.Semaphore;

import javax.swing.JTextArea;
/**
 * Class that handles a character buffer. The buffer got room for only 1 character.
 * Methods for both async and sync mode is included.
 * @author mlind
 *
 */


public class CharacterBuffer {
	private char queue;
	private boolean hasCharacter = false;
	private Semaphore semaphore = new Semaphore(2);
	private JTextArea listW;
	private JTextArea listR;

	public CharacterBuffer(JTextArea listW, JTextArea listR) {
		this.listW = listW;
		this.listR = listR;
	}

	/**
	 * Method that adds a character to the queue in async mode.
	 * @param c Character
	 */
	public void setChar(char c) {
		hasCharacter = true;
		queue = c;
	}

	/**
	 * Method that fetch a character from the queue in async mode.
	 * @return character
	 */
	public char getChar() {
		if (hasCharacter) {
			hasCharacter = false;
			return queue;
		}
		return ' ';
	}
/**
 * Method that adds a character to the queue in sync mode. If the queue is full the thread will 
 * wait until the reader has fetch the character from the queue. When the thread is done it
 * will notify all other threads waiting.
 * 
 * @param c Character
 */
	public synchronized void push(char c) {
		if (hasCharacter) {
			listW.append("Data exists. Writer waits\n");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
			try {
				semaphore.acquire();
				queue = c;
			} catch (InterruptedException e) {
				System.out.println("exception i push");
			}
			hasCharacter = true;
			semaphore.release();
			notifyAll();
		
	}
/* Method that takes a character to the queue in sync mode. If the queue is empty the thread will 
 * wait until the writer has added a new character to the queue. When the thread is done it
 * will notify all other threads waiting.
 * @return Character
 */
	public synchronized char pop() {
		if (!hasCharacter) {
			listR.append("No data. Reader waits\n");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				System.out.println("exception i pop");
				e.printStackTrace();
			}
			char c = queue;
			semaphore.release();
			notifyAll();
			hasCharacter = false;
			return c;
		
		
	}
}