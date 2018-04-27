package assignment4;

public class Modifier implements Runnable {

	private int nbrOfStrings; // Total numbers of strings to test
	private BoundedBuffer buffer;
	private Thread t1;

	public Modifier(BoundedBuffer buffer, int nbrOfStrings) {
		this.buffer = buffer;
		this.nbrOfStrings = nbrOfStrings;
	}
	
	
	public void modifierLoop() {
		for (int i =0;i<nbrOfStrings;i++) {
			buffer.modify();
		}
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


	@Override
	public void run() {
		
		for (int i = 0;i<nbrOfStrings;i++) {
			buffer.modify();
		}
		
	}

}
