package assignment3;

public class Consumer implements Runnable {

	private Buffer buffer;
	private Thread t1;
	private String name;
	private int weightLimitation;
	private int volumeLimitation;
	private int maxNumberOfItems;
	private boolean running = false;

	public Consumer(Buffer buffer, String name, int weightLimitation, int volumeLimitation, int maxNumberOfItems) {
		this.buffer = buffer;
		this.name = name;
		this.weightLimitation = weightLimitation;
		this.volumeLimitation = volumeLimitation;
		this.maxNumberOfItems = maxNumberOfItems;
	}
	
	public boolean checkIfFull() {
		
		
		return false;
	}
	public void startThread() {
		if (t1 == null) {
			running = true;
			t1 = new Thread(this);
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
		}
	}

	public void run() {
		while(running) {
			
		}
	}
}
