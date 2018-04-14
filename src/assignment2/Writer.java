package assignment2;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextArea;

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

	public void startWriter(String text, boolean async) {
		if (!running) {
			this.async = async;
			this.text = text;
			t1 = new Thread(this);
			t1.start();
			running = true;
		}
	}

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

