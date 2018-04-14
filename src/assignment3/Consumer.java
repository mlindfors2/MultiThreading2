package assignment3;

public class Consumer implements Runnable {

	private Buffer buffer;
	private Thread t1;
	private String name;
	private double weightLimitation;
	private double volumeLimitation;
	private int maxNumberOfItems;
	private double currentVolumeLeft;
	private double currentWeightLeft;
	private int numberOfItems;
	private boolean running = false;
	private boolean continueLoad = false;

	public Consumer(Buffer buffer, String name, double weightLimitation, double volumeLimitation, int maxNumberOfItems) {
		this.buffer = buffer;
		this.name = name;
		this.weightLimitation = weightLimitation;
		this.volumeLimitation = volumeLimitation;
		this.maxNumberOfItems = maxNumberOfItems;
		this.currentVolumeLeft = volumeLimitation;
		this.currentWeightLeft = weightLimitation;

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
		while (running) {
			FoodItem item = buffer.pop();
			if (currentVolumeLeft - item.getVolume() >= 0 || currentWeightLeft - item.getWeight() >= 0) {
				numberOfItems++;
				currentVolumeLeft= currentVolumeLeft - item.getVolume();
				currentWeightLeft = currentWeightLeft - item.getWeight();
			} else {
				if (continueLoad) {
					try {
						Thread.sleep(5000);
						numberOfItems = 0;
						currentVolumeLeft = volumeLimitation;
						currentWeightLeft = weightLimitation;
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				} else {
					running = false;
					try {
						t1.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
