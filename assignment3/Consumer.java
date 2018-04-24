package assignment3;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
/**
 * Class that handles Consumers which fetch FoodItems from our queue.
 * @author Mikael Lindfors
 *
 */
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
	private JLabel lblConsumerStatus;
	private JTextArea lstStore;
	private JButton btnConsumerStart;
	private JButton btnConsumerStop;

	
	public Consumer(Buffer buffer, String name, double weightLimitation, double volumeLimitation, int maxNumberOfItems,
			JLabel lblStoreItems, JLabel lblStoreWeight, JLabel lblStoreVolume, JTextArea lstStore, JLabel lblConsumerStatus, JButton btnConsumerStart, JButton btnConsumerStop) {
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
		this.lblConsumerStatus = lblConsumerStatus;
		this.btnConsumerStart = btnConsumerStart;
		this.btnConsumerStop = btnConsumerStop;
	}

	/**
	 * Method that sets the continues load status true or false
	 * @param status boolean status 
	 */
	public void setContinuesLoad(boolean status) {
		this.continueLoad = status;
	}

	/**
	 * Method that check if program is already running, else it will start a new Thread.
	 */

	public void startThread() {
		if (!running) {
			if (t1 == null) {
				t1 = new Thread(this);
				t1.start();
			}
			running = true;
			btnConsumerStart.setEnabled(false);
			btnConsumerStop.setEnabled(true);
		}
	}

	/**
	 * Method that check if program is already running, and if so it will interrupt the work and join the thread
	 */
	public void stopThread() {
			
			if (t1 != null) {
				running = false;
				lblConsumerStatus.setText("Stop");
				try {
					t1.join();
					t1 = null;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			btnConsumerStop.setEnabled(false);
			btnConsumerStart.setEnabled(true);
		
	}
/**
 * Run-method that will fetch FoodItems from the queue and see how much room is left on the truck. If its full the truck will empty itself (for 5 sec) and 
 * start loading again.
 */
	public void run() {
		while (running) {
			FoodItem item = buffer.pop(name);
			lstStore.append(item.getName() + "\n");
			if ((currentVolumeLeft - item.getVolume() >= 0) && (currentWeightLeft - item.getWeight() >= 0)) {
				numberOfItems++;
				currentVolumeLeft = currentVolumeLeft - item.getVolume();
				currentWeightLeft = currentWeightLeft - item.getWeight();
				lblStoreItems.setText("" + numberOfItems);
				lblStoreWeight.setText("" + currentWeightLeft);
				lblStoreVolume.setText("" + currentVolumeLeft);
			} else {
				if (continueLoad) {
					try {
						lblConsumerStatus.setText("Emptying truck");
						Thread.sleep(5000);
						numberOfItems = 0;
						currentVolumeLeft = volumeLimitation;
						currentWeightLeft = weightLimitation;
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				} else {
					lblConsumerStatus.setText("Truck full");
					running = false;
					
				}
			}
		}
//		stopThread();
		
	}
}
