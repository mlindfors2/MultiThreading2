package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

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
	
	public Buffer(int maxCapacity, JProgressBar bufferStatus, JLabel lblStatusA, JLabel lblStatusS, JLabel lblStatusX) {
		this.maxCapacity = maxCapacity;
		this.bufferStatus = bufferStatus;
		this.readSemaphore = new Semaphore(0);
		this.writeSemaphore = new Semaphore(maxCapacity);
		this.mutex = new Semaphore(1);
		this.bufferStatus.setMaximum(maxCapacity);
		this.lblStatusA = lblStatusA;
		this.lblStatusS = lblStatusS;
		this.lblStatusX = lblStatusX;
	}

	public void push(FoodItem item) {
		try {
			if ( item.getProducerName() == "Arla") {
				lblStatusA.setText("idle");
			}
			if ( item.getProducerName() == "HKscan") {
				lblStatusS.setText("idle");
			}
			if ( item.getProducerName() == "Axfood") {
				lblStatusX.setText("idle");
			}
			writeSemaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			mutex.acquire();
			if ( item.getProducerName() == "Arla") {
				lblStatusA.setText("Producing");
			}
			if ( item.getProducerName() == "HKscan") {
				lblStatusS.setText("Producing");
			}
			if ( item.getProducerName() == "Axfood") {
				lblStatusX.setText("Producing");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		queue.addLast(item);
		bufferStatus.setValue(queue.size());
		mutex.release();
		readSemaphore.release();
	}

	public FoodItem pop() {
		FoodItem item = null;
		try {
			
			
			readSemaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			mutex.acquire();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		item = queue.removeFirst();
		mutex.release();
		writeSemaphore.release();
		return item;

	}
}
