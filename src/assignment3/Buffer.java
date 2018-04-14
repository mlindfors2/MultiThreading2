package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Buffer {

	private LinkedList<FoodItem> queue = new LinkedList<FoodItem>();
	private int maxCapacity;
	private Semaphore semaphore;
	private int readers;
	private int writers;

	public Buffer(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.semaphore = new Semaphore(2);
	}

	public void push(FoodItem item) {
		if (queue.size() == maxCapacity) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {

			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			queue.addLast(item);
			notify();
		}
	}

	public FoodItem pop() {
		FoodItem item = null;
		if (queue.size() > 0) {
			item = queue.removeFirst();
		} else {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		return item;

	}
}
