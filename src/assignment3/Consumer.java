package assignment3;

import javax.swing.JLabel;
import javax.swing.JTextArea;

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
	private JLabel lblStoreItems;
	private JLabel lblStoreWeight;
	private JLabel lblStoreVolume;
	private JTextArea lstStore;
	
	

	public Consumer(Buffer buffer, String name, double weightLimitation, double volumeLimitation, int maxNumberOfItems, JLabel lblStoreItems, JLabel lblStoreWeight, JLabel lblStoreVolume, JTextArea lstStore) {
		this.buffer = buffer;
		this.name = name;
		this.weightLimitation = weightLimitation;
		this.volumeLimitation = volumeLimitation;
		this.maxNumberOfItems = maxNumberOfItems;
		this.currentVolumeLeft = volumeLimitation;
		this.currentWeightLeft = weightLimitation;
		this.lblStoreItems = lblStoreItems;
		this.lblStoreWeight = lblStoreWeight;
		this.lblStoreVolume = lblStoreVolume;
		this.lstStore = lstStore;
	}
	public void setContinuesLoad(boolean status) {
		this.continueLoad = status;
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
			lstStore.append(item.getName() + "\n");
			if ((currentVolumeLeft - item.getVolume() >= 0) && (currentWeightLeft - item.getWeight() >= 0)) {
				numberOfItems++;
				currentVolumeLeft= currentVolumeLeft - item.getVolume();
				currentWeightLeft = currentWeightLeft - item.getWeight();
				lblStoreItems.setText(""+numberOfItems);
				lblStoreWeight.setText(""+currentWeightLeft);
				lblStoreVolume.setText("" + currentVolumeLeft);
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
						System.out.println("avbröts");
						t1.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
