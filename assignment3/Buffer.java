package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
/**
 * Buffer class that receives FoodItems from different Producers and send them along to different Consumers
 * @author Mikael Lindfors
 *
 */
public class Buffer {

	private LinkedList<FoodItem> queue = new LinkedList<FoodItem>();
	private int maxCapacity;
	private Semaphore readSemaphore;
	private Semaphore writeSemaphore;
	private Semaphore mutex;
	private int readers;
	private int writers;
	private JProgressBar bufferStatus;
	private JLabel lblStatusA;
	private JLabel lblStatusS;
	private JLabel lblStatusX;
	private JLabel lblIcaStatus;
	private JLabel lblCoopStatus;
	private JLabel lblCGStatus;
	private JLabel lblMax;
	
	public Buffer(int maxCapacity, JProgressBar bufferStatus, JLabel lblStatusA, JLabel lblStatusS, JLabel lblStatusX, JLabel lblIcaStatus, JLabel lblCoopStatus, JLabel lblCGStatus, JLabel lblmax) {
		this.maxCapacity = maxCapacity;
		this.bufferStatus = bufferStatus;
		this.readSemaphore = new Semaphore(0);
		this.writeSemaphore = new Semaphore(maxCapacity);
		this.mutex = new Semaphore(1);
		this.bufferStatus.setMaximum(maxCapacity);
		this.lblStatusA = lblStatusA;
		this.lblStatusS = lblStatusS;
		this.lblStatusX = lblStatusX;
		this.lblIcaStatus = lblIcaStatus;
		this.lblCoopStatus = lblCoopStatus;
		this.lblCGStatus = lblCGStatus;
		this.lblMax = lblmax;
	}

	/**
	 * Method used for updating Producer-status in the GUI.
	 * @param item FoodItem, used for identify which producers status is going to updated.
	 * @param status String status text
	 */
	public void updateProducerStatus(FoodItem item, String status) {
		if ( item.getProducerName() == "Arla") {
			lblStatusA.setText(status);			
		}
		if ( item.getProducerName() == "HKscan") {
			lblStatusS.setText(status);
		}
		if ( item.getProducerName() == "Axfood") {
			lblStatusX.setText(status);
		}
	}
	/**
	 * Method used for updating Consumer-status in the GUI.
	 * @param consumer String used for identify which consumers status is going to be updated.
	 * @param status String status text.
	 */
	public void updateConsumerStatus(String consumer, String status) {
		if ( consumer.equals("Ica")) {
			lblIcaStatus.setText(status);			
		}
		if ( consumer.equals("Coop")) {
			lblCoopStatus.setText(status);
		}
		if ( consumer.equals("CityGross")) {
			lblCGStatus.setText(status);
		}
	}
	/** 
	 * Push method that adds an FoodItem to the queue. WriteSemaphore is queue's maxcapacity while only 1 producer might enter the critical section (mutex).
	 * @param item FoodItem to be added to queue
	 */
	public void push(FoodItem item) {
		
		try {
			updateProducerStatus(item, "idle");
			writeSemaphore.acquire();
			updateProducerStatus(item, "Producing");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			updateProducerStatus(item, "idle");
			mutex.acquire();
			updateProducerStatus(item, "Producing");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		queue.addLast(item);
		bufferStatus.setValue(queue.size());
		lblMax.setText("Max capacity: " + queue.size() + "/" + maxCapacity);
		mutex.release();
		readSemaphore.release();
	}

	/**
	 * Pop method that removed an FoodItem from the queue. ReadSemaphore starts at 0 (No access, until an FoodItem has been pushed to the queue).
	 * @param consumer String used for identifying which Consumer is using the method.
	 * @return FoodItem
	 */
	public FoodItem pop(String consumer) {
		FoodItem item = null;
		try {			
			readSemaphore.acquire();
			updateConsumerStatus(consumer,"Loading");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			mutex.acquire();
			updateConsumerStatus(consumer,"Loading");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		item = queue.removeFirst();
		bufferStatus.setValue(queue.size());
		lblMax.setText("Max capacity: " + queue.size() + "/" + maxCapacity);
		mutex.release();
		writeSemaphore.release();
		return item;

	}
}
