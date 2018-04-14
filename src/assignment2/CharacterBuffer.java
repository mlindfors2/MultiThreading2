package assignment2;

import java.util.concurrent.Semaphore;

import javax.swing.JTextArea;

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

	public void setChar(char c) {
		hasCharacter = true;
		queue = c;
	}

	public char getChar() {
		if (hasCharacter) {
			hasCharacter = false;
			return queue;
		}
		return ' ';
	}

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